/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package thelastpromise;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * This abstract class extends the Object. The loc is recieved/returned and
 * the image does the same. The graphics is also created.  
 * 
 */
public abstract class Tile extends Object  {
    protected int resistance;
    /**
     * 
     * The method creates the image texture at   
    * the location loc.   
     */
    public Tile(Location loc, Image texture)
    {
        location = loc;
        image = texture;
    }
    
    public abstract String getSound();

    /**
     * The method draws an image at the location 
    * location.getExactX() - GameRunner.getView().getExactX() + 20,
    * location.getExactY() - GameRunner.getView().getExactY() + 40, 164,164, 
    * null 
    *   
    */
    public void draw(Graphics g)
    {
        if(location.getExactX() + 160 > GameRunner.getViewLoc().getExactX() && location.getExactX() < GameRunner.getViewLoc().getExactX() + GameRunner.getViewLoc().getX())
        {
            if(location.getExactY() + 160 > GameRunner.getViewLoc().getExactY() && location.getExactY() < GameRunner.getViewLoc().getExactY() + GameRunner.getViewLoc().getY())
            {
            
                Graphics2D g2d = (Graphics2D)g;
                g2d.drawImage(image, location.getExactX() - GameRunner.getView().getExactX() + 20,
                 location.getExactY() - GameRunner.getView().getExactY() + 40, 164,164,
                 null);
            }
        }
   
         
    }
    
    
    public ArrayList<String> save()
    {
        //saves type,x,y,imageString each on own line
        ArrayList<String> data = new ArrayList<String>();
        data.add(type);
        data.add(""+location.getX());
        data.add(""+location.getY());
        data.add(image.toString());
        return data;
    }
    
    public static Tile load(ArrayList<String> data)
    { 
       //Important!!!! subclasses called from this load method must overide the 
       //load method to avoid an infinite loop
       if(data.get(0).equals("Grass"))
       { return GrassTile.load(data); }
       else if(data.get(0).equals("Forest"))
       { return ForestTile.load(data); }
       else if(data.get(0).equals("Water"))
       { return WaterTile.load(data); }
       else if(data.get(0).equals("Gold Mine"))
       { return GoldMine.load(data); }
       return GrassTile.load(data);
    }
}
