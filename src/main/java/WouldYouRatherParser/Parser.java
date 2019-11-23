package WouldYouRatherParser;

import Question.Question;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Parser {

    private static final String siteURL = "http://either.io";
    private static final WebClient webClient = new WebClient(BrowserVersion.CHROME);
    private static HtmlPage htmlPage = null;
    private static HtmlAnchor nextBtn = null;

    public static Question parse() {
        Question q = new Question();
        turnOffLogger();
        getPage();
        List<String> options = readOptions();
        q.setQuestion(getQuestion());
        q.setRed(options.get(0));
        q.setBlue(options.get(1));
        q.setCategory(getCategoryTags());
        return q;
    }

    private static String getQuestion() {
        HtmlHeading3 question = (HtmlHeading3) htmlPage.getByXPath("//*[@id=\"question\"]/h3").get(0);
        return question.getTextContent();
    }

    private static String getCategoryTags() {
        HtmlUnorderedList tagsList = (HtmlUnorderedList) htmlPage.getByXPath("//*[@id=\"content\"]/div[1]/div[2]/ul/li[3]/ul").get(0);
        List<String> tags = new ArrayList<String>();
        for (DomNode o: tagsList.getChildNodes()) {
            if (o.getNodeName().equals("li")) {
                tags.add(o.getTextContent());
            }
        }
        return StringUtils.join(tags, ",");
    }

    private static void refreshPage() {
        try {
            htmlPage.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getPage() {
        try {
            htmlPage = webClient.getPage(siteURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getBtn() {
        List<HtmlAnchor> anchors = htmlPage.getAnchors();
        for (HtmlAnchor a: anchors) {
            if (a.getAttribute("id").equals("btn-next-dark")) {
                nextBtn = a;
            }
        }
    }

    private static HtmlPage clickNextBtn() {
        try {
            return nextBtn.click();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<String> readOptions() {
        final ArrayList<Object> options = (ArrayList<Object>) htmlPage.getByXPath("//span[contains(@class, 'option-text')]");
        List<String> optionList = new ArrayList<String>();
        for (Object o:  options.subList(0,2)) {
            HtmlSpan span = (HtmlSpan) o;
            optionList.add(span.getTextContent());
        }
        return optionList;
    }

    private static void turnOffLogger() {
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
    }
}
