package ConfigurationLogger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigLogger {
    public static final Logger applog = Logger.getGlobal();

    public static void setup() {

// Create and set handler
        Handler systemOut = new ConsoleHandler();
        systemOut.setLevel( Level.ALL );
        applog.addHandler( systemOut );
        applog.setLevel( Level.ALL );

// Prevent logs from processed by default Console handler.
//        applog.setUseParentHandlers( false ); // Solution 1
        Logger.getLogger("").setLevel( Level.OFF ); // Solution 2
    }
}
