/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thelastpromise;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * This class is used to draw grass tiles in specific locations
 * and return the sound they will make if they are walked across.
 */
 
public class GrassTile extends Tile {
    /**
     * 
     * loc is passed to the parent class (Tile) as the location on which
     * the texture will be drawn.
     *  texture is an image of the texture which will be drawn on the 
     * specified location.
     */
    public GrassTile(Location loc, Image texture)
    {
        super(loc,texture);
        resistance = 2;
        type = "Grass";
    }
    /**
     * 
     *  returns the name of the sound that will be played if the tile is
     * walked across. 
     */
    public String getSound() {
        if(Math.random() < .5)
        {   return "src/move/move1.wav";    }
        else
        {   return "src/move/move2.wav";    }
    }
    
    public static Tile load(ArrayList<String> data)
    {
        try {
            Image image;
            double rand = Math.random();
            if(rand<.3)
            { 
                image = GameRunner.getGrass().get(0);
                return (new GrassTile(new Location(Integer.parseInt(data.get(1)),
                Integer.parseInt(data.get(2))),image)); 
            }
            else if(rand<.7)
            { 
                image = GameRunner.getGrass().get(1);
                return (new GrassTile(new Location(Integer.parseInt(data.get(1)),
                Integer.parseInt(data.get(2))),image)); 
            }
            
            if(rand<.8)
            { 
                image = GameRunner.getGrass().get(2);
                return (new GrassTile(new Location(Integer.parseInt(data.get(1)),
                Integer.parseInt(data.get(2))),image)); 
            }
            
            if(rand<.9)
            { 
                image = GameRunner.getGrass().get(3);
                return (new GrassTile(new Location(Integer.parseInt(data.get(1)),
                Integer.parseInt(data.get(2))),image)); 
            }
            else
            { 
                image = GameRunner.getGrass().get(4);
                return (new GrassTile(new Location(Integer.parseInt(data.get(1)),
                Integer.parseInt(data.get(2))),image)); 
            }
        } catch (Exception e) {
            LogHandling.logError("Can't load grass texture..."+e);
        }
        return null;
    }
}
