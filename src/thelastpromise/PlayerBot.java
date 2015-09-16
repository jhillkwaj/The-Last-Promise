/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thelastpromise;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author justi_000
 */
public class PlayerBot extends Player {
    
    private int attackUnits = 0;
    private int goldUnits = 0;
    private float aggressiveness = .5f;
    private int population = 7;
    private int buildings = 0;
    
    public PlayerBot(int gold, Boolean turn, ArrayList<Unit> units, String name, float aggressiveness, int population)
    { super(gold,turn,units,name);
      this.aggressiveness=aggressiveness;
      this.population=population;
      bot=true;}
    
    
    public PlayerBot(int gold, Boolean turn, ArrayList<Unit> units, String name, int level, float aggressiveness, int population)
    { super(gold,turn,units,name,level);
      this.aggressiveness=aggressiveness;
      this.population=population;
      bot=true;}
    
    
    public void setTurn(boolean isTurn)
    {
       super.setTurn(isTurn);
    }
    
    
    public void draw(Graphics g)
    {
        super.draw(g);
        if(turn&&!win&&(turnTime>50||!GameRunner.getBotBattle()))
       {  makeMoves(); }
    }
    
    public float getAggressiveness()
    { return aggressiveness; }
    
    public int getPopulation()
    { return population; }
    
    private void makeMoves()
    {
        setUnitNums();
        for(int i = 0; i < units.size(); i++)
        {
            Unit u = units.get(i);
            u.bot();
        }
        if(win==false){GameRunner.nextPlayer();}
        else{Sound.stopSound();}
    }
    
    public void setUnitNums()
    {
        attackUnits=0;
        goldUnits=0;
        buildings=0;
        for(int i = 0; i < units.size(); i++)
        {
            Unit u = units.get(i);
            if(u.type.equals("Attack Unit"))
            { attackUnits++; }
            else if(u.type.equals("Support Unit") && ((SupportUnit)u).mine)
            { goldUnits++; }
            else if(u.type.equals("Building"))
            { buildings++; }
        }
    }
    
    public int[] getUnitNums()
    {
        int[] nums = {attackUnits,goldUnits,buildings};
        return nums;
    }
    
    public static Unit closestEnemyUnit(Location loc)
    {
        //returns closest enemy unit to loc
        Unit closestUnit = null;
        for(Player p : GameRunner.getPlayers())
        {
            for(Unit u : p.getUnits())
            {
                if(GameRunner.containsEnemyUnit(u.location)!=null&& 0<GameRunner.containsEnemyUnit(loc).helth)
                {
                    if(closestUnit==null)
                    { closestUnit=u; }
                    else if(Math.sqrt(Math.pow(u.location.getX()-loc.getX(), 2)+
                            Math.pow(u.location.getY()-loc.getY(), 2))<
                            Math.sqrt(Math.pow(closestUnit.location.getX()
                            -loc.getX(), 2)+Math.pow(closestUnit.location.getY()
                            -loc.getY(), 2)))
                    {
                        closestUnit=u;
                    }
                }
            }
        }
        return closestUnit;
    }
    
    
    public static double enemyDistance(Location loc)
    {
        //returns the distance to the closest enemy to loc
        double distance=Double.MAX_VALUE;
        for(Player p : GameRunner.getPlayers())
        {
            for(Unit u : p.getUnits())
            {
                if(GameRunner.containsEnemyUnit(u.location)!=null && 0<GameRunner.containsEnemyUnit(u.location).helth)
                {
                    if(Math.sqrt(Math.pow(u.location.getX()-loc.getX(), 2)+
                            Math.pow(u.location.getY()-loc.getY(), 2))<distance)
                    {
                        distance=Math.sqrt(Math.pow(u.location.getX()-loc.getX()
                            ,2)+ Math.pow(u.location.getY()-loc.getY(), 2));
                    }
                }
            }
        }
        return distance;
    }
    
    
    public static double mineDistance(Location loc)
    {
        //returns the distance to the closest enemy to loc
        double distance=Double.MAX_VALUE;
        for(int i = 0; i<=GameRunner.lastTile().location.getX(); i++) 
        {
            for(int j = 0; j<=GameRunner.lastTile().location.getY(); j++) 
            {
                if(GameRunner.getTile(new Location(i,j)).type.equals("Gold Mine"))
                {
                    for(int a = i-1; a <= i+1; a++)
                    {
                        if(a<=GameRunner.lastTile().location.getX()&&a>=0)
                        {
                            for(int b = j-1; b<=j+1; b++)
                            {
                                if(b<=GameRunner.lastTile().location.getY()&&b>=0)
                                {
                                     Location newLoc = new Location(a,b);
                                     if(GameRunner.isEmpty(newLoc)&&!newLoc.equals("Water")&&!newLoc.equals("Gold Mine"))
                                     {
                                         if(Math.sqrt(Math.pow(a-loc.getX(),2)+Math.pow(b-loc.getY(),2))<distance)
                                         {
                                            distance=Math.sqrt(Math.pow(a-loc.getX(),2)+Math.pow(b-loc.getY(),2));
                                         }
                                     }
                                }
                            }  
                        }
                    }
                    
                }
             }
         }
        return distance;
    }
    
    public ArrayList<String> save()
     {
        ArrayList<String> data = new ArrayList<String>();
        data.add(name);
        data.add(""+gold);
        data.add(""+level);
        data.add(""+turn);
        data.add(""+aggressiveness);
        data.add(""+population);
        
        //save units
        data.add("*Units*");
        data.add(""+units.size());
         
        for(Unit unit : units)
        { 
           ArrayList<String> unitData = unit.save();
           for(String string : unitData)
           { data.add(string); }
           data.add("*Unit*");
        }
        
        return data;
     }
    
    
    public static Player load(ArrayList<String> data)
     {
         ArrayList<Unit> u = new ArrayList<Unit>();
         int dataNum = 0;
         for(int i = 0; i < Integer.parseInt(data.get(7)); i++)
         {
             ArrayList<String> unitData = new ArrayList<String>();
            while(dataNum+8<data.size()&&!data.get(8+dataNum).equals("*Unit*"))
            {
                unitData.add(data.get(8+dataNum));
                dataNum++;
            }
            dataNum++;
            u.add(Unit.load(unitData));
         }
         //load units
         
         PlayerBot p = new PlayerBot(Integer.parseInt(data.get(1)), 
                 Boolean.parseBoolean(data.get(3)), u, data.get(0),
                 Integer.parseInt(data.get(2)),Float.parseFloat(data.get(4)),
                 Integer.parseInt(data.get(5)));
         return p;
     }
    
    
    
    public void win()
    {  
        Sound.playSound("src/TLP 8bitloss.wav");
        win = true;
    }
    
    public void loss()
    {
        win = false;
    }
}
