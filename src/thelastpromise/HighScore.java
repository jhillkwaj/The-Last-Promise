/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package thelastpromise;

import java.util.ArrayList;

/**
 * This class returns the arraylist of strings in the form of "name-moves", 
 * which has the high-scores added. 
 */
public class HighScore {
    
    //return arraylist of strings in form "name-moves"
   public static ArrayList<String> get()
   {
       ArrayList<String> scores = new ArrayList<String>();
       scores.add("Justin Hill-1");
       scores.add("Justin Hill-2");
       return scores;
   }
}
