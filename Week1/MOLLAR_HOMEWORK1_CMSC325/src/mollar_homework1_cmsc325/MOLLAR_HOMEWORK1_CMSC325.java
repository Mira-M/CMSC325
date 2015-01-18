/*
 * Project: Homework 1 
 * CMSC 325 - Spring 2015
 * Shamireya Mollar 
 * Professor Wiseman
 * Due: January 18, 2015
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

/**
 *
 * @author Shamireya Mollar
 */
public class MOLLAR_HOMEWORK1_CMSC325 extends JPanel implements ActionListener  
{
    final String title = "Coin Toss Game";
    JButton btnHeads;
    JButton btnTails;
    JButton btnProb;
    
    JRadioButton radManual;
    JRadioButton radAuto;
    
    final JTextArea tossResultText;
    final JTextArea welcome;
    
    static JScrollPane scroll;
    
    ButtonGroup gameType;
    int choice;
    String flipResult;
    int flipIntResult;
    int autoSelect;
    
    ArrayList<String> resultData;
    ArrayList<Integer> openData;
    ArrayList<Integer> resultIntData;
    
    StringBuilder sbProb;
    
    public MOLLAR_HOMEWORK1_CMSC325 () 
    {
        super(new BorderLayout());
          
        welcome = new JTextArea("Instructions");
        welcome.setPreferredSize(new Dimension(300, 80));
        welcome.setText("Beginning Text Area");
        add(welcome,BorderLayout.PAGE_START);
          
        gameType = new ButtonGroup();
         
        radManual = new JRadioButton("Automatic");
        add(radManual,BorderLayout.PAGE_START);
        radManual.addActionListener(this);
        
        btnProb = new JButton("Probability");
        btnProb.setPreferredSize(new Dimension(180,80));
        add(btnProb, BorderLayout.LINE_START);
        btnProb.addActionListener(this);
         
        gameType.add(radManual);
        gameType.add(radAuto);
          
        btnHeads = new JButton("HEADS TOSS");
        btnHeads.setPreferredSize(new Dimension(180, 50));
        add(btnHeads, BorderLayout.CENTER);
        btnHeads.addActionListener(this);
        
         
        btnTails = new JButton("TAILS TOSS");
        btnTails.setPreferredSize(new Dimension(180, 50));
        add(btnTails, BorderLayout.LINE_END);
        btnTails.addActionListener(this); 

        tossResultText = new JTextArea("Instructions");
        tossResultText.setText("Ending Text Area");
        tossResultText.setLineWrap(true);
        
        scroll = new JScrollPane(tossResultText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
            , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(400,300));
        add(scroll, BorderLayout.PAGE_END);
        
        resultData = new ArrayList<> ();
        openData = new ArrayList<> ();
        resultIntData = new ArrayList<> ();
        
        sbProb = new StringBuilder();
        
         
    }//End MOLLAR_HOMEWORK1_CMSC325
    
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
    }//End GUI()

    public static void main(String[] args) 
    {
        
         javax.swing.SwingUtilities.invokeLater(new Runnable() 
         {
         public void run() 
         {
         MOLLAR_HOMEWORK1_CMSC325.GUI();
         }
         });

    }//End Main

    @Override
    public void actionPerformed(final ActionEvent e) 
    {
        
      Toolkit.getDefaultToolkit().beep();
         javax.swing.SwingUtilities.invokeLater(new Runnable() {
         public void run() {
                switch (e.getActionCommand()) {
                     case "HEADS TOSS" :
                          choice = 0;
                          ProcessFlip headsFlip = new ProcessFlip();
                          headsFlip.tossCoin();
                          flipResult = headsFlip.getTossResult();
                          
                          if (flipResult.equals("Heads"))
                          {
                            tossResultText.append("\n" + "Your Choice: Heads! Actual Toss: " + flipResult + "... You Win!");
                          } else
                          {
                            tossResultText.append("\n" + "Your Choice: Heads! Actual Toss: " + flipResult + "... Sorry, You Lose :(");
                          }
                          
                        break;
                         
                     case "TAILS TOSS" :
                          choice = 1;
                          ProcessFlip tailsFlip = new ProcessFlip();
                          tailsFlip.tossCoin();
                          flipResult = tailsFlip.getTossResult();
                          
                          if (flipResult.equals("Tails"))
                          {
                            tossResultText.append("\n" + "Your Choice: Tails! Actual Toss: " + flipResult + "... You Win!");
                          } else
                          {
                            tossResultText.append("\n" + "Your Choice: Tails! Actual Toss: " + flipResult + "... Sorry, You Lose :(");
                          }
                          
                        break;
                         
                     case "Automatic" :
                         int count = 0;
                         StrategyRandom autoToss = new StrategyRandom();
                         ProcessFlip autoFlip = new ProcessFlip();
                         
                         while (count < 101) {
                             //Randomize Choices
                             autoSelect = autoToss.nextMove();
                             autoFlip.tossCoin();
                             flipResult = autoFlip.getTossResult();
                             resultData.add(flipResult);
                             
                             flipIntResult = autoFlip.getResultInt();
                             resultIntData.add(flipIntResult);
                             tossResultText.append("\n" + flipResult);
                         count++;
                         }
                                
                            try {
                                FileManager manageFile = new FileManager();
                                manageFile.saveRandom(resultData);
                                manageFile.saveProb(resultIntData);
                          
                            } catch (IOException ex) {
                                Logger.getLogger(MOLLAR_HOMEWORK1_CMSC325.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                
                         break;
                         
                     case "Probability" :
                            try {
                                FileManager openFile = new FileManager();

                                openFile.readProb();
                                openData = openFile.getDataProb();
                                
                                    for (int s : openData)
                                      {
                                          sbProb.append("");
                                          sbProb.append(s);
                                      }
                                 StrategyProbabilistic stratProb = new StrategyProbabilistic();
                                 
                                 stratProb.setMoves(sbProb.toString());
                                 
                                 System.out.println("sbProb = " + sbProb);
                                 System.out.println("sbProb.toString() = " + sbProb.toString());
                             
                                ProbFlip.createAndShowGUI(stratProb);

                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(MOLLAR_HOMEWORK1_CMSC325.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (        IOException | ClassNotFoundException ex) {
                                Logger.getLogger(MOLLAR_HOMEWORK1_CMSC325.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         
                         
                         break;
                 }// Switch
                 }// Run
             });
    }//End actionPerformed()
}//end Class
