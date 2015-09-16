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
 * This class extends the Unit class and is used to send the 
 * drawing information for the AttackUnit to its parent Unit class so it can be
 * drawn, and to highlight the tiles that the AttackUnit can move to if it is
 * selected.
 */
public class AttackUnit extends Unit {
    protected ArrayList<Location> attackLocs = new ArrayList<Location>();
    protected int maxDamage;
    protected int minDamage;
    /**
     * 
     *  loc is the location for the unit being passed in to be drawn
     *  texture is a texture for the image so it can be drawn
     * This method calls the parent class of AttackUnit(Unit) and tells it to 
     * draw the unit's texture in the location it is being passed. 
     */
    public AttackUnit(String name,Location loc, Image texture)
    {
        super(name,loc, texture);
        type="Attack Unit";
    }
    
    @Override
    /**
     * This method highlights tiles the AttackUnit can move to and units they
     * can attack when selected.
     */
     public void highlight(Graphics g)
     {
        super.highlight(g);
        if(selected == true)
        {
            Graphics2D g2d = (Graphics2D)g;
            getAttackLocs();
            for(Location location : attackLocs)
            {
                g2d.drawImage(GameRunner.getRed(),location.getExactX() -
                    GameRunner.getView().getExactX() + 20,location.getExactY()
                    - GameRunner.getView().getExactY() + 40, 164,164,null);
            }
        }
        
    }
     
     public void getAttackLocs()
     {
         attackLocs.clear();
         for(int i = 0; i <= GameRunner.lastTile().location.getY(); i++)
         {
             for(int j = 0; j <= GameRunner.lastTile().location.getX(); j++)
             {
                 if(Math.sqrt(Math.pow(location.getX()-i, 2)+Math.pow(
                         location.getY()-j, 2)) <= attackRange && actionPoints
                         >=attackActionPoints)
                 {
                     if(GameRunner.containsEnemyUnit(new Location(i,j))!=null)
                     {
                         attackLocs.add(new Location(i,j));
                     }
                 }
             }
         }
     }
     
     public void move(Location loc)
    {
        super.move(loc);
        getAttackLocs();
        for(int i = 0; i < attackLocs.size(); i++)
        {
            if(attackLocs.get(i).equals(loc))
            {
                actOn(loc);
                attackLocs.clear();
                actionPoints-=attackActionPoints;
                return;
            }
        }
    }
     
     public void actOn(Location loc)
     {
         int dam = (int)((maxDamage+1-minDamage)*Math.random()) + minDamage;
         GameRunner.containsEnemyUnit(loc).attack(dam);
         if(GameRunner.containsEnemyUnit(loc).helth<=0)
         {
             levelUp();
         }
     }
     
     
     public void levelUp()
    {
        int lastLev = level;
        super.levelUp();
        if(level>lastLev)
        {
        helth+=(maxHelth/5);
        maxHelth+=(maxHelth/5);
        }
    }
     
     public ArrayList<String> save() 
     {
         ArrayList<String> data = super.save();
         data.add(""+maxDamage);
         data.add(""+minDamage);
         return data;
     }
     
     
    public static Unit load(ArrayList<String> data)
    {
        try{
        AttackUnit u = null;
        if(data.get(1).equals("Swordsman"))
        { u = (AttackUnit)Swordsman.load(data);}
        else if(data.get(1).equals("Archer"))
        { u = (AttackUnit)Archer.load(data); }
        else if(data.get(1).equals("Horseman"))
        { u = (AttackUnit)Horseman.load(data); }
        else if(data.get(1).equals("Knight"))
        { u = (AttackUnit)Knight.load(data); }
        else if(data.get(1).equals("Mage"))
        { u = (AttackUnit)Mage.load(data); }
        else if(data.get(1).equals("Arch Mage"))
        { u = (AttackUnit)ArchMage.load(data); }
        else if(data.get(1).equals("Butcher"))
        { u = (AttackUnit)Butcher.load(data); }
        else if(data.get(1).equals("Fire Spirit"))
        { u = (AttackUnit)FireSpirit.load(data); }
        u.maxDamage=Integer.parseInt(data.get(11));
        u.minDamage=Integer.parseInt(data.get(12));
        return u;
        }
        catch(Exception e)
        { LogHandling.logError("Can't load unit..."+e);
        }
        return null;
    }
    
    
    
    public void bot()
    { 
        boolean attacked = false;
        boolean loop = true;
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
                if(0<GameRunner.containsEnemyUnit(attackLoc).helth)
                {
                attacked=true;
                actionPoints-=attackActionPoints;
                actOn(attackLoc);
                moveLocs.clear();
                }
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
                        if((moveLoc==null||PlayerBot.enemyDistance(moveLoc)>
                                PlayerBot.enemyDistance(loc)) && !GameRunner.getTile(loc).type.equals("Water") && GameRunner.isEmpty(loc))
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
