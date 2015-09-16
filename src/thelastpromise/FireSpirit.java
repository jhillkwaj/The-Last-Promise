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
public class FireSpirit extends AttackUnit {
    public FireSpirit(Location loc, Image texture)
    {
        super("Fire Spirit",loc,texture);
        speed = 6;
        maxActionPoints = 7;
        actionPoints = 7;
        helth = 60;
        maxHelth = 60;
        attackActionPoints = 5;
        attackRange = 2.0f;
        waterUnit = 0;
        forestUnit = true;
        maxDamage = 10;
        minDamage = 2;
        faceImage = "In-game Faces/fire_spirit_face-final";
    }
    
    public void actOn(Location loc)
    {
        double dam = minDamage;
        if(helth<60)
        { dam = (((maxDamage+1-minDamage)*Math.random()) + minDamage);  }
        else
        { dam = ((((helth/6)+1-minDamage)*Math.random()) + minDamage); }
        if(dam<12) {helth+=dam;}
        else {helth+=12;}
         GameRunner.containsEnemyUnit(loc).attack((int)dam);
         if(GameRunner.containsEnemyUnit(loc).helth<=0)
         {
             levelUp();
         }
         GameRunner.addAttack(new Fire(new Location(location.getX(),location.getY()),loc,GameRunner.getFire())); 
         Sound.playSound("src/magic.wav");
    }
    
     public void levelUp()
    {
        int lastLev = level;
        super.levelUp();
        if(level>lastLev)
        {
        maxActionPoints+=1;
        actionPoints+=1;
        maxDamage+=(maxDamage/5);
        minDamage+=(minDamage/5);
        }
    }
     
     
    public static Unit load(ArrayList<String> data)
    {
        FireSpirit a = new FireSpirit(new Location(Integer.parseInt(data.get(4)),Integer.parseInt(data.get(5))),GameRunner.getArchMage());
        return a;
    }
}
