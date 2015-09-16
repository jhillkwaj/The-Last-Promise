/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package thelastpromise;

import java.applet.Applet;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This main menu class presents the main credits page, and calls the 
 * loghandling class it also plays this- "src/TLPTheme Song.wav" - 
 * during the beginning.
 *
 */
public class Main {

    /**
     * @param args, creates the main method for the game.
     * Sets up the LogHandling class. Brings up in the main window of the 
     * main menu the credits of the Techs. Team and the publisher- BitBlit
     * Plays the main theme of the game.
     * 
     * This method runs the game, displays credits, and
     * plays the looped sound "src/TLPTheme Song.wav". If 
     * the class cannot be found, an error will be displayed.
     * @param args 
     */
    public static void main(String[] args) {

        try
        {
            LogHandling.setup();
            MainMenu menu = new MainMenu();
            menu.run();
            JOptionPane.showMessageDialog(null, "                    "
                 +" \nPublisher:                      BitBlit Interactive\n"
                 + "                    The Techs\n"
                 +"Lead Programer:         Justin\n2nd Programmer:        "
                 +"Mohit\nDesign/Organization:   Amir\n"
                 +"Assistant Developer:  Jeevan");
            Sound.playLoopSound("src/TLPTheme Song.wav");
            
        }
        catch(Exception e)
        {
            LogHandling.logNonRecoverableError("Can't find Class \""+
                    e.toString() + "\"");
        }
        LogHandling.logMessage("Main Done");
    }
}
