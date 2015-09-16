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
public class WorkerUnit extends SupportUnit {
    public WorkerUnit(Location loc, Image texture)
    {
        super("Worker",loc,texture);
        speed = 8;
        maxActionPoints = 10;
        actionPoints = 10;
        mineAp=10;
        mineGold=10;
        helth = 10;
        maxHelth = 10;
        waterUnit = 0;
        forestUnit = true;
        heal=false;
        build=true;
        mine=true;
    }
    
    public static Unit load(ArrayList<String> data)
    {
        WorkerUnit m = new WorkerUnit (new Location(Integer.parseInt(data.get(4)),Integer.parseInt(data.get(5))),GameRunner.getWorker());
        return m;
    }
}
