import javax.lang.model.util.ElementScanner14;
import javax.swing.*;
import java.awt.*;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;  

public class ScoreBoard{
    private int totalScore;
    private int totalScUp;
    private int bonus;
    private int totalScLow;
    private int Ybonus;

    private ArrayList<Button> upperButtons = new ArrayList<Button>();
    private ArrayList<Button> lowerButtons = new ArrayList<Button>(); //dothis

    private JFrame frame;

    private JLabel labelScoreUp1;
    private JLabel labelScoreBonus;
    private JLabel labelScoreUp2;
    private JLabel labelScoreYBonus;
    private JLabel labelScoreLow;
    private JLabel labelScoreUp;
    private JLabel labelScoreTotal;

    private int sectionsLeft;
    private int sectionsLeftUp;

    private int x; // position of the score card
    private boolean myTurn;

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

    public void createFrame(){
        frame = new JFrame();        
        frame.setTitle("Yahtzee!"); 
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920,1080);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.WHITE);
    }

    public void createBoard(){

        // labels
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

        // sets up labels
        labelScoreUp1 = new JLabel(Integer.toString(totalScUp));
        labelScoreBonus = new JLabel(Integer.toString(bonus));
        labelScoreUp2 = new JLabel(Integer.toString(totalScUp));
        labelScoreYBonus = new JLabel(Integer.toString(Ybonus));
        labelScoreUp = new JLabel(Integer.toString(totalScUp));
        labelScoreLow = new JLabel(Integer.toString(totalScLow));
        labelScoreTotal = new JLabel(Integer.toString(totalScore));


        // buttons
        x += size1.width;
        int y = 112;
        int BWidth = 41;
        int BLength = 53;
        
        for (int i = 1; i <= 6; i++){
            final int temp = i;
            Button button1 = new Button("score");  
            button1.setBounds(x, y, BLength, BWidth); 
            // action on click
            button1.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){                     
                    // if the button has not been pressed
                    if (button1.getLabel().equals("score") && YahtzeeDice.getCanScore() && myTurn){
                        int tempScore = getScoreUpper(temp);  
                        totalScUp += tempScore;
                        totalScore += tempScore;
                        sectionsLeft -= 1;
                        sectionsLeftUp -= 1;
                        updateScUp();

                        // display score
                        button1.setLabel(Integer.toString(tempScore));

                        YahtzeeDice.setCanScore(false);
                        YahtzeeDice.setRollsRemaining(3);
                        YahtzeeDice.resetKeep();
                        DiceInterface.updateRollBtn();

                        // transparent button ++

                    }
                }  
            }); 

            upperButtons.add(button1);
            frame.add(button1);
            y += BWidth;
        }

        // total/bonus 1st section
        int lblWidth = 35;
        labelScoreUp1.setBounds(x + 22, y, BLength, lblWidth);
        y += lblWidth;
        labelScoreBonus.setBounds(x + 22, y, BLength, lblWidth);
        y += lblWidth;
        labelScoreUp2.setBounds(x + 22, y, BLength, lblWidth);
        y += lblWidth;

        y += 128 - lblWidth * 3;

        int BWidth2 = 35;
        for (int i = 1; i <= 7; i++){
            Button button1 = new Button("score");  
            button1.setBounds(x, y, BLength, BWidth2); 
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

        // yahtzee bonus 
        int lblLength2 = 28;
        labelScoreYBonus.setBounds(x + 22, y, BLength, 46);
        y += 48;
        labelScoreLow.setBounds(x + 22, y, BLength, lblLength2);
        y += lblLength2;
        labelScoreUp.setBounds(x + 22, y, BLength, lblLength2);
        y += lblLength2;
        labelScoreTotal.setBounds(x + 22, y, BLength, lblLength2);
        
        
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

    public int getScoreUpper(int btnNum){
        return YahtzeeDice.calcUpper(btnNum);
    }

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

    public int getTotalScore(){
        return totalScore;
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





    
}
