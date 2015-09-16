/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package thelastpromise;

import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * 
 */
public class MainMenu {
      
    public static MainMenuScreen build = new MainMenuScreen();
    //private static MainMenuBuild frame = new MainMenuBuild();
    private static PlayMenuBuild frame2 = new PlayMenuBuild();
    public void run()
    {
            try
            {
                build.start();
                //frame.setExtendedState(frame.MAXIMIZED_BOTH);  
                frame2.setVisible(false);
                frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                //frame.setSize(800, 600);
                //frame.setLocation(300, 200);
                //frame.setVisible(true);

            }
            catch(Exception e)
            { 
                LogHandling.logNonRecoverableError("Can't Open Main Menu \""+
                    e.toString() + "\"");
            }
            LogHandling.logMessage("Main Menu Opened");
        
        
    }
    
    public void b1()
    { 
        
        try
        {
            frame2.setLocation(300, 200);
            frame2.setVisible(true);
            frame2.setIconImage(ImageIO.read(new File("src/sword-final.png")));
        }
        catch(Exception e)
        {
            LogHandling.logError("Can't Open Menu \""+
                    e.toString() + "\"");
        }
        LogHandling.logMessage("Start Menu Opened");
    }
    
    public void exit()
    {
        try
        {
            LogHandling.logMessage("Exiting");
            System.exit(1);
        }
        catch(Exception e)
        {
            LogHandling.logNonRecoverableError("Can't Exit \""+
                    e.toString() + "\"");
        }
    }
    
    public void credits()
    {
        try
        {
         JOptionPane.showMessageDialog(null, "                    "
                 + " \nPublisher:                         BitBlit Interactive\n"
                 + "                          The Techs\n"
                 + "Programer:                         Justin\n"
                 + "Art and Graphics:                Mohit\n"
                 + "Sound and Music:               Justin\n"
                 + "Project Design:                    Justin\n"
                 + "                                                 Mohit\n"
                 + "Documentation:                    Amir\n"
                 + "                                                 Jeevan\n"
                 + "Presentation Design:          Amir\n"
                 + "                                                 Mohit\n"
                 + "                           Tools Used\n"
                 + "Netbeans:                      Programming Environment\n"
                 + "FamiTracker:                Sound Design\n"
                 + "Audacity:                        Sound Editing\n"
                 + "Google docs/drive:       Group Collaboration\n"
                 + "Paint:                              Graphics\n"
                 + "Photoshop:                    Graphics");
        }
        catch(Exception e)
        {
           LogHandling.logError("Can't Show Credits \"" + e.toString() + "\""); 
        }
        LogHandling.logMessage("Credits Shown");
    }

    public void highScore() {
        try
        {
            ArrayList<String> a = HighScore.get();
            JOptionPane.showMessageDialog(null, a.toString(), "High Scores", 1);
        }
        catch(Exception e)
        {
            LogHandling.logError("Can't Show High Scores \"" +
                    e.toString() + "\""); 
        }
        LogHandling.logMessage("High Scores Shown");
    }
    
    public static void close()
    {
        frame2.setVisible(false);
        build.close();
    }
    
}
