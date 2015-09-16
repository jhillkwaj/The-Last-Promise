/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thelastpromise;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author justi_000
 */
public class Dialogue extends JPanel {
    public BuildMenuFrame frame = new BuildMenuFrame(false);
    private int optionsNum = 4;
    private String[] lines;
    private Image image;
    public Dialogue(Image speaker, String[] lines)
    {
        try
        {
        //name
        frame.setTitle(lines[0]);
        
        //size
        this.setPreferredSize(new Dimension(800, lines.length*20));
        
        //location
        frame.setLocation((int)((GameRunner.getWidthHeight().getExactX()/2)-400),(int)((GameRunner.getWidthHeight().getExactY()/2)-lines.length*20));
        
        this.image=speaker;
        this.lines=lines;
        frame.add(this);
        frame.setVisible(true);
        frame.pack();
        frame.repaint();
        }
        catch(Exception e)
        { LogHandling.logError("Can't show message..."+e);}
    }
    
    
    public void paintComponent(Graphics g)
    {
        try{
        //set background
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        //Draw Image
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(image, 0,0, 50,50,null);
        
        //draw text under image
        g2d.setColor(Color.BLACK);
        g.setFont(new Font("Monotype Corsiva", Font.PLAIN,15));
        g.drawString(lines[1], 0, 60);
        
        //draw lines of text
        g.setFont(new Font("Arial", Font.PLAIN,20));
        for(int i = 2; i < lines.length; i++)
        {
            if(lines[i].contains("red"))
            { 
              g2d.setColor(Color.red); 
              g.drawString(lines[i].replaceFirst("red", ""), 70, 25+((i-2)*20));
            }
            else if(lines[i].contains("blue"))
            { 
                g2d.setColor(Color.BLUE); 
                g.drawString(lines[i].replaceFirst("blue", ""), 70, 25+((i-2)*20));
            }
            else
            { 
                g2d.setColor(Color.BLACK);
                g.drawString(lines[i], 70, 25+((i-2)*20));
            } 
            
        }
        } catch(Exception e)
        {
            LogHandling.logMessage("Can't draw main menu   " + e);
        }
    }
}
