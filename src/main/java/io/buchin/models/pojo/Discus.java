package io.buchin.models.pojo;

/**
 * Created by yuri on 13.03.17.
 */
public class Discus {
    private int idDiscus;
    private int userId;
    private String userName;
    private int riddleId;
    private String text;

    public Discus(int idDiscus, int userId, String userName, int riddleId, String text) {
        this.idDiscus = idDiscus;
        this.userId = userId;
        this.userName = userName;
        this.riddleId = riddleId;
        this.text = text;
    }

    public int getIdDiscus() {
        return idDiscus;
    }

    public void setIdDiscus(int idDiscus) {
        this.idDiscus = idDiscus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRiddleId() {
        return riddleId;
    }

    public void setRiddleId(int riddleId) {
        this.riddleId = riddleId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
