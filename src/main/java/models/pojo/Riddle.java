package models.pojo;

/**
 * Created by yuri on 25.02.17.
 */
public class Riddle {
    private int idRiddle;
    private String name;
    private String text;
    private String answer;
    private int level;
    private String category;
    private int idUser;
    private boolean block;

    public Riddle(int idRiddle, String name, String text, String answer, int level, String category, int idUser, boolean block) {
        this.idRiddle = idRiddle;
        this.name = name;
        this.text = text;
        this.answer = answer;
        this.level = level;
        this.category = category;
        this.idUser = idUser;
        this.block = block;
    }

    public Riddle(){}

    public int getIdRiddle() {
        return idRiddle;
    }

    public void setIdRiddle(int idRiddle) {
        this.idRiddle = idRiddle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }
}
