import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class DiceInterface{
    private static Frame frame;
    private final static ImageIcon iconOne = new ImageIcon("/workspace/FinalProjectGUI/Dice1.jpg");
    private final static ImageIcon iconTwo = new ImageIcon("/workspace/FinalProjectGUI/Dice2.jpg");
    private final static ImageIcon iconThree = new ImageIcon("/workspace/FinalProjectGUI/Dice3.jpg");
    private final static ImageIcon iconFour = new ImageIcon("/workspace/FinalProjectGUI/Dice4.jpg");
    private final static ImageIcon iconFive = new ImageIcon("/workspace/FinalProjectGUI/Dice5.jpg");
    private final static ImageIcon iconSix = new ImageIcon("/workspace/FinalProjectGUI/Dice6.jpg");
    private static ArrayList<ImageIcon> diceImages = new ArrayList<ImageIcon>(Arrays.asList(
        iconOne, iconTwo, iconThree, iconFour, iconFive, iconSix));
    private static ArrayList<JButton> fiveDice = new ArrayList<JButton>();
    private static JButton roll;

    public static void createDice(){
        int x = 1300;
        int y = 400;
        for (int i = 0; i < 5; i++){
            final int num = i; // used to record which die it is
            // gets the corresponding image of the dice that was rolled
            JButton dice = new JButton(diceImages.get(YahtzeeDice.getDice()[i] - 1));  
            dice.setBounds(x, y, 145, 145); // 145 is the sidelength of the dice
            dice.setBorderPainted(false);
            dice.setContentAreaFilled(false);
            dice.setBorder(BorderFactory.createLineBorder(Color.red, 4));

            // action on click
            dice.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    YahtzeeDice.keepDice(num, dice);
                }  
            }); 
            frame.add(dice);
            fiveDice.add(dice);

            if (i == 2){
                y += 145;
                x -= 363;
            }
            x += 145;
        }
        
        int btnL = 180;
        roll = new JButton("Rolls Remaining: " + 3);  
        roll.setBounds(x - 145 - btnL / 2, y + 220, btnL, 50); 
        roll.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                YahtzeeDice.roll();
            }  
        }); 
        frame.add(roll);
        
    }

    public static void setFrame(Frame f){
        frame = f;
    }

    public static JButton getDice(int i){
        return fiveDice.get(i);
    }

    public static void changeDiceImg(int dice, int num){
        getDice(dice).setIcon(diceImages.get(num));
    }

    public static JButton getRollBtn(){
        return roll;
    }

    public static void updateRollBtn(){
        roll.setText("Rolls Remaining: " + Integer.toString(YahtzeeDice.getRolls()));
    }


}