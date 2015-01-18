/*
 * Project: Homework 1 
 * CMSC 325 - Spring 2015
 * Shamireya Mollar 
 * Professor Wiseman
 * Due: January 18, 2015
 */
package mollar_homework1_cmsc325;

public class StrategyRandom extends Strategy
 
   {
 
  /**
 
   * Encoding for a strategy.
 
   */
 
  // 0 = heads, 1 = tails
 
   public StrategyRandom()
 
      {
 
      name = "Random";
 
      }  /* StrategyRandom */
 

 
   @Override
   public int nextMove()
 
      {
 
      if (Math.random() < 0.5)  return 1;
 
      return 0;
 
      }  /* nextMove */
 
   }  /* class StrategyRandom */
