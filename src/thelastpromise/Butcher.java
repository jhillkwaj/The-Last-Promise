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
public class Butcher extends AttackUnit {
    public Butcher(Location loc, Image texture)
    {
        super("Butcher",loc,texture);
        speed = 6;
        maxActionPoints = 6;
        actionPoints = 6;
        helth = 40;
        maxHelth = 30;
        attackActionPoints = 4;
        attackRange = 1.5f;
        waterUnit = 0;
        forestUnit = true;
        maxDamage = 50;
        minDamage = 10;
        faceImage = "In-game Faces/magic_melee_face-final";
    }
    
    public void levelUp()
    {
        int lastLev = level;
        super.levelUp();
        if(level>lastLev)
        {
        maxActionPoints+=1;
        actionPoints+=1;
        maxDamage+=(maxDamage/4);
        minDamage+=(minDamage/6);
        }

    }
    
    //This attacks the unit at loc
    public void actOn(Location loc)
    {
         super.actOn(loc);
         Sound.playSound("src/bang/bang3.wav");
    }
    
    
    public static Unit load(ArrayList<String> data)
    {
        Butcher b = new Butcher(new Location(Integer.parseInt(data.get(4)),Integer.parseInt(data.get(5))),GameRunner.getButcher());
        return b;
    }
}
