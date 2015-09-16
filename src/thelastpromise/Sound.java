/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thelastpromise;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.Timer;

/**
 *
 *  This class handles the sounds portion of the game
 */
public class Sound {
         private static Clip clip;
         private static Boolean loop;
         private static Clip backClip;
         private static Boolean firstBackLoop = true;
         private static boolean backgrounds = false;
         private static int playCount = 0;
         
         
         /**
          * 
          * file is the name of the sound being played
          * This method plays the sounds in the game, and has a log-handling
          * portion in-case any errors occur.
          */
         public static void playLoopSound(String file)
         {
             try
             {
                loop = true;
                play(file);
             }
             catch(Exception e)
             {
                 LogHandling.logError("Error playing sound   " + e);
             }
         }
         
         /**
          * Plays a sound and starts a timer to end sounds.
          * File is the name of the sound being played
          */
         public static void playSound(String file)
         {
             closeClip();
             playCount++;
             loop = false;
             play(file);
             
         }
         
         

         /**
          * 
          * name is the name of the sound being played
          * This method plays the actual sound and has a long-handling section
          * in case the sound could not be played
          */
         private static void play(final String name)
         {
                try 
		{
                    File sound = new File(name);
                    AudioInputStream in = AudioSystem.getAudioInputStream(sound);
                    DataLine.Info info = new DataLine.Info(Clip.class, in.getFormat());
                    clip = (Clip) AudioSystem.getLine(info);
                    clip.open(in);
                    clip.addLineListener(new LineListener() {
                    public void update(LineEvent event) {
                        if (event.getType() == LineEvent.Type.STOP && loop == true) {
                            clip.drain();
                            play(name);
                            }}});
                   clip.start();
		}
		catch(Exception e)
		{
			LogHandling.logError("Could not play music \"" + name + "\"   " + e);		
		}  
         }
         
         
         /**
          * This method stops the sound after its corresponding on-screen action
          * has been completed. This also has a long-handling portion in-case
          * the sound could not be played
          */
         public static void stopSound()
         {
             try
             {
                loop = false;
                clip.stop();
                //clip.close();
                
             }
             catch(Exception e)
             {
                 LogHandling.logError("Can't stop sonud   " + e);
             }
         }
         
         public static void stopBackgrounds()
         {
             try
             {
                 backgrounds=false;
                 backClip.stop();
             }
             catch(Exception e)
             {
                  LogHandling.logError("Can't stop the music   " + e);
             }
         }
         
         
          public static void playbackloop()
          {
             try 
		{
                    
                    backgrounds=true;
                    File sound;
                    if(GameRunner.epicNum()>20)
                    {
                    if(firstBackLoop == true)
                    { 
                    sound = new File("src/8bitback.wav");
                    firstBackLoop=false; 
                    }
                    else
                    { sound = new File("src/8bitback2.wav"); }
                    }
                    else
                    {   
                        sound = new File("src/8bitback-2.wav");
                        firstBackLoop = true;
                    }        
                    
                    
                    AudioInputStream in = AudioSystem.getAudioInputStream(sound);
                    DataLine.Info info = new DataLine.Info(Clip.class, in.getFormat());
                    backClip = (Clip) AudioSystem.getLine(info);
                    backClip.open(in);
                    FloatControl gainControl = 
                    (FloatControl) backClip.getControl(FloatControl.Type.MASTER_GAIN);
                    if(GameRunner.getVolume() < 0)
                    {
                        gainControl.setValue(GameRunner.getVolume());
                    }
                    backClip.addLineListener(new LineListener() {
                    public void update(LineEvent event) {
                        if (event.getType() == LineEvent.Type.STOP && backgrounds) {
                            backClip.drain();
                            playbackloop();
                            }}});
                   backClip.start();
		}
		catch(Exception e)
		{
			LogHandling.logError("Could not play background music    " + e);		
		}  
         }
          
          
          
   
       
       public static void closeClip()
       {
             try
             {
                    if(playCount>15)
                    {
                       stopSound();
                       clip.close();
                       clip.drain();
                       playCount=0;
                     }
             }
             catch(Exception ex)
             {
                   LogHandling.logError("Can't stop sound..." + ex);
             }
    }
}
