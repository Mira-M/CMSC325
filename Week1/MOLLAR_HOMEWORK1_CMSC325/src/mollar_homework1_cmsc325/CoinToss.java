/*
 * Project: Homework 1 
 * CMSC 325 - Spring 2015
 * Shamireya Mollar 
 * Professor Wiseman
 * Due: January 18, 2015
 */
package mollar_homework1_cmsc325;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Mark
 */
public class CoinToss extends JPanel 
  implements ActionListener {
     JButton buttonP1;
     JButton buttonP2;
     JTextArea howto;
     
     
  /**
   * Prisoner's Dilemma program.
   */
   Strategy toss;

   String heads = "Heads";
   String tails = "Tails";

   String result;
   String choiceString;
   int resultMove;
   int choice;

   public CoinToss(int choice, Strategy toss)
      {
         super(new BorderLayout());
         buttonP1 = new JButton(toss.name);
         buttonP1.setPreferredSize(new Dimension(180,180));
         add(buttonP1, BorderLayout.LINE_START);
         buttonP1.addActionListener(this);
         
      this.toss = toss;
      this.choice = choice;
      
      }  /* PrisonersDilemma */
   
   /**
      * Create the GUI and show it.  For thread safety,
      * this method should be invoked from the
      * event-dispatching thread.
      */
     public static void createAndShowGUI(int choice, Strategy toss) {
         //Create and set up the window.
         JFrame frame = new JFrame("Coin Toss");
         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

         //Create and set up the content pane.
         JComponent newContentPane = new CoinToss(choice, toss);
         newContentPane.setOpaque(true); //content panes must be opaque
         frame.setContentPane(newContentPane);

         //Display the window.
         frame.pack();
         frame.setVisible(true);
     }
     
   public void actionPerformed(ActionEvent e) {
       playPD();
       
       CoinToss.infoBox("Your Guess = " + choiceString + " : Toss Result = " + result , e.getActionCommand());
     }

    public static void infoBox(String infoMessage, String location)
    {
        JOptionPane.showMessageDialog(null, infoMessage, location, JOptionPane.INFORMATION_MESSAGE);
    }
    
   public int playPD()
      {
     // 0 = defect, 1 = cooperate
      resultMove = toss.nextMove();

      toss.saveMyMove(resultMove);

      if (resultMove == 0)
         {
         result = heads;
         }
      else if (resultMove == 1)
         {
         result = tails;
         }
      
      /* Convert Toss to String */
      if (choice == 0)
      {
          choiceString = "Heads";
      }
      else if (choice == 1)
      {
          choiceString = "Tails";
      }
      return 0;
      }  /* playPD */

   public int getPlayerToss()  { return resultMove; }
   public String getTossResult()  { return result; }
   
   }  /* class PrisonersDilemmd */