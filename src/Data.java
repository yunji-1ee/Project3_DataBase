import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Data extends JFrame {

    Data() {
        super("Data"); // 타이틀
        JPanel panel = new JPanel();
        panel.setLayout(null); // 레이아웃 매니저를 null로 설정하여 절대 위치 지정
        panel.setBackground(Color.WHITE);
        setSize(350, 550);
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

        // 이미지 패널 설정----------------------------------------------------------------------
        JPanel ImagePanel = new JPanel() {
            Image background = new ImageIcon(getClass().getResource("/Image/King1.jpg")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };

        ImagePanel.setLayout(null); // 레이아웃 매니저를 null로 설정하여 절대 위치 지정
        ImagePanel.setBackground(Color.BLUE);
        ImagePanel.setBounds(0, 42, 350, 480); // 위치와 크기 설정

        // 환영~ 출력하기----------------------------------------------------------------------
        JLabel welcome = new JLabel("관리자");
        welcome.setBounds(125, 68, 300, 60);
        welcome.setFont(new Font("SansSerif", Font.PLAIN, 16));
        welcome.setForeground(Color.BLACK);

        ImagePanel.add(welcome);

        // 데이터 버튼----------------------------------------------------------------------
        JButton data = new JButton("전체 데이터 ");
        data.setBounds(18, 130, 150, 35);
        data.setFont(new Font("SansSerif", Font.PLAIN, 20));
        data.setForeground(Color.WHITE);
        data.setBorderPainted(false);
        data.setContentAreaFilled(false);
        data.setOpaque(false);

        ImagePanel.add(data);

        // 로그아웃/탈퇴 버튼----------------------------------------------------------------------
        JButton statics = new JButton("통계");
        statics.setBounds(202, 130, 110, 35);
        statics.setFont(new Font("SansSerif", Font.PLAIN, 18));
        statics.setForeground(Color.BLACK);
        statics.setBorderPainted(false);
        statics.setContentAreaFilled(false);
        statics.setOpaque(false);

        ImagePanel.add(statics);

        // 사용자 정보를 가져와서 출력하기 위한 코드--------------------------------------------
        List<User> users = fetchAllUsers();

        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.setBackground(Color.BLUE);

        for (User user : users) {
            JLabel userInfo = new JLabel("\uD83E\uDE75 이름: " + user.getName() + ", 아이디(학번): " + user.getId() + ", 비밀번호: " + user.getPassword());
            userInfo.setFont(new Font("SansSerif", Font.PLAIN, 12));
            userInfo.setForeground(Color.WHITE);
            userInfoPanel.add(userInfo);
        }

        JScrollPane scrollPane = new JScrollPane(userInfoPanel);
        scrollPane.setBounds(20, 200, 310, 300);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        ImagePanel.add(scrollPane);

        // 패널에 레이아웃 패널 추가----------------------------------------------------------------------
        panel.add(ImagePanel);
        ImagePanel.setVisible(true);

        // 갤러리 버튼 누르면 마이페이지로 돌아가기--------------------------------------------
        statics.addActionListener(e -> {
            new Statics();
            setVisible(false); // 창 안보이게 하기
        });
    }

    private List<User> fetchAllUsers() {
        List<User> users = new ArrayList<>();
        Connection conn = DBProject.getConnection();
        String sql = "SELECT name, student_id, password FROM user";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User(rs.getString("name"), rs.getString("student_id"), rs.getString("password"));
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
        new Data();
    }
}

class User {
    private String name;
    private String id;
    private String password;

    public User(String name, String id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
