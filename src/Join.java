import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Objects;

import static java.sql.DriverManager.getConnection;


public class Join extends JFrame {

    String gender = "";

    Join() {
        super("Join "); // 타이틀
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
            Image background = new ImageIcon(getClass().getResource("/Image/join.jpg")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };

        ImagePanel.setLayout(null); // 레이아웃 매니저를 null로 설정하여 절대 위치 지정
        ImagePanel.setBackground(Color.WHITE);
        ImagePanel.setBounds(0, 25, 350, 150); // 위치와 크기 설정

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

        // 약관동의----------------------------------------------------------------------
        JLabel agree1 = new JLabel("회원가입을 완료하시면 약관 및 개인정보수집에 동의됩니다.");
        agree1.setFont(new Font("Serial", Font.PLAIN, 10));
        agree1.setForeground(Color.RED);
        agree1.setBounds(55, 215, 250, 35);
        layoutPanel.add(agree1);

        JLabel agree2 = new JLabel("동의하시면 아래의 \"회원가입완료\" 버튼을 눌러주세요.");
        agree2.setFont(new Font("Serial", Font.PLAIN, 10));
        agree2.setForeground(Color.RED);
        agree2.setBounds(63, 230, 250, 35);
        layoutPanel.add(agree2);

        // 회원가입 완료하기---------------------------------------------------------------------------
        JButton SuccessJoin = new JButton("회원가입 완료하기");
        SuccessJoin.setBounds(55, 260, 250, 35);
        layoutPanel.add(SuccessJoin);

        // 패널에 레이아웃 패널 추가----------------------------------------------------------------------
        panel.add(layoutPanel);
        panel.add(ImagePanel);
        layoutPanel.setVisible(true);


        boy.addActionListener(gender_BoyGirl);
        girl.addActionListener(gender_BoyGirl);

        //회원가입완료 버튼 눌렀을 때 --------------------------------------------------------------------
        SuccessJoin.addActionListener(e -> {

            String name = nameField.getText(); //각각 값들을 받아와서
            String id = idField.getText();
            String password = new String (pwField.getPassword()); //비밀번호 받아와서 문자열로 바꿔주기
            String birthDay = new SimpleDateFormat ("yyy-MM-dd").format (dateChooser.getDate() );


            //회원가입 조건-------------------------------------------------------------------------------------------------------
            if (nameField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null," 이름을 입력해주세요.");

            }
            else if (idField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"학번(아이디)를 입력해주세요.");
            }
            else if (password.isEmpty()){
                JOptionPane.showMessageDialog(null,"비밀번호를 입력하시오.");
            }
            else if (gender.isEmpty()){
                JOptionPane.showMessageDialog(null," 성별을 입력해주세요.");
            }
            else if (birthDay.isEmpty()){
                JOptionPane.showMessageDialog(null," 생,년,월,일을 입력해주세요.");
            }


            else {

                DBProject dbProject = new DBProject();
                Boolean result = dbProject.Creation(name, id, password, birthDay, gender);

                if (result) {
                    JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
                    new Login();
                    setVisible(false); // 창 안보이게 하기
                } else
                    JOptionPane.showMessageDialog(null, "입력한 값을 다시 확인해주세요.");
            }
        });
    }
    ActionListener gender_BoyGirl  = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton gender_bt = (JButton)e.getSource();
            gender = gender_bt.getText();

        }
    };
}
