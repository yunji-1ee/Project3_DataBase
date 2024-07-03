import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    Login(){
        //화면 초기설정-----------------------------------------------------------------
        super("로그인화면"); //타이틀
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

        //백 버튼 누르면 메인화면으로 가기----------------------------------------------------
        JButton back = new JButton("Back");
        panel.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main();
                setVisible(false);
            }


        });


    } //Login
}