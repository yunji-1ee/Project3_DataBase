import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Table extends JFrame {
    private DefaultTableModel tableModel;

    Table() {
        super("데이타테이블"); // 타이틀
        JPanel panel = new JPanel();
        panel.setLayout(null); // 레이아웃 매니저를 null로 설정하여 절대 위치 지정
        panel.setBackground(Color.WHITE);
        setSize(800, 600);
        add(panel);

        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width - 100),
                (windowSize.height - frameSize.height) / 2); // 화면 중앙에 띄우기
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        // 백버튼 누르면 메인화면으로 가기----------------------------------------------------
        JButton back = new JButton("\uD83C\uDFE0");
        back.setBounds(10, 5, 50, 30); // 위치와 크기 설정
        panel.add(back);

        back.addActionListener(e -> {
            new Main().setVisible(true);
            setVisible(false); // 창 안보이게 하기
        });

        // 뒤로가기 누르면 관리자페이지로 가기----------------------------------------------------
        JButton baaack = new JButton("\uD83D\uDD19");
        baaack.setBounds(60, 5, 50, 30); // 위치와 크기 설정
        panel.add(baaack);

        baaack.addActionListener(e -> {
            new Data().setVisible(true);
            setVisible(false); // 창 안보이게 하기
        });

        // 테이블 데이터 설정------------------------------------------------------------
        List<UserInfo> users = fetchAllUsers();
        String[] columnNames = {"이름", "아이디(학번)", "비밀번호", "생일", "성별"};
        tableModel = new DefaultTableModel(columnNames, 0);

        for (UserInfo user : users) {
            Object[] rowData = {user.getName(), user.getId(), user.getPassword(), user.getBirthDate(), user.getGender()};
            tableModel.addRow(rowData);
        }

        // JTable 생성 및 설정
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 50, 760, 400); // 테이블 위치 및 크기 설정
        panel.add(scrollPane);

        // 수정 버튼
        JButton editButton = new JButton("수정");
        editButton.setBounds(20, 470, 100, 30);
        panel.add(editButton);

        // 삭제 버튼
        JButton deleteButton = new JButton("삭제");
        deleteButton.setBounds(130, 470, 100, 30);
        panel.add(deleteButton);

        // 추가 버튼
        JButton addButton = new JButton("추가");
        addButton.setBounds(240, 470, 100, 30);
        panel.add(addButton);

        // 완료 버튼
        JButton saveButton = new JButton("완료");
        saveButton.setBounds(350, 470, 100, 30);
        panel.add(saveButton);

        // 수정 버튼 이벤트 핸들러
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                for (int i = 0; i < table.getColumnCount(); i++) {
                    tableModel.setValueAt(JOptionPane.showInputDialog("Enter new value for " + columnNames[i]), selectedRow, i);
                }
            }
        });

        // 삭제 버튼 이벤트 핸들러
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String userId = tableModel.getValueAt(selectedRow, 1).toString();
                deleteUser(userId);
                tableModel.removeRow(selectedRow);
            }
        });

        // 추가 버튼 이벤트 핸들러
        addButton.addActionListener(e -> {
            Object[] newRow = new Object[5];
            for (int i = 0; i < columnNames.length; i++) {
                newRow[i] = JOptionPane.showInputDialog("Enter " + columnNames[i]);
            }
            tableModel.addRow(newRow);
            addUser(new UserInfo(newRow[0].toString(), newRow[1].toString(), newRow[2].toString(), newRow[3].toString(), newRow[4].toString()));
        });

        // 완료 버튼 이벤트 핸들러
        saveButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                UserInfo user = new UserInfo(
                        tableModel.getValueAt(selectedRow, 0).toString(),
                        tableModel.getValueAt(selectedRow, 1).toString(),
                        tableModel.getValueAt(selectedRow, 2).toString(),
                        tableModel.getValueAt(selectedRow, 3).toString(),
                        tableModel.getValueAt(selectedRow, 4).toString()
                );
                updateUser(user);
            }
        });
    }

    private List<UserInfo> fetchAllUsers() {
        List<UserInfo> users = new ArrayList<>();
        Connection conn = DBProject.getConnection();
        String sql = "SELECT name, student_id, password, birthday, gender FROM user";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                UserInfo user = new UserInfo(rs.getString("name"), rs.getString("student_id"), rs.getString("password"), rs.getString("birthday"), rs.getString("gender"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return users;
    }

    private void deleteUser(String userId) {
        Connection conn = DBProject.getConnection();
        String sql = "DELETE FROM user WHERE student_id = ?";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void addUser(UserInfo user) {
        Connection conn = DBProject.getConnection();
        String sql = "INSERT INTO user (name, student_id, password, birthday, gender) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getId());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getBirthDate());
            ps.setString(5, user.getGender());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void updateUser(UserInfo user) {
        Connection conn = DBProject.getConnection();
        String sql = "UPDATE user SET name = ?, password = ?, birthday = ?, gender = ? WHERE student_id = ?";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getBirthDate());
            ps.setString(4, user.getGender());
            ps.setString(5, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        new Table();
    }
}
