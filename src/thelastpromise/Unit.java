/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package thelastpromise;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

/**
 * This Unit class extends the Object and presents the different 
 * components of the game, such as health, actionpoints, maxactionpoints
 * etc.
 * 
 */

public class Unit extends Object {
    protected String firstName;
    protected String name;
    protected int helth;
    protected int maxHelth;
    protected int actionPoints;
    protected int maxActionPoints;
    protected int attackActionPoints;
    protected double attackRange;
    protected Location moveLoc;
    protected int speed;
    protected Boolean selected = false;
    //for waterUnit 0=can't move on, 1 = can move but can't stop
    protected int waterUnit;
    protected Boolean forestUnit;
    protected ArrayList<Location> moveLocs = new ArrayList<Location>();
    protected ArrayList<Integer> moveLocNums = new ArrayList<Integer>();
    protected boolean lastAct;
    protected int level;
    protected int realLevel;
    protected String levelName = "";
    protected String faceImage = "";
    
    
    public Unit(String name, Location loc, Image texture)
    {
        this.name = name;
        firstName=name;
        image = texture;
        location = loc;
        moveLoc = loc;
        type = "Unit";
        realLevel=level;
    }
    
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
                Sound.stopSound();
                lastAct=false;
            }
        }
        
        if(tempX != Location.getXofExactX(location.getExactX() + 100) || tempY != Location.getYofExactY(location.getExactY() + 100))
        {
           Sound.playSound(GameRunner.getTile(new Location(Location.getXofExactX(location.getExactX() + 100),Location.getYofExactY(location.getExactY() + 100))).getSound());
        }
    }
    
    public void draw(Graphics g) {
        
        act();
        if(location.getExactX() + 160 > GameRunner.getViewLoc().getExactX() && location.getExactX() < GameRunner.getViewLoc().getExactX() + GameRunner.getViewLoc().getX())
        {
            if(location.getExactY() + 160 > GameRunner.getViewLoc().getExactY() && location.getExactY() < GameRunner.getViewLoc().getExactY() + GameRunner.getViewLoc().getY())
            {
        if((GameRunner.containsEnemyUnit(location)!=null||(actionPoints>=2)||
                (type.equals("Building")&&!firstName.equals("Summoner")))||
                (location.getExactX()!=moveLoc.getExactX()||moveLoc.getExactY()
                !=location.getExactY()))
        {
        Graphics2D g2d = (Graphics2D)g;
            g2d.drawImage(image, location.getExactX() - 
                    GameRunner.getView().getExactX() + 20,location.getExactY()
                    - GameRunner.getView().getExactY() + 40, 164,164,null);
        }
        else
        {
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawImage(image, location.getExactX() - 
                    GameRunner.getView().getExactX() + 20 +32,location.getExactY()
                    - GameRunner.getView().getExactY() + 40 + 32, 100,100,null);
        }
        highlight(g);
        if(selected)
        {
            //draw gray box
            Graphics2D g2d = (Graphics2D)g;
            g2d.setColor(Color.GRAY);
            g2d.fillRoundRect(-20, GameRunner.getWidthHeight().getExactY() - 150, 320, 150,20,20);
            //draw unit info
            if(faceImage.equals("")){g2d.drawImage(image, 20, GameRunner.getWidthHeight().getExactY()-130,80,80,null);}
            else{g2d.drawImage(GameRunner.getImage(faceImage), 20, GameRunner.getWidthHeight().getExactY()-130,80,80,null);}
            g2d.setColor(Color.BLACK);
            g.setFont(new Font("Monotype Corsiva", Font.BOLD,15));
            g2d.drawString(levelName+" "+name, 20, GameRunner.getWidthHeight().getExactY()-135);
            g2d.drawString("Health:"+helth, 100, GameRunner.getWidthHeight().getExactY()-120);
            g2d.drawString("Action Points:"+actionPoints, 100, GameRunner.getWidthHeight().getExactY()-105);
            g2d.drawString("Level:"+level, 100, GameRunner.getWidthHeight().getExactY()-90);
            g2d.drawString("Max Action Points:"+maxActionPoints, 100, GameRunner.getWidthHeight().getExactY()-75);
            g2d.drawString("Action Points to Attack:"+attackActionPoints, 100, GameRunner.getWidthHeight().getExactY()-60);
        }
            }}
    }
    
    public void drawTooltip(Graphics g,Location tipCentLoc){
        GameRunner.setMouseImage("blank");
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.LIGHT_GRAY);
        if(level==0&&!type.equals("Building"))
            g2d.fillRect(tipCentLoc.getExactX()-30, tipCentLoc.getExactY()-15 , 80, 50);
        else
           g2d.fillRect(tipCentLoc.getExactX()-30, tipCentLoc.getExactY()-15 , 160, 50); 
        g2d.setColor(Color.BLACK);
        g.setFont(new Font("Monotype Corsiva", Font.BOLD,15));
        g2d.drawString(""+levelName+name, tipCentLoc.getExactX()-25, tipCentLoc.getExactY());
        g.setFont(new Font("Arial", Font.PLAIN,12));
        g2d.drawString("HP:"+helth, tipCentLoc.getExactX()-25, tipCentLoc.getExactY()+12);
        g2d.drawString("AP:"+actionPoints, tipCentLoc.getExactX()-25, tipCentLoc.getExactY()+22);
        g2d.drawString("ATAP:"+attackActionPoints, tipCentLoc.getExactX()-25, tipCentLoc.getExactY()+32);
    }
    
    public void actOn(Location loc)
    { }
    
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
    
    public void levelUp()
    {
        if(realLevel<GameRunner.getPlayerisTurn().level+5)
        {
        realLevel++;
        level++;
        if(level==0)
        { levelName = ""; }
        else if(level==1)
        { levelName = "Superior "; }
        else if(level == 2)
        { levelName = "Experienced "; }
        else if(level == 3)
        { levelName = "Veteran "; }
        else if(level == 4)
        { levelName = "Mighty "; }
        else if(level == 5)
        { levelName = "Heroic "; }
        else if(level == 6)
        { levelName = "Legendary "; }
        else if(level == 7)
        { levelName = "Mythic "; }
        else if(level == 8)
        { levelName = "Epic "; }
        else if(level == 9)
        { levelName = "Inhuman "; }
        else if(level == 10)
        { levelName = "Demonic "; }
        else if(level == 11)
        { levelName = "Immortal "; }
        else if(level == 12)
        { levelName = "Godlike "; }
        else if(name !="Higher Being" && name != "Death Harbinger" && name != "Chuck Norris" && name != "Medusa" && name != "Deity")
        { 
            name = "Death Harbinger";
            level=3;
            levelName="";
        }
        else if(name=="Death Harbinger")
        {
            name = "Medusa";
            level=3;
            levelName="";
        }
        else if(name=="Medusa")
        {
            name = "Higher Being";
            level=3;
            levelName="";
        }
        else if(name=="Higher Being")
        {
            name = "Deity";
            level=3;
            levelName="";
        }
        else if(name=="Deity")
        {
            name = "Chuck Norris";
            level = 10;
            levelName="";
        }
        }
        
    }
    
    public void newTurn()
    {
        actionPoints = maxActionPoints;
    }
    
    public void select()
    {
        selected=!selected;
        if(selected)
        {
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
    
    public void attack(int damage)
    {
        helth-=damage;
        GameRunner.setEpicNum(GameRunner.epicNum()+damage);
    }

    @Override
    public ArrayList<String> save() {
        ArrayList<String> data = new ArrayList<String>();
        data.add(name);
        data.add(firstName);
        data.add(type);
        data.add(levelName);
        data.add(""+location.getX());
        data.add(""+location.getY());
        data.add(""+helth);
        data.add(""+maxHelth);
        data.add(""+realLevel);
        data.add(""+actionPoints);
        data.add(""+maxActionPoints);
        return data;
    }
    
    public static Unit load(ArrayList<String> data)
    {
        Unit u = null;
        if(data.get(2).equals("Attack Unit"))
        { u = AttackUnit.load(data); }
        else if(data.get(2).equals("Support Unit"))
        { u = SupportUnit.load(data); }
        else if(data.get(2).equals("Building"))
        { u = Building.load(data); }
        u.name=data.get(0);
        u.firstName=data.get(1);
        u.type=data.get(2);
        u.levelName=data.get(3);
        u.helth=Integer.parseInt(data.get(6));
        u.maxHelth=Integer.parseInt(data.get(7));
        u.level=Integer.parseInt(data.get(8))-1;
        u.realLevel=Integer.parseInt(data.get(8));
        u.levelUp();
        u.actionPoints=Integer.parseInt(data.get(9));
        u.maxActionPoints=Integer.parseInt(data.get(10));
        return u;
    }
    
    public void bot()
    {
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
                        if(moveLoc==null||PlayerBot.enemyDistance(moveLoc)>
                                PlayerBot.enemyDistance(loc))
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
    

}
