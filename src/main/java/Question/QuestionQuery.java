package Question;

public class QuestionQuery {
    public static final String DUPLICATEOPTION = "select * from Question where question = ? and red = ? and blue = ? limit 1";
    public static final String DUPLICATEOPTION_TWO = "select * from Question where question = ? and oneoption = ? and twooption = ? limit 1";
    public static final String INSERTQUESTION = "insert into Question(question, red, blue, category, isnsfw) values(?, ?, ?, ?, ?)";
    public static final String INSERTQUESTIONTWO = "insert into Question(question, oneoption, twooption, oneimage, twoimage, category)" +
            " values(?, ?, ?, ?, ?, ?)";
}
