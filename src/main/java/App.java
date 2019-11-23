import ConfigurationLogger.ConfigLogger;
import CustomThread.VoteAdderThread;
import RrratherAPICall.BotAPIAdder;
import Vote.FakeVoteAdder;
import WouldYouRatherWebParser.Page;

public class App {
    public static void main(String[] args) {
//        ConfigLogger.setup();
//        Page.parse(155664, 254549, false);

//        21259
        FakeVoteAdder.adder();
    }
}
