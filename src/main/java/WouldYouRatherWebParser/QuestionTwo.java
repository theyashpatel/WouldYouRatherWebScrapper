package WouldYouRatherWebParser;

public class QuestionTwo {
    private Integer uniqueId;
    private String question;
    private String optionOne;
    private String optionTwo;
    private String categories;
    private String imagePrefix = "https:";
    private String oneImage;
    private String twoImage;

    public Integer getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(Integer uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionOne() {
        return optionOne;
    }

    public void setOptionOne(String optionOne) {
        this.optionOne = optionOne;
    }

    public String getOptionTwo() {
        return optionTwo;
    }

    public void setOptionTwo(String optionTwo) {
        this.optionTwo = optionTwo;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getOneImage() {
        return oneImage;
    }

    public void setOneImage(String oneImage) {
        this.oneImage = imagePrefix + oneImage;
    }

    public String getTwoImage() {
        return twoImage;
    }

    public void setTwoImage(String twoImage) {
        this.twoImage = imagePrefix + twoImage;
    }

    @Override
    public String toString() {
        String s = "UniqueId: " + this.uniqueId + "\n" +
                "Question: " + this.question + "\n" +
                "One: " + this.optionOne + "\n" +
                "Two: " + this.optionTwo + "\n" +
                "OneImage: " + this.oneImage + "\n" +
                "TwoImage: " + this.twoImage + "\n" +
                "Categories: " + this.categories + "\n" ;
        return s;

    }
}
