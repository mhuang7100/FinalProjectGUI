import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

// this is used for multiplayer 
public class ScoreBoardMultiplayer extends ScoreBoard{
    private ScoreBoardMultiplayer p2;

    public ScoreBoardMultiplayer(){
        super();
        setTurn(true);   // p1 goes first 
    }

    // stores the other player as p2
    public void setPlayer2(ScoreBoardMultiplayer p){
        p2 = p;
    }

    // creates the RESTART button that resets the game if pressed
    // uses RECURSION to continue to create reset buttons 
    public void createBtnReset(JFrame f){
        JButton reset = new JButton("RESTART");
        reset.setBounds(900, 970, 150, 20);
        f.add(reset);
        reset.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                // displays final scores 
                System.out.println("Player 1 Final Score: " + getTotalScore());
                System.out.println("Player 2 Final Score: " + p2.getTotalScore());
                // closes the application
                f.dispose();
                // resets dice info 
                YahtzeeDice.resetKeep();    
                YahtzeeDice.setRollsRemaining(3);
                YahtzeeDice.setCanScore(false);
                
                // creates new objects and new frames
                ScoreBoardMultiplayer one = new ScoreBoardMultiplayer();
                one.createFrame();
                ScoreBoard2 two = new ScoreBoard2(one.getFrame(), one);
                one.setPlayer2(two);
                one.createBoard();        
                one.createDiceBtn();
                two.createBoard();
                // creates another reset button
                one.createBtnReset(one.getFrame());
            }
        });
    }

    // updates the score AND also switches turns
    public void updateScTotal(){
        super.updateScTotal();
        setTurn(false);
        p2.setTurn(true);
    }

}
