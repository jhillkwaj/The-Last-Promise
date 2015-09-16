/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thelastpromise;

import java.awt.Image;
import java.util.ArrayList;

/**
 * This class, Horseman, extends the AttackUnit. This 
 * sets the basic values and operations of the Horseman. 
 * This also places the location of the Horseman while the sound,
 * "src/bang/bang4.wav", is being played 
 */
public class Horseman extends AttackUnit {
    /**
     * 
     *  loc is received as a Location for the Horseman to move 
     * texture is also received for the image of the Horseman, 
     * to be implemented. 
     * This method sets the basic values and operations of the Horseman, 
     * such as speed, the health, and the points it is allowed for attacking
     * moving, and damage
     */
    public Horseman(Location loc, Image texture)
    {
    super("Horseman",loc,texture);
    speed = 20;
    maxActionPoints = 16;
    actionPoints = 16;
    helth = 40;
    maxHelth = 30;
    attackActionPoints = 5;
    attackRange = 1.0f;
    waterUnit = 0;
    forestUnit = true;
    maxDamage = 10;
    minDamage = 2;
    faceImage = "In-game Faces/horseman_face-final";
    }
    
    public void levelUp()
    {
        int lastLev = level;
        super.levelUp();
        if(level>lastLev)
        {
            maxActionPoints+=1;
            actionPoints+=1;
        }
    }
    
    //This attacks the unit at loc
    public void actOn(Location loc)
    {
         super.actOn(loc);
         Sound.playSound("src/bang/bang4.wav");
    }
    
    public static Unit load(ArrayList<String> data)
    {
        Horseman h = new Horseman(new Location(Integer.parseInt(data.get(4)),Integer.parseInt(data.get(5))),GameRunner.getHorseman());
        return h;
    }
    
    
    public void calculateMoveLocs(Location location, int AP)
    {
        //spots to the East
        Location newLoc = new Location(location.getX() + 1, location.getY());
        if(newLoc.getX() <= GameRunner.lastTile().location.getX() &&
                GameRunner.isEmpty(newLoc) && (!GameRunner.getTile(newLoc).
                type.equals("Water") || waterUnit!=0) && (!GameRunner.getTile
                (newLoc).type.equals("Forest") || forestUnit!=false))
        {
            int newAp = AP - GameRunner.getTile(newLoc).resistance;
            
            if(GameRunner.getTile(newLoc).type.equals("Forest"))
            { newAp = AP - (GameRunner.getTile(newLoc).resistance*3); }
            
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
        if(newLoc.getX() >= 0 && GameRunner.isEmpty(newLoc)&& (!GameRunner.getTile(newLoc).
                type.equals("Water") || waterUnit!=0) && (!GameRunner.getTile
                (newLoc).type.equals("Forest") || forestUnit!=false))
        {
            int newAp = AP - GameRunner.getTile(newLoc).resistance;
            
            if(GameRunner.getTile(newLoc).type.equals("Forest"))
            { newAp = AP - (GameRunner.getTile(newLoc).resistance*3); }
            
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
        if(newLoc.getY() <= GameRunner.lastTile().location.getY() && GameRunner.isEmpty(newLoc)&& (!GameRunner.getTile(newLoc).
                type.equals("Water") || waterUnit!=0) && (!GameRunner.getTile
                (newLoc).type.equals("Forest") || forestUnit!=false))
        {
            int newAp = AP - GameRunner.getTile(newLoc).resistance;
            
            if(GameRunner.getTile(newLoc).type.equals("Forest"))
            { newAp = AP - (GameRunner.getTile(newLoc).resistance*3); }
            
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
        if(newLoc.getY() >= 0 && GameRunner.isEmpty(newLoc)&& (!GameRunner.getTile(newLoc).
                type.equals("Water") || waterUnit!=0) && (!GameRunner.getTile
                (newLoc).type.equals("Forest") || forestUnit!=false))
        {
            int newAp = AP - GameRunner.getTile(newLoc).resistance;
            
            if(GameRunner.getTile(newLoc).type.equals("Forest"))
            { newAp = AP - (GameRunner.getTile(newLoc).resistance*3); }
            
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
    
}
