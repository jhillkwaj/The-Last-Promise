/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thelastpromise;

import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * This class extends the AttackUnit and sets the image file 
 * on location loc, and sets the values for the attributes 
 * of the Swordsman. The loc is recieved and the sound file
 * "src/bang/bang1.wav", plays during the movements.
 */
public class Swordsman extends AttackUnit {
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
    public Swordsman(Location loc, Image texture)
    {
        super("Swordsman",loc,texture);
        speed = 6;
        maxActionPoints = 6;
        actionPoints = 6;
        helth = 35;
        maxHelth = 30;
        attackActionPoints = 3;
        attackRange = 1.9f;
        waterUnit = 0;
        forestUnit = true;
        maxDamage = 20;
        minDamage = 5;
        faceImage = "In-game Faces/swordsman_face-final";
    }
    /**
     * 
     * This method calls the actOn class and
     * plays the sound file "src/bang/bang1.wav".
     *  loc is received and the location 
     * is set for the swordsman to move to while the 
     * music is being played. 
     */
    public void actOn(Location loc)
    {
         super.actOn(loc);
         Sound.playSound("src/bang/bang1.wav");
    }
    
    public static Unit load(ArrayList<String> data)
    {
        Swordsman s = new Swordsman(new Location(Integer.parseInt(data.get(4)),Integer.parseInt(data.get(5))),GameRunner.getSwordsman());
        return s;
    }
}
