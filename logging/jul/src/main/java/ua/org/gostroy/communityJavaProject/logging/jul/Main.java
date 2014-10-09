package ua.org.gostroy.communityJavaProject.logging.jul;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by Panov Sergey on 10/10/2014.
 */
public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        System.out.println("test JUL ...");
        try {
            LogManager.getLogManager().readConfiguration(
                    Main.class.getResourceAsStream("logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
        LOG.log(Level.SEVERE, "SEVERE: testing Java Util Logging (JUL): do you see me?");
        LOG.log(Level.WARNING, "WARNING: testing Java Util Logging (JUL): do you see me?");
        LOG.log(Level.INFO, "INFO: testing Java Util Logging (JUL): do you see me?");
        LOG.log(Level.CONFIG, "CONFIG: testing Java Util Logging (JUL): do you see me?");
        LOG.log(Level.FINE, "FINE: testing Java Util Logging (JUL): do you see me?");
        LOG.log(Level.FINER, "FINER: testing Java Util Logging (JUL): do you see me?");
        LOG.log(Level.FINEST, "FINEST: testing Java Util Logging (JUL): do you see me?");
        LOG.log(Level.ALL, "ALL: testing Java Util Logging (JUL): do you see me?");
        LOG.log(Level.OFF, "OFF: testing Java Util Logging (JUL): do you see me?");
    }
}
