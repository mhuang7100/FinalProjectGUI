import java.util.Scanner; 

public class Main {
    public static void main(String[] args){
        System.out.print("\033[H\033[2J");
        System.out.println("SinglePlayer or Multiplayer? (1 / 2)");
        Scanner input = new Scanner(System.in);

        boolean repeat = true;
        while (repeat = true){
            if (input.nextLine().equals("1")){
                System.out.print("\033[H\033[2J");
                ScoreBoard p1 = new ScoreBoard();
                p1.createFrame();
                p1.createBoard();
                p1.createDiceBtn();
                p1.createBtnReset(p1.getFrame());
                repeat = false;
            } else if (input.nextLine().equals("2")){
                System.out.print("\033[H\033[2J");
                ScoreBoardMultiplayer p1 = new ScoreBoardMultiplayer();
                p1.createFrame();
                ScoreBoard2 p2 = new ScoreBoard2(p1.getFrame(), p1);
                p1.setPlayer2(p2);
                p1.createBoard();        
                p1.createDiceBtn();
                p2.createBoard();
                p1.createBtnReset(p1.getFrame());
                repeat = false;
            } else repeat = true;
        }
        

        
    }
}
