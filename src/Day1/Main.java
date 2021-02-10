package Day1;

import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> entries = new ArrayList<Integer>();

        while (sc.hasNextInt()){
            entries.add(sc.nextInt());
        }

        for(Integer firstEntry : entries){
            for(Integer secondEntry : entries){
                for(Integer thirdEntry : entries){
                    if (firstEntry + secondEntry + thirdEntry == 2020){
                        System.out.println("First: " + firstEntry + " Second:" + secondEntry + " Third:" + thirdEntry);
                        System.out.println("Result: " + firstEntry * secondEntry * thirdEntry);
                        System.exit(0);
                    }
                }
            }
        }
    }
}
