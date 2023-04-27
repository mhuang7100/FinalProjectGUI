import java.util.Scanner; 

public class Main {
    public static void main(String[] args){
        System.out.print("\033[H\033[2J");
        System.out.println("Welcome to Yahtzee! You have 3 rolls and may score each section by clicking the 'score' button once per game. You may keep a dice by clicking it. Press RESTART to start a new game.");
        System.out.println("SinglePlayer or Multiplayer? (1 / 2)");

        // if the player doesn't select 1 or 2 the question is repeated
        boolean repeat = true;
        try (Scanner input = new Scanner(System.in)) {
            while (repeat = true){
                String num = input.nextLine();

                // starts singleplayer 
                if (num.equals("1")){
                    System.out.print("\033[H\033[2J");

                    // makes the board and starts the game
                    ScoreBoard p1 = new ScoreBoard();
                    p1.createFrame();
                    p1.createBoard();
                    p1.createDiceBtn();
                    p1.createBtnReset(p1.getFrame());
                    repeat = false;

                // starts multiplayer
                } else if (num.equals("2")){
                    System.out.print("\033[H\033[2J");

                    // makes the board and starts the game
                    ScoreBoardMultiplayer p1 = new ScoreBoardMultiplayer();
                    p1.createFrame();
                    ScoreBoard2 p2 = new ScoreBoard2(p1.getFrame(), p1);
                    p1.setPlayer2(p2);
                    p1.createBoard();        
                    p1.createDiceBtn();
                    p2.createBoard();
                    p1.createBtnReset(p1.getFrame());
                    repeat = false;
                } else {
                    System.out.println("SinglePlayer or Multiplayer? (1 / 2)");
                    repeat = true;
                }
            }
        }
        

        
    }
}
