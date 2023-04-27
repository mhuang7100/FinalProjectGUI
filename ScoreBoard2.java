import javax.swing.JFrame;

public class ScoreBoard2 extends ScoreBoardMultiplayer{


    public ScoreBoard2(JFrame f, ScoreBoardMultiplayer p){
        super();
        setFrame(f);
        setX(600);
        setTurn(false);
        setPlayer2(p);;
    }

    


}