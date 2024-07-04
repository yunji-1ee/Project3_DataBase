public class Session {
    private static Session instance;
    private String userId;
    private String name;
    private String password;
    private String gender;
    private String birthDate;

    private Session() {}

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserPassword(String password) {
        this.password = password;
    }

    public void setUserName(String name) {
        this.name = name;
    }

    public void setUserGender(String gender) {
        this.gender = gender;
    }

    public void setUserBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setUserInfo(String userId, String name, String password, String gender, String birthDate) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthDate() {
        return birthDate;
    }
}
