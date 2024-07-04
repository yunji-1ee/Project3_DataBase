import javax.swing.*;
import java.sql.*;

public class DBProject {

    public static Connection getConnection() { // 부를 때마다 데이터베이스가 오픈됨
        Connection con = null;

        String server = "localhost"; //내 컴퓨터에서 돌아가는 디비를 사용해줄거다라는 의미
        String db = "summerProjectDB"; //내 데이터베이스 이름
        String user = "root";
        String password = "dbswl1219!";

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + db + "?useSSL=false", user, password);
            System.out.println("Connected");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return con;
    }

    // 아이디 중복 체크를 위한 메소드
    public boolean isIdExist(String id) {
        String sql = "SELECT * FROM user WHERE student_id = ?";
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exists = false;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return exists;
    }

    // 회원가입을 위한 메소드
    public boolean Creation(String name, String id, String password, String birthDay, String gender) {
        if (isIdExist(id)) {
            JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.");
            return false;
        }

        String sql = "INSERT INTO user(name,student_id,password,birthday,gender) VALUES(?,?,?,?,?)";
        Connection conn = getConnection();
        PreparedStatement ps = null;
        int count = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, id);
            ps.setString(3, password);
            ps.setString(4, birthDay);
            ps.setString(5, gender);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null) conn.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return count > 0;
    }

    // 로그인을 위한 메소드
    public boolean validateLogin(String id, String password) {
        String sql = "SELECT * FROM user WHERE student_id = ? AND password = ?";
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean isValid = false;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                isValid = true;
                Session.getInstance().setUserInfo(id, rs.getString("name"), password, rs.getString("gender"), rs.getString("birthday"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return isValid;
    }

    // 탈퇴 위한 메소드
    public static boolean Delete_info(String id) {
        String sql = "DELETE FROM user WHERE student_id = ?";
        Connection conn = getConnection();
        PreparedStatement ps = null;
        int count = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return count > 0;
    }

    // 로그인시 아이디를 통해 다른 정보저장을 위함
    public boolean fetchUserInfo(String id) {
        String sql = "SELECT * FROM user WHERE student_id = ?";
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean isValid = false;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                isValid = true;
                // 세션에 사용자 정보 저장
                Session.getInstance().setUserInfo(id, rs.getString("name"), rs.getString("password"), rs.getString("gender"), rs.getString("birthday"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return isValid;
    }

    // 정보 수정할 때
    public boolean updateUser(String name, String id, String password, String birthDate, String gender) {
        String sql = "UPDATE user SET name = ?, password = ?, birthday = ?, gender = ? WHERE student_id = ?";
        Connection conn = getConnection();
        PreparedStatement ps = null;
        int count = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, birthDate);
            ps.setString(4, gender);
            ps.setString(5, id);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return count > 0;
    }
}
