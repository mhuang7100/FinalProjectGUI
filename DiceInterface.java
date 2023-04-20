import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.*;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class DiceInterface{
    private static Frame frame;
    public static void createDice(){
        ImageIcon iconOne = new ImageIcon("/workspace/FinalProjectGUI/Dice1.jpg");
        ImageIcon iconTwo = new ImageIcon("/workspace/FinalProjectGUI/Dice2.jpg");
        ImageIcon iconThree = new ImageIcon("/workspace/FinalProjectGUI/Dice3.jpg");
        ImageIcon iconFour = new ImageIcon("/workspace/FinalProjectGUI/Dice4.jpg");
        ImageIcon iconFive = new ImageIcon("/workspace/FinalProjectGUI/Dice5.jpg");
        ImageIcon iconSix = new ImageIcon("/workspace/FinalProjectGUI/Dice6.jpg");
        ArrayList<ImageIcon> sixDice = new ArrayList<ImageIcon>(Arrays.asList(
            iconOne, iconTwo, iconThree, iconFour, iconFive, iconSix));
        
        int x = 1300;
        int y = 400;
        for (int i = 0; i < 5; i++){
            final int num = i; // used to record which die it is
            // gets the corresponding image of the dice that was rolled
            JButton dice = new JButton(sixDice.get(YahtzeeDice.getDice()[i] - 1));  
            Dimension diceSize = dice.getPreferredSize(); //Gets the size of the image
            dice.setBounds(x, y, 145, 145); //Sets the location of the image
            dice.setBorderPainted(false);
            dice.setContentAreaFilled(false);

            // action on click
            dice.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                        YahtzeeDice.keepDice(num);
                }  
            }); 
            frame.add(dice);

            if (i == 2){
                y += 145;
                x -= 363;
            }
            x += 145;
        }
        

        
    }

    public static void setFrame(Frame f){
        frame = f;
    }
}