package RrratherAPICall;

import Question.Question;

import java.io.IOException;

import static Question.QuestionHelper.insertQuestion;
import static Question.QuestionUtil.addQuestion;
import static RrratherAPICall.BotAPICall.call;
import static RrratherAPICall.BotAPICall.close;
import static WouldYouRatherParser.Parser.parse;

public class BotAPIAdder {

    public static void addOne() {
        if (addQuestion(call())) {
            System.out.println("1 Question added to DB.");
        }
        close();
    }

    public static void addQuestions(Integer limit) {
        if (limit < 2) {
            return;
        }
        Integer count = 0;
        Boolean loop = true;
        while (loop) {
            if (addQuestion(call())) {
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
        close();
    }
}
