/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package thelastpromise;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 * This class presents the player to the game along with their name, 
 * and the different units that the player has obtained, such as the gold,
 * turn movements, and listens to the time of actions. This class also 
 * presents who has won the game.
 */
public class Player {
    protected String name;
    protected int gold;
    protected ArrayList<Unit> units = new ArrayList<Unit>();
    protected Boolean turn = false;
    protected boolean win = false;
    protected int level = 1;
    protected boolean bot = false;
    
    
    protected Timer timer;
    protected int timerSpeed = 30;
    protected int turnTime = 0;
     
    /**
     * This receives the integer gold and String 
     * name then defines them again in the method.
     * The setupTime(); is also called.
     * gold received
     * name received
     */
    public Player()
    {
        setupTime();
        bot=false;
    }
    /**
     *  This receives the integer gold and String 
     * name then defines them again in the method.
     * The setupTime(); is also called.
     * gold received
     * name received
     * 
     */
    public Player(int gold, String name)
    { 
       this.gold = gold;
       this.name = name;
       setupTime();
       bot=false;
    }
    /**
     * 
     * This receives the integer gold, the boolean
    * turn, and the string name, then redefines them
    * again in the method. Calls the setTurn and uses
    * turn as a parameter. The setupTime methods and 
    * gold is received
    * turn is received
    * name is received
     */
    public Player(int gold, Boolean turn, String name)
    {
       this.gold = gold;
       this.turn = turn;
       this.name = name;
       setTurn(turn);
       setupTime();
       bot=false;
    }
    /**
     * 
     * This receives the integer gold, the boolean turn,
    * the ArrayList<Unit> units, and the string name, then
    * redefines them again in the method. Calls the setTurn 
    * uses turn as a parameter. The setupTime methods and 
    * gold received
    * turn received
    * units received
    * name received
     */
    public Player(int gold, Boolean turn, ArrayList<Unit> units, String name)
    { 
       this.gold = gold;
       this.turn = turn;
       this.units = units;
       this.name = name;
       setTurn(turn);
       setupTime();
       bot=false;
    }
    
    public Player(int gold, Boolean turn, ArrayList<Unit> units, String name, int level)
    { 
       this.gold = gold;
       this.turn = turn;
       this.units = units;
       this.name = name;
       this.level = level;
       this.turn=turn;
       setupTime();
       bot=false;
    }
    
    
    /**
     *  This setupTime() method starts the speed handler
     *  which controls how long the start of the turn message lasts.
     */
    public void setupTime()
    {
      timer = new Timer(timerSpeed, timerListener);
      timer.start();
    }
    /**
     * 
     * This method transfers the two main turns made. 
     * @returns the 'turn' made in the game
     */
    public Boolean isTurn()
    { 
        return turn;
    }
    
    public void win()
    { 
        Sound.playSound("src/TLP8bitwin.wav");
        win = true;
    }
    
    public boolean won()
    { return win; }
    /**
     * 
     * @return returns the units.
     * Units is terms of arraylist which will be used to 
     * calculate the moves which the avatar can make.
     */
    public ArrayList<Unit> getUnits()
    {   return units;   }
    /**
     * 
     * isTurn is received and used to make the turn for 
     * the amount of units. An if statement is used to check 
     * for the units and turns.
     */
    public void setTurn(boolean isTurn)
    {
       if(isTurn)
       {
           turn=true;
           turnTime = 0;
           for(int i = 0; i < units.size(); i++)
           {
               Unit u = units.get(i);
                u.newTurn();
                u.selected=false;
           }
           gold+=10;
       }
       else
       {
           turn = false;
           for(Unit u : units)
           {
                u.selected=false;
           }
       }
    }
    /**
     * 
     *  g (Graphics method) is received in order to write 
     * on the basis of each turn change. During each turn change,
     * the text is shown to show whose next turn it is. An if statement
     * is used to verify this.
     */
    public void draw(Graphics g)
    {
        if(turn)
        {
        g.setColor(Color.cyan);
        g.setFont(new Font("Monotype Corsiva", Font.PLAIN, 30));
        g.drawString(name + " Turn", 200, 30);
         g.setColor(new Color(255,191,0));
        g.setFont(new Font("Monotype Corsiva", Font.ITALIC, 70));
         //Graphics2D g2d = (Graphics2D)g;
           // g2d.drawImage(GameRunner.getGold(), 0, 40, 80,80,null);
        //g.drawString("" + gold, 80, 100);
        Graphics2D g2d = (Graphics2D)g;
           g2d.drawImage(GameRunner.getGold(), GameRunner.getWidthHeight().getExactX()-280, 0, 80,80,null);
        g.drawString("" + GameRunner.writeGold(gold), GameRunner.getWidthHeight().getExactX()-200, 60);
        if(turnTime < 60)
        {
            g.setColor(Color.RED);
            g.setFont(new Font("Monotype Corsiva", Font.PLAIN,70));
            g.drawString(name + " Turn", GameRunner.getWidthHeight().getExactX()/8, GameRunner.getWidthHeight().getExactY()/2);
        }
        
        }
        if(win==true)
        {
             g.setColor(Color.RED);
            g.setFont(new Font("Monotype Corsiva", Font.PLAIN,70));
            g.drawString(name + " WINS!", GameRunner.getWidthHeight().getExactX()/8, GameRunner.getWidthHeight().getExactY()/2);
        }
    }
    
    
    ActionListener timerListener = new ActionListener() 
    {
                /**
                 * ActionEvent e (method) is used in order to increase
                 * the turn time by one each instance.
                 */
   		public void actionPerformed(ActionEvent e)
   		{ 
   			turnTime++;
   		}

    };
    
    
    public void addUnit(Unit u)
    { units.add(u); }
    
    public int getLevel()
    { return level; }
    
    public void levelUp()
    { level++; }
    
    public int getGold()
     { return gold; }
     
     public void addGold(int amount)
     { gold+=amount; }
     
     public ArrayList<String> save()
     {
        ArrayList<String> data = new ArrayList<String>();
        data.add(name);
        data.add(""+gold);
        data.add(""+level);
        data.add(""+turn);
        
        //save units
        data.add("*Units*");
        data.add(""+units.size());
         
        for(Unit unit : units)
        { 
           ArrayList<String> unitData = unit.save();
           for(String string : unitData)
           { data.add(string); }
           data.add("*Unit*");
        }
        
        return data;
     }
     
     public static Player load(ArrayList<String> data)
     {
         if(data.get(4).equals("*Units*"))
         {
         GameRunner.setBotBattle(false);
         ArrayList<Unit> u = new ArrayList<Unit>();
         int dataNum = 0;
         for(int i = 0; i < Integer.parseInt(data.get(5)); i++)
         {
             ArrayList<String> unitData = new ArrayList<String>();
            while(dataNum+6<data.size()&&!data.get(6+dataNum).equals("*Unit*"))
            {
                unitData.add(data.get(6+dataNum));
                dataNum++;
            }
            dataNum++;
            u.add(Unit.load(unitData));
         }
         //load units
         
         Player p = new Player(Integer.parseInt(data.get(1)), 
                 Boolean.parseBoolean(data.get(3)), u, data.get(0),
                 Integer.parseInt(data.get(2)));
         return p;
         }
         else
         { return PlayerBot.load(data); }
     }

}
