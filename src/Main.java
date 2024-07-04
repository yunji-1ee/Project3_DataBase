import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

        public Main() {

            super("메인페이지"); //타이틀
            JPanel panel = new JPanel();
            panel.setBackground(Color. WHITE);
            panel.setLayout(null); // 레이아웃 매니저를 null로 설정하여 절대 위치 지정
            panel.setBounds(0, 0, 350, 600); // 위치와 크기 설정

            add(panel);

            // 이미지 패널 설정----------------------------------------------------------------------
            JPanel ImagePanel = new JPanel() {
                Image background = new ImageIcon(getClass().getResource("/Image/Main.jpg")).getImage();

                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
                }
            };
            ImagePanel.setLayout(null); // 레이아웃 매니저를 null로 설정하여 절대 위치 지정
            ImagePanel.setBackground(Color.YELLOW);
            ImagePanel.setBounds(2, 40, 350, 390); // 위치와 크기 설정
            panel.add(ImagePanel);

        //프레임 초기설정---------------------------------------------------------------------

            setSize(350, 550); //창 크기 설정

            Dimension frameSize = getSize();
            Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
            setLocation((windowSize.width - frameSize.width-100) ,
                    (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            setVisible(true);

            //
            // 로그인 확인버튼----------------------------------------------------------------------

            JButton login = new JButton("로그인");
            login.setBounds(35, 450, 130, 35);
            panel.add(login);


            // 회원가입으로 돌아가기 버튼----------------------------------------------------------------------

            JButton join = new JButton("회원가입");

            panel.add(join);
            join.setBounds(180, 450, 130, 35);
            panel.add(join);

            //로그인버튼 리스너 -----------------------------------------------------------
            login.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Login();
                    setVisible(false); // 창 안보이게 하기!
                }
            });
            //회원가입버튼 리스너 -----------------------------------------------------------
            join.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Join();
                    setVisible(false); // 창 안보이게 하기
                }
            });
        }

        public static void main(String[] args) {
            new Main();
        }
    }

