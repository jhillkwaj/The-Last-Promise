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
 * This class is used to draw the ForestTile in the location being given,
 * and to return the name of the ForestTiles sound file so it can be played
 * 
 */
public class ForestTile extends Tile {
   /**
    * 
    *  loc is passed to its parent class tile, as the location on which
    * the texture is to be drawn
    *  texture is the texture(image) of the ForestTile which will be drawn
    * on the location specified. Also it specifies the type of tile and states 
    * its resistance value, (which increases the action points needed to move 
    * across it?)
    */
    public ForestTile(Location loc, Image texture)
    {
        super(loc,texture);
        resistance = 3;
        type = "Forest";
    }
    /**
     * 
     *  returns the name of the sound the ForestTile gives if it is moved
     * across.
     */
    public String getSound() {
        return "src/move/move3.wav";
    }
    
    public static Tile load(ArrayList<String> data)
    {
        try {
            Image image = GameRunner.getForest();
            return (new ForestTile(new Location(Integer.parseInt(data.get(1)),     
                    Integer.parseInt(data.get(2))),image));
        } catch (Exception e) {
            LogHandling.logError("Can't load forest texture..."+e);
        }
        return null;
    }

}
