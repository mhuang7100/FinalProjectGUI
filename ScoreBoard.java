import javax.swing.*;
import java.awt.*;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;  

public class ScoreBoard{
    // stores the scores of the upper/lower sections and the total score
    private int totalScore;
    private int totalScUp;
    private int bonus;          // bonus if upper section score is >=63
    private int totalScLow;
    private int Ybonus;         // bonus if the player gets more than one yahtzee

    // stores the buttons that the player presses to score
    private ArrayList<Button> upperButtons = new ArrayList<Button>();
    private ArrayList<Button> lowerButtons = new ArrayList<Button>(); 

    // the frame that contains the entire interface
    private JFrame frame;

    // displays the scores on the scoreboard
    private JLabel labelScoreUp1;
    private JLabel labelScoreBonus;
    private JLabel labelScoreUp2;
    private JLabel labelScoreYBonus;
    private JLabel labelScoreLow;
    private JLabel labelScoreUp;
    private JLabel labelScoreTotal;

    // stores the number of sectiosn that haven't been scored 
    private int sectionsLeft;
    private int sectionsLeftUp;     // used to check when to calculate the upper section bonus

    private int x;      // position of the score card (default 0, only changes if there is a second player)
    private boolean myTurn;   // used in multiplayer

    // constructor 
    public ScoreBoard(){
        sectionsLeft = 13;
        sectionsLeftUp = 6;

        totalScore = 0;
        totalScUp = 0;
        bonus = 0;
        totalScLow = 0;
        Ybonus = 0;
        x = 0;
        myTurn = true;
    }

    // uses RECURSION to create new buttons and a new frame when the "restart" button is pressed
    public void createBtnReset(JFrame f){
        JButton reset = new JButton("RESTART");
        reset.setBounds(900, 970, 150, 20);
        f.add(reset);
        reset.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                System.out.println("Final Score: " + totalScore);
                f.dispose();
                YahtzeeDice.resetKeep();
                YahtzeeDice.setRollsRemaining(3);
                YahtzeeDice.setCanScore(false);
                

                ScoreBoard one = new ScoreBoard();
                one.createFrame();
                one.createBoard();        
                one.createDiceBtn();
                one.createBtnReset(one.getFrame());
            }
        });
    }

    // sets up the frame of the scoreboard
    public void createFrame(){
        frame = new JFrame();        
        frame.setTitle("Yahtzee!"); 
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920,1080);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.WHITE);
    }

    // sets up the entire interface
    public void createBoard(){

        // labels that display the images of the yahtzee score card
        ImageIcon icon = new ImageIcon("/workspace/FinalProjectGUI/yahtzeeLEFT.jpg");
        ImageIcon icon2 = new ImageIcon("/workspace/FinalProjectGUI/yahtzeeTop.jpg");
        //left image
        JLabel left = new JLabel(icon);
        Dimension size1 = left.getPreferredSize(); //Gets the size of the image
        left.setBounds(x, 0, size1.width, size1.height); //Sets the location of the image
        // top image
        JLabel top = new JLabel(icon2);
        Dimension size2 = top.getPreferredSize(); //Gets the size of the image
        top.setBounds(x + 250, 0, size2.width, size2.height); //Sets the location of the image

        // sets up labels that display scores
        labelScoreUp1 = new JLabel(Integer.toString(totalScUp));
        labelScoreBonus = new JLabel(Integer.toString(bonus));
        labelScoreUp2 = new JLabel(Integer.toString(totalScUp));
        labelScoreYBonus = new JLabel(Integer.toString(Ybonus));
        labelScoreUp = new JLabel(Integer.toString(totalScUp));
        labelScoreLow = new JLabel(Integer.toString(totalScLow));
        labelScoreTotal = new JLabel(Integer.toString(totalScore));


        // buttons
        x += size1.width;       // puts position of the buttons to the right of the score card
        int y = 112;            // y position below the top part of the score card
        int BWidth = 41;        // desired width of the buttons
        int BLength = 53;       // desired length of the buttons
        
        // creates the buttons
        for (int i = 1; i <= 6; i++){
            final int temp = i;
            Button button1 = new Button("score");           // creates button
            button1.setBounds(x, y, BLength, BWidth);       // sets location of the button
            
            // sets button to calculate score when clicked 
            button1.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){                     
                    // displays score if the button has not been pressed and dice have been rolled
                    if (button1.getLabel().equals("score") && YahtzeeDice.getCanScore() && myTurn){
                        int tempScore = getScoreUpper(temp);   // calculates the score of the section that was clicked
                        // updates the values of the scores 
                        totalScUp += tempScore;         
                        totalScore += tempScore;
                        sectionsLeft -= 1;
                        sectionsLeftUp -= 1;

                        // displays the new scores
                        updateScUp();    // total scores
                        button1.setLabel(Integer.toString(tempScore));   // individual button 

                        // resets the dice
                        YahtzeeDice.setCanScore(false);
                        YahtzeeDice.setRollsRemaining(3);
                        YahtzeeDice.resetKeep();
                        DiceInterface.updateRollBtn();
                    }
                }  
            }); 
            upperButtons.add(button1);   // adds button to the ArrayList of buttons 
            frame.add(button1);     // adds button to the interface
            y += BWidth;    // increases the y pos of the next button so they will line up 
        }

        // sets location of upper section score labels 
        int lblWidth = 35;
        labelScoreUp1.setBounds(x + 22, y, BLength, lblWidth);   // Total Score upper before bonus
        y += lblWidth;
        labelScoreBonus.setBounds(x + 22, y, BLength, lblWidth); // bonus score
        y += lblWidth;
        labelScoreUp2.setBounds(x + 22, y, BLength, lblWidth);   // Total Score upper after bonus
        y += lblWidth;

        y += 128 - lblWidth * 3;

        
        int BWidth2 = 35;
        // creates lower buttons 
        for (int i = 1; i <= 7; i++){
            Button button1 = new Button("score");  
            button1.setBounds(x, y, BLength, BWidth2);      // location of the buttons
            final int temp = i;

            button1.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){                     
                    // if the button has not been pressed
                    if (button1.getLabel().equals("score") && YahtzeeDice.getCanScore() && myTurn){
                        int tempScore = getScoreLower(temp);  
                        totalScLow += tempScore;
                        totalScore += tempScore;
                        sectionsLeft -= 1;
                        updateScLow();

                        // display score
                        button1.setLabel(Integer.toString(tempScore));
                        YahtzeeDice.setCanScore(false);
                        YahtzeeDice.setRollsRemaining(3);
                        YahtzeeDice.resetKeep();
                        DiceInterface.updateRollBtn();
                        // transparent button ++

                        sectionsLeft -= 1; 
                    }
                }  
            });

            lowerButtons.add(button1);
            frame.add(button1);
            y += BWidth2;
        }

        // label for yahtzee bonus and the total scores 
        int lblLength2 = 28;
        labelScoreYBonus.setBounds(x + 22, y, BLength, 46);
        y += 48;
        labelScoreLow.setBounds(x + 22, y, BLength, lblLength2);
        y += lblLength2;
        labelScoreUp.setBounds(x + 22, y, BLength, lblLength2);
        y += lblLength2;
        labelScoreTotal.setBounds(x + 22, y, BLength, lblLength2);
        
        // adds all the components to the frame
        frame.add(left);
        frame.add(top);
        frame.add(labelScoreUp1);
        frame.add(labelScoreBonus);
        frame.add(labelScoreUp2);
        frame.add(labelScoreYBonus);
        frame.add(labelScoreUp);
        frame.add(labelScoreLow);
        frame.add(labelScoreTotal);
        frame.setVisible(true);
    }

    // creates the dice buttons and the roll button
    public void createDiceBtn(){
        Button firstRoll = new Button("Roll!");  
        firstRoll.setBounds(1427, 765, 180, 50); 
        frame.add(firstRoll);
        DiceInterface.setFrame(frame);
        firstRoll.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
                frame.remove(firstRoll);
                DiceInterface.createDice();
                YahtzeeDice.roll();
            }
        });
    }

    // calculates the score of a upper section button 
    public int getScoreUpper(int btnNum){
        return YahtzeeDice.calcUpper(btnNum);
    }

    // calculates the score of a lower section button 
    public int getScoreLower(int btnNum){
        if (btnNum == 1){
            return YahtzeeDice.calcThreeKind();
        } else if (btnNum == 2){
            return YahtzeeDice.calcFourKind();
        } else if (btnNum == 3){
            return YahtzeeDice.calcFullHouse();
        } else if (btnNum == 4){
            return YahtzeeDice.calcSmStraight();
        } else if (btnNum == 5){
            return YahtzeeDice.calcLgStraight();
        } else if (btnNum == 6){
            return YahtzeeDice.calcYahtzee();
        } else if (btnNum == 7){
            return YahtzeeDice.calcChance();
        }
        return 0;
    }

    public void setScoreUp(int n){
        totalScUp = n;
        labelScoreUp.setText(Integer.toString(n));
    }

    public void setScoreLow(int n){
        totalScLow = n;
        labelScoreLow.setText(Integer.toString(n));
    }

    public void setScoreTotal(int n){
        totalScore = n;
        labelScoreTotal.setText(Integer.toString(n));
    }

    public void updateScTotal(){
        labelScoreTotal.setText(Integer.toString(totalScore));
    }

    public void updateScUp(){
        labelScoreUp1.setText(Integer.toString(totalScUp));
        labelScoreUp.setText(Integer.toString(totalScUp));
        
        // calculates bonus if upper section is finished
        if (sectionsLeftUp == 0){
            if (totalScUp >= 63){
                updateBonus();
            } else labelScoreUp2.setText(Integer.toString(totalScUp));
        } else updateScTotal();
    }

    public void updateScLow(){
        labelScoreLow.setText(Integer.toString(totalScLow));
        updateScTotal();
    }

    public void updateBonus(){
        totalScore += 35;
        totalScUp += 35;
        labelScoreBonus.setText("35");
        labelScoreUp2.setText(Integer.toString(totalScUp));
        labelScoreUp.setText(Integer.toString(totalScUp));
        updateScTotal();
    }

    public int getTotalScore(){
        return totalScore;
    }

    public JFrame getFrame(){
        return frame;
    }

    public void setFrame(JFrame f){
        frame = f;
    }

    public void setX(int n){
        x = n;
    }

    public void setTurn(boolean b){
        myTurn = b;
    }

    public boolean getTurn(){
        return myTurn;
    }
}
