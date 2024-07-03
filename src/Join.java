import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Join extends JFrame {
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
        JButton back = new JButton("Back");
        back.setBounds(10, 5, 80, 30); // 위치와 크기 설정
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
        JLabel idLabel = new JLabel("아이디 :");
        idLabel.setBounds(55, 45, 80, 35);

        JTextField idField = new JTextField(10);
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
        JLabel gender = new JLabel("성별 :");
        gender.setBounds(55, 180, 80, 35);
        layoutPanel.add(gender);
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

        SuccessJoin.addActionListener(e -> {
            new Login();
            setVisible(false); // 창 안보이게 하기
        });
    }


    public static void main(String[] args) {
        new Join();
    }
}

// 회원가입화면 정보입력받기
class User {
    private String id;
    private String pw;
    private String name;
    private String nickName;
    private String gender;

    public User(String id, String pw, String name, String nickName, String gender) {
        setId(id);
        setPw(pw);
        setName(name);
        setNickName(nickName);
        setGender(gender);
    }

    public User(String id) {
        setId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof User)) {
            return false;
        }
        User temp = (User) o;
        return id.equals(temp.getId());
    }

    @Override
    public String toString() {
        String info = "Id: " + id + "\n";
        info += "Pw: " + pw + "\n";
        info += "Name: " + name + "\n";
        info += "NickName: " + nickName + "\n";
        info += "Gender: " + gender + "\n";
        return info;
    }
}
