/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thelastpromise;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * This class extend the Tile and loc is recieved/returned and
 * the image does the same. The "src/move/move4.wav" sound is played, 
 * on this tile.  
 * 
 */
public class WaterTile extends Tile {
    private int draws;
    /**
     * 
     * This method calls on the parent class
     * Tile and places the image texture on 
     * location loc. Then it sets the type to
     * water and resistance to 5.
     * loc is returned for the usage 
     * of the location of the water tile
     * texture is returned for the 
     * image of the water tile which will 
     * be used on the specific locations
     */
     public WaterTile(Location loc, Image texture)
    {
        super(loc,texture);
        draws=0;
        resistance = 5;
        type = "Water";
    }
     /**
      * 
      * the sound "src/move/move4.wav". Meaning it plays the 
      * file while something is one the water or passing through.
      */
    public String getSound() {
        return "src/move/move4.wav";    
    }
    
    public void draw(Graphics g)
    {
        draws++;
        if(Math.random()<.1f)
        { draws--; }
        if(draws<10)
        { image = GameRunner.getWater().get(0); }
        else if(draws<20)
        { image = GameRunner.getWater().get(1); }
        else if(draws<30)
        { image = GameRunner.getWater().get(2); }
        else if(draws<=40)
        { image = GameRunner.getWater().get(1); }
        else
        { draws = 0; }
         
         super.draw(g);
   
         
    }
    
    public static Tile load(ArrayList<String> data)
    {
        try {
            Image image = GameRunner.getWater().get(0);
            return (new WaterTile(new Location(Integer.parseInt(data.get(1)),     
                    Integer.parseInt(data.get(2))),image));
        } catch (Exception e) {
            LogHandling.logError("Can't load forest texture..."+e);
        }
        return null;
    }
}
