import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class MyEdit extends JFrame {

    String gender = "";

    MyEdit() {
        super("정보 수정하기"); // 타이틀
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

        // 뒤로가기 누르면 관리자페이지로 가기----------------------------------------------------
        JButton baaack = new JButton("\uD83D\uDD19");
        baaack.setBounds(5, 5, 50, 30); // 위치와 크기 설정
        panel.add(baaack);

        baaack.addActionListener(e -> {
            new MyPage().setVisible(true);
            setVisible(false); // 창 안보이게 하기
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
        JButton SuccessEdit = new JButton("수정 완료");
        SuccessEdit.setBounds(55, 230, 250, 35);
        layoutPanel.add(SuccessEdit);

        // 패널에 레이아웃 패널 추가----------------------------------------------------------------------
        panel.add(layoutPanel);
        panel.add(ImagePanel);
        layoutPanel.setVisible(true);


        boy.addActionListener(gender_BoyGirl);
        girl.addActionListener(gender_BoyGirl);

        SuccessEdit.addActionListener(e -> {

            String name = nameField.getText(); //각각 값들을 받아와서
            String id = idField.getText();
            String password = new String(pwField.getPassword()); //비밀번호 받아와서 문자열로 바꿔주기

            boolean gotbirth;
            String birthDay = "";
            try {
                birthDay = new SimpleDateFormat("yyy-MM-dd").format(dateChooser.getDate());
                gotbirth = true;
            } catch (Exception ex) {
                gotbirth = false;
            }

            //회원가입 조건-------------------------------------------------------------------------------------------------------


            if (nameField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, " 이름을 입력해주세요.");
            } else if (idField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "학번(아이디)를 입력해주세요.");
            } else if (password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "비밀번호를 입력하시오.");
            } else if (gender.isEmpty()) {
                JOptionPane.showMessageDialog(null, "성별을 입력해주세요.");
            } else if (!gotbirth) {
                JOptionPane.showMessageDialog(null, "생,년,월,일을 입력해주세요.");
            } else {
                DBProject dbProject = new DBProject();
                boolean isUpdate = dbProject.updateUser(name, id, password, birthDay, gender);

                if (isUpdate) {
                    JOptionPane.showMessageDialog(null, "정보가 성공적으로 수정되었습니다.");
                    new Login();
                    setVisible(false); // 창 안보이게 하기
                } else
                    JOptionPane.showMessageDialog(null, "정보수정에 실패했습니다. 학번은 변결할 수 없습니다.");
            }
        });
    }
    ActionListener gender_BoyGirl = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton gender_bt = (JButton) e.getSource();
            gender = gender_bt.getText();

        }
    };

}
