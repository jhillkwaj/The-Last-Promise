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
public class ArchMage extends AttackUnit {
    
    public ArchMage(Location loc, Image texture)
    {
        super("Arch Mage",loc,texture);
        speed = 6;
        maxActionPoints = 5;
        actionPoints = 5;
        helth = 40;
        maxHelth = 40;
        attackActionPoints = 5;
        attackRange = 2.5f;
        waterUnit = 0;
        forestUnit = true;
        maxDamage = 40;
        minDamage = 0;
        faceImage = "In-game Faces/arch_mage_face-final";
    }
    
    public void actOn(Location loc)
    {
         double dam = (((maxDamage+1-minDamage)*Math.random()) + minDamage)/((Math.sqrt(Math.pow(location.getX()-loc.getX(),2)+Math.pow(location.getY()-loc.getY(),2)))/3);
         //System.out.println(""+dam + " at " + (Math.sqrt(Math.pow(location.getX()-loc.getX(),2)+Math.pow(location.getY()-loc.getY(),2))));
         GameRunner.containsEnemyUnit(loc).attack((int)Math.ceil(dam));
         if(GameRunner.containsEnemyUnit(loc).helth<=0)
         {
             levelUp();
         }
         GameRunner.addAttack(new Storm(new Location(location.getX(),location.getY()),loc)); 
         if(Math.sqrt(Math.pow(location.getX()-loc.getX(),2)+Math.pow(location.getY()-loc.getY(),2))<=1.5f)
         {GameRunner.addAttack(new Fire(new Location(location.getX(),location.getY()),loc,GameRunner.getFire())); }
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
        maxDamage+=(maxDamage/6);
        attackRange+=.5f;
        }
    }
     
     
    public static Unit load(ArrayList<String> data)
    {
        ArchMage a = new ArchMage(new Location(Integer.parseInt(data.get(4)),Integer.parseInt(data.get(5))),GameRunner.getArchMage());
        return a;
    }
     
}
