/*
 * Project: Homework 1
 * CMSC 325 - Spring 2015
 * Shamireya Mollar
 * Professor Wiseman
 * Due: January 18, 2015
 */
package mollar_homework1_cmsc325;

public class ProcessFlip {

    StrategyRandom toss;
    String heads;
    String tails;
    String result;
    String choiceString;
    int resultMove;
    String probMoves;

    public ProcessFlip() {

        heads = "Heads";
        tails = "Tails";
        result = "null";
        choiceString = "null";
        probMoves = "";
        toss = new StrategyRandom();

    } //ProfessFlip()

    public void tossCoin() {
        // 0 = Heads, 1 = Tails
        resultMove = toss.nextMove();

        if (resultMove == 0) {
            result = heads;
        } else if (resultMove == 1) {
            result = tails;
        }
    } // tossCoin()

    public String getTossResult() {
        return result;
    } // getTossResult()

    public int getResultInt() {
        return resultMove;
    } // getResultInt()
}//End class ProcessFlip
