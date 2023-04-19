import java.util.ArrayList;

import javax.xml.namespace.QName;

// dice 
public class YahtzeeDice {
    private static ArrayList<Integer> keep = new ArrayList<Integer>();
    private static int[] dice = {5, 2, 5, 5, 2};

    public static void main(String[] args){
        printDice();

        System.out.println(calcFullHouse());
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

    public static int calcFullHouse(){
        int total = 0;
        int count = 0;
        int count2 = 0;
        int temp = 0;

        for (int i : dice){
            if (i == dice[0]){
                count += 1;
            } else temp = i; 
            total += i;
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
            return total;
        } else return 0;
    }

    

    public ArrayList<Integer> getKeep(){
        return keep;
    }

    public int[] getDice(){
        return dice;
    }
}
