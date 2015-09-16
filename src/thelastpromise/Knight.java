/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package thelastpromise;

import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author 100032528
 */
public class Knight extends AttackUnit {

    /**
     * 
     * This method calls the parent class AttackUnit and 
     * places the image texture on location loc, and sets
     * values for the attributes of the Swordsman.
     *  loc is received and used for the location of 
     * the swordsman
     *  texture is received and used for the image of 
     * swordsman
     */
    public Knight(Location loc, Image texture)
    {
        super("Knight",loc,texture);
        speed = 4;
        maxActionPoints = 4;
        actionPoints = 4;
        helth = 90;
        maxHelth = 90;
        attackActionPoints = 4;
        attackRange = 1.5f;
        waterUnit = 0;
        forestUnit = true;
        maxDamage = 30;
        minDamage = 10;
        faceImage = "In-game Faces/armored_knight_face-final";
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
         Sound.playSound("src/bang/bang1.wav");
    }
    
    
    public static Unit load(ArrayList<String> data)
    {
        Knight k = new Knight(new Location(Integer.parseInt(data.get(4)),Integer.parseInt(data.get(5))),GameRunner.getKnight());
        return k;
    }
}
