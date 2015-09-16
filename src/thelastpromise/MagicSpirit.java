/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thelastpromise;

import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author justi_000
 */
public class MagicSpirit extends SupportUnit {
     public MagicSpirit(Location loc, Image texture)
    {
        super("Spirit",loc,texture);
        speed = 6;
        maxActionPoints = 8;
        actionPoints = 8;
        mineAp=8;
        mineGold=10;
        helth = 10;
        maxHelth = 10;
        waterUnit = 1;
        forestUnit = true;
        heal=false;
        build=true;
        mine=true;
    }
     
     
    public void act()
    {
        super.act();
    
        if(Math.sqrt(Math.pow(location.getExactX()-moveLoc.getExactX(), 2) + Math.pow(location.getExactY()-moveLoc.getExactY(), 2))>speed)
        {
        }
           
    }
    
    
    public void levelUp()
    {
        super.levelUp();
        int lastLev = level;
        super.levelUp();
        if(level>lastLev)
        {
        if(level == 1)
        { levelName = "Bound "; }
        else if(level == 2)
        { levelName = "Enslaved "; }
        else if(level == 3)
        { levelName ="Tortured "; }
        else if(level == 4)
        { levelName = "Mighty "; }
        else if(level == 5)
        { levelName = "Legendary "; }
        else if(level == 6)
        { levelName = "Mythic "; }
        else if(level == 7)
        { levelName = "Demonic "; }
        else if(level == 8)
        { levelName = "Angelic "; }
        else if(level == 9)
        { levelName = "Godly  "; }
        else if(name !="Soul" && name != "Essence" && name != "Ghost")
        { 

            name = "Soul";
            level=0;
            levelName="";
        }
        else if(name=="Soul")
        {
            name = "Essence";
            level=0;
            levelName="";
        }
        else if(name=="Essence")
        {
            name = "Ghost";
            level=0;
            levelName="";
        }
        }
        
    }
    
    public static Unit load(ArrayList<String> data)
    {
        MagicSpirit m = new MagicSpirit(new Location(Integer.parseInt(data.get(4)),Integer.parseInt(data.get(5))),GameRunner.getMagicSpirit());
        return m;
    }
}
