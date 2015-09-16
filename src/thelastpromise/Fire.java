/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thelastpromise;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author justi_000
 */
public class Fire extends AttackEffect {

    public Fire(Location loc, Location moveLoc, Image i)
    {
        super(loc,moveLoc,i);
        speed=30;
    }

    
    public void act()
    {
        super.act();
        if(lastAct)
        {
            Sound.playSound("src/fireSound.wav");
        }
    }
}
