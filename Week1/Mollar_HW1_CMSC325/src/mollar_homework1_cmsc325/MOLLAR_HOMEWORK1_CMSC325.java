/*
 * Project: Homework 1
 * CMSC 325 - Spring 2015
 * Shamireya Mollar
 * Professor Wiseman
 * Due: January 18, 2015
 * 
 * PURPOSE: 
 * To create a game that mimics tossing a coin. 
 * 
 * FEATURES:
 * 1) Accepts a guess from the user, either "heads" or "tails" in Manual play section
 * 2) Option to auto generate results of a toss with the computer guessing either "heads" or "tails"
 * 3) Third option to generate a guess based on probability results from auto toss results. 
 * 4) Saves results into external files
 */
package mollar_homework1_cmsc325;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class MOLLAR_HOMEWORK1_CMSC325 extends JPanel implements ActionListener 
{

    final String title = "Coin Toss Game";                  // Game Title
    JButton btnHeads;                                       // Button for "Heads" Selection - Manual
    JButton btnTails;                                       // Button for "Tails" Selection - Manual
    JButton btnProb;                                        // Button for "Probability" Selection - Manual
    JButton btnAuto;                                        // Radio Button for "Auto" Run of Game
    final JTextArea tossResultText;                         // Main Text Area - Toss Results Displayed here
    final JTextArea welcome;                                // Welcome Text for user
    static JScrollPane scroll;                             // Scroll Option for tossResultText
    int choice;                                             // Holds user's choice for either heads or tails
    String flipResult;                                      // Holds a string flip result to display to user
    int flipIntResult;                                      // Holds an int flip result - for probability data
    int guess;                                              // Holds user guesses
    String guessString;                                     // Holds user guesses - string
    int autoWins;                                           // Holds the number of wins for the Automatic feature
    int autoLosses;                                         // Holds the number of losses for the Automatic feature
    int heads;
    int tails;
    int headsR;
    int tailsR;
    ArrayList<String> resultData;                           // Holds the string values of all results for auto plays
    ArrayList<Integer> openData;                            // Holds the int values of results retrieved from save file
    ArrayList<Integer> resultIntData;                       // Holds the int values of results for probability plays
    ArrayList<Integer> guessIntData;                        // Holds the int guess values for the auto play
    StringBuilder sbProbResult;                             // Used to parse string to the StrategyProbabilistic Class - Results
    StringBuilder sbProbGuess;                              // Used to parse string to the StrategyProbabilistic Class - Guesses

    public MOLLAR_HOMEWORK1_CMSC325() 
    {
        super(new BorderLayout());
        
        welcome = new JTextArea("Instructions");
        welcome.setPreferredSize(new Dimension(300, 80));
        welcome.setText("Beginning Text Area");
        add(welcome, BorderLayout.PAGE_START);

        btnAuto = new JButton("Automatic");
        btnAuto.setPreferredSize(new Dimension(300, 80));
        add(btnAuto, BorderLayout.PAGE_START);
        btnAuto.addActionListener(this);

        btnProb = new JButton("Probability");
        btnProb.setPreferredSize(new Dimension(180, 80));
        add(btnProb, BorderLayout.LINE_START);
        btnProb.addActionListener(this);

        btnHeads = new JButton("HEADS TOSS");
        btnHeads.setPreferredSize(new Dimension(180, 50));
        add(btnHeads, BorderLayout.CENTER);
        btnHeads.addActionListener(this);


        btnTails = new JButton("TAILS TOSS");
        btnTails.setPreferredSize(new Dimension(180, 50));
        add(btnTails, BorderLayout.LINE_END);
        btnTails.addActionListener(this);

        tossResultText = new JTextArea();
//        tossResultText.setText("Ending Text Area");
        tossResultText.setLineWrap(true);
        
        scroll = new JScrollPane(tossResultText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(400, 300));
        add(scroll, BorderLayout.PAGE_END);

        resultData = new ArrayList<>();
        openData = new ArrayList<>();
        resultIntData = new ArrayList<>();
        guessIntData = new ArrayList<>();

        sbProbResult = new StringBuilder();
        sbProbGuess = new StringBuilder();
        

    }// MOLLAR_HOMEWORK1_CMSC325

    public static void GUI() 
    {
        //Create and set up the window.
        JFrame frame = new JFrame("Coin Toss Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new MOLLAR_HOMEWORK1_CMSC325();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }// GUI()

    public static void main(String[] args) 
    {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MOLLAR_HOMEWORK1_CMSC325.GUI();
            } // run()
        });// Runnable()

    }// Main

    @Override
    public void actionPerformed(final ActionEvent e) 
    {
        Toolkit.getDefaultToolkit().beep();
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                switch (e.getActionCommand()) 
                {
                    /*
                     * This section allows the user to toss the coin manually.
                     * After each roll, the program reports to the user their selection
                     * and the result of their flip (Win or Lose). The options are either
                     * Heads or Tails.
                     */
                    case "HEADS TOSS":
                        choice = 0;
                        ProcessFlip headsFlip = new ProcessFlip();
                        headsFlip.tossCoin();
                        flipResult = headsFlip.getTossResult();

                        if (flipResult.equals("Heads")) 
                        {
                            tossResultText.append("\n" + "Your Choice: Heads! Actual Toss: " + flipResult + "... You Win!");
                        } // if(flipResult.equals("Heads")
                        else 
                        {
                            tossResultText.append("\n" + "Your Choice: Heads! Actual Toss: " + flipResult + "... Sorry, You Lose :(");
                        } // else
                        

                        break;

                    case "TAILS TOSS":
                        
                        choice = 1;
                        ProcessFlip tailsFlip = new ProcessFlip();
                        tailsFlip.tossCoin();
                        flipResult = tailsFlip.getTossResult();

                        if (flipResult.equals("Tails")) 
                        {
                            tossResultText.append("\n" + "Your Choice: Tails! Actual Toss: " + flipResult + "... You Win!");
                        } // if (flipResult.equals("Tails")
                        else 
                        {
                            tossResultText.append("\n" + "Your Choice: Tails! Actual Toss: " + flipResult + "... Sorry, You Lose :(");
                        }// else

                        break;

                    /*
                     * This section uses the StrategyRandom Class to generate a random choice
                     * for the user, then tosses the coin and displays the result to the user
                     * This continues for 100 turns, the results are stored both as the literal
                     * string text "Heads" or "Tails" and as the corresponding integer (0 - Heads
                     * 1 - Tails).  These are stored as Array Lists and passed through to the File
                     * Manager class to be saved to a file
                     */

                    case "Automatic":
                        int count = 0;
                        autoWins = 0;
                        autoLosses = 0;
                        heads = 0;
                        tails = 0;
                        headsR = 0;
                        tailsR = 0;

                        StrategyRandom autoToss = new StrategyRandom();
                        ProcessFlip autoFlip = new ProcessFlip();

                        while (count < 100) {
                            //Randomize Choices
                            guess = autoToss.nextMove();
                            autoFlip.tossCoin();
                            
                            flipResult = autoFlip.getTossResult();
                            resultData.add(flipResult);

                            flipIntResult = autoFlip.getResultInt();
                            resultIntData.add(flipIntResult);
                            guessIntData.add(guess);

                            /*
                             * This section prints the computer's choice then based on the
                             * on the computer's selection, communicates to the user if
                             * the toss was a winning toss or not!
                             */
                            if (guess == 0) 
                            {
                                tossResultText.append("\n" + "The computer chose: "
                                        + "Heads! Actual Toss: " + flipResult);
                                
                                if (flipResult.equals("Heads")) 
                                {
                                    tossResultText.append("... You Win!");
                                    autoWins++;
                                    headsR++;

                                } else 
                                {
                                    tossResultText.append("... Sorry, You Lose :(");
                                    autoLosses++;
                                    tailsR++;
                                }
                                heads++;
                                
                            } else 
                            {
                                tossResultText.append("\n" + "The computer chose: "
                                        + "Tails! Actual Toss: " + flipResult);
                                
                                if (flipResult.equals("Tails")) 
                                {
                                    tossResultText.append("... You Win!");
                                    autoWins++;
                                    tailsR++;
                                } 
                                else 
                                {
                                    tossResultText.append("... Sorry, You Lose :(");
                                    autoLosses++;
                                    headsR++;
                                }
                                tails++;
                            }
                            count++;

                        }
                        //Displays toss results to user
                        tossResultText.append("\n| Wins : " + autoWins + " | Losses: " + autoLosses + " |");
                        tossResultText.append("\n| Guesses \"Head\": " + heads + " | Guesses \"Tails\": " + tails + " |");
                        tossResultText.append("\n| Results \"Head\": " + headsR + " | Results \"Tails\": " + tailsR + " |");

                        try {
                            
                            /* Saves the results of the auto run in string and  
                             * integer formats.
                             */
                            AutoFileManager manageFile = new AutoFileManager();
                            manageFile.saveRandom(resultData);
                            manageFile.saveProb(resultIntData);

                        } catch (IOException ex) {
                            Logger.getLogger(MOLLAR_HOMEWORK1_CMSC325.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        break;
                    /*
                     * This section uses the StrategyProbabilistic class to generate 
                     * a toss selection based on the results of the automatic running of the 
                     * game. The 100 results are used to determine the probability that the 
                     * Toss will actually result in either heads or tails.  
                     */
                        
                    case "Probability":
                        autoWins = 0;
                        autoLosses = 0;
                        heads = 0;
                        tails = 0;
                        headsR = 0;
                        tailsR = 0;
                        
                        
                        try {
                            // Creates new file and opens the results from the auto run
                            AutoFileManager openFile = new AutoFileManager();

                            openFile.readProb();
                            openData = openFile.getDataProb();
                            
                            /* Parses the ints from the auto run and converts it to 
                             * one string to be read by the StrategyProbabilistic Class. 
                             */
                            

                            ListIterator it = openData.listIterator();
                            while (it.hasNext()) 
                            {
                                sbProbResult.append(it.next());
                            }
                            
                            ListIterator gIt = guessIntData.listIterator();
                            while(gIt.hasNext())
                            {
                                sbProbGuess.append(gIt.next());
                            }

                            tossResultText.setText(null);
                            
                            StrategyProbabilistic probResult = new StrategyProbabilistic();
                            probResult.setMoves(sbProbResult.toString());
                            
                            StrategyProbabilistic probGuess = new StrategyProbabilistic();
                            probGuess.setMoves(sbProbGuess.toString());
                            
                            int probCount = 0;
                            
                            while (probCount < 100)
                            {
                                ProbFlip flip = new ProbFlip(probResult); 
                                flip.probToss();
                                flipResult = flip.getResultString();
                                
                                ProbFlip flipGuess = new ProbFlip(probGuess);
                                flipGuess.probToss();
                                guessString = flipGuess.getResultString();
                                
                                if (guessString.equals("Heads")) 
                                {
                                tossResultText.append("\n" + "The Probability Guess: "
                                        + "Heads! Probability Toss: " + flipResult);
                                        if (flipResult.equals("Heads")) 
                                        {
                                            tossResultText.append("... You Win!");
                                            autoWins++;
                                            headsR++;
                                        } // if (flipResult.equals("Heads"))
                                        else 
                                        {
                                            tossResultText.append("... Sorry, You Lose :(");
                                            autoLosses++;
                                            tailsR++;
                                        } // Else (flipResult.equals("Heads"))
                                     heads++;
                                } // if(guessString.equals("Heads")) 
                                else 
                                {
                                tossResultText.append("\n" + "The Probability Guess : "
                                        + "Tails! Probability Toss: " + flipResult);
                                        if (flipResult.equals("Tails")) 
                                        {
                                            tossResultText.append("... You Win!");
                                            autoWins++;
                                            tailsR++;
                                        } // if (flipResult.equals("Tails"))
                                        else 
                                        {
                                            tossResultText.append("... Sorry, You Lose :(");
                                            autoLosses++;
                                            headsR++;
                                        } // else (flipResult.equals("Tails"))
                                        
                                        tails++;
                                 } // else (guessString.equals("Heads"))
                                
                                
                                probCount++;
                            }// while
                            
                            //Displays toss results to user
                        tossResultText.append("\n| Wins : " + autoWins + " | Losses: " + autoLosses + " |");
                        tossResultText.append("\n| Guesses \"Head\": " + heads + " | Guesses \"Tails\": " + tails + " |");
                        tossResultText.append("\n| Results \"Head\": " + headsR + " | Results \"Tails\": " + tailsR + " |");


                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(MOLLAR_HOMEWORK1_CMSC325.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException | ClassNotFoundException ex) {
                            Logger.getLogger(MOLLAR_HOMEWORK1_CMSC325.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        break;
                }// Switch
            }// Run
        });
    }//End actionPerformed()
}//end Class
