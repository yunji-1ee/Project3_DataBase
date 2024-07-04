import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    Login() {
        super("Login"); // 타이틀
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

        // 레이아웃 패널 설정----------------------------------------------------------------------
        JPanel layoutPanel = new JPanel();
        layoutPanel.setLayout(null); // 레이아웃 매니저를 null로 설정하여 절대 위치 지정
        layoutPanel.setBackground(Color.WHITE);
        layoutPanel.setBounds(0, 180, 350, 380); // 위치와 크기 설정

        // 이미지 패널 설정----------------------------------------------------------------------
        JPanel ImagePanel = new JPanel() {
            Image background = new ImageIcon(getClass().getResource("/Image/login.jpg")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };

        ImagePanel.setLayout(null); // 레이아웃 매니저를 null로 설정하여 절대 위치 지정
        ImagePanel.setBackground(Color.YELLOW);
        ImagePanel.setBounds(0, 40, 350, 150); // 위치와 크기 설정

        // 아이디 입력받기----------------------------------------------------------------------
        JLabel idLabel = new JLabel("아이디 (학번) :");
        idLabel.setBounds(40, 0, 80, 35);

        JTextField idField = new JTextField(10);
        idField.setBounds(35, 25, 280, 35);

        layoutPanel.add(idLabel);
        layoutPanel.add(idField);

        // 비밀번호 입력받기----------------------------------------------------------------------
        JLabel pwLabel = new JLabel("비밀번호 :");
        pwLabel.setBounds(40, 55, 80, 35);

        JPasswordField pwField = new JPasswordField();
        pwField.setBounds(35, 80, 280, 35);

        layoutPanel.add(pwLabel);
        layoutPanel.add(pwField);

        // 로그인 확인버튼----------------------------------------------------------------------
        JButton checkLogin = new JButton("로그인");
        checkLogin.setBounds(35, 180, 280, 35);

        layoutPanel.add(checkLogin);

        // 회원가입으로 돌아가기 버튼----------------------------------------------------------------------
        JButton backJoin = new JButton("회원가입");
        backJoin.setBounds(35, 220, 280, 35);

        layoutPanel.add(backJoin);

        // 아이디 확인버튼----------------------------------------------------------------------
        JButton King = new JButton("관리자모드");
        King.setBounds(65, 260, 100, 35);
        King.setBorderPainted(false);
        King.setContentAreaFilled(false);
        King.setOpaque(false);

        layoutPanel.add(King);

        // 관리자모드-----------------------------------------------------------------------------------
        King.addActionListener(e -> {

            String id = idField.getText(); //아이디 받아오고
            String password = new String (pwField.getPassword()); //비밀번호 받아와서 문자열로 바꿔주고

            if (id.equals("admin") && password.equals("admin12")) {
                JOptionPane.showMessageDialog(null, "관리자 모드로 전환되었습니다.");
                new Data().setVisible(true); // 관리자 페이지로 이동
                setVisible(false); // 창 안보이게 하기
            } else {
                JOptionPane.showMessageDialog(null, "관리자모드의 아이디와 비밀번호를 바르게 입력해주세요.");
            }
        });

        // 비밀번호 찾기 버튼----------------------------------------------------------------------
        JButton FindPw = new JButton("비밀번호찾기");
        FindPw.setBounds(185, 260, 120, 35);
        FindPw.setBorderPainted(false);
        FindPw.setContentAreaFilled(false);
        FindPw.setOpaque(false);

        layoutPanel.add(FindPw);

        // 패널에 레이아웃 패널 추가----------------------------------------------------------------------
        panel.add(layoutPanel);
        panel.add(ImagePanel);
        layoutPanel.setVisible(true);

        // 백버튼 누르면 메인화면으로 가기----------------------------------------------------
        JButton back = new JButton("\uD83C\uDFE0");
        back.setBounds(10, 5, 50, 30); // 위치와 크기 설정
        panel.add(back);

        back.addActionListener(e -> {
            new Main().setVisible(true);
            setVisible(false); // 창 안보이게 하기
        });

        // Join 버튼 누르면 회원가입 페이지로 돌아가기--------------------------------------------
        backJoin.addActionListener(e -> {
            new Join().setVisible(true);
            setVisible(false); // 창 안보이게 하기
        });

        //로그인에 버튼을 눌렀을 때------------------------------------------------------------------
        checkLogin.addActionListener(e -> {

            String id = idField.getText(); //아이디 받아오고
            String password = new String (pwField.getPassword()); //비밀번호 받아와서 문자열로 바꿔주고

            DBProject dbProject = new DBProject(); //디비 프로젝트 객체로 불러와서
            boolean isValid = dbProject.validateLogin(id,password); //isValid를 디비프로젝트의 메소드를 불러와서

            if (idField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "학번(아이디)를 입력해주세요.");
            } else if (password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "비밀번호를 입력하시오.");
            } else {
                if (isValid) { //타당하면 메인페이지로
                    JOptionPane.showMessageDialog(null, "로그인 성공!");
                    boolean userInfoFetched = dbProject.fetchUserInfo(id);

                    if (userInfoFetched) {
                        new MyPage().setVisible(true);
                        setVisible(false); // 창 안보이게 하기
                    }
                } else { // 아니면 경고메세지
                    JOptionPane.showMessageDialog(null, "입력한 값을 다시 확인해주세요.");
                }
            }
        });
    }
}
