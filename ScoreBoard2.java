import javax.swing.JFrame;

// Player 2 class in multiplayer
public class ScoreBoard2 extends ScoreBoardMultiplayer{
    public ScoreBoard2(JFrame f, ScoreBoardMultiplayer p){
        super();
        setFrame(f);     // p2 uses the same frame as p1
        setX(600);     // sets p2's score card to the right 
        setTurn(false);     // p1 goes first
        setPlayer2(p);       // stores the other player as a variable 
    }
}