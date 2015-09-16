/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package thelastpromise;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author 100032528
 */
public class MainMenuScreen extends JPanel {
    
    private static JFrame frame = new JFrame();
    private Image back;
    private Image playBack;
 
    private JButton play;
    private JButton tutorial;
    private JButton credits;
    private JButton highScore;
    private JButton exit;
    
    public void start()
    {
        frame.setTitle("The Last Promise");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setExtendedState(frame.MAXIMIZED_BOTH);  
        try {
            back = ImageIO.read(new File("src/background-finalword.png"));
            playBack = ImageIO.read(new File("src/button-final.png"));
            frame.setIconImage(ImageIO.read(new File("src/T-ShirtofImmortality-final.png")));
        } catch (Exception ex) {
            LogHandling.logMessage("Can't show background   " + ex);
        }
        
        frame.add(this);
        frame.setVisible(true);
        addButtions();
        frame.repaint();
    }
    
    public void addButtions()
    {
        play = new JButton("Play",new ImageIcon(playBack.getScaledInstance(frame.getWidth()/2, frame.getHeight()/16, 0)));
        play.setBorder(BorderFactory.createEmptyBorder());
        play.setContentAreaFilled(false);
        play.setHorizontalTextPosition(JButton.CENTER);
        play.setVerticalTextPosition(JButton.CENTER);
        play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu main = new MainMenu();
                                main.b1();
			}
		});
                
                
        tutorial = new JButton("Tutorial",new ImageIcon(playBack.getScaledInstance(frame.getWidth()/2, frame.getHeight()/16, 0)));
        tutorial.setBorder(BorderFactory.createEmptyBorder());
        tutorial.setContentAreaFilled(false);
        tutorial.setHorizontalTextPosition(JButton.CENTER);
        tutorial.setVerticalTextPosition(JButton.CENTER);
        tutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartGame.startTutorial();
			}
		});
                
       highScore = new JButton("High Scores",new ImageIcon(playBack.getScaledInstance(frame.getWidth()/2, frame.getHeight()/16, 0)));
       highScore.setBorder(BorderFactory.createEmptyBorder());
       highScore.setContentAreaFilled(false);
       highScore.setHorizontalTextPosition(JButton.CENTER);
       highScore.setVerticalTextPosition(JButton.CENTER);
       highScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu m = new MainMenu();
                                m.highScore();
			}
		});
                
                
       credits = new JButton("Credits",new ImageIcon(playBack.getScaledInstance(frame.getWidth()/2, frame.getHeight()/16, 0)));
       credits.setBorder(BorderFactory.createEmptyBorder());
       credits.setContentAreaFilled(false);
       credits.setHorizontalTextPosition(JButton.CENTER);
       credits.setVerticalTextPosition(JButton.CENTER);
       credits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu m = new MainMenu();
                                m.credits();
			}
		});
                
                
        exit = new JButton("Exit",new ImageIcon(playBack.getScaledInstance(frame.getWidth()/2, frame.getHeight()/16, 0)));
        exit.setBorder(BorderFactory.createEmptyBorder());
        exit.setContentAreaFilled(false);
        exit.setHorizontalTextPosition(JButton.CENTER);
        exit.setVerticalTextPosition(JButton.CENTER);
        exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu main = new MainMenu();
                                main.exit();
			}
		});
                        
                
        
        play.setBounds(frame.getWidth()/4, frame.getHeight()/4, frame.getWidth()/2, frame.getHeight()/16);
        this.setLayout(null);
        this.add(play);
        
        tutorial.setBounds(frame.getWidth()/4, frame.getHeight()/4+(4*(frame.getHeight()/16)), frame.getWidth()/2, frame.getHeight()/16);
        this.setLayout(null);
        this.add(tutorial);
        
        highScore.setBounds(frame.getWidth()/4, frame.getHeight()/4+(6*(frame.getHeight()/16)), frame.getWidth()/2, frame.getHeight()/16);
        this.setLayout(null);
        this.add(highScore);
        
        credits.setBounds(frame.getWidth()/4, frame.getHeight()/4+(8*(frame.getHeight()/16)), frame.getWidth()/2, frame.getHeight()/16);
        this.setLayout(null);
        this.add(credits);
        
        exit.setBounds(frame.getWidth()/4, frame.getHeight()/4+(10*(frame.getHeight()/16)), frame.getWidth()/2, frame.getHeight()/16);
        this.setLayout(null);
        this.add(exit);
    }
    
    public void paintComponent(Graphics g)
    {
        addButtions();
        try{
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
         Graphics2D g2d = (Graphics2D)g;
         g2d.drawImage(back, 0,0,this.getWidth(),this.getHeight(),this);
        } catch(Exception e)
        {
            LogHandling.logMessage("Can't draw main menu   " + e);
        }
    }
    
    public void close()
    {
        frame.setVisible(false);
        frame.dispose();
    }
}
