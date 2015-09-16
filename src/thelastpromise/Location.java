/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package thelastpromise;

/**
 * This class has the location passed down and creates the coordinates
 * 
 */
public class Location {
    // X and Y are X,Y of the tile in the grid while exactX,Y are the location 
    //in pixles
    private int x;
    private int y;
    private int exactX;
    private int exactY;
    
    public Location(int x, int y)
    {
        this.y = y;
        this.x = x;
        exactX = x*165;
        exactY = y*165;
    }
    
    public Location(int x, int y, int exactX, int exactY)
    {
        this.y = y;
        this.x = x;
        this.exactX = exactX;
        this.exactY = exactY;
    }
    
    public int getX()
    { return x; }
    
    public int getY()
    { return y; }
    
    public int getExactX()
    { return exactX; }
    
    public int getExactY()
    { return exactY; }
    
    public void setExactY(int newY)
    { 
        exactY = newY;
        y = exactY/165;
    }
    
    public void setExactX(int newX)
    { 
        exactX = newX;
        x = exactX/165;
    }
    
    public void setX(int newX)
    { x = newX; }
            
    public void setY(int newY)
    { y = newY; }
    
    public void setOnlyExactX(int newX)
    { exactX = newX; }
    
    public void setOnlyExactY(int newY)
    { exactY = newY; }
    
    public static int getXofExactX(int x)
    {
        return x/165;
    }
    
    public static int getYofExactY(int y)
    {
        return y/165;
    }
    
    public static int getExactXofX(int x)
    {
        return x*165;
    }
    
    public static int getExactYofY(int y)
    {
        return y*165;
    }
    
    public boolean equals(Location location)
    {
        if (location.x == this.x && location.y == this.y & location.getExactX()
                == this.exactX && location.getExactY() == this.exactY)
        {
            return true;
        }
        return false;
    }

}
