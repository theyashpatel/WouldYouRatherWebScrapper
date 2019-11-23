package Question;

import static WouldYouRatherParser.Parser.parse;
import static Question.QuestionUtil.addQuestion;

public class QuestionAdder {

    public static void addOne() {
        if (addQuestion(parse())) {
            System.out.println("1 Question added to DB.");
        }
    }

    public static void addQuestions(Integer limit) {
        if (limit < 2) {
            return;
        }
        Integer count = 0;
        Boolean loop = true;
        while (loop) {
            if (addQuestion(parse())) {
                count++;
                System.out.println(count + " question added.");
            }
            else {
                System.out.println(" found duplicate.");
            }

            if (count > limit - 1) {
                loop = false;
            }
        }
    }
}
