import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Table extends JFrame {

    Table() {
        super("데이타테이블"); // 타이틀
        JPanel panel = new JPanel();
        panel.setLayout(null); // 레이아웃 매니저를 null로 설정하여 절대 위치 지정
        panel.setBackground(Color.WHITE);
        setSize(800, 550);
        add(panel);

        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width - 100),
                (windowSize.height - frameSize.height) / 2); // 화면 중앙에 띄우기
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        // 백버튼 누르면 메인화면으로 가기----------------------------------------------------
        JButton back = new JButton("\uD83C\uDFE0");
        back.setBounds(10, 5, 50, 30); // 위치와 크기 설정
        panel.add(back);

        back.addActionListener(e -> {
            new Main().setVisible(true);
            setVisible(false); // 창 안보이게 하기
        });

        // 뒤로가기 누르면 관리자페이지로 가기----------------------------------------------------
        JButton baaack = new JButton("\uD83D\uDD19");
        baaack.setBounds(60, 5, 50, 30); // 위치와 크기 설정
        panel.add(baaack);

        baaack.addActionListener(e -> {
            new Data().setVisible(true);
            setVisible(false); // 창 안보이게 하기
        });

        // 테이블 데이터 설정------------------------------------------------------------
        List<UserInfo> users = fetchAllUsers();
        String[] columnNames = {"이름", "아이디(학번)", "비밀번호", "생일", "성별"};
        String[][] data = new String[users.size()][5];

        for (int i = 0; i < users.size(); i++) {
            UserInfo user = users.get(i);
            data[i][0] = user.getName();
            data[i][1] = user.getId();
            data[i][2] = user.getPassword();
            data[i][3] = user.getBirthDate();
            data[i][4] = user.getGender();
        }

        // JTable 생성 및 설정
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 50, 760, 450); // 테이블 위치 및 크기 설정
        panel.add(scrollPane);
    }

    private List<UserInfo> fetchAllUsers() {
        List<UserInfo> users = new ArrayList<>();
        Connection conn = DBProject.getConnection();
        String sql = "SELECT name, student_id, password, birthday, gender FROM user";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                UserInfo user = new UserInfo(rs.getString("name"), rs.getString("student_id"), rs.getString("password"), rs.getString("birthday"), rs.getString("gender"));
                users.add(user);
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
        return users;
    }

    public static void main(String[] args) {
        new Table();
    }
}
