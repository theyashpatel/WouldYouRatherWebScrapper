package RrratherAPICall;

import Question.Question;
import com.mysql.cj.xdevapi.JsonArray;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class BotAPICall {

    private static final CloseableHttpClient httpClient = HttpClients.createDefault();
    private static final String botAPI = "https://www.rrrather.com/botapi";

    public static Question call() {
        try {
            Question q = parseJson(sendGet());
            return q;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }

    public static void close() {
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String sendGet() throws Exception {

        HttpGet request = new HttpGet(botAPI);

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    return result;
                }
            }

        }

        return null;
    }

    private static Question parseJson(String jsonString) {
        JSONParser parse = new JSONParser();
        Question q = new Question();
        try {
            JSONObject obj = (JSONObject) parse.parse(jsonString);
            q.setQuestion((String) obj.get("title"));
            q.setRed((String) obj.get("choicea"));
            q.setBlue((String) obj.get("choiceb"));
            q.setCategory(setTags(obj.get("tags")));
            Boolean isnsfw = (Boolean) obj.get("nsfw");
            if (isnsfw) q.setIsnsfw("y");

            return q;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String setTags(Object o) {
        String result = "None";
        try {
            if (!(boolean) o) {
                return result;
            }

        } catch (ClassCastException e) {
            result = (String) o;
            return result;
        }
        return result;
    }
}
