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
public class Archer extends AttackUnit {
    
    public Archer(Location loc, Image texture)
    {
        super("Archer",loc,texture);
        speed = 8;
        maxActionPoints = 8;
        actionPoints = 8;
        helth = 20;
        maxHelth = 20;
        attackActionPoints = 3;
        attackRange = 4.0f;
        waterUnit = 1;
        forestUnit = true;
        maxDamage = 10;
        minDamage = 1;
        faceImage = "In-game Faces/archer_face-final";
    }
    
    
    //This attacks the unit at loc
    public void actOn(Location loc)
    {

         double dist = Math.sqrt(Math.pow(location.getX()-loc.getX(),2)+Math.pow(location.getY()-loc.getY(),2));
         if((Math.random()*dist)<1.9f)
          {
         GameRunner.containsEnemyUnit(loc).attack((int)((maxDamage+1-minDamage)*Math.random()) + minDamage);
         if(GameRunner.containsEnemyUnit(loc).helth<=0)
         {
             levelUp();
         }
         GameRunner.addAttack(new Arrow(new Location(location.getX(),location.getY()),loc,GameRunner.getArrow(),true)); 
         }
         else
         {
             GameRunner.addAttack(new Arrow(new Location(location.getX(),location.getY()),loc,GameRunner.getArrow(),false)); 
         }
    }

    public static Unit load(ArrayList<String> data)
    {
        Archer a = new Archer(new Location(Integer.parseInt(data.get(4)),Integer.parseInt(data.get(5))),GameRunner.getArcher());
        return a;
    }
    
    public void levelUp()
    {
        int lastLev = level;
        super.levelUp();
        if(level>lastLev)
        {
        maxActionPoints+=1;
        actionPoints+=1;
        attackRange+=1;
        }

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
                        if((moveLoc==null||Math.abs(4-PlayerBot.enemyDistance(moveLoc))>Math.abs(4-PlayerBot.enemyDistance(loc)))&&!GameRunner.getTile(loc).type.equals("Water")&& GameRunner.isEmpty(loc))
                        {
                            moveLoc=loc;
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
