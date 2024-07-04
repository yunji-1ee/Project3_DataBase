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
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return con;
    }


    public static void main(String[] args) throws SQLException {
        Connection con = null;
        Statement statement = null;
        statement = con.createStatement(); //커리문으로 명령어 넘김

        ResultSet resultSet = null; //결과가 나올 객체 -클래스
        resultSet = statement.executeQuery("select * from user"); //데이터베이스에 추가할 것


        resultSet.next(); //

        String name = null;
        name = resultSet.getString("name"); //내가 지정해준 컬럼 이름

        System.out.println(name);

        resultSet.close();
        statement.close();
        con.close();

    }

//회원가입을 위한 메소드---------------------------------------------------------------------------------------------------
    public boolean Creation (String name, String id, String password, String birthDay, String gender){
        String sql = "INSERT INTO user(name,student_id,password,birthday,gender) VALUES(?,?,?,?,?)";

        Connection conn = getConnection();
        PreparedStatement ps = null;

        int count = 0;

        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, id);
            ps.setString(3, password);
            ps.setString(4, birthDay);
            ps.setString(5, gender);
            count = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }   finally {
            //DB 닫아주기
            //접속됐음
            try{
                if ( conn != null){
                    conn.close();
                }
                if ( ps != null){
                    ps.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return count > 0 ? true : false;
    }

    //로그인을 위한 메소드-------------------------------------------------------------------------------------------
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


    //탈퇴 위한 메소드---------------------------------------------------------------------------------------------------
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
}
