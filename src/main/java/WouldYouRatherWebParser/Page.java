package WouldYouRatherWebParser;

import ConfigurationLogger.ConfigLogger;
import Question.Question;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.LogFactory;

import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ConfigurationLogger.ConfigLogger.applog;
import static WouldYouRatherWebParser.XPath.*;

public class Page {

    private static final String commonURL = "https://www.rrrather.com/view/";
    private static final WebClient webClient = new WebClient(BrowserVersion.CHROME);
    private static HtmlPage htmlPage = null;

    public synchronized static void parse(Integer start, Integer end, Boolean checkDuplicate) {
        preRead();
        for (int i = start; i <= end; i++ ) {
            getPage(commonURL + i);
            if (pageExists()) {
                QuestionTwo data = readData();
                QuestionTwoUtil.addQuestion(data, checkDuplicate);
                System.out.println(data.toString());
            }
            else {
                System.out.println(commonURL + i + " does not exists.");
            }
        }
    }

    private static void preRead() {
        turnOffLogger();
        setupWebClient();
    }


    private static void setupWebClient() {
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
    }

    private static QuestionTwo readData() {
        QuestionTwo q = new QuestionTwo();
        q.setUniqueId(Integer.parseInt(getValueFromXPath(UNIQUE_ID)));
        q.setQuestion(getValueFromXPath(QUESTION));
        q.setOptionOne(getValueFromXPath(OPTION_ONE));
        q.setOptionTwo(getValueFromXPath(OPTION_TWO));
        q.setOneImage(getValueFromXPath(OPTION_ONE_IMAGE));
        q.setTwoImage(getValueFromXPath(OPTION_TWO_IMAGE));
        q.setCategories(getValuesFromXPath(CATEGORIES));
        return q;
    }

    private static void getPage(String siteURL) {
        try {
            htmlPage = webClient.getPage(siteURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void turnOffLogger() {
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
    }

    private static String getValueFromXPath(String path) {
        try {
            DomNode node = (DomNode) htmlPage.getByXPath(path).get(0);
            return node.getTextContent();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return "void";
        }


    }

    private static String getValuesFromXPath(String path) {
        List<String> res = new ArrayList<>();
        try {
            List<Object> nodes = (List<Object>) htmlPage.getByXPath(path);
            for (Object node: nodes) {
                DomNode d = (DomNode) node;
                res.add((d.getTextContent()));
            }
            return StringUtils.join(res, ",");
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return "void";
        }
    }

    private static Boolean pageExists() {
        Integer code = htmlPage.getWebResponse().getStatusCode();
        if (code == 200) {
            return true;
        }
        else {

        }
        return false;
    }

    private static void sleep(Integer seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
