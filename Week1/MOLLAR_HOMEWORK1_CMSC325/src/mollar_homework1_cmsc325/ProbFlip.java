/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mollar_homework1_cmsc325;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class ProbFlip extends JPanel implements ActionListener{
   JButton btnHeads;
   JButton btnTails;
   JTextArea info;

   Strategy toss;

   String heads;
   String tails;

   String result;
   String choiceString;
   int resultMove;
   int sbIt;
   
   String probMoves;
   
   ArrayList <Integer> resultData;
   StringBuilder sbProbString;
    
    ProbFlip(Strategy stratProb) 
    {
       //Initialize GUI elements
       super(new BorderLayout());
       
       //Initialize elements
       heads = "Heads";
       tails = "Tails";
       result = "null";
       choiceString = "null";
       probMoves = "";
       sbProbString = new StringBuilder();
       toss = new Strategy();
       
       info = new JTextArea("Instructions");
       info.setText("Probability");
       add(info,BorderLayout.CENTER);
       
       btnHeads = new JButton("START");
       btnHeads.setPreferredSize(new Dimension(180, 50));
       add(btnHeads, BorderLayout.LINE_START);
       btnHeads.addActionListener(this);
       
       toss = stratProb;
      
    }//constructor ProbFlip
    
    public void run(){
         javax.swing.SwingUtilities.invokeLater(new Runnable() 
         {
         public void run() 
         {
         MOLLAR_HOMEWORK1_CMSC325.GUI();
         }
         });
    }
    
    
          /**
      * Create the GUI and show it.  For thread safety,
      * this method should be invoked from the
      * event-dispatching thread.
      */
     public static void createAndShowGUI(Strategy stratProb) {
         //Create and set up the window.
         JFrame frame = new JFrame("Heads or Tails Probability");
         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

         //Create and set up the content pane.
         JComponent newContentPane = new ProbFlip(stratProb);
         newContentPane.setOpaque(true); //content panes must be opaque
         frame.setContentPane(newContentPane);

         //Display the window.
         frame.pack();
         frame.setVisible(true);
         
     }//createAndShowGUI()
     
   
   public void probToss()
   {
       int count = 0;
       while (count < 100) 
       {
            resultMove = toss.nextMove();
            System.out.println("ResultMove = " + resultMove);

            if (resultMove == 0)
            {
                result = heads;
            }
            else if (resultMove == 1)
            {
                result = tails;
            }
            else 
            {
                result = "Invalid Information";
            }
                
            info.append("\n" + result);
            
            count++;
       }

   }// probToss()
  
    @Override
    public void actionPerformed(ActionEvent e) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
                
                    System.out.println("Entered public void run()");
                    
                    System.out.println("Entering probToss()");
                    probToss();
                    info.append("completed run probToss()");
                    System.out.println("completed run probToss");
            }
            
        });
    }// actionPerformed()
    
    public int getResultInt(){ return resultMove;}
    
}//class ProbFlip
