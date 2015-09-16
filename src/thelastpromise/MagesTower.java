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
public class MagesTower extends Building {
    
    public MagesTower(Location loc, Image texture)
    {
        super("Mages Tower",loc,texture);
        helth = 80;
        maxHelth = 80;
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
        try
        {
        int lastLevel = level;
        super.levelUp();
        if(lastLevel != level)
        {
        helth = (int)((Math.pow(2.5,level-1))-Math.pow(2.0,level-1)+(40*level)+40) - (maxHelth-helth);
        maxHelth = (int)((Math.pow(2.5,level-1))-Math.pow(2.0,level-1)+(40*level)+40);    
        buildImages.clear();
        costs.clear();
        
            
        if(level>=1)
        {
            MagicSpirit m = new MagicSpirit(null, null);
            m.level=level-2;
            m.levelUp();
            buildImages.add(GameRunner.getMagicSpirit());
            costs.add(50);
            buildNames.add("Train " + m.firstName);
            
            Mage ma = new Mage(null, null);
            ma.level=level-2;
            ma.levelUp();
            buildImages.add(GameRunner.getMage());
            costs.add(100);
            buildNames.add("Train " + ma.firstName);
            
            buildImages.add(ImageIO.read(new File("src/+1.png")));
            costs.add((int)(Math.pow(3.4, level)+(300*level)+196.6));
            buildNames.add("Level Up to gain better units");
            
            buildImages.add(GameRunner.getAutoMine());
            costs.add(0);
            buildNames.add("Automatically mine gold with any units that can this turn");
        }
        if(level>=2)
        {
            Summoner s = new Summoner(null, null);
            buildImages.add(GameRunner.getSummoner());
            costs.add(100);
            buildNames.add("Train " +s.name +" to summon units into battle");
        }
        if(level>=3)
        {
            ArchMage am = new ArchMage(null, null);
            am.level=level-4;
            am.levelUp();
            buildImages.add(GameRunner.getArchMage());
            costs.add(300);
            buildNames.add("Train " + am.firstName);
        }
        
        }
        }
        catch(Exception e)
        {
            LogHandling.logError("Can't add image  " + e);
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
            MagicSpirit s = new MagicSpirit(addLoc(location,false,true), GameRunner.getMagicSpirit());
            for(int i = 0; i < level-1; i++)
            {
                s.levelUp();
            }
            GameRunner.getPlayerisTurn().addUnit(s);
        }
        else if(build == 1)
        {
            Mage s = new Mage(addLoc(location,false,true), GameRunner.getMage());
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
                { ((SupportUnit)u).autoMine();  } 
            }
        }
        else if(build == 4)
        {
            Summoner s = new Summoner(addLoc(location,false,true), GameRunner.getSummoner());
            GameRunner.getPlayerisTurn().addUnit(s);
        }
        else if(build == 5)
        {
            ArchMage n = new ArchMage(addLoc(location,false,true), GameRunner.getArchMage());
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
        MagesTower m = new MagesTower(new Location(Integer.parseInt(data.get(4)),Integer.parseInt(data.get(5))),GameRunner.getMageTower());
        return m;
    }
    
    public void botBuild(int maxNum)
    {
        PlayerBot bot = (PlayerBot)GameRunner.getPlayerisTurn();
        boolean done = false;
        while(!done&&maxNum>0)
        {
            bot.setUnitNums();
            if(((float)bot.getUnitNums()[2])<level)
            {
                if(level>=2&&bot.gold>=costs.get(4))
                { build(4); }
            }
            if(((float)bot.getUnitNums()[1])/((float)bot.getUnits().size())<(1.0f-bot.getAggressiveness()))
            {
                if(bot.gold>=costs.get(0))
                { build(0); }
                else
                { done=true; }
            }
            else if(((float)bot.getUnitNums()[0])/((float)bot.getUnits().size())<bot.getAggressiveness())
            {
                if(level>=2&&bot.gold>=costs.get(4)&&Math.random()<=.1f)
                { build(4); }
                if(level>=3&&bot.gold>=costs.get(5)&&Math.random()<=.7f)
                { build(5); }
                else if(bot.gold>=costs.get(1))
                { build(1); }
                else
                { done=true; }
            }
            maxNum--;
        }
        
    }
    
    
    
} 
