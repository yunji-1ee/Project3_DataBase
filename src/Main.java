import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

        public Main() {

            super("메인페이지"); //타이틀
            JPanel panel = new JPanel();
            JButton login = new JButton("로그인화면");
            JButton join = new JButton("회원가입화면");
            setSize(500, 800); //창 크기 설정
            panel.add(login);
            panel.add(join);
            add(panel);

            Dimension frameSize = getSize();

            Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
            setLocation((windowSize.width - frameSize.width) ,
                    (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            setVisible(true);

            login.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Login();
                    setVisible(false); // 창 안보이게 하기!
                }
            });

            join.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Join();
                    setVisible(false); // 창 안보이게 하기
                }
            });
        }

        public static void main(String[] args) {
            new Main();
        }
    }

