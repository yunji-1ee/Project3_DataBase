import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Join extends JFrame {
    Join(){
        super("회원가입화면"); //타이틀
        JPanel panel = new JPanel();


        panel.setBackground(Color.WHITE);

        setSize(500, 800);

        add(panel);


        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) ,
                (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        //백버튼 누르면 메인화면으로 가기

        JButton back = new JButton("Back");
        panel.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main();
                setVisible(false); // 창 안보이게 하기
            }
        });

        //
        JLabel nameLabel = new JLabel("이름 :");
        JTextField nameField = new JTextField(10);
        panel.add(nameLabel);
        panel.add(nameField);




    }
}

//회원가입화면 정보입력받기-------------------------------------------------------------------
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
        if(o == null || !(o instanceof User)) {
            return false;
        }
        User temp = (User)o;

        return id.equals(temp.getId());
    }

    @Override
    public String toString() {
        String info = "Id: " + id + "\n";
        info += "Pw: " + pw + "\n";
        info += "Name: " + name + "\n";
        info += "NickName: " + nickName + "\n";
        info += "gender: " + gender + "\n";
        return info;
    }
}