/*
 * Project: Homework 1 
 * CMSC 325 - Spring 2015
 * Shamireya Mollar 
 * Professor Wiseman
 * Due: January 18, 2015
 */

package mollar_homework1_cmsc325;
 
public class Strategy extends Object
 
   {
 
  /*
 
   * Encoding for a strategy.
 
   */
    
   int opponentLastMove = 1;
 
   int myLastMove = 1;
  
   String name;
 
  // 0 = heads, 1 = tails
 

 
   public Strategy()
 
      {
 
      }  /* Strategy */
 

 
   public int nextMove()
 
      {
 
      return 0;
 
      }  /* nextMove */
 
   
 
   public void saveOpponentMove(int move)  { opponentLastMove = move; }
 
   public int getOpponentLastMove()  { return opponentLastMove; }
 
   public void saveMyMove(int move)  { myLastMove = move; }
 
   public int getMyLastMove()  { return myLastMove; }
 
   public String getName()  { return name; }
 
   }  /* class Strategy */
 