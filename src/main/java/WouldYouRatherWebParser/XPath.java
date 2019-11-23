package WouldYouRatherWebParser;

public class XPath {
    public static final String UNIQUE_ID = "//*[@id=\"main\"]/div[1]/div/@data-id";
    public static final String QUESTION = "//*[@id=\"main\"]/div[1]/div/div[1]/h2/text()";
    public static final String OPTION_ONE = "//*[@id=\"main\"]/div[1]/div/div[1]/div[1]/div[1]/a/span[2]/text()";
    public static final String OPTION_TWO = "//*[@id=\"main\"]/div[1]/div/div[1]/div[1]/div[2]/a/span[2]/text()";
    public static final String CATEGORIES = "//*[@id=\"main\"]/div[1]/div/div[1]/ul/li/a/text()";
    public static final String OPTION_ONE_IMAGE = "//*[@id=\"main\"]/div[1]/div/div[1]/div[1]/div[1]/a/img/@src";
    public static final String OPTION_TWO_IMAGE = "//*[@id=\"main\"]/div[1]/div/div[1]/div[1]/div[2]/a/img/@src";
}
