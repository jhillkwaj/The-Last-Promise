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
public class AttackEffect extends Object {
    protected Location moveLoc;
    protected boolean lastAct = false;
    protected int speed = 20;
    
    public AttackEffect(Location loc, Location moveLoc, Image i)
    {
        location = loc;
        image = i;
        this.moveLoc=moveLoc;
    }

    @Override
    public void draw(Graphics g) {
        if(lastAct==false)
        {
            act();
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawImage(image, location.getExactX() - 
                    GameRunner.getView().getExactX() + 20,location.getExactY()
                    - GameRunner.getView().getExactY() + 40, 164,164,null);
        }
    }

    @Override
    public ArrayList<String> save() {
        return null;
    }
    
    public boolean getLastAct()
    { return lastAct; }
    
    public void act()
    {
        int tempX = Location.getXofExactX(location.getExactX() + 100);
        int tempY = Location.getYofExactY(location.getExactY() + 100);
        if(Math.sqrt(Math.pow(location.getExactX()-moveLoc.getExactX(), 2) + Math.pow(location.getExactY()-moveLoc.getExactY(), 2))>speed)
        {
            double ang = Math.atan((moveLoc.getExactY()-location.getExactY())/(moveLoc.getExactX()-location.getExactX() + .001));
            if(moveLoc.getExactX() < location.getExactX())
            { ang+=Math.PI; }
            location.setOnlyExactX(location.getExactX() +(int)(Math.cos(ang) * speed));
            location.setOnlyExactY(location.getExactY() +(int)(Math.sin(ang) * speed));
            lastAct=false;
        }
        else
        {
            location = moveLoc;
            if(lastAct==false)
            {
                lastAct=true;
            }
        }
        
    }
}
