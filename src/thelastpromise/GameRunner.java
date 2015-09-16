
package thelastpromise;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ToolTipManager;

/**
 *
 * This is the main runner class
 */
public class GameRunner extends JPanel implements MouseMotionListener, MouseListener  {
    //row,col
    private static JFrame frame = new JFrame();
    
    private static Tile[][] tiles;
    
    private static Image grass;
    private static Image grass2;
    private static Image grass3;
    private static Image grass4;
    private static Image grass5;
    private static Image water;
    private static Image water2;
    private static Image water3;
    private static Image forest;
    private static Image mage;
    private static Image archMage;
    private static Image butcher;
    private static Image fireSpirit;
    private static Image summoner;
    private static Image red;
    private static Image blue;
    private static Image green;
    private static Image yellow;
    private static Image nextButton;
    private static Image optionsButton;
    private static Image swordsman;
    private static Image knight;
    private static Image archer;
    private static Image horseman;
    private static Image castle;
    private static Image mageTower;
    private static Image magicSpirit;
    private static Image worker;
    private static Image goldMine;
    private static Image sword;
    private static Image shirt;
    private static Image gold;
    private static Image autoMine;
    private static Image target;
    private static Image king;
    private static Image ssKing;
    private static Image fire;
    private static Image cloud;
    private static Image cloudL;
    private static Image arrow;
    private static Image explosion;
    
    
    private static int tileR = 0;
    private static int tileC = 0;
    
    private int mouseDownX;
    private int mouseDownY;
    
    private static Timer timer;
    private int timerSpeed = 30;
    
    private JButton button1;
    private JButton options;

    
    private static Location view = new Location(-1,-1,0,0);
    private static int viewMoveSpeed =  14;
    
    private static ArrayList<Player> players = new ArrayList<Player>();
    
    private static Unit lastSelected = null;
    
    private static int epicNum = 0;
    private static float volume = -5.0f;
    
    private static MouseEvent lastMouseMove = null;
    
    private static ArrayList<Object> attacks = new ArrayList<Object>();
    
    private static boolean botBattle = true;
    
   
    

    /**
     * Sets up and starts the game
     */
    public void start()
    {
        try
        {
        timer = null;
        view = new Location(-1,-1,0,0);
        epicNum=0;
        lastSelected = null;
        lastMouseMove=null;
        
        
        frame.setVisible(false);
        frame.setTitle("The Last Promise");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setExtendedState(frame.MAXIMIZED_BOTH);  
        frame.setVisible(true);
        frame.repaint();
        frame.add(this);
        
        //MyListener myListener = new MyListener();
        addMouseListener(this);
        addMouseMotionListener(this);
        
        //add buttons
        button1 = new JButton(new ImageIcon(nextButton.getScaledInstance(150, 40, 0)));
        button1.setBorder(BorderFactory.createEmptyBorder());
        button1.setContentAreaFilled(false);
        button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextPlayer();
			}
		});
        
        button1.setBounds(0, 0, 150, 40);
        this.setLayout(null);
        this.add(button1);
        
        
        
        options = new JButton(new ImageIcon(optionsButton.getScaledInstance(150, 40, 0)));
        options.setBorder(BorderFactory.createEmptyBorder());
        options.setContentAreaFilled(false);
        options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OptionsMenu m = new OptionsMenu();
			}
		});
        
        options.setBounds((frame.getSize().width/2)-(150/2), 0, 150, 40);
        this.setLayout(null);
        this.add(options);
        
        
        
        frame.setIconImage(shirt);
        setMouseImage(sword);
        
        
        timer = new Timer(timerSpeed, timerListener);
        timer.start();
        Sound.playbackloop();
        LogHandling.logMessage("Game Started");
        }
        catch(Exception e)
        {
            LogHandling.logNonRecoverableError("Could not start game   " + e);
        }
    }
    
    public static void setupImage()
    {
        
        //import art
         try {
             if(knight==null)
             {
            knight = ImageIO.read(new File("src/knight_armored-final.png"));
            archMage = ImageIO.read(new File("src/arch_mage-final.png"));
            summoner = ImageIO.read(new File("src/mage_summoner-final.png"));
            butcher = ImageIO.read(new File("src/magic_melee-final.png"));
            fireSpirit = ImageIO.read(new File("src/fire_spirit-final.png"));
            grass = ImageIO.read(new File("src/Final-Grass.png"));
            grass2 = ImageIO.read(new File("src/Final-Grass2.png"));
            grass3 = ImageIO.read(new File("src/Final-Grass3.png"));
            grass4 = ImageIO.read(new File("src/Final-Grass4.png"));
            grass5 = ImageIO.read(new File("src/Final-Grass5.png"));
            water = ImageIO.read(new File("src/Final-Ocean.png"));
            water2 = ImageIO.read(new File("src/Final-Ocean(2).png"));
            water3 = ImageIO.read(new File("src/Final-Ocean(3).png"));
            forest = ImageIO.read(new File("src/Final-Forest.png"));
            mage = ImageIO.read(new File("src/mage-final.png"));
            green = ImageIO.read(new File("src/TileGreen.png"));
            yellow = ImageIO.read(new File("src/TileYellow.png"));
            red = ImageIO.read(new File("src/TileRed.png"));
            blue = ImageIO.read(new File("src/TileBlue.png"));
            nextButton = ImageIO.read(new File("src/EndTurn.png"));
            optionsButton = ImageIO.read(new File("src/Options.png"));
            swordsman = ImageIO.read(new File("src/swordsman-final.png"));
            horseman = ImageIO.read(new File("src/final-horseman.png"));
            castle = ImageIO.read(new File("src/castle-final.png"));
            mageTower = ImageIO.read(new File("src/mage_tower-final.png"));
            magicSpirit = ImageIO.read(new File("src/magic_spirit-final.png"));
            worker = ImageIO.read(new File("src/worker-final.png"));
            goldMine = ImageIO.read(new File("src/gold_mine-final.png"));
            sword = ImageIO.read(new File("src/sword-final.png"));
            shirt = ImageIO.read(new File("src/T-ShirtofImmortality-final.png"));
            gold = ImageIO.read(new File("src/gold-final.png"));
            autoMine = ImageIO.read(new File("src/autoMine.png"));
            target = ImageIO.read(new File("src/archery_target-final.png"));
            king = ImageIO.read(new File("src/king_face-final.png"));
            ssKing = ImageIO.read(new File("src/SS_face-final.png"));
            fire = ImageIO.read(new File("src/fireball-final.png"));
            cloud = ImageIO.read(new File("src/cloud.png"));
            cloudL = ImageIO.read(new File("src/cloudL-final.png"));
            archer = ImageIO.read(new File("src/archer-final.png"));
            arrow = ImageIO.read(new File("src/arrow.png"));
            explosion = ImageIO.read(new File("src/explosion-final.png"));
            LogHandling.logMessage("Art imported");
             }
             } catch (Exception e) {
                  LogHandling.logError("Could not import art   " + e);
             }
        
    }
    
    /**
     * Sets the row column size of the game map. 
     */
    public static void setupSize(int r, int c)
    { tileR=r; tileC=c; tiles=new Tile[r][c]; }
    
    
    public void setupPlayers(ArrayList<Player> players)
    { this.players=players; }
    
    
    public void paintComponent(Graphics g)
    {//draw units and tiles if helth > 0
        lookWin();
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        //view.setExactY(view.getExactY()+1);
         for(int i = 0; i < tileR; i++)
         {
                for(int j = 0; j < tileC; j++)
                {
                    tiles[i][j].draw(g);
                }
         }
            
         for(int i = 0; i < players.size(); i++)
         {
             
                for(int j = 0; j < players.get(i).getUnits().size(); j++)
                {
                    if(players.get(i).getUnits().get(j).helth>0)
                    {
                        players.get(i).getUnits().get(j).draw(g);
                        
                    }
                    else
                    {
                        players.get(i).getUnits().remove(j);
                    }
                }
         }
         
         
         
         for(int i = 0; i < attacks.size(); i++)
         {
             attacks.get(i).draw(g);
             if(((AttackEffect)attacks.get(i)).getLastAct())
             { 
                 attacks.remove(i);
                 i--;
             }
         }
         
         for(int i = 0; i < players.size(); i++)
         {
             players.get(i).draw(g);  
         }
         drawTooltips(g);
    }
    
    public static void addAttack(Object o)
    { attacks.add(o); }
    
    public static Tile getTile(Location loc)
    {
        return tiles[loc.getX()][loc.getY()];
    }
    
    public static void setTile(Tile tile, Location loc)
    {
        tiles[loc.getX()][loc.getY()] = tile;
    }
    
    public static Player getPlayerisTurn()
    {
        for(Player p : players)
        {
            if(p.isTurn())
            {
                return p;
            }
        }
        return null;
    }
 
    
    public static Location getView()
    {   return view; }
    
    public void lookWin()
    {
        int count = players.size();
        int p = 0;
        for(int i = 0; i < players.size(); i++)
        {
            if(players.get(i).getUnits().size()==0)
            { count--; }
            else
            { p=i; }
        }
        if(count==1&&!players.get(p).won())
        {
            LogHandling.logMessage(players.get(p).name + " Wins");
            Sound.stopBackgrounds();
            Sound.stopSound();
            players.get(p).win();
            StartGame.onWin();
        }
    }

    public void mouseClicked(MouseEvent e) {
        lookWin();
        boolean selected = false;
        if(lastSelected!=null){lastSelected.select();}
        for(int i = 0; i < players.size(); i++)
        {
            if(players.get(i).isTurn())
            {
                for(int j = 0; j < players.get(i).getUnits().size(); j++)
                {
                    if(players.get(i).getUnits().get(j).location.getX() == 
                        Location.getXofExactX(e.getX() + view.getExactX() - 20) && 
                        players.get(i).getUnits().get(j).location.getY() ==
                        Location.getYofExactY(e.getY() + view.getExactY() - 40)
                        && (players.get(i).getUnits().get(j) != lastSelected))
                    {
                        lastSelected = players.get(i).getUnits().get(j);
                        players.get(i).getUnits().get(j).select();
                        selected = true;
                    }
                }
            }
        }
        if(selected == false)
        {
            //move unit
            if(lastSelected!=null){lastSelected.move(new Location(Location.
                    getXofExactX(e.getX() + view.getExactX() - 20),
                    Location.getYofExactY(e.getY() + view.getExactY() - 40)));}
            lastSelected = null;
        }
        
    }
    
    public static void nextPlayer()
    {
        
        setEpicNum(epicNum-10);
        lastSelected = null;
        for(int i = 0; i < players.size(); i++)
        {
            if(players.get(i).isTurn())
            {
                if(i+1<players.size())
                {
                    players.get(i+1).setTurn(true);
                    players.get(i).setTurn(false);
                    return;
                }
                else
                {
                    players.get(0).setTurn(true);
                    players.get(players.size()-1).setTurn(false);
                    return;
                }
            }
        }
        LogHandling.logMessage("Next Player");
    }
    
    public static Boolean isEmpty(Location loc)
    {
        int x = loc.getX();
        int y = loc.getY();
        for(int i = 0; i < players.size(); i++)
        {
                for(int j = 0; j < players.get(i).getUnits().size(); j++)
                {
                    if(players.get(i).getUnits().get(j).location.getX() == x &&
                         players.get(i).getUnits().get(j).location.getY() == y)
                    {
                        return false;
                    }
                }
       }
       return true;
    }
   
    
    //returns null if no enemy unit else returns unit
    public static Unit containsEnemyUnit(Location loc)
    {
        int x = loc.getX();
        int y = loc.getY();
        for(int i = 0; i < players.size(); i++)
        {
            if(!players.get(i).isTurn())
            {
                for(int j = 0; j < players.get(i).getUnits().size(); j++)
                {
                    if(players.get(i).getUnits().get(j).location.getX() == x &&
                         players.get(i).getUnits().get(j).location.getY() == y)
                    {
                        return players.get(i).getUnits().get(j);
                    }
                }
            }
       }
       return null;
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
        mouseDownY = -1;
        mouseDownX = -1;
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
    
    public void mouseDragged(MouseEvent e) {
        if(mouseDownY < MouseInfo.getPointerInfo().getLocation().y)
        {
            mouseDownY = MouseInfo.getPointerInfo().getLocation().y;
            view.setExactY((int)Math.round(view.getExactY() - ((viewMoveSpeed/30.0)*Math.abs(e.getLocationOnScreen().y-lastMouseMove.getLocationOnScreen().y))));
        }
        else if(mouseDownY > MouseInfo.getPointerInfo().getLocation().y)
        {
            mouseDownY = MouseInfo.getPointerInfo().getLocation().y;
            view.setExactY((int)Math.round(view.getExactY() + ((viewMoveSpeed/30.0)*Math.abs(e.getLocationOnScreen().y-lastMouseMove.getLocationOnScreen().y))));
        }
        
        if(mouseDownX < MouseInfo.getPointerInfo().getLocation().x)
        {
            mouseDownX = MouseInfo.getPointerInfo().getLocation().x;
            view.setExactX((int)Math.round(view.getExactX() - ((viewMoveSpeed/30.0)*Math.abs(e.getLocationOnScreen().y-lastMouseMove.getLocationOnScreen().y))));
        }
        if(mouseDownX > MouseInfo.getPointerInfo().getLocation().x)
        {
            mouseDownX = MouseInfo.getPointerInfo().getLocation().x;
            view.setExactX((int)Math.round(view.getExactX() + ((viewMoveSpeed/30.0)*Math.abs(e.getLocationOnScreen().y-lastMouseMove.getLocationOnScreen().y))));
        }
        
    }
    
    	ActionListener timerListener = new ActionListener() 
   	{
   		public void actionPerformed(ActionEvent e)
   		{
   			repaint();
   		}

   	};
        
   public static void changeViewMoveSpeed(int amount)
   { viewMoveSpeed+=amount; }

    public void mouseMoved(MouseEvent e) 
    { 
        lastMouseMove=e;
        if(frame.getContentPane().getCursor().getName().equals("blank"))
        {
            setMouseImage(sword);
        }
    }
    
    public void drawTooltips(Graphics g)
    {
        //This is used to display a tooltip over a unit
        //If mouse is not moved
        if(lastMouseMove!=null&&lastMouseMove.getWhen()+400<System.currentTimeMillis())
        {
        //Find the unit the mouse is over
        for(Player p : players)
        {
            for(Unit u : p.getUnits())
            {
                if(u.location.getX() == Location.getXofExactX(lastMouseMove.getX() + view.getExactX()) &&
                        u.location.getY() == Location.getYofExactY(lastMouseMove.getY() + view.getExactY()))
                {
                    //and make the tooltip
                    u.drawTooltip(g,new Location(0,0,lastMouseMove.getX(),lastMouseMove.getY())); 
                }
            }
        }
        }
        else
        {
        }
    }
    
    
    
    // for get color or image methods setup MUST have been called
    
    public static Image getKing()
    {   return king; }
    
    public static ArrayList<Image> getCloud()
    { 
        ArrayList<Image> clouds = new ArrayList<Image>();
        clouds.add(cloud);
        clouds.add(cloudL);
        return clouds;
    }
    
    
    public static Image getFire()
    {   return fire; }
    
    public static Image getRed()
    {   return red; }
    
    public static Image getyYellow()
    {   return yellow; }
       
    public static Image getBlue()
    {   return blue; }
    
    public static Image getyGreen()
    {   return green; }
    
    public static Image getGold()
    { return gold; }
    
    public static Image getGoldMine()
    { return goldMine; }
    
    public static Image getArrow()
    { return arrow; }
    
    public static Image getExplosion()
    { return explosion; }
    
    public static Image getCastle()
    { return castle; }
    
    public static Image getMageTower()
    { return mageTower; }
    
    public static Image getMagicSpirit()
    { return magicSpirit; }
    
    public static ArrayList<Image> getWater()
    { 
        ArrayList<Image> waters = new ArrayList<Image>();
        waters.add(water);
        waters.add(water2);
        waters.add(water3);
        return waters; 
    }
    
    public static Image getForest()
    { return forest; }
    
    public static Image getMage()
    { return mage; }
    
    public static Image getArchMage()
    { return archMage; }
    
    public static Image getSwordsman()
    { return swordsman; }
    
    public static Image getFireSpirit()
    { return fireSpirit; }
    
    public static Image getKnight()
    { return knight; }
    
    public static Image getHorseman()
    { return horseman; }
    
    public static Image getWorker()
    { return worker; }
    
    public static Image getAutoMine()
    { return autoMine; }
    
    public static Image getButcher()
    { return butcher; }
    
    public static Image getArcher()
    { return archer; }
    
    public static Image getSummoner()
    { return summoner; }
    
    public static Image getTarget()
    { return target; }
    
    public static ArrayList<Image> getGrass()
    {
        ArrayList<Image> grasses = new ArrayList<Image>();
        grasses.add(grass);
        grasses.add(grass2);
        grasses.add(grass3);
        grasses.add(grass4);
        grasses.add(grass5);
        return grasses;
    }
    
    public static Tile lastTile()
    {
        return tiles[tileR-1][tileC-1];
    }
    
    public static Location getWidthHeight()
    {
        return new Location(0,0,frame.getSize().width,frame.getSize().height);
    }
    
    public static Location getViewLoc()
    { return new Location(frame.getWidth(),frame.getHeight(),view.getExactX(),view.getExactY()); }
    
    public static int epicNum()
    { return epicNum; }
    
    public static void setEpicNum(int newEpicNum)
    {  
        epicNum = newEpicNum; 
        if(epicNum<0)
        { epicNum = 0; }
    }
    
    public static float getVolume()
    { return volume; }
    
    public static ArrayList<Player> getPlayers()
    { return players; }
    
    public static void setMouseImage(Image image)
    {
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(
           image, new Point(0, 0), "mouse cursor");

        frame.getContentPane().setCursor(cursor);
    }
    
    public static void setMouseImage(String image)
    {
        try
        {
        Image i = ImageIO.read(new File("src/"+image+".png"));;
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(
           i, new Point(0, 0), image);

        frame.getContentPane().setCursor(cursor);
        }
        catch(Exception e)
        {
            LogHandling.logError("Can't load image file" + image +"    " + e);
        }
    }
    
    public static String writeGold(int gold)
    {
        if(gold<100000)
        { return (""+gold); }
        else if(gold < 1000000)
        { return (""+(gold/1000)+"K"); }
        else if(gold < 10000000)
        { return ("" + (gold/1000000) + "." + ((gold%1000000)/100000) + "M"); }
        else if(gold < 1000000000)
        { return ("" + (gold/1000000) + "M"); }
        else
        { return ("" + (gold/1000000000) + "B"); }
    }
    
    public static void setBotBattle(boolean bot)
    { botBattle = bot; }
    
    public static boolean getBotBattle()
    { return botBattle; }
    
    public static void exit()
    { 
        timer.stop();
        frame.dispose();
    }
    
    public static Image getImage(String name)
    {
        try{
            return  ImageIO.read(new File("src/" + name + ".png"));
        }
        catch(Exception e)
        { LogHandling.logError("Could not get image" + name + ".png from src..."+e);}
        return null;
    }

}
