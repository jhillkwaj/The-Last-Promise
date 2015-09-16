/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thelastpromise;

import java.awt.Image;

/**
 *
 * @author justi_000
 */
public class Storm extends AttackEffect {
    int stormNum = 0;
    public Storm(Location loc, Location moveLoc)
    {
        super(loc,moveLoc,GameRunner.getCloud().get(0));
    }
    
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
            if(stormNum==0)
            {
                Sound.playSound("src/Light.wav");
                image = GameRunner.getCloud().get(1);
            }
            location = moveLoc;
            if(lastAct==false)
            {
                stormNum++;
                if(stormNum>30)
                {
                    lastAct=true;
                }
            }
        }
        
    }
}
