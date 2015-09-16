/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package thelastpromise;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;

/**
 *
 * 
 */
public class LogHandling {
        private static Logger log = Logger.getLogger(MainMenu.class.getName());
        private static SimpleFormatter sf = new SimpleFormatter();
        private static Handler fh; 
    
    public static void setup()
    {
       try {
            fh = new FileHandler("error.log");
            fh.setFormatter(sf);
            log.addHandler(fh);
        } catch (Exception e)
        {
            LogHandling.logNonRecoverableError("Can't start log \""+
                    e.toString() + "\"");
        }
       LogHandling.logMessage("Log started");
    }
    
    public static void logMessage(String message)
    {
        log.log(Level.INFO, message);
    }
    
    public static void logError(String message)
    {
    log.warning("Warning Message"+message);
        JOptionPane.showMessageDialog(null, "Error:" + message,
                "Error", JOptionPane.WARNING_MESSAGE);
        LogHandling.logMessage("Error Logged");
    }
    
    public static void logNonRecoverableError(String message)
    {
        log.log(Level.SEVERE, message+" please restart the game");
        JOptionPane.showMessageDialog(null, "Error:" + message + " please restart the game",
                "CRASH!!!", JOptionPane.ERROR_MESSAGE);
        LogHandling.logMessage("NonRecoceravleError...Exiting...Restart the game");
        System.exit(0);
    }
    
    

}
