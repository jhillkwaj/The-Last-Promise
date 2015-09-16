/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thelastpromise;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author justi_000
 */
public class Castle extends Building {
    
    public Castle(Location loc, Image texture)
    {
        super("Castle",loc,texture);
        helth = 100;
        maxHelth = 100;
    }
    
   @Override
    public void select()
    { 
        super.select();
        //get autoMine amount
        int mineGold=0;
        for(Unit u : GameRunner.getPlayerisTurn().getUnits())
        {
             if(u.type.equals("Support Unit"))
             { mineGold+=((SupportUnit)u).getAutoMineGold(); }
        }
        costs.set(3, -1*mineGold);
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
        helth = (int)((Math.pow(2.5,level-1))-Math.pow(2.0,level-1)+(50*level)+50) - (maxHelth-helth);
        maxHelth = (int)((Math.pow(2.5,level-1))-Math.pow(2.0,level-1)+(50*level)+50);    
        buildImages.clear();
        costs.clear();
        buildNames.clear();
        
            
        if(level>=1)
        {
            WorkerUnit w = new WorkerUnit(null, null);
            w.level=level-2;
            w.levelUp();
            buildImages.add(GameRunner.getWorker());
            costs.add(50);
            buildNames.add("Train " + w.levelName+w.name);
            
            Swordsman s = new Swordsman(null, null);
            s.level=level-2;
            s.levelUp();
            buildImages.add(GameRunner.getSwordsman());
            costs.add(100);
            buildNames.add("Train " + s.levelName+s.name);
            
            buildImages.add(ImageIO.read(new File("src/+1.png")));
            costs.add((int)(Math.pow(3.4, level)+(300*level)+196.6));
            buildNames.add("Level Up to gain better units");
            
            buildImages.add(GameRunner.getAutoMine());
            costs.add(0);
            buildNames.add("Automatically mine gold with any units that can this turn");
        }
        if(level>=2)
        {
            Horseman h = new Horseman(null, null);
            h.level=level-3;
            h.levelUp();
            buildImages.add(GameRunner.getHorseman());
            costs.add(150);
            buildNames.add("Train " + h.levelName+h.name);
            
            Archer a = new Archer(null, null);
            h.level=level-3;
            h.levelUp();
            buildImages.add(GameRunner.getArcher());
            costs.add(125);
            buildNames.add("Train " + h.levelName+a.name);
        }
        if(level>=3)
        {
            Knight k = new Knight(null, null);
            k.level=level-4;
            k.levelUp();
            buildImages.add(GameRunner.getKnight());
            costs.add(200);
            buildNames.add("Train " + k.levelName+k.name);
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
        if(GameRunner.getPlayerisTurn().getGold()-costs.get(build)>=0)
        {
        if(costs.get(build)>0)
            GameRunner.getPlayerisTurn().addGold((-1)*costs.get(build));
        if(build == 0)
        {
            WorkerUnit s = new WorkerUnit(addLoc(location,false,true), GameRunner.getWorker());
            for(int i = 0; i < level-1; i++)
            {
                s.levelUp();
            }
            GameRunner.getPlayerisTurn().addUnit(s);
        }
        if(build == 1)
        {
            Swordsman s = new Swordsman(addLoc(location,false,true), GameRunner.getSwordsman());
            for(int i = 0; i < level-1; i++)
            {
                s.levelUp();
            }
            GameRunner.getPlayerisTurn().addUnit(s);
        }
        else if(build == 2)
        {
            GameRunner.getPlayerisTurn().levelUp();
            levelUp();
        }
        else if(build == 3)
        {
            for(Unit u : GameRunner.getPlayerisTurn().getUnits())
            {
                if(u.type.equals("Support Unit"))
                { ((SupportUnit)u).autoMine(); } 
            }
        }
        else if(build == 4)
        {
            Horseman h = new Horseman(addLoc(location,false,false), GameRunner.getHorseman());
            for(int i = 0; i < level-2; i++)
            {
                h.levelUp();
            }
            GameRunner.getPlayerisTurn().addUnit(h);
        }
        else if(build == 5)
        {
            Archer a = new Archer(addLoc(location,false,false), GameRunner.getArcher());
            for(int i = 0; i < level-2; i++)
            {
                a.levelUp();
            }
            GameRunner.getPlayerisTurn().addUnit(a);
        }
        else if(build == 6)
        {
            Knight n = new Knight(addLoc(location,false,true), GameRunner.getKnight());
            for(int i = 0; i < level-3; i++)
            {
                n.levelUp();
            }
            GameRunner.getPlayerisTurn().addUnit(n);
        }
        }
        }
        catch(Exception e)
        {
            LogHandling.logError("Can't add unit   " + e);
        }
    }

    
   public static Unit load(ArrayList<String> data)
    {
        Castle c = new Castle(new Location(Integer.parseInt(data.get(4)),Integer.parseInt(data.get(5))),GameRunner.getCastle());
        return c;
    }
    
    public void botBuild(int maxNum)
    {
        PlayerBot bot = (PlayerBot)GameRunner.getPlayerisTurn();
        boolean done = false;
        while(!done&&maxNum>0)
        {
            bot.setUnitNums();
            //System.out.println(""+bot.getUnitNums()[1]);
            if(((float)bot.getUnitNums()[1])/((float)bot.getUnits().size())<(1.0f-bot.getAggressiveness()))
            {
                if(bot.gold>=costs.get(0))
                { build(0); }
                else
                { done=true; }
            }
            else if(((float)bot.getUnitNums()[0])/((float)bot.getUnits().size())<bot.getAggressiveness())
            {
                if(level>=3&&bot.gold>=costs.get(6)&&Math.random()<.5f)
                { build(6); }
                else if(level>=2&&bot.gold>=costs.get(5)&&Math.random()<.3f)
                { build(5); }
                else if(level>=2&&bot.gold>=costs.get(4)&&Math.random()<.5f)
                { build(4); }
                else if(bot.gold>=costs.get(1))
                { build(1); }
                else
                { done=true; }
            }
            maxNum--;
        }
        
    }
} 
