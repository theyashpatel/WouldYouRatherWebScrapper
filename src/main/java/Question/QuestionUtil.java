package Question;

import static Question.QuestionHelper.checkDuplicate;
import static Question.QuestionHelper.insertQuestion;
import static Question.QuestionQuery.DUPLICATEOPTION;

public class QuestionUtil {
    public static Boolean addQuestion(Question question) {

        if (!isDuplicate(question)) {
            insertQuestion(question);
            return true;
        }
        else {
            System.out.println(question.getQuestion() + "," + question.getRed() + "," + question.getBlue() + "," + question.getCategory());
            return false;
        }

    }

    private static boolean isDuplicate(Question question) {
        String qstn = question.getQuestion();
        String red = question.getRed();
        String blue = question.getBlue();
        return checkDuplicate(DUPLICATEOPTION, qstn, red, blue) || checkDuplicate(DUPLICATEOPTION, qstn, blue, red);
    }
}
