import java.sql.*;

public class DBProject {
    public static void main(String[] args) throws SQLException {
        Connection con = null;

        String server = "localhost"; //내 컴퓨터에서 돌아가는 디비를 사용해줄거다라는 의미
        String db = "summerProjectDB"; //내 데이터베이스 이름
        String user = "root";
        String password = "dbswl1219!";

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + db + "?useSSL=false", user, password);
            System.out.println("Connected");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Statement statement = null;
        statement = con.createStatement(); //커리문으로 명령어 넘김

        ResultSet resultSet = null; //결과가 나올 객체 -클래스
        resultSet = statement.executeQuery("select * from user"); //데이터베이스에 추가할 것


        resultSet.next(); //

        String name = null;
        name = resultSet.getString("name"); //내가 지정해준 컬럼 이름

        System.out.println(name);

        resultSet.close();
        statement.close();
        con.close();


    }


}
