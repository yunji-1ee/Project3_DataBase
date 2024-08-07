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


            // 이미지 패널 설정----------------------------------------------------------------------
            JPanel ImagePanel = new JPanel() {
                Image background = new ImageIcon(getClass().getResource("/Image/MyPage.jpg")).getImage();

                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
                }
            };

            ImagePanel.setLayout(null); // 레이아웃 매니저를 null로 설정하여 절대 위치 지정
            ImagePanel.setBackground(Color.BLUE);
            ImagePanel.setBounds(0, 55, 350, 480); // 위치와 크기 설정



            // 환영~ 출력하기----------------------------------------------------------------------
            JLabel welcome = new JLabel(Session.getInstance().getName());
            welcome.setBounds(125, 75, 300, 60);
            welcome.setFont( new Font("SansSerif", Font.PLAIN, 16));
            welcome.setForeground(Color.BLACK);

            ImagePanel.add(welcome);

            // 이름 출력하기----------------------------------------------------------------------
            JLabel name = new JLabel("\uD83E\uDE75 이름 _ " + Session.getInstance().getName());
            name.setBounds(40, 203, 300, 35);
            name.setFont( new Font("SansSerif", Font.PLAIN, 16));
            name.setForeground(Color.WHITE);

            ImagePanel.add(name);

            // 아이디(학번) 출력하기----------------------------------------------------------------------
            JLabel id = new JLabel("\uD83E\uDE75 아이디(학번) _" + Session.getInstance().getUserId());
            id.setBounds(40, 238, 300, 35);
            id.setFont( new Font("SansSerif", Font.PLAIN, 16));
            id.setForeground(Color.WHITE);

            ImagePanel.add(id);

            // 비밀번호 출력하기----------------------------------------------------------------------
            JLabel pw = new JLabel("\uD83E\uDE75 비밀번호 _" + Session.getInstance().getPassword());
            pw.setBounds(40, 273, 300, 35);
            pw.setFont( new Font("SansSerif", Font.PLAIN, 16));
            pw.setForeground(Color.WHITE);

            ImagePanel.add(pw);

            // 성별 출력하기----------------------------------------------------------------------
            JLabel gender = new JLabel("\uD83E\uDE75 성별 _" + Session.getInstance().getGender());
            gender.setBounds(40, 308, 300, 35);
            gender.setFont( new Font("SansSerif", Font.PLAIN, 16));
            gender.setForeground(Color.WHITE);

            ImagePanel.add(gender);

            // 생일 출력하기----------------------------------------------------------------------
            JLabel bd = new JLabel("\uD83E\uDE75 생일 _" + Session.getInstance().getBirthDate());
            bd.setBounds(40, 343, 300, 35);
            bd.setFont( new Font("SansSerif", Font.PLAIN, 16));
            bd.setForeground(Color.WHITE);

            ImagePanel.add(bd);

            // 정보 수정하기 버튼----------------------------------------------------------------------
            JButton editInfo = new JButton("정보 수정하기");
            editInfo.setBounds(35, 400, 280, 35);

            ImagePanel.add(editInfo);

            // 기본정보버튼----------------------------------------------------------------------
            JButton info = new JButton("기본정보");
            info.setBounds(-10, 140, 100, 35);
            info.setFont( new Font("SansSerif", Font.PLAIN, 14));
            info.setForeground(Color.WHITE);
            info.setBorderPainted(false);
            info.setContentAreaFilled(false);
            info.setOpaque(false);

            ImagePanel.add(info);

            // 할 일 버튼----------------------------------------------------------------------
            JButton todo = new JButton("할 일");
            todo.setBounds(73, 140, 70, 35);
            todo.setFont( new Font("SansSerif", Font.PLAIN, 12));
            todo.setForeground(Color.BLACK);
            todo.setBorderPainted(false);
            todo.setContentAreaFilled(false);
            todo.setOpaque(false);

            ImagePanel.add(todo);

            // 면담신청 버튼----------------------------------------------------------------------
            JButton counseling= new JButton("면담신청");
            counseling.setBounds(130, 140, 85, 35);
            counseling.setFont( new Font("SansSerif", Font.PLAIN, 12));
            counseling.setForeground(Color.BLACK);
            counseling.setBorderPainted(false);
            counseling.setContentAreaFilled(false);
            counseling.setOpaque(false);

            ImagePanel.add(counseling);

            // 팀 갤러리 버튼----------------------------------------------------------------------
            JButton gallery = new JButton("팀 사진");
            gallery.setBounds(191, 140, 80, 35);
            gallery.setFont( new Font("SansSerif", Font.PLAIN, 12));
            gallery.setForeground(Color.BLACK);
            gallery.setBorderPainted(false);
            gallery.setContentAreaFilled(false);
            gallery.setOpaque(false);

            ImagePanel.add(gallery);

            // 로그아웃/탈퇴 버튼----------------------------------------------------------------------
            JButton out = new JButton("로그아웃/탈퇴");
            out.setBounds(250, 140, 110, 35);
            out.setFont( new Font("SansSerif", Font.PLAIN, 12));
            out.setForeground(Color.BLACK);
            out.setBorderPainted(false);
            out.setContentAreaFilled(false);
            out.setOpaque(false);

            ImagePanel.add(out);


            // 패널에 레이아웃 패널 추가----------------------------------------------------------------------
            panel.add(ImagePanel);
            panel.add(ImagePanel);
            ImagePanel.setVisible(true);


            // 내 정보수정 버튼 누르면 MYEDIT 페이지로가기--------------------------------------------
            editInfo.addActionListener(e -> {
                new MyEdit().setVisible(true);
                setVisible(false); // 창 안보이게 하기
            });

            // 로그아웃/탈퇴 버튼 누르면 회원가입 페이지로 돌아가기--------------------------------------------
            out.addActionListener(e -> {
                new Out().setVisible(true);
                setVisible(false); // 창 안보이게 하기
            });

            // 기본정보 버튼 누르면 마이페이지로 돌아가기--------------------------------------------
            todo.addActionListener(e -> {
                new ToDo().setVisible(true);
                setVisible(false); // 창 안보이게 하기
            });

            // 상담 버튼 누르면 마이페이지로 돌아가기--------------------------------------------
            counseling.addActionListener(e -> {
                new Counseller().setVisible(true);
                setVisible(false); // 창 안보이게 하기
            });


            // 갤러리 버튼 누르면 마이페이지로 돌아가기--------------------------------------------
            gallery.addActionListener(e -> {
                new Gallery();
                setVisible(false); // 창 안보이게 하기
            });
        }

    }


