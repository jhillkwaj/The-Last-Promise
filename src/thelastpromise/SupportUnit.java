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
 *  This class draws the SupportUnit at the specifed location
 */
public class SupportUnit extends Unit {
    protected boolean heal;
    protected boolean build;
    protected boolean mine;
    protected int mineAp;
    protected int mineGold;
    private ArrayList<Location> mineLocs = new ArrayList<Location>();
    
    public SupportUnit(String name,Location loc, Image texture)
    {
        super(name,loc, texture);
        type="Support Unit";
    }
    
    //moveing and mining
    public void move(Location loc)
    {
        super.move(loc);
        mineLocs.clear();
        getMineLocs();
        for(int i = 0; i < mineLocs.size(); i++)
        {
            //mine
            mine(loc);
        }
    }
    
    
    private void mine(Location loc)
    {
        if(mine&&actionPoints>=mineAp)
        {
            for(int i = 0; i < mineLocs.size(); i++)
            {
                //mine
                if(mineLocs.get(i).equals(loc))
                {
                    GameRunner.getPlayerisTurn().addGold(mineGold);
                    mineLocs.clear();
                    actionPoints-=mineAp;
                    return;
                }
           }
       }
    }
    
    
    public void levelUp()
    {
        level++;
        mineGold+=5;
        if(level==0)
        { levelName = ""; }
        else if(level == 1)
        { levelName = "Productive "; }
        else if(level==2)
        { levelName = "Superior "; }
        else if(level == 3)
        { levelName = "Experienced "; }
        else if(level == 4)
        { levelName = "Mighty "; }
        else if(level == 5)
        { levelName = "Revered "; }
        else if(level == 6)
        { levelName = "Legendary "; }
        else if(level == 7)
        { levelName = "Mythic "; }
        else if(name !="Athena" && name != "Hephaestus" && name != "Zeus")
        { 
            name = "Hephaestus";
            level=0;
            levelName="";
        }
        else if(name=="Hephaestus")
        {
            name = "Athena";
            level=0;
            levelName="";
        }
        else if(name=="Athena")
        {
            name = "Zeus";
            level=0;
            levelName="";
        }
        
    }
    
    
    
    public void getMineLocs()
    {
        if(mine&&actionPoints>=mineAp)
        {
            for(int x = location.getX()-1; x<=location.getX()+1;x++)
            {
                if(x>=0 && x<=GameRunner.lastTile().location.getX())
                {
                for(int y = location.getY() -1; y<=location.getY()+1; y++)
                {
                    if(y>=0 && (y <=GameRunner.lastTile().location.getY()))
                    {
                    if(GameRunner.getTile(new Location(x,y)).type.equals("Gold Mine"))
                    {
                        mineLocs.add(new Location(x,y));
                    }
                    }
                }
                }
            }
        }
    }
    
     public void highlight(Graphics g)
     {
        super.highlight(g);
        if(selected == true)
        {
            mineLocs.clear();
            getMineLocs();
            Graphics2D g2d = (Graphics2D)g;
            for(Location location : mineLocs)
            {
                g2d.drawImage(GameRunner.getyGreen(),location.getExactX() -
                    GameRunner.getView().getExactX() + 20,location.getExactY()
                    - GameRunner.getView().getExactY() + 40, 164,164,null);
            }
        }
        
    }
     
     public ArrayList<String> save() 
     {
         ArrayList<String> data = super.save();
         data.add(""+heal);
         data.add(""+build);
         data.add(""+mine);
         data.add(""+mineGold);
         return data;
     }
     
     
    public static Unit load(ArrayList<String> data)
    {
        SupportUnit u = null;
        if(data.get(1).equals("Spirit"))
        { u = (SupportUnit)MagicSpirit.load(data); }
        else if(data.get(1).equals("Worker"))
        { u = (SupportUnit)WorkerUnit.load(data); }
        u.heal=Boolean.parseBoolean(data.get(11));
        u.build=Boolean.parseBoolean(data.get(12));
        u.mine=Boolean.parseBoolean(data.get(13));
        u.mineGold=Integer.parseInt(data.get(14));
        return u;
    }
    
    public void bot()
    {
        boolean loop = true;
        while(loop)
        {
            Location moveLoc = null;
            moveLocs.clear();
            mineLocs.clear();
            getMineLocs();
            calculateMoveLocs(location,actionPoints);
            moveLocs.add(location);
            
            //mine
            if(mineLocs.size()>0)
            {
                //mine
                mine(mineLocs.get(0));
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
                        if((moveLoc==null||PlayerBot.mineDistance(moveLoc)
                                >PlayerBot.mineDistance(loc))&&!GameRunner.getTile(loc).type.equals("Water") && GameRunner.isEmpty(loc))
                        {
                            moveLoc=loc;
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
    
    public void autoMine()
    {
        if(mine)
        {
            getMineLocs();
            if(mineLocs.size()>0)
            {
                //mine
                mine(mineLocs.get(0));
            }
        }
    }
    
    
    public int getAutoMineGold()
    {
        int gold = 0;
        if(mine)
        {
            getMineLocs();
            if(mineLocs.size()>0)
            {
                gold+=mineGold;
            }
        } 
        return gold;
    }
   
}
