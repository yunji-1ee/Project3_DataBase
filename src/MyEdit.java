import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Objects;

import static java.sql.DriverManager.getConnection;

public class MyEdit extends JFrame {
    MyEdit() {
        super("My Edit "); // 타이틀
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

        // 백버튼 누르면 메인화면으로 가기-------------------------------------------------------------
        JButton back = new JButton("\uD83C\uDFE0");
        back.setBounds(10, 5, 50, 30); // 위치와 크기 설정
        panel.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main();
                setVisible(false); // 창 안보이게 하기
            }
        });

        // 레이아웃 패널 설정----------------------------------------------------------------------
        JPanel layoutPanel = new JPanel();
        layoutPanel.setLayout(null); // 레이아웃 매니저를 null로 설정하여 절대 위치 지정
        layoutPanel.setBackground(Color.WHITE);
        layoutPanel.setBounds(0, 180, 350, 400);

        // 이미지 패널 설정----------------------------------------------------------------------
        JPanel ImagePanel = new JPanel() {
            Image background = new ImageIcon(getClass().getResource("/Image/MyEdit.jpg")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };

        ImagePanel.setLayout(null); // 레이아웃 매니저를 null로 설정하여 절대 위치 지정
        ImagePanel.setBackground(Color.WHITE);
        ImagePanel.setBounds(30, 50, 280, 125); // 위치와 크기 설정

        // 이름 입력받기----------------------------------------------------------------------
        JLabel nameLabel = new JLabel("이름 :");
        nameLabel.setBounds(55, 0, 80, 35);

        JTextField nameField = new JTextField(10);
        nameField.setBounds(110, 0, 185, 35);

        layoutPanel.add(nameLabel);
        layoutPanel.add(nameField);

        // 아이디 입력받기----------------------------------------------------------------------
        JLabel idLabel = new JLabel("학번 :");
        idLabel.setBounds(55, 45, 80, 35);

        JTextField idField = new JTextField();
        idField.setBounds(110, 45, 140, 35);

        layoutPanel.add(idLabel);
        layoutPanel.add(idField);

        // 아이디 중복체크
        JButton IdCheck = new JButton("완료");
        IdCheck.setBounds(250, 45, 43, 35);
        layoutPanel.add(IdCheck);

        // 비밀번호 입력받기----------------------------------------------------------------------
        JLabel pwLabel = new JLabel("비밀번호 :");
        pwLabel.setBounds(55, 90, 80, 35);

        JPasswordField pwField = new JPasswordField();
        pwField.setBounds(110, 90, 185, 35);

        layoutPanel.add(pwLabel);
        layoutPanel.add(pwField);

        // 생일 입력받기----------------------------------------------------------------------
        JLabel birthLabel = new JLabel("생일 :");
        birthLabel.setBounds(55, 135, 80, 35);

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(110, 135, 185, 35);

        layoutPanel.add(birthLabel);
        layoutPanel.add(dateChooser);


        // 성별 입력받기----------------------------------------------------------------------
        JLabel Gender = new JLabel("성별 :");
        Gender.setBounds(55, 180, 80, 35);
        layoutPanel.add(Gender);
        // 남
        JButton boy = new JButton("남");
        boy.setBounds(110, 180, 80, 35);
        layoutPanel.add(boy);

        // 여
        JButton girl = new JButton("여");
        girl.setBounds(215, 180, 80, 35);
        layoutPanel.add(girl);

        // 회원가입 완료하기---------------------------------------------------------------------------
        JButton SuccessJoin = new JButton("수정 완료");
        SuccessJoin.setBounds(55, 230, 250, 35);
        layoutPanel.add(SuccessJoin);

        // 패널에 레이아웃 패널 추가----------------------------------------------------------------------
        panel.add(layoutPanel);
        panel.add(ImagePanel);
        layoutPanel.setVisible(true);

        //회원가입완료 버튼 눌렀을 때 --------------------------------------------------------------------
        SuccessJoin.addActionListener(e -> {
/*
            String name = nameField.getText();
            String id = idField.getText();
            String password = new String (pwField.getPassword()); //비밀번호 받아와서 문자열로 바꿔주기
            String birthDay = new SimpleDateFormat ("yyy-MM-dd").format (dateChooser.getDate() );

            JButton gender_bt = (JButton)e.getSource();
            String gender = gender_bt.getText();

            if( gender.equals("남")){
                gender = "남";
            } else if ( gender.equals("여")) {
                gender = "여";
            } else {
                gender = " ";
            }
 */
            new MyPage();
                setVisible(false); // 창 안보이게 하기

        });
    }

    public static void main(String[] args) {
        new MyEdit();
    }
}
