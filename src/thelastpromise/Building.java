/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package thelastpromise;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 * The class extends the Object, draws the graphics, and opens the openBuildMenu
 */
public class Building extends Unit {
    
    protected ArrayList<Image> buildImages = new ArrayList<Image>();
    protected ArrayList<Integer> costs = new ArrayList<Integer>();
    protected ArrayList<String> buildNames = new ArrayList<String>();
    
    public Building(String name,Location loc, Image texture)
    {
        super(name,loc, texture);
        levelName = "Level 1 ";
        type = "Building";
    }
    
    @Override
    public void levelUp()
    {   
        if(GameRunner.containsEnemyUnit(location)==null)
        {
            if(GameRunner.getPlayerisTurn()!=null)
            { level=GameRunner.getPlayerisTurn().getLevel(); }
            else
            { level=level+1; }
            levelName = "Level " + level + " ";
        }
    }
    
    public void build(int build)
    { }
    
    @Override
    public void act()
    { levelUp(); }
    
    @Override
    public void calculateMoveLocs(Location location, int AP)
    { }
    
    @Override
    public void highlight(Graphics g)
    { }

    @Override
    public void select()
    { 
        selected=!selected; 
        if(selected){BuildMenu b = new BuildMenu(buildImages,costs,buildNames,this); }
    }
    
    @Override
    public void move(Location loc)
    { }

    public Location addLoc(Location loc,boolean water, boolean forest)
    {
        Location closest = new Location(-9999,-9999);
        for(int i = 0; i <= GameRunner.lastTile().location.getX();i++)
        {
            for(int j = 0; j <= GameRunner.lastTile().location.getY();j++)
            {
                if(GameRunner.isEmpty(new Location(i,j)))
                {
                    if(water==true || !GameRunner.getTile(new Location(i,j)).type.equals("Water"))
                    {
                        if(forest==true || !GameRunner.getTile(new Location(i,j)).type.equals("Forest"))
                        {
                            if(!GameRunner.getTile(new Location(i,j)).type.equals("Gold Mine"))
                            {
                            if(Math.sqrt(Math.pow(loc.getX()-i, 2) + 
                                    Math.pow(loc.getY()-j,2)) <
                                    Math.sqrt(Math.pow(loc.getX()-closest.getX()
                                    , 2) + Math.pow(loc.getY()-closest.getY(),2)))
                            {
                                closest = new Location(i,j);
                            }
                            }
                        }
                    }
                }
            }
        }
        return closest;
    }
    

    public ArrayList<String> save() 
     {  return super.save(); }
    
    public static Unit load(ArrayList<String> data)
    {
        Unit u = null;
        if(data.get(1).equals("Castle"))
        { u = Castle.load(data); }
        else if(data.get(1).equals("Mages Tower"))
        { u = MagesTower.load(data); }
        else if(data.get(1).equals("Summoner"))
        { u = Summoner.load(data); }
        u.level=Integer.parseInt(data.get(8))-1;
        u.levelUp();
        return u;
    }
    
    
    public void bot()
    {
        PlayerBot bot = (PlayerBot)GameRunner.getPlayerisTurn();
        bot.setUnitNums();
        if(bot.getUnits().size()<bot.level*bot.getPopulation())
        {
           botBuild((int)Math.ceil((bot.level*bot.getPopulation() - bot.getUnits().size())/bot.getUnitNums()[2]));
        }
        botLevel();
    }
    
    public void botBuild(int max)
    {}
    
    public void botLevel()
    {
        if(!firstName.equals("Summoner")&&GameRunner.getPlayerisTurn().gold >= costs.get(2))
        {
            GameRunner.getPlayerisTurn().levelUp();
            levelUp();
            bot();
        }
    }
}
