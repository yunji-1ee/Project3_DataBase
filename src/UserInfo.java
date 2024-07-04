public class UserInfo {
    private String name;
    private String id;
    private String password;
    private String birthDate;
    private String gender;

    public UserInfo(String name, String id, String password, String birthDate, String gender) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }
}
