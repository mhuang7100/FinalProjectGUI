import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;


public class ScoreBoardMultiplayer extends ScoreBoard{
    private ScoreBoard p2;
    boolean myTurn;

    public ScoreBoardMultiplayer(){
        super();
        myTurn = true;
    }

    public void setPlayer2(ScoreBoard p){
        p2 = p;
    }

    public void switchTurn(){
        if (myTurn){
            myTurn = false;
        } else myTurn = true;
    }

    public void setTurn(boolean bool){
        myTurn = bool;
    }
    
    public void createBtnReset(JFrame f){
        JButton reset = new JButton("RESTART");
        reset.setBounds(900, 970, 150, 20);
        f.add(reset);
        reset.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                System.out.println("Player 1 Final Score: " + getTotalScore());
                System.out.println("Player 2 Final Score: " + p2.getTotalScore());
                f.dispose();
                YahtzeeDice.resetKeep();
                YahtzeeDice.setRollsRemaining(3);
                YahtzeeDice.setCanScore(false);
                

                ScoreBoard one = new ScoreBoard();
                one.createFrame();
                ScoreBoard2 two = new ScoreBoard2(one.getFrame(), one);
                one.setPlayer2(two);
                one.createBoard();        
                one.createDiceBtn();
                two.createBoard();
                one.createBtnReset(one.getFrame());
            }
        });
    }

    public void updateScTotal(){
        super.updateScTotal();
        myTurn = false;
        p2.setTurn(true);
    }

}
