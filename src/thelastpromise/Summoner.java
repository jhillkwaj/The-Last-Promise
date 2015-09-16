/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thelastpromise;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author justi_000
 */
public class Summoner extends Building {
    private int summonAP = 5;
    public Summoner(Location loc, Image texture)
    {
        super("Summoner",loc,texture);
        speed = 7;
        helth=10;
        maxHelth=10;
        actionPoints=3;
        maxActionPoints=3;
        waterUnit = 0;
        forestUnit = true;
    }
    
    
    
    
    public void levelUp()
    {
        //pass -1 to toLevel for players level
        try
        {
        int lastLevel = level;
        super.levelUp();
        
        //set autoMine gold
        if(lastLevel != level)
        {
        helth=(10*level)-(maxHelth-helth);
        maxHelth = 10*level;
        actionPoints=((level*2)+1)-(maxActionPoints-actionPoints);
        maxActionPoints=(level*2)+1;
        
        buildImages.clear();
        costs.clear();
        buildNames.clear();
        
            
        if(level>=2)
        {
            MagicSpirit m = new MagicSpirit(null, null);
            m.level=level-2;
            m.levelUp();
            buildImages.add(GameRunner.getMagicSpirit());
            costs.add(50);
            buildNames.add("Train " + m.levelName+m.name);
            
            Butcher b = new Butcher(null, null);
            b.level=level-3;
            b.levelUp();
            buildImages.add(GameRunner.getButcher());
            costs.add(175);
            buildNames.add("Train " + b.levelName+b.name);
            
            FireSpirit f = new FireSpirit(null, null);
            b.level=level-3;
            b.levelUp();
            buildImages.add(GameRunner.getFireSpirit());
            costs.add(125);
            buildNames.add("Train " + f.levelName+f.name);
        }
        if(level>=2)
        {
        }
        if(level>=3)
        {
        }
        
        }
        }
        catch(Exception e)
        {
            LogHandling.logError("Can't level up castle..." + e);
        }
        
    }
    
    public void build(int build)
    {
        try
        {
        if(GameRunner.getPlayerisTurn().getGold()-costs.get(build)>=0&&actionPoints>=summonAP)
        {
            GameRunner.getPlayerisTurn().addGold((-1)*costs.get(build));
            actionPoints-=summonAP;
        if(build == 0)
        {
            MagicSpirit s = new MagicSpirit(addLoc(location,false,true), GameRunner.getMagicSpirit());
            for(int i = 0; i < level-1; i++)
            {
                s.levelUp();
            }
            GameRunner.getPlayerisTurn().addUnit(s);
        }
        if(build == 1)
        {
            Butcher b = new Butcher(addLoc(location,false,true), GameRunner.getButcher());
            for(int i = 0; i < level-2; i++)
            {
                b.levelUp();
            }
            GameRunner.getPlayerisTurn().addUnit(b);
        }
        else if(build == 2)
        {
            FireSpirit f = new FireSpirit(addLoc(location,false,true), GameRunner.getFireSpirit());
            for(int i = 0; i < level-2; i++)
            {
                f.levelUp();
            }
            GameRunner.getPlayerisTurn().addUnit(f);
        }
        else if(build == 3)
        {
        }
        else if(build == 4)
        {
        }
        else if(build == 5)
        {
        }
        }
        }
        catch(Exception e)
        {
            LogHandling.logError("Can't add unit   " + e);
        }
    }
    
    

      
    @Override
    public void act()
    {   
        int tempX = Location.getXofExactX(location.getExactX() + 100);
        int tempY = Location.getYofExactY(location.getExactY() + 100);
        if(Math.sqrt(Math.pow(location.getExactX()-moveLoc.getExactX(), 2) + Math.pow(location.getExactY()-moveLoc.getExactY(), 2))>speed)
        {
            selected = false;
            double ang = Math.atan((moveLoc.getExactY()-location.getExactY())/(moveLoc.getExactX()-location.getExactX() + .001));
            if(moveLoc.getExactX() < location.getExactX())
            { ang+=Math.PI; }
            location.setOnlyExactX(location.getExactX() +(int)(Math.cos(ang) * speed));
            location.setOnlyExactY(location.getExactY() +(int)(Math.sin(ang) * speed));
            lastAct=true;
        }
        else
        {
            location = moveLoc;
            if(lastAct==true)
            {
                lastAct=false;
            }
        }
        
        if(tempX != Location.getXofExactX(location.getExactX() + 100) || tempY != Location.getYofExactY(location.getExactY() + 100))
        {
           Sound.playSound(GameRunner.getTile(new Location(Location.getXofExactX(location.getExactX() + 100),Location.getYofExactY(location.getExactY() + 100))).getSound());
        }
        levelUp(); 
    }
    
    
    
    
    
    public void calculateMoveLocs(Location location, int AP)
    {
        //spots to the East
        Location newLoc = new Location(location.getX() + 1, location.getY());
        if(newLoc.getX() <= GameRunner.lastTile().location.getX() &&
                GameRunner.containsEnemyUnit(newLoc)==null && (!GameRunner.getTile(newLoc).
                type.equals("Water") || waterUnit!=0) && (!GameRunner.getTile
                (newLoc).type.equals("Forest") || forestUnit!=false))
        {
            int newAp = AP - GameRunner.getTile(newLoc).resistance;
            int spot = 0;
            for(int i = 0; i < moveLocs.size(); i++)
            {
                if(moveLocs.get(i).equals(newLoc))
                {   spot = i;  }
            }
            if(spot == 0)
            {
                if(newAp >= 0)
                {
                    moveLocs.add(newLoc);
                    moveLocNums.add(newAp);
                    calculateMoveLocs(newLoc,newAp);
                }
            }
            else if(newAp > moveLocNums.get(spot))
            {
                 moveLocNums.set(spot,newAp);
                 calculateMoveLocs(newLoc,newAp);
            }
        }
        
        //spots to the West
        newLoc = new Location(location.getX() - 1, location.getY());
        if(newLoc.getX() >= 0 && GameRunner.containsEnemyUnit(newLoc)==null&& (!GameRunner.getTile(newLoc).
                type.equals("Water") || waterUnit!=0) && (!GameRunner.getTile
                (newLoc).type.equals("Forest") || forestUnit!=false))
        {
            int newAp = AP - GameRunner.getTile(newLoc).resistance;
            int spot = 0;
            for(int i = 0; i < moveLocs.size(); i++)
            {
                if(moveLocs.get(i).equals(newLoc))
                {   spot = i;  }
            }
            if(spot == 0)
            {
                if(newAp >= 0)
                {
                    moveLocs.add(newLoc);
                    moveLocNums.add(newAp);
                    calculateMoveLocs(newLoc,newAp);
                }
            }
            else if(newAp > moveLocNums.get(spot))
            {
                 moveLocNums.set(spot,newAp);
                 calculateMoveLocs(newLoc,newAp);
            }
            
        }
        
        
        
        //spots to the North
        newLoc = new Location(location.getX(), location.getY() + 1);
        if(newLoc.getY() <= GameRunner.lastTile().location.getY() && GameRunner.containsEnemyUnit(newLoc)==null && (!GameRunner.getTile(newLoc).
                type.equals("Water") || waterUnit!=0) && (!GameRunner.getTile
                (newLoc).type.equals("Forest") || forestUnit!=false))
        {
            int newAp = AP - GameRunner.getTile(newLoc).resistance;
            int spot = 0;
            for(int i = 0; i < moveLocs.size(); i++)
            {
                if(moveLocs.get(i).equals(newLoc))
                {   spot = i;  }
            }
            if(spot == 0)
            {
                if(newAp >= 0)
                {
                    moveLocs.add(newLoc);
                    moveLocNums.add(newAp);
                    calculateMoveLocs(newLoc,newAp);
                }
            }
            else if(newAp > moveLocNums.get(spot))
            {
                 moveLocNums.set(spot,newAp);
                 calculateMoveLocs(newLoc,newAp);
            }
        }
        
        //spots to the South
        newLoc = new Location(location.getX(), location.getY() - 1);
        if(newLoc.getY() >= 0 && GameRunner.containsEnemyUnit(newLoc)==null && (!GameRunner.getTile(newLoc).
                type.equals("Water") || waterUnit!=0) && (!GameRunner.getTile
                (newLoc).type.equals("Forest") || forestUnit!=false))
        {
            int newAp = AP - GameRunner.getTile(newLoc).resistance;
            int spot = 0;
            for(int i = 0; i < moveLocs.size(); i++)
            {
                if(moveLocs.get(i).equals(newLoc))
                {   spot = i;  }
            }
            if(spot == 0)
            {
                if(newAp >= 0)
                {
                    moveLocs.add(newLoc);
                    moveLocNums.add(newAp);
                    calculateMoveLocs(newLoc,newAp);
                }
            }
            else if(newAp > moveLocNums.get(spot))
            {
                 moveLocNums.set(spot,newAp);
                 calculateMoveLocs(newLoc,newAp);
            }
            
        }
        

    }
    
    
    
    
    public void select()
    {
        selected=!selected;
        if(selected)
        {
            if(actionPoints>=summonAP)
            {BuildMenu b = new BuildMenu(buildImages,costs,buildNames,this);}
            moveLocs.clear();
            moveLocNums.clear();
            calculateMoveLocs(location,actionPoints);
        }
        else
        {
            moveLocs.clear();
            moveLocNums.clear();
        }
    }
    
    
    
    
    public void highlight(Graphics g)
    {
        if(selected == true)
        {
            moveLocs.clear();
            moveLocNums.clear();
           calculateMoveLocs(location,actionPoints);
           if(waterUnit == 1 || waterUnit == 0)
           {
              for(int i = 0; i < moveLocs.size();i++)
              {
                 if(GameRunner.getTile(moveLocs.get(i)).type.equals("Water") || !GameRunner.isEmpty(moveLocs.get(i)))
                 {
                     moveLocs.remove(i);
                     moveLocNums.remove(i);
                     i--;
                 }
              }
           }
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawImage(GameRunner.getyYellow(),location.getExactX() - GameRunner.getView().getExactX() + 20,
                 location.getExactY() - GameRunner.getView().getExactY() + 40, 164,164,
                 null);
            for(Location location : moveLocs)
            {
                g2d.drawImage(GameRunner.getBlue(),location.getExactX() - GameRunner.getView().getExactX() + 20,
                 location.getExactY() - GameRunner.getView().getExactY() + 40, 164,164,
                 null);
            }
        }
    }
    
    
    
    
    public void move(Location loc)
    {
        moveLocs.clear();
        moveLocNums.clear();
        calculateMoveLocs(location,actionPoints);
        if(waterUnit == 1 || waterUnit == 0)
        {
            for(int i = 0; i < moveLocs.size();i++)
            {
                 if(GameRunner.getTile(moveLocs.get(i)).type.equals("Water") || !GameRunner.isEmpty(moveLocs.get(i)))
                 {
                     moveLocs.remove(i);
                     moveLocNums.remove(i);
                     i--;
                 }
            }
        }

        for(int i = 0; i < moveLocs.size(); i++)
        {
            if(moveLocs.get(i).equals(loc))
            {
                moveLoc = loc;
                actionPoints = moveLocNums.get(i);
                moveLocs.clear();
                moveLocNums.clear();
                location.setX(moveLoc.getX());
                location.setY(moveLoc.getY());
                return;
            }
        }

        
    }
    
    
    public static Unit load(ArrayList<String> data)
    {
        Summoner s = new Summoner(new Location(Integer.parseInt(data.get(4)),Integer.parseInt(data.get(5))),GameRunner.getSummoner());
        return s;
    }
    
    
    public void bot()
    {
        super.bot();
        boolean loop = true;
        while(loop)
        {
            Location moveLoc = null;
            moveLocs.clear();
            calculateMoveLocs(location,actionPoints);
            moveLocs.add(location);
            
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
                    if((location.getY()-loc.getY()>=-1&&location.getY()-loc.getY()<=1&&location.getX()==loc.getX())||(location.getY()-loc.getY()==0))
                    {
                        if((moveLoc==null||Math.abs(5-PlayerBot.enemyDistance(moveLoc))>Math.abs(5-PlayerBot.enemyDistance(loc)))&&!GameRunner.getTile(loc).type.equals("Water")&& GameRunner.isEmpty(loc))
                        { 
                            moveLoc=loc;
                        }
                    }
                }
            }
            }
            }
            if(moveLoc!=null&&(moveLoc.getX()!=location.getX()||moveLoc.getY()!=location.getY()))
            { move(moveLoc); }
            else
            { loop=false; }
        }
    }
    
    
    @Override
    public void botBuild(int maxNum)
    {
        PlayerBot bot = (PlayerBot)GameRunner.getPlayerisTurn();
        boolean done = false;
        while(!done&&maxNum>0)
        {
            bot.setUnitNums();
            //System.out.println(""+bot.getUnitNums()[1]);
            if(((float)bot.getUnitNums()[0])/((float)bot.getUnits().size())<bot.getAggressiveness())
            {
                if(level>=2&&bot.gold>=costs.get(1) && Math.random()<.5f)    
                { build(1); }
                else if(level>=2&&bot.gold>=costs.get(2))
                { build(2); }
                else
                { done=true; }
            }
            else if(((float)bot.getUnitNums()[1])/((float)bot.getUnits().size())<(1.0f-bot.getAggressiveness()))
            {
                if(level>=2&&bot.gold>=costs.get(0))
                { build(0); }
                else
                { done=true; }
            }
            
            maxNum--;
        }
        
    }
    
    
}
