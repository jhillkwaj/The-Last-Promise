/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thelastpromise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This SaveLoad class receives the user's password and username.
 * And places them to the Logins.txt. It encripts them to the txt file
 * so that mis-use does not occur. 
 */
public class SaveLoad {
    private static String let = "qwertyuioplkjhgfdsazxmcbnv?!0912837645";
    private static int[] nums = {353,23,653,313,41,31,409,113,701,911,29,99,131,
                                557,83,47,673, 283, 257, 421, 743, 601, 419,311,
                                619,173,43,227,751,277,797,267,191,281,139,19,
                                829,349,359};
    private static String loginUsername = "";
    
    //save an incripted password and username
    public static void newLogin(String password, String username)
    {
        
        try
        {
          
            File file = new File("Logins.dat");
            if(!file.isFile())
            {
              file.createNewFile();
            }
            
            BufferedReader in = new BufferedReader(new FileReader(file));
            ArrayList<String> lines = new ArrayList<String>();
            String line = in.readLine();
            while(line!=null)
            {
                lines.add(line);
                line = in.readLine();
            }
            
            //test if username exists
            for(int i = 0; i < lines.size(); i++)
            {
                if(lines.get(i).indexOf(username)>0 && lines.get(i).
                        substring(lines.get(i).indexOf(username)).equals(username))
                { 
                    LogHandling.logError("Username already exists."
                            + " Enter a different one and try again.");
                    return;
                }
            }
            
            float newPas = incript(password); 
            FileWriter f = new FileWriter(file);
            BufferedWriter out = new BufferedWriter(f);
            out.write(""+newPas+username);
            out.newLine();
            for(int i = 0; i < lines.size(); i++)
            {
                out.write(lines.get(i));
                out.newLine();
            }
            out.close();
            loginUsername = username;
            LogHandling.logMessage("New Login made");
        }
        catch(Exception e)
        {
            LogHandling.logError("Can't save Login!!!    " + e);
        }
    }
    /**
     * 
     * recieves String password which has been already encripted
     * recieves String username which has been already encripted
     * true is the username and password match for a user. return
     * false if the username and password don't match or do not exist.
     */
    public static boolean login(String password, String username)
    {
        try
        {
            File file = new File("Logins.dat");
            if(!file.isFile())
            {
              file.createNewFile();
            }
            
            BufferedReader in = new BufferedReader(new FileReader(file));
            ArrayList<String> lines = new ArrayList<String>();
            String line = in.readLine();
            while(line!=null)
            {
                lines.add(line);
                line = in.readLine();
            }
            float newPas = incript(password);
            for(int i = 0; i < lines.size(); i++)
            {
                if(lines.get(i).equals(""+newPas+username))
                {  
                    loginUsername = username;
                    LogHandling.logMessage("Login Successfull");
                    return true;
                }
                else if(lines.get(i).indexOf(username)>0 && lines.get(i).
                        substring(lines.get(i).indexOf(username)).equals(username))
                { 
                    LogHandling.logError("Incorrect Password");
                    return false;
                }
            }
        }
        catch(Exception e)
        {
            LogHandling.logError("Can't Login!!!    " + e );
        }
        LogHandling.logError("Incorrect Username/Username dosn't exist");
        return false;
    }
    /**
     * 
     *  The string password is recieved, is encripted, and returned. 
     * 
     */
    private static float incript(String password)
    {
        float newPas = 0;
        for(int i = 0; i < password.length(); i++)
        {
                newPas+=nums[let.indexOf(password.substring(i, i+1))]*((i+1)*17);
        }
        LogHandling.logMessage("Password Incripted");
        return newPas;
    }
    
    
    
    public static void saveGame()
    {
        try {
            //write to file
            FileWriter f = new FileWriter(loginUsername+" Save.txt");
            BufferedWriter out = new BufferedWriter(f);
            out.write("*---*");
            out.newLine();
            out.write(""+StartGame.getGame());
            out.newLine();
            
            
            //number of tiles in x and y directions
            out.write(""+GameRunner.lastTile().location.getX());
            out.newLine();
            out.write(""+GameRunner.lastTile().location.getY());
            out.newLine();
           
            //write each tile's data
            for(int i = 0; i <= GameRunner.lastTile().location.getY(); i++)
            {
                for(int j = 0; j <= GameRunner.lastTile().location.getX(); j++)
                {
                    ArrayList<String> tileInfo = GameRunner.getTile(new Location(j,i)).save();
                    for(String s : tileInfo)
                    { 
                        out.write(s);
                        out.newLine();
                    }
                    out.write("*Tile*");
                    out.newLine();
                }
            }
            
            //write number of players
            out.write("*Players*");
            out.newLine();
            out.write(""+GameRunner.getPlayers().size());
            
            //write each players data
            for(int i = 0; i < GameRunner.getPlayers().size(); i++)
            {
                Player p = GameRunner.getPlayers().get(i);
                ArrayList<String> playerInfo = p.save();
                out.newLine();
                for(String s : playerInfo)
                { 
                    out.write(s);
                    out.newLine();
                }
                out.write("*Player*");
                
            }
            
            out.close();
            LogHandling.logMessage("Game Saved");
            
        } catch (Exception e) {
            LogHandling.logError("Can't save game   " + e);
        }
    }
    
    public static boolean loadGame()
    {
            
            try {
            GameRunner.setBotBattle(true);
            GameRunner run = new GameRunner(); 
            if(GameRunner.getForest()==null)
            { GameRunner.setupImage(); }
                
                
            //find the file
            File file = new File(loginUsername+" Save.txt");
            if(!file.isFile())
            {
                return false;
            }
            //read file
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line = "";
           
            //find start of save
            while(!line.equals("*---*"))
            {
               line=in.readLine();
            }
            
            StartGame.setGame(Integer.parseInt(in.readLine()));
            
            //read number of tiles
            int r = Integer.parseInt(in.readLine());
            int c = Integer.parseInt(in.readLine());
            GameRunner.setupSize(r+1, c+1);
            
            
            //read and set each tile
            for(int i = 0; i <= c; i++)
            {
                for(int j = 0; j <= r; j++)
                {
                    //read tile
                    ArrayList<String> tileInfo = new ArrayList<String>();
                    line=in.readLine();
                    while(!line.equals("*Tile*"))
                    {
                        tileInfo.add(line);
                        line=in.readLine();
                    }
                    
                    //set tile
                    GameRunner.setTile(Tile.load(tileInfo), new Location(j,i));
                }
            }
            in.readLine();
            //set number of players
            int playerNum = Integer.parseInt(in.readLine());
            //set player data
            ArrayList<Player> players = new ArrayList<Player>();
            for(int i = 0; i < playerNum; i++)
            {
                //read Player data
                ArrayList<String> playerInfo = new ArrayList<String>();
                line=in.readLine();
                while(!line.equals("*Player*"))
                {
                    playerInfo.add(line);
                    line=in.readLine();
                }
                
                Player p = Player.load(playerInfo);
                players.add(p);
                LogHandling.logMessage("Game Loaded");
            }
            run.setupPlayers(players);
            run.start();
            } catch (Exception e) {
                LogHandling.logError("Can't load saved game...save may have been corrupted..." + e);
            }
        return true;
    }
    
}
