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
        setLocation((windowSize.width - frameSize.width-100),
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
        layoutPanel.setBounds(0, 100, 350, 400);

        // 이름 입력받기----------------------------------------------------------------------
        JLabel nameLabel = new JLabel("이름 :");
        nameLabel.setBounds(10, 20, 80, 30);
        JTextField nameField = new JTextField(10);
        nameField.setBounds(100, 20, 200, 30);
        layoutPanel.add(nameLabel);
        layoutPanel.add(nameField);

        // 아이디 입력받기----------------------------------------------------------------------
        JLabel idLabel = new JLabel("ID :");
        idLabel.setBounds(60, 60, 80, 30);
        JTextField idField = new JTextField(10);
        idField.setBounds(100, 60, 200, 30);
        layoutPanel.add(idLabel);
        layoutPanel.add(idField);

        // 비밀번호 입력받기----------------------------------------------------------------------
        JLabel pwLabel = new JLabel("Password :");
        pwLabel.setBounds(10, 100, 80, 30);
        JPasswordField pwField = new JPasswordField();
        pwField.setBounds(100, 100, 200, 30);
        layoutPanel.add(pwLabel);
        layoutPanel.add(pwField);

        // 패널에 레이아웃 패널 추가----------------------------------------------------------------------
        panel.add(layoutPanel);
        layoutPanel.setVisible(true);
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
