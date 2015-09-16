/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thelastpromise;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

/**
 *
 * @author justi_000
 */
public class Arrow extends AttackEffect {
    private boolean hit;
    private double ang;
    public Arrow(Location loc, Location hitLoc, Image im, boolean hit)
    {
        super(loc,hitLoc,im);
        speed=40;
        this.hit = hit;
        if(hit==false)
        {
            Location newLoc=new Location(location.getX(),location.getY());
            for(int i = 0; i < GameRunner.lastTile().location.getX(); i++)
            {
                for(int j = 0; j < GameRunner.lastTile().location.getY(); j++)
                {
                    //if not hitLoc
                    if(moveLoc.getX()!=i||moveLoc.getY()!=j)
                    {
                        //if empty
                        if(GameRunner.isEmpty(new Location(i,j)))
                        {
                            //if closer to hitLoc htan newLoc
                            if(Math.sqrt(Math.pow(i-hitLoc.getX(),2)+Math.pow(j-hitLoc.getY(),2))<Math.sqrt(Math.pow(newLoc.getX()-hitLoc.getX(),2)+Math.pow(newLoc.getY()-hitLoc.getY(),2)))
                            {
                                newLoc=new Location(i,j);
                            }
                            else if(Math.sqrt(Math.pow(i-hitLoc.getX(),2)+Math.pow(j-hitLoc.getY(),2))==Math.sqrt(Math.pow(newLoc.getX()-hitLoc.getX(),2)+Math.pow(newLoc.getY()-hitLoc.getY(),2))&&Math.random()<=.3f)
                            {
                                newLoc=new Location(i,j);
                            }
                        }
                    }
                }
            }
            moveLoc=newLoc;
        }
        ang=Math.atan((moveLoc.getExactY()-location.getExactY())/(moveLoc.getExactX()-location.getExactX() + .001));
    }

    
    public void act()
    {
        super.act();
        if(lastAct)
        {
            if(hit)
            {
                Sound.playSound("src/bang/bang4.wav");
            }
        }
    }
    
    @Override
    public void draw(Graphics g) {
        if(lastAct==false)
        {
            act();
            Graphics2D g2d = (Graphics2D)g;  
            AffineTransform origXform = g2d.getTransform();
	     	AffineTransform newXform = (AffineTransform)(origXform.clone());
	     	//center of rotation is center of the image
	     	int xRot = location.getExactX()-GameRunner.getView().getExactX() + 82;
	     	int yRot = location.getExactY()-GameRunner.getView().getExactY() + 82;
	     	newXform.rotate(ang, xRot, yRot);
	     	g2d.setTransform(newXform);
	     
            
            g2d.drawImage(image, location.getExactX() - 
                    GameRunner.getView().getExactX() + 20 + 32,location.getExactY()
                    - GameRunner.getView().getExactY() + 40 + 32, 100,100,null);
            
            g2d.setTransform(origXform);
            
            
        }
    }
}
