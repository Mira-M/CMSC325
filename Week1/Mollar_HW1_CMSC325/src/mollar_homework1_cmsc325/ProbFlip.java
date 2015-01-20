/*
 * Project: Homework 1
 * CMSC 325 - Spring 2015
 * Shamireya Mollar
 * Professor Wiseman
 * Due: January 18, 2015
 * 
 * PURPOSE: 
 * The ProbFlip class is the handler for results for the probability tosses
 */

package mollar_homework1_cmsc325;

public class ProbFlip {

    Strategy toss;                           //Instance of the Strategy class to run probability
    String heads;                            //Holds the string value of "heads" toss result
    String tails;                            //Holds the string value of "tails" toss result
    String result;                           //Holds toss string result value
    int resultMove;                          //Holds toss int result value

    ProbFlip(Strategy stratProb) {

        //Initialize elements
        heads = "Heads";
        tails = "Tails";
        result = null;
        toss = stratProb;

    }//constructor ProbFlip

    public void probToss() {

        resultMove = toss.nextMove();

        if (resultMove == 0) {
            result = heads;
        } else {
            result = tails;
        }

    }// probToss()

    public int getResultInt() {
        return resultMove;
    }
    
    public String getResultString() 
    {
        return result;
    }
}//class ProbFlip
