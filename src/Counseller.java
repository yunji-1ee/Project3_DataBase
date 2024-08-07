import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.JSpinner;


public class Counseller extends JFrame{

    Counseller() {
        super("면담"); // 타이틀
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


        // 이미지 패널 설정----------------------------------------------------------------------
        JPanel ImagePanel = new JPanel() {
            Image background = new ImageIcon(getClass().getResource("/Image/counseller.jpg")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };

        ImagePanel.setLayout(null); // 레이아웃 매니저를 null로 설정하여 절대 위치 지정
        ImagePanel.setBackground(Color.BLUE);
        ImagePanel.setBounds(0, 53, 350, 480); // 위치와 크기 설정


        // 기본정보버튼----------------------------------------------------------------------
        JButton info = new JButton("기본정보");
        info.setBounds(-15, 140, 100, 35);
        info.setFont( new Font("SansSerif", Font.PLAIN, 12));
        info.setForeground(Color.BLACK);
        info.setBorderPainted(false);
        info.setContentAreaFilled(false);
        info.setOpaque(false);

        ImagePanel.add(info);

        // 할 일 버튼----------------------------------------------------------------------
        JButton todo = new JButton("할 일");
        todo.setBounds(72, 140, 70, 35);
        todo.setFont( new Font("SansSerif", Font.PLAIN, 12));
        todo.setForeground(Color.BLACK);
        todo.setBorderPainted(false);
        todo.setContentAreaFilled(false);
        todo.setOpaque(false);

        ImagePanel.add(todo);

        // 면담신청 버튼----------------------------------------------------------------------
        JButton counseling= new JButton("면담신청");
        counseling.setBounds(126, 140, 100, 35);
        counseling.setFont( new Font("SansSerif", Font.PLAIN, 16));
        counseling.setForeground(Color.WHITE);
        counseling.setBorderPainted(false);
        counseling.setContentAreaFilled(false);
        counseling.setOpaque(false);

        ImagePanel.add(counseling);

        // 팀 갤러리 버튼----------------------------------------------------------------------
        JButton gallery = new JButton("팀 사진");
        gallery.setBounds(200, 140, 80, 35);
        gallery.setFont( new Font("SansSerif", Font.PLAIN, 12));
        gallery.setForeground(Color.BLACK);
        gallery.setBorderPainted(false);
        gallery.setContentAreaFilled(false);
        gallery.setOpaque(false);

        ImagePanel.add(gallery);

        // 로그아웃/탈퇴 버튼----------------------------------------------------------------------
        JButton out = new JButton("로그아웃/탈퇴");
        out.setBounds(250, 140, 120, 35);
        out.setFont( new Font("SansSerif", Font.PLAIN, 12));
        out.setForeground(Color.BLACK);
        out.setBorderPainted(false);
        out.setContentAreaFilled(false);
        out.setOpaque(false);

        ImagePanel.add(out);



        // 면담날짜 입력받기----------------------------------------------------------------------
        JLabel coun_date = new JLabel("\uD83E\uDE75 면담 날짜");
        coun_date.setBounds(40, 280, 200,  50);
        coun_date.setFont( new Font("SansSerif", Font.PLAIN, 18));
        coun_date.setForeground(Color.WHITE);

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(170, 286, 120, 35);

        panel.add(coun_date);
        panel.add(dateChooser);

        // 면담날짜 입력받기----------------------------------------------------------------------
       // JLabel coun_time = new JLabel("\uD83E\uDE75 면담 시간");
       // coun_time.setBounds(40, 280, 200,  50);
       // coun_time.setFont( new Font("SansSerif", Font.PLAIN, 18));
       // coun_time.setForeground(Color.WHITE);

        JLabel agree1 = new JLabel("상담은 매일 오후 2시부터 2시 30분 까지 진행됨");
        agree1.setFont(new Font("Serial", Font.PLAIN, 13));
        agree1.setForeground(Color.WHITE);
        agree1.setBounds(40, 330, 250, 35);
        panel.add(agree1);



        dateChooser.setBounds(170, 286, 120, 35);

        //panel.add(coun_time);
        panel.add(dateChooser);

        // 패널에 레이아웃 패널 추가----------------------------------------------------------------------
        panel.add(ImagePanel);
        panel.add(ImagePanel);
        ImagePanel.setVisible(true);


        // 환영~ 출력하기----------------------------------------------------------------------
        JLabel welcome = new JLabel(Session.getInstance().getName());
        welcome.setBounds(125, 72, 300, 60);
        welcome.setFont( new Font("SansSerif", Font.PLAIN, 16));
        welcome.setForeground(Color.BLACK);

        ImagePanel.add(welcome);


        // 로그아웃/탈퇴 버튼 누르면 회원가입 페이지로 돌아가기--------------------------------------------
        out.addActionListener(e -> {
            new Out().setVisible(true);
            setVisible(false); // 창 안보이게 하기
        });

        // 기본정보 버튼 누르면 마이페이지로 돌아가기--------------------------------------------
        info.addActionListener(e -> {
            new MyPage().setVisible(true);
            setVisible(false); // 창 안보이게 하기
        });
        // 할 일 버튼 누르면 마이페이지로 돌아가기--------------------------------------------
        todo.addActionListener(e -> {
            new ToDo().setVisible(true);
            setVisible(false); // 창 안보이게 하기
        });

        // 상담 버튼 누르면 상담페이지로 돌아가기--------------------------------------------
        counseling.addActionListener(e -> {
            new Counseller().setVisible(true);
            setVisible(false); // 창 안보이게 하기
        });


        // 갤러리 버튼 누르면 갤러리페이지로 돌아가기--------------------------------------------
        gallery.addActionListener(e -> {
            new Gallery().setVisible(true);
            setVisible(false); // 창 안보이게 하기
        });

    }

}


