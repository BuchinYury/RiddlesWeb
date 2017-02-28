package models.pojo;

/**
 * Created by yuri on 24.02.17.
 */
public class User {
    private int idUser;
    private String login;
    private String password;
    private String userName;
    private boolean adminTrue;
    private String email;
    private boolean notification;

    public User(int idUser, String login, String password, String userName, boolean adminTrue, String email, boolean notification) {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.userName = userName;
        this.adminTrue = adminTrue;
        this.email = email;
        this.notification = notification;
    }

    public User(){}

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isAdminTrue() {
        return adminTrue;
    }

    public void setAdminTrue(boolean adminTrue) {
        this.adminTrue = adminTrue;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }
}
