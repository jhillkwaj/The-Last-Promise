/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package thelastpromise;

import java.util.ArrayList;

/**
 *
 *  This is the class that starts the actual game
 */
public class StartGame {
    /**
     * This method starts the tutorial portion of the game
     */
    private static int game = 0;
    public static void startTutorial()
    {
        GameRunner.setBotBattle(false);
        game=0;
        int r = 10;
        int c = 10;
        GameRunner run = new GameRunner(); 
        if(GameRunner.getForest()==null)
        { GameRunner.setupImage(); }
        GameRunner.setupSize(r, c);
        for(int i = 0; i < r; i++)
        {
            for(int j = 0; j < c; j++)
            {
                double rand = Math.random();
                if(rand < .2)
                { GameRunner.setTile(new GrassTile(new Location(i,j), GameRunner.getGrass().get(0)),new Location(i,j)); }
               else if(rand < .4)
                { GameRunner.setTile(new GrassTile(new Location(i,j), GameRunner.getGrass().get(1)),new Location(i,j)); }
                else if(rand < .6)
                { GameRunner.setTile(new GrassTile(new Location(i,j), GameRunner.getGrass().get(2)),new Location(i,j)); }
                else if(rand < .65)
                { GameRunner.setTile(new GrassTile(new Location(i,j), GameRunner.getGrass().get(3)),new Location(i,j)); }
                else if(rand < .75)
                { GameRunner.setTile(new GrassTile(new Location(i,j), GameRunner.getGrass().get(4)),new Location(i,j)); }
                else if (Math.random() < .9)
                { GameRunner.setTile(new ForestTile(new Location(i,j), GameRunner.getForest()),new Location(i,j)); }
                else
                { GameRunner.setTile(new WaterTile(new Location(i,j), GameRunner.getWater().get(0)),new Location(i,j)); }
            }
        }
        
        GameRunner.setTile(new GoldMine(new Location(3,3), GameRunner.getGoldMine(), GameRunner.getGrass().get(2)),new Location(3,3));
        GameRunner.setTile(new GrassTile(new Location(5,5), GameRunner.getGrass().get(0)),new Location(5,5));
       Swordsman s = new Swordsman(new Location(2,2), GameRunner.getSwordsman());
        
        Target t = new Target(new Location(3,2), GameRunner.getTarget(), 1);
        
        GameRunner.setTile(new GrassTile(new Location(2,2), GameRunner.getGrass().get(0)),new Location(2,2));
        
        
         ArrayList<Unit> p1Units = new ArrayList<Unit>();
         ArrayList<Unit> p2Units = new ArrayList<Unit>();
         
        p2Units.add(t);
        p1Units.add(s);
        
         
        // Set players
        ArrayList<Player> players = new ArrayList<Player>();
        Player player1 = new Player(90, true, p1Units, "Azdens",3);
        PlayerBot player2 = new PlayerBot(0, false, p2Units, "The Society of Souls", 2 , 1, 10000);
        players.add(player1);
        players.add(player2);
        run.setupPlayers(players);
        
        Sound.stopSound();
        run.start();
        
        startTutorial(0);
    }
    
    
    /**
     * Start an event within the tutorial.
     */
     public static void startTutorial(int event)
     {
         if(event==0)
         {
             String[] s = {"Royal Message",
                 "The King",
                 "Hello, Tactician, whom I just recruited.",
                 "I'm King Rex: Ruler of the Azdens;",
                 "Protector of the Empire's people;",
                 "and rightful owner of all the world's gold.",
                 "I know you have no experience at being a tactician,", 
                 "but I've got a good feeling about you…or am I just hungry?",
                 "redTo play The Last Promise, you will represent either the Kingdom of ",
                 "red    the Azdens or the mages from the Society of Souls.",
                 "redAll you have to do is click on a unit like that swordsman over there to select it.",
                 "redYou can then click on the spaces that are highlighted blue to move to them.",
                 "redOr you could click on the red spaces to attack the unit highlighted on them.",
                 "redTwo teams take turns moving their units trying to eliminate the other team.",
                 "redEach unit has a limited number of Action Points that can be used each turn ",
                 "red     to attack or move.",
                 "redAs the Action Points (seen by holding your mouse over the unit) are used up, ",
                 "red     there will be fewer available moves.",
                 "redWhen you've done everything you want to do just click \"end turn\".",
                 "blueNow go and try to use the swordsman to destroy the target.",
              };
             Dialogue d = new Dialogue(GameRunner.getKing(),s);
         }
         else if(event==1)
         {
             Target t = new Target(new Location(4,4), GameRunner.getTarget(), 2);
             GameRunner.getPlayers().get(1).units.add(t);
             String[] s = {"Royal Message",
                 "The King",
                 "Good work destroying that target.",
                 "The Society of Souls will be no match for you.",
                 "I knew I put my faith in the right person.",
                 "redRemember that each unit only has a limited number of action points each turn.",
                 "redYou will have to push the \"End Turn\" button in the upper left when you've made all",
                 "redthe available moves you wish to with this turn's action points.",
                 "blueNow go and destroy the next target with the Swordsman.",
                 "redYou might have to move the view around.  You can move the view",
                 "redby holding down the right mouse button while you move the mouse around.",
                 "This may take a while so I’ll take a nap…",
                 "red...don’t forget to push \"End Turn\" when you've made all the moves you want",
                 "redwith the action points you have available.  Don't worry.  You'll have more turns."
                 };
             Dialogue d = new Dialogue(GameRunner.getKing(),s);
         }
         else if(event==2)
         {
             Target t = new Target(new Location(8,8), GameRunner.getTarget(), 3);
             GameRunner.getPlayers().get(1).units.add(t);
             String[] s = {"Royal Message",
                 "The King",
                 "But I wasn’t done my nap yet!!!!",
                 "You’ll have to destroy another target while I finish it.",
                 "blueThe next target is hidden. You’ll need to move the view around to find it.",
                 "redDo that by holding down the right mouse button while you move the mouse around.",
                 "redYou’ll see that your swordsman moves differently over different terrain.", 
                 "redIt takes more action points for him to move over forests than over the grass.",
                 "redYou can hold your mouse over a unit to see its Health and Action Points.",
                 "redYou can also see how much of my gold you have in the upper left of the screen.",
                 "Now let me get back to my nap."};
             Dialogue d = new Dialogue(GameRunner.getKing(),s);
         }
         else if(event==3)
         {
             //add castle
             Castle c = new Castle(new Location(4,4), GameRunner.getCastle());
             GameRunner.getPlayers().get(0).units.add(c);
             GameRunner.getPlayers().get(0).units.remove(0);
             

             //and new target
             Target t = new Target(new Location(3,8), GameRunner.getTarget(), 4);
             GameRunner.getPlayers().get(1).units.add(t);
             
             String[] s = {"Royal Message",
                 "The King",
                 "HaHaHa…I love to see archery targets beaten to death by swordsmen.",
                 "Now it’s a time for something else. Let’s build some units at the castle.",
                 "redIt costs gold to build units.",
                 "redThe costs are shown below the icons when you select the castle.",
                 "redYou can click on an icon to build it or put your mouse over it to learn what it is.", 
                 "redBuild workers to mine for gold.  ",
                 "redWorkers can mine when they are adjacent to a gold mine.",
                 "redYou can also level up to gain better units and upgrade the ones you have.",
                 "redOr you could level up your units the fun way…BY KILLIN’ THINGS!!!",
                 "redYou can also build many different types of units.",
                 "redDifferent types of units have different characteristics. For example, ",
                 "redthe Horsemen cannot attack diagonally and is slowed dramatically by forests.",
                 "I’ve duct-taped a mage and a summoner to the back of the next target.",
                 "It’s OK. Those guys are craaazzy anyhow. I don’t think they mind dyin’.",
                 "blueBuild a small force and destroy the target when you are ready for a fight.",
                 "I gotta go. Remember that all the gold is mine!"};
             Dialogue d = new Dialogue(GameRunner.getKing(),s);
         }
         else if(event==4)
         {
             GameRunner.nextPlayer();
             //add gold
             GameRunner.getPlayers().get(0).addGold(100);
             GameRunner.getPlayers().get(1).addGold((-1*GameRunner.getPlayers().get(1).gold)+150);
             
            //and units
             Mage m = new Mage(new Location(3,8), GameRunner.getMage());
             Summoner s = new Summoner(new Location(3,8), GameRunner.getSummoner());
             GameRunner.getPlayers().get(1).units.add(m);
             GameRunner.getPlayers().get(1).units.add(s);
         }
         else if(event==5)
         {
             String[] s = {"Royal Message",
                 "The King",
                 "YOU Win"};
             Dialogue d = new Dialogue(GameRunner.getKing(),s);
         }
             
      
     }
     
     
     public static void onWin()
     {
         
         if(game>=0&&game<100)
         {
         for(Player p : GameRunner.getPlayers())
         {
         System.out.println(""+p.bot);
         System.out.println(""+p.win);
         if(p.bot==false&p.win==true)
         {
            if(game==0)
            {
             startTutorial(5);
             return;
            }
            else
            {
                storyGame(++game);
            }
         }
         else if(p.win==true)
         {
             storyGame(game);
             return;
         }
         }
         }
         
     }
     

    public static void storyGame(int gameType)
    {
        GameRunner.setBotBattle(true);
        game = gameType;
        int r = 20;
        int c = 20;
        GameRunner run = new GameRunner(); 
        if(GameRunner.getForest()==null)
        { GameRunner.setupImage(); }
        GameRunner.setupSize(r, c);
        for(int i = 0; i < r; i++)
        {
            for(int j = 0; j < c; j++)
            {
                double rand = Math.random();
                if(rand < .2)
                { GameRunner.setTile(new GrassTile(new Location(i,j), GameRunner.getGrass().get(0)),new Location(i,j)); }
                else if(rand<.22)
                { GameRunner.setTile(new GoldMine(new Location(i,j), GameRunner.getGoldMine(), GameRunner.getGrass().get(1)),new Location(i,j)); }
               else if(rand < .4)
                { GameRunner.setTile(new GrassTile(new Location(i,j), GameRunner.getGrass().get(1)),new Location(i,j)); }
                else if(rand < .6)
                { GameRunner.setTile(new GrassTile(new Location(i,j), GameRunner.getGrass().get(2)),new Location(i,j)); }
                else if(rand < .65)
                { GameRunner.setTile(new GrassTile(new Location(i,j), GameRunner.getGrass().get(3)),new Location(i,j)); }
                else if(rand < .75)
                { GameRunner.setTile(new GrassTile(new Location(i,j), GameRunner.getGrass().get(4)),new Location(i,j)); }
                else if (Math.random() < .9)
                { GameRunner.setTile(new ForestTile(new Location(i,j), GameRunner.getForest()),new Location(i,j)); }
                else
                { GameRunner.setTile(new WaterTile(new Location(i,j), GameRunner.getWater().get(0)),new Location(i,j)); }
            }
        }
        
        run.start();
        if(gameType==1)
        {
        GameRunner.setTile(new GoldMine(new Location(2,2), GameRunner.getGoldMine(), GameRunner.getGrass().get(2)),new Location(2,2));
        GameRunner.setTile(new GoldMine(new Location(3,5), GameRunner.getGoldMine(), GameRunner.getGrass().get(2)),new Location(3,7));
        GameRunner.setTile(new GoldMine(new Location(18,18), GameRunner.getGoldMine(), GameRunner.getGrass().get(2)),new Location(18,18));

        
         ArrayList<Unit> p1Units = new ArrayList<Unit>();
         ArrayList<Unit> p2Units = new ArrayList<Unit>();
         
         for(int i = 5; i < 9;i++)
         {
             GameRunner.setTile(new GrassTile(new Location(3,i), GameRunner.getGrass().get(0)),new Location(3,i));
             GameRunner.setTile(new GrassTile(new Location(4,i), GameRunner.getGrass().get(0)),new Location(4,i));
             GameRunner.setTile(new GrassTile(new Location(5,i), GameRunner.getGrass().get(0)),new Location(5,i));
             Archer a = new Archer(new Location(3,i), GameRunner.getArcher());
             Swordsman s = new Swordsman(new Location(4,i), GameRunner.getSwordsman());
             Horseman h = new Horseman(new Location(5,i), GameRunner.getHorseman());
             p1Units.add(a);
             p1Units.add(s);
             p1Units.add(h);
         }

         Castle ca = new Castle(new Location(3,3), GameRunner.getCastle());
         p1Units.add(ca);
         
         MagesTower s = new MagesTower(new Location(16,16), GameRunner.getMageTower());
         ArchMage am = new ArchMage(new Location(15,15), GameRunner.getArchMage());
         Butcher b = new Butcher(new Location(16,15), GameRunner.getButcher());
         FireSpirit f = new FireSpirit(new Location(15,16), GameRunner.getFireSpirit());
         p2Units.add(s);
         p2Units.add(am);
         p2Units.add(b);
         p2Units.add(f);
        
        
        // Set players
        ArrayList<Player> players = new ArrayList<Player>();
        Player player1;
        Player player2;
        player1 = new Player(500, true, p1Units, "Azdens",3);
        player2 = new PlayerBot(500, false, p2Units, "The Society of Souls", 1 , .5f, 10);
        GameRunner.setBotBattle(false);
        players.add(player1);
        players.add(player2);
        run.setupPlayers(players);
        String[] di = {"Royal Message",
                 "The King",
                 "It's time for some fun!!!!",
                 "So a long time ago...",
                 "the grand Azden empire was at war with the Society of Souls.",
                 "this powerfull mage named Dour was expelled for the Society of Souls.",
                 "He then chaneled his anger into making the Golden T-Shirt of Immortality.",
                 "Dour then used this shirt to destroy every city but one.", 
                 "He was only defeated when the Azdens and the Society of Souls came together",
                 "under the great king Osiris and Dour was sent into eternal darkness.",
                 "The shirt was then locked away on Dread Island where Dour was killed until now.",
                 "The Society of Souls has taken the shirt for themselves.",
                 "You will help me kill all of them for this.",
                 "blueLets start by destroying the nearby mages tower using.",
                 "redSome forces forces are awaiting your command near the castle.",
                 "redI would also like you to mine as much gold as you can.",
                 "I have to buy a new gold solid gold pillow."};
        Dialogue d = new Dialogue(GameRunner.getKing(),di);
        
        }
        else if(gameType==2)
        {
        for(int i = 6; i < 16; i++)
        {
            for(int j = 0; j < 20; j++)
            {
                if (Math.random() < .7||j == 8)
                { GameRunner.setTile(new ForestTile(new Location(i,j), GameRunner.getForest()),new Location(i,j)); }
                else
                { GameRunner.setTile(new WaterTile(new Location(i,j), GameRunner.getWater().get(0)),new Location(i,j)); }
            }
        }
        GameRunner.setTile(new GoldMine(new Location(3,13), GameRunner.getGoldMine(), GameRunner.getGrass().get(2)),new Location(3,13));
        GameRunner.setTile(new GoldMine(new Location(3,7), GameRunner.getGoldMine(), GameRunner.getGrass().get(2)),new Location(3,7));
        GameRunner.setTile(new GoldMine(new Location(18,10), GameRunner.getGoldMine(), GameRunner.getGrass().get(2)),new Location(18,10));

        
         ArrayList<Unit> p1Units = new ArrayList<Unit>();
         ArrayList<Unit> p2Units = new ArrayList<Unit>();
         

         Castle ca = new Castle(new Location(3,4), GameRunner.getCastle());
         p1Units.add(ca);
         Summoner s = new Summoner(new Location(17,10), GameRunner.getSummoner());
         p2Units.add(s);
        
        
        // Set players
        ArrayList<Player> players = new ArrayList<Player>();
        Player player1;
        Player player2;
        player1 = new Player(500, true, p1Units, "Azdens",2);
        player2 = new PlayerBot(1000, false, p2Units, "The Society of Souls", 3 , .5f, 100000);
        GameRunner.setBotBattle(false);
        players.add(player1);
        players.add(player2);
        run.setupPlayers(players);
        String[] di = {"Royal Message",
                 "The King",
                 "Good work!!",
                 "A summoner form that mages tower you destroyed fled into a swamp near the castle.",
                 "He is a powerful summoner who runs the local mages circle.",
                 "blueYou will need to go throught he swamp to kill him.",
                 "redI would recomend using swordsman and archers to quickly move through the trees.",
                 "redLets show him why you don't take peoples t-shirts from them."};
        Dialogue d = new Dialogue(GameRunner.getKing(),di);
        }
        
        
        
    }
    
    
    /**
     * This method calls the GameRunner class which handles all of the in-game 
     * functions and it stops the title screens sound
     */
    public static void randomGame(int gameType)
    {
        GameRunner.setBotBattle(true);
        game = gameType;
        int r = 20;
        int c = 20;
        GameRunner run = new GameRunner(); 
        if(GameRunner.getForest()==null)
        { GameRunner.setupImage(); }
        GameRunner.setupSize(r, c);
        for(int i = 0; i < r; i++)
        {
            for(int j = 0; j < c; j++)
            {
                double rand = Math.random();
                if(rand < .2)
                { GameRunner.setTile(new GrassTile(new Location(i,j), GameRunner.getGrass().get(0)),new Location(i,j)); }
                else if(rand<.22)
                { GameRunner.setTile(new GoldMine(new Location(i,j), GameRunner.getGoldMine(), GameRunner.getGrass().get(1)),new Location(i,j)); }
               else if(rand < .4)
                { GameRunner.setTile(new GrassTile(new Location(i,j), GameRunner.getGrass().get(1)),new Location(i,j)); }
                else if(rand < .6)
                { GameRunner.setTile(new GrassTile(new Location(i,j), GameRunner.getGrass().get(2)),new Location(i,j)); }
                else if(rand < .65)
                { GameRunner.setTile(new GrassTile(new Location(i,j), GameRunner.getGrass().get(3)),new Location(i,j)); }
                else if(rand < .75)
                { GameRunner.setTile(new GrassTile(new Location(i,j), GameRunner.getGrass().get(4)),new Location(i,j)); }
                else if (Math.random() < .9)
                { GameRunner.setTile(new ForestTile(new Location(i,j), GameRunner.getForest()),new Location(i,j)); }
                else
                { GameRunner.setTile(new WaterTile(new Location(i,j), GameRunner.getWater().get(0)),new Location(i,j)); }
            }
        }
        
        GameRunner.setTile(new GoldMine(new Location(r/2,(c+1)/2), GameRunner.getGoldMine(), GameRunner.getGrass().get(2)),new Location(r/2,(c+1)/2));
        GameRunner.setTile(new GoldMine(new Location(3,3), GameRunner.getGoldMine(), GameRunner.getGrass().get(2)),new Location(3,3));
        GameRunner.setTile(new GoldMine(new Location(r-4,c-4), GameRunner.getGoldMine(), GameRunner.getGrass().get(2)),new Location(c-4,c-4));
        GameRunner.setTile(new GrassTile(new Location(4,4), GameRunner.getGrass().get(0)),new Location(4,4));
        GameRunner.setTile(new GrassTile(new Location(r-5,c-5), GameRunner.getGrass().get(0)),new Location(r-5,c-5));

        
         ArrayList<Unit> p1Units = new ArrayList<Unit>();
         ArrayList<Unit> p2Units = new ArrayList<Unit>();
         

         Castle ca = new Castle(new Location(4,4), GameRunner.getCastle());
         //Summoner s = new Summoner(new Location(4,12), GameRunner.getSummoner());
         p1Units.add(ca);
         MagesTower t = new MagesTower(new Location(r-5,c-5), GameRunner.getMageTower());
         p2Units.add(t);
         //p2Units.add(s);
         //p2Units.add(ma);
         
        
        
        // Set players
        ArrayList<Player> players = new ArrayList<Player>();
        Player player1;
        Player player2;
        if(gameType==101||gameType==103)
        { player1 = new Player(990, true, p1Units, "Azdens"); }
        else
        { player1 = new PlayerBot(990, true, p1Units, "Azdens", ((((float)Math.random())/10.0f)*8.0f)+.1f, (int)(Math.random()*10)+5); }
        
        if(gameType==102||gameType==103)
        { player2 = new Player(990, false, p2Units, "The Society of Souls"); }
        else
        { player2 = new PlayerBot(990, false, p2Units, "The Society of Souls", ((((float)Math.random())/10.0f)*8.0f)+.1f, (int)(Math.random()*10)+5); }
        if(gameType!=100)
        { GameRunner.setBotBattle(false); }
        players.add(player1);
        players.add(player2);
        run.setupPlayers(players);
        
        Sound.stopSound();
        run.start();
        
    }
    
    
    public static int getGame()
    { return game; }
    
    public static void setGame(int game)
    { StartGame.game = game; }
    
    
    public static void startFromFile(String name)
    {
        Sound.stopSound();
        SaveLoad.loadGame();
    }
    
}
