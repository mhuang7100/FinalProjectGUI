import java.util.ArrayList;

// dice 
public class YahtzeeDice {
    private static ArrayList<Integer> keep = new ArrayList<Integer>();
    private static int[] dice = {2, 1, 2, 2, 2};

    public static void main(String[] args){
        printDice();

        System.out.println(calcYahtzee());
    }

    public static void keepDice(int index){
        keep.add(index);
    }

    public static void resetKeep(int index){
        keep.removeAll(keep);
    }

    // rolls the dice that were not selected to be retained
    public static void roll(){
        for (int i = 0; i < 5; i++){
            if (!keep.contains(i)){
                dice[i] = (int) (Math.random() * 6) + 1;
            }
        }
    }

    public static void printDice(){
        System.out.print("Dice: ");
        for (int n : dice){
            System.out.print(n + " ");
        }
        System.out.println();
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
            } else temp = i; 
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

    public static ArrayList<Integer> getKeep(){
        return keep;
    }

    public static int[] getDice(){
        return dice;
    }
}
