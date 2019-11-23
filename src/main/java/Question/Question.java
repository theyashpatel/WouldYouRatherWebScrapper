package Question;

public class Question {

    private String question;
    private String red;
    private String blue;
    private String category;
    private String isnsfw = "n";

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getBlue() {
        return blue;
    }

    public void setBlue(String blue) {
        this.blue = blue;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIsnsfw() {
        return isnsfw;
    }

    public void setIsnsfw(String isnsfw) {
        this.isnsfw = isnsfw;
    }
}
