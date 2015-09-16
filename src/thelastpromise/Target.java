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
public class Target extends Building {
    private int deathNum = 0;
    public Target(Location loc, Image texture, int deathNum)
    {
        super("Target",loc,texture);
        helth = 1;
        maxHelth = 1;
        this.deathNum = deathNum;
        levelName="";
    }
    
    public void levelUp()
    {  }
    
    public void botBuild(int maxNum)
    {  }
    
    public void botLevel()
    {}
    
    public ArrayList<String> save() 
    {  
        ArrayList<String> data = new ArrayList<String>();
        data.add(""+deathNum);
        return data;
    }
    
    public static Unit load(ArrayList<String> data)
    {
        Target c = new Target(new Location(Integer.parseInt(data.get(4)),Integer.parseInt(data.get(5))),GameRunner.getTarget(), Integer.parseInt(data.get(data.size()-1)));
        return c;
    }
    
    public void attack(int damage)
    {
        StartGame.startTutorial(deathNum);
        helth=0;
        GameRunner.setEpicNum(GameRunner.epicNum()+damage);
    }
    
    
}
