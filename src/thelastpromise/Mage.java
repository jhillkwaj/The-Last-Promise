/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package thelastpromise;

import java.awt.Image;
import java.util.ArrayList;

/**
 *  The class passes the location for the mage to be drawn and
 * defines calls on its parent to allow the mage to move.
 */
public class Mage extends AttackUnit {
    /**
     * 
     * loc is the location that the mage will drawn on
     * texture is an image of the mage that will be drawn on the location
     * that is being passed.
     * This method also specifies some of the mages, basic in-game values.
     */
    public Mage(Location loc, Image texture)
    {
        super("Mage",loc,texture);
        speed = 8;
        maxActionPoints = 8;
        actionPoints = 8;
        helth = 20;
        maxHelth = 20;
        attackActionPoints = 5;
        attackRange = 3.0f;
        waterUnit = 1;
        forestUnit = true;
        maxDamage = 20;
        minDamage = 0;
        faceImage = "In-game Faces/mage_face-final";
    }
    
    
    //This attacks the unit at loc
    public void actOn(Location loc)
    {
         int distFall = 2;
         double newMaxDamage = maxDamage+distFall-(distFall*(Math.sqrt(Math.pow(location.getX()-loc.getX(),2)+Math.pow(location.getY()-loc.getY(),2))));
         double dam = (((newMaxDamage+1-minDamage)*Math.random()) + minDamage);
         //System.out.println(""+dam + " at " + (Math.sqrt(Math.pow(location.getX()-loc.getX(),2)+Math.pow(location.getY()-loc.getY(),2))));
         GameRunner.containsEnemyUnit(loc).attack((int)Math.ceil(dam));
         if(GameRunner.containsEnemyUnit(loc).helth<=0)
         {
             levelUp();
         }
         GameRunner.addAttack(new Storm(new Location(location.getX(),location.getY()),loc)); 
         Sound.playSound("src/magic.wav");
    }

    public static Unit load(ArrayList<String> data)
    {
        Mage h = new Mage(new Location(Integer.parseInt(data.get(4)),Integer.parseInt(data.get(5))),GameRunner.getMage());
        return h;
    }
    
    
     public void bot()
    { 
        boolean attacked = false;
        boolean loop = true;
        boolean run = false;
        while(loop)
        {
            Location moveLoc = null;
            attacked = false;
            moveLocs.clear();
            attackLocs.clear();
            getAttackLocs();
            calculateMoveLocs(location,actionPoints);
            moveLocs.add(location);
            
            //attack
            if(attackLocs.size()>0)
            {
                //attack lowest helth
                Location attackLoc = attackLocs.get(0);
                for(Location loc : attackLocs)
                {
                    if(GameRunner.containsEnemyUnit(loc).helth<GameRunner.containsEnemyUnit(attackLoc).helth && 0<GameRunner.containsEnemyUnit(loc).helth)
                    {
                        attackLoc=loc;
                        
                    }
                }
                run=true;
                attacked=true;
                actionPoints-=attackActionPoints;
                actOn(attackLoc);
                moveLocs.clear();
            }
            
            //move
            int dist = 0;
            while(!(moveLoc!=null&&(moveLoc.getX()!=location.getX()||moveLoc.getY()!=location.getY()))&& dist < actionPoints/2)
            {
                dist++;
            for(int i = 0; i < moveLocs.size(); i++)
            {
                Location loc = moveLocs.get(i);
       
                if(location.getX()-loc.getX()>=-1*dist&&location.getX()-loc.getX()<=1*dist)
                {
                    if(location.getY()-loc.getY()>=-1*dist&&location.getY()-loc.getY()<=1*dist && (location.getX()==loc.getX()||location.getY()-loc.getY()==0||dist!=1))
                    {
                        if(!run)
                        {
                        if((moveLoc==null||PlayerBot.enemyDistance(moveLoc)>
                                PlayerBot.enemyDistance(loc)) && !GameRunner.getTile(loc).type.equals("Water") && GameRunner.isEmpty(loc))
                        {
                            moveLoc=loc;
                        }
                        }
                        else
                        {
                            if((moveLoc==null||PlayerBot.enemyDistance(moveLoc)<
                                PlayerBot.enemyDistance(loc)) && !GameRunner.getTile(loc).type.equals("Water") && GameRunner.isEmpty(loc))
                        {
                            moveLoc=loc;
                        }
                        }
                    }
                }
            }
            }
            if(moveLoc!=null&&(moveLoc.getX()!=location.getX()||moveLoc.getY()!=location.getY()))
            { move(moveLoc);  }
            else if(attacked==false)
            { loop=false; }
            }
        
    }

}
