import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MyPage extends JFrame{

        MyPage() {
            super("MyPage"); // 타이틀
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
                Image background = new ImageIcon(getClass().getResource("/Image/")).getImage();

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

            layoutPanel.add(idLabel);

            // 비밀번호 입력받기----------------------------------------------------------------------
            JLabel pwLabel = new JLabel("비밀번호 :");
            pwLabel.setBounds(40, 55, 80, 35);

            layoutPanel.add(pwLabel);

            // 로그인 확인버튼----------------------------------------------------------------------
            JButton checkLogin = new JButton("로그인");
            checkLogin.setBounds(35, 180, 280, 35);

            layoutPanel.add(checkLogin);

            // 회원가입으로 돌아가기 버튼----------------------------------------------------------------------
            JButton backJoin = new JButton("회원가입");
            backJoin.setBounds(35, 220, 280, 35);

            layoutPanel.add(backJoin);

            // 아이디 확인버튼----------------------------------------------------------------------
            JButton FindId = new JButton("아이디찾기");
            FindId.setBounds(65, 260, 100, 35);
            FindId.setBorderPainted(false);
            FindId.setContentAreaFilled(false);
            FindId.setOpaque(false);

            layoutPanel.add(FindId);

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
                new Main();
                setVisible(false); // 창 안보이게 하기
            });

            // Join 버튼 누르면 회원가입 페이지로 돌아가기--------------------------------------------
            backJoin.addActionListener(e -> {
                new Join();
                setVisible(false); // 창 안보이게 하기
            });
        }

        // 메인함수---------------------------------------------------------------------------------
        public static void main(String[] args) {
            new MyPage();
        }
    }

