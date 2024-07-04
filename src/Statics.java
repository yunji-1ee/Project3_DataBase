import javax.swing.*;
import java.awt.*;

public class Statics extends JFrame{

    Statics() {
        super("Statics"); // 타이틀
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
            Image background = new ImageIcon(getClass().getResource("/Image/King2.jpg")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };

        ImagePanel.setLayout(null); // 레이아웃 매니저를 null로 설정하여 절대 위치 지정
        ImagePanel.setBackground(Color.BLUE);
        ImagePanel.setBounds(0, -28, 350, 550); // 위치와 크기 설정



        // 환영~ 출력하기----------------------------------------------------------------------
        JLabel welcome = new JLabel("관리자");
        welcome.setBounds(125, 139, 300, 60);
        welcome.setFont( new Font("SansSerif", Font.PLAIN, 16));
        welcome.setForeground(Color.BLACK);

        ImagePanel.add(welcome);


        // 데이터 버튼----------------------------------------------------------------------
        JButton data = new JButton("전체 데이터 ");
        data.setBounds(18, 205, 150, 35);
        data.setFont( new Font("SansSerif", Font.PLAIN, 18));
        data.setForeground(Color.BLACK);
        data.setBorderPainted(false);
        data.setContentAreaFilled(false);
        data.setOpaque(false);

        ImagePanel.add(data);

        // 로그아웃/탈퇴 버튼----------------------------------------------------------------------
        JButton statics= new JButton("통계");
        statics.setBounds(202, 205, 110, 35);
        statics.setFont( new Font("SansSerif", Font.PLAIN, 20));
        statics.setForeground(Color.WHITE);
        statics.setBorderPainted(false);
        statics.setContentAreaFilled(false);
        statics.setOpaque(false);

        ImagePanel.add(statics);



        // 패널에 레이아웃 패널 추가----------------------------------------------------------------------
        panel.add(ImagePanel);
        panel.add(ImagePanel);
        ImagePanel.setVisible(true);

        // 데이터 누르면 마이페이지로 돌아가기--------------------------------------------
        data.addActionListener(e -> {
            new Data().setVisible(true);
            setVisible(false); // 창 안보이게 하기
        });
    }

}


