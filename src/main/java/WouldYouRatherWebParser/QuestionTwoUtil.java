package WouldYouRatherWebParser;

import Question.Question;

import static Question.QuestionHelper.checkDuplicate;
import static Question.QuestionHelper.insertQuestionTwo;
import static Question.QuestionQuery.DUPLICATEOPTION_TWO;

public class QuestionTwoUtil {
    public static Boolean addQuestion(QuestionTwo question) {
        return addQuestion(question, true);
    }

    public static Boolean addQuestion(QuestionTwo question, Boolean checkDuplicate) {

        if (checkDuplicate) {
            if (!isDuplicate(question)) {
                insertQuestionTwo(question);
                return true;
            }
            else {
                System.out.println(question.getQuestion() + "," + question.getOptionOne() + "," +
                        question.getOptionTwo() + "," + question.getCategories());
                return false;
            }
        }
        else {
            insertQuestionTwo(question);
            return true;
        }

    }

    private static boolean isDuplicate(QuestionTwo question) {
        String qstn = question.getQuestion();
        String red = question.getOptionOne();
        String blue = question.getOptionTwo();
        return checkDuplicate(DUPLICATEOPTION_TWO, qstn, red, blue) || checkDuplicate(DUPLICATEOPTION_TWO, qstn, blue, red);
    }
}
