/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package thelastpromise;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 * This Object class passes an image and sets the coordinates for it. 
 * 
 */
public abstract class Object {
    protected Location location;
    protected Image image;
    protected int sizeX;
    protected int sizeY;
    protected String type;
   
    /**
     * 
     *  g is passed for the graphics and the images, presented.
     */
    public abstract void draw(Graphics g);
    
    public abstract ArrayList<String> save();
    
}
