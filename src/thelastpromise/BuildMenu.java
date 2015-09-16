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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author justi_000
 */
public class BuildMenu extends JPanel {
    public BuildMenuFrame frame = new BuildMenuFrame();
    private static Building builder;
    ArrayList<Integer> costs;
    ArrayList<String> names;
    
    public BuildMenu(ArrayList<Image> buildIcons, ArrayList<Integer> costs , ArrayList<String> name,Building builder)
    {
        this.costs = costs;
        this.builder = builder;
        this.names = name;
        
        //name
        frame.setTitle("Build Menu");
        
        //size
        this.setPreferredSize(new Dimension((buildIcons.size()*100)+20, 120));
        
        //location
        frame.setLocation((int)((GameRunner.getWidthHeight().getExactX()/2)-(((buildIcons.size()*100)+20))/2),(int)(GameRunner.getWidthHeight().getExactY()/2)-60);
        
        int num = 0;
        for(Image i : buildIcons)
        {
            final int n = num;
            JButton b = new JButton("",new ImageIcon(i.getScaledInstance(80, 80, 0)));
            b.setToolTipText(name.get(num));
            b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(n);
                                frame.dispose();
			}
		});
            b.setBounds(20+(num*100), 10, 80, 80);
            this.setLayout(null);
            this.add(b);
            
            num++;
        }
        frame.add(this);
        frame.setVisible(true);
        frame.pack();
        frame.repaint();
    }
    
    public void paintComponent(Graphics g)
    {
        try{
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        for(int i = 0; i < costs.size(); i++)
        {
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawImage(GameRunner.getGold(), 15+(100*i),90, 30,30,null);
            if(GameRunner.getPlayerisTurn().getGold()<costs.get(i))
            { g2d.setColor(Color.red); }
            else
            { g2d.setColor(new Color(255,191,0)); }
            
            g.setFont(new Font("Monotype Corsiva", Font.BOLD,30));
            g2d.drawString(""+GameRunner.writeGold(costs.get(i)), 45+(100*i), 115);
            g.setFont(new Font("Arial", Font.BOLD,10));
             g2d.setColor(Color.BLACK);
            g2d.drawString(names.get(i).split(" ")[1], 55-(2*names.get(i).split(" ")[1].length())+(100*i), 8);
        }
        } catch(Exception e)
        {
            LogHandling.logMessage("Can't draw main menu   " + e);
        }
    }
    
    
    private void click(int button)
    {
        this.removeAll();
        builder.select();
        builder.build(button);
    }
    
    public static Building getLastBuilder()
    { return builder; }

}
