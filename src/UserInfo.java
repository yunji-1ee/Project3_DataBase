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

    // Getter와 Setter 메소드
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
