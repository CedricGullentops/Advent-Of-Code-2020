package Day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> entries = new ArrayList<Integer>();

        while (sc.hasNextInt()){
            entries.add(sc.nextInt());
        }

        Collections.sort(entries);

        int oneDiff = 0, twoDiff = 0, threeDiff = 0;
        for (int i=1; i<entries.size(); i++){
            int diff = entries.get(i)-entries.get(i-1);
            if (diff == 0 || diff > 3){
                System.out.println("The diff is larger than 3 or smaller than 0...: " + diff);
            }
            else if(diff == 1){
                oneDiff++;
            }else if(diff == 2){
                twoDiff++;
            }
            else{
                threeDiff++;
            }
        }
        oneDiff++;
        threeDiff++;

        System.out.println("OneDiff: " + oneDiff + " TwoDiff: " + twoDiff + " ThreeDiff: " + threeDiff);
        System.out.println("Result: " + oneDiff*threeDiff);
    }
}
