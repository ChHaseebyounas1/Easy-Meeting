package app.tecsol.easymeeting;

public class UserModel {


    String fullname;
    String email;
    String password;

    public UserModel(String fullname, String email, String password) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
    }


    public UserModel() {
    }

    public String getfullname() {
        return fullname;
    }

    public void setFullname(String firstname) {
        this.fullname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
