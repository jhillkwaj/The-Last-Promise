/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thelastpromise;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author justi_000
 */
public class OptionsMenu extends JPanel {
    public BuildMenuFrame frame = new BuildMenuFrame(false);
    private int optionsNum = 4;
    public OptionsMenu()
    {
        //name
        frame.setTitle("Options");
        
        //size
        this.setPreferredSize(new Dimension(200, 20+(60*optionsNum)));
        
        //location
        frame.setLocation((int)((GameRunner.getWidthHeight().getExactX()/2)-100),(int)((GameRunner.getWidthHeight().getExactY()/2)-(((60*optionsNum)+20)/2)));
        
        //save game
        JButton s = new JButton("Save Game",null);
        s.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			SaveLoad.saveGame();
		}    
	});
        s.setBounds(20, 20+(0*60), 160, 40);
        this.setLayout(null);
        this.add(s);
        
        
        
        //increase view movement speed
        JButton sp = new JButton("Increase view movement speed",null);
        sp.setFont(new Font("Dialog", Font.PLAIN, 8));
        sp.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			GameRunner.changeViewMoveSpeed(2);
		}    
	});
        sp.setBounds(20, 20+(1*60), 160, 20);
        this.setLayout(null);
        this.add(sp);
        
        
        
        
        //decrease view movement speed
        JButton sm = new JButton("Decrease view movement speed",null);
        sm.setFont(new Font("Dialog", Font.PLAIN, 8));
        sm.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			GameRunner.changeViewMoveSpeed(-2);
		}    
	});
        sm.setBounds(20, 20+(1*60)+20, 160, 20);
        this.setLayout(null);
        this.add(sm);
        
        
        
        
        //exit to main menu
        JButton b = new JButton("Exit to Main Menu",null);
        b.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    if(confirm("Are you sure you want to exit to the main menu?"
                            + " All unsaved progress will be lost forever."))
                    {
			MainMenu menu = new MainMenu();
                        menu.run();
                        Sound.stopSound();
                        Sound.stopBackgrounds();
                        Sound.playLoopSound("src/TLPTheme Song.wav");
                        GameRunner.exit();
                    }
		}    
	});
        b.setBounds(20, 20+(2*60), 160, 40);
        this.setLayout(null);
        this.add(b);
        
        
    
        
        //exit game
        JButton e = new JButton("Exit Game",null);
        e.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    if(confirm("Are you sure you want to exit The Last Promise?"
                            + " All unsaved progress will be lost forever."))
			System.exit(0);
		}    
	});
        e.setBounds(20, 20+(3*60), 160, 40);
        this.setLayout(null);
        this.add(e);
        
        
        
            
        
            
        frame.add(this);
        frame.setVisible(true);
        frame.pack();
        frame.repaint();
    }
    
    private boolean confirm(String message)
    {
       int reply = JOptionPane.showConfirmDialog(null, message, "Confirm Exit",
               JOptionPane.YES_NO_OPTION);
        
       if (reply == JOptionPane.YES_OPTION) {
          return true;
        }
        else {
           OptionsMenu o = new OptionsMenu();
           return false;
        }
   }
    

    

}
