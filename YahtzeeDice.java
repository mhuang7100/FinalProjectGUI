import java.util.ArrayList;
import javax.swing.JButton;

// dice 
public class YahtzeeDice {
    private static ArrayList<Integer> keep = new ArrayList<Integer>();
    private static int[] dice = {2, 3, 4, 5, 5};
    private static int rollsRemaining = 2;
    private static boolean canScore = false;

    public static void main(String[] args){
        printDice();

        System.out.println(calcYahtzee());
    }

    public static void setRollsRemaining(int i){
        rollsRemaining = i;
    }

    public static void setCanScore(boolean b){
        canScore = b;
    }

    public static boolean getCanScore(){
        return canScore;
    }

    // add/remove dice from being kept, also highlights/unhighlights
    public static void keepDice(int index, JButton dice){
        // unselect the dice 
        if (keep.contains(index)){
            keep.remove(Integer.valueOf(index));
            dice.setBorderPainted(false);
        // select the dice
        } else {
            keep.add(index);
            dice.setBorderPainted(true);
        }
    }

    public static void resetKeep(){
        keep.removeAll(keep);
        for (int i = 0; i < 5; i++){
            DiceInterface.getDice(i).setBorderPainted(false);
        }
    }

    public static void resetRolls(){
        rollsRemaining = 0;
    }

    public static int getRolls(){
        return rollsRemaining;
    }

    // rolls the dice that were not selected to be retained
    public static void roll(){
        if (rollsRemaining > 0){
            for (int i = 0; i < 5; i++){
                if (!keep.contains(i)){
                    int newNum = (int) (Math.random() * 6) + 1;
                    dice[i] = newNum;
                    DiceInterface.changeDiceImg(i, newNum - 1);
                }
            }
            rollsRemaining -= 1;    
            JButton roll = DiceInterface.getRollBtn();
            roll.setText("Rolls Remaining: " + Integer.toString(rollsRemaining));
            
        }
    }

    public static int calcUpper(int num){
        int total = 0;
        for (int i : dice){
            if (i == num){
                total += i;
            }
        }
        return total;
    }

    // returns three of a kind score
    public static int calcThreeKind(){
        int count = 0;
        for (int i = 0; i < 5; i++){
            int temp = 0;
            count = 0;
            for (int j : dice){
                if (j == dice[i]){
                    count += 1;
                }
                temp += j;
            }
            if (count >= 3){
                return temp;
            }
        }
            
        return 0;
    }

    // returns four of a kind score
    public static int calcFourKind(){
        int count = 0;
        for (int i = 0; i < 5; i++){
            int temp = 0;
            count = 0;
            for (int j : dice){
                if (j == dice[i]){
                    count += 1;
                }
                temp += j;
            }
            if (count >= 4){
                return temp;
            }
        }
            
        return 0;
    }

    // checks for full house and returns score
    public static int calcFullHouse(){
        int score = 25;
        int count = 0;
        int count2 = 0;
        int temp = 0;

        for (int i : dice){
            if (i == dice[0]){
                count += 1;
            } else {
                temp = i; 
            }
        }
        // if its not a full house, no score
        if (count == 1 || count >= 4){
            return 0;
        } 
        for (int j : dice){
            if (j == temp){
                count2 += 1;
            }
        }
        if (count2 + count == 5){
            return score;
        } else return 0;
    }

    // checks for small straight (sequence of 4) and returns score
    public static int calcSmStraight(){
        int score = 30;
        int count = 1;
        int min = 6;
        int minNext = 6;

        // gets the 2 minimum values
        for (int i : dice){
            if (i < min){
                min = i;
            } else if (i < minNext){
                minNext = i;
            }
        }
        for (int i = 2; i <= 4; i++){
            for (int j : dice){
                if (j == min + count){
                    count += 1;
                }
            }
            if (count != i){
                break;
            }
        }
        
        if (count >= 4){
            return score;
        } else {
            count = 1;
            for (int i = 2; i <= 4; i++){
                for (int j : dice){
                    if (j == minNext + count){
                        count += 1;
                    }
                }
                if (count != i){
                    return 0;
                }
            }
            if (count >= 4){
                return score;
            }
        }

        return 0;
    }

    // checks for large straight (sequence of 5) and returns score
    public static int calcLgStraight(){
        int score = 40;
        int count = 1;
        int min = 3;

        // gets the min value
        for (int i : dice){
            if (i < min){
                min = i;
            }
        }
        for (int i = 2; i <= 5; i++){
            for (int j : dice){
                if (j == min + count){
                    count += 1;
                    break;
                }
            }
            if (count != i){
                return 0;
            }
        }

        return score;
    }

    public static int calcYahtzee(){
        int score = 50; 
        for (int i : dice){
            if (i != dice[0]){
                return 0;
            }
        }
        return score;
    }

    public static int calcChance(){
        int score = 0;
        for (int i : dice){
            score += i;
        }
        return score;
    }

    public static ArrayList<Integer> getKeep(){
        return keep;
    }

    public static int[] getDice(){
        return dice;
    }

    // troubleshooting purposes only
    public static void printDice(){
        System.out.print("Dice: ");
        for (int n : dice){
            System.out.print(n + " ");
        }
        System.out.println();
    }
}
