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
 *
 * @author justi_000
 */
public class GoldMine extends Tile {
    Image mine;
    public GoldMine(Location loc, Image mine, Image background)
    {
        super(loc,background);
        resistance = Integer.MAX_VALUE;
        type = "Gold Mine";
        this.mine=mine;
    }

    public String getSound() {
        return "src/move/move1.wav";
    }
    
    public void draw(Graphics g)
    {
         super.draw(g);
         Graphics2D g2d = (Graphics2D)g;
         g2d.drawImage(mine, location.getExactX() - GameRunner.getView().getExactX() + 20,
                 location.getExactY() - GameRunner.getView().getExactY() + 40, 164,164,
                 null);
   
         
    } 
    
    
    public static Tile load(ArrayList<String> data)
    {
        try {
            Image imageBack = ImageIO.read(new File("src/Final-Grass.png"));
            Image imageMine = ImageIO.read(new File("src/gold_mine-final.png"));
            return (new GoldMine(new Location(Integer.parseInt(data.get(1)),     
                    Integer.parseInt(data.get(2))),imageMine,imageBack));
        } catch (Exception e) {
            LogHandling.logError("Can't load forest texture..."+e);
        }
        return null;
    }
}
