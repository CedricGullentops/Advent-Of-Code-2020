package Day15b;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

class Main {
    private static HashMap<Integer, Integer> mapping = new HashMap<>();
    private static final int MAX = 30000000;
    private static int lastNumber = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        int[] numbers = Stream.of(line.split(",")).mapToInt(Integer::parseInt).toArray();
        int currentTurn = initializeNumbers(numbers);

        while (currentTurn != MAX+1){
            iterateNumbers(currentTurn);
            currentTurn++;
        }

        System.out.println("EndValue: "  + lastNumber);
    }

    private static void iterateNumbers(int currentTurn){
        int lastSeen = 0;
        boolean seen = false;
        if (mapping.get(lastNumber) != null){
            lastSeen = mapping.get(lastNumber);
            seen = true;
        }
        mapping.put(lastNumber, currentTurn-1);
        if (seen){
            lastNumber = currentTurn-1-lastSeen;
        }
        else{
            lastNumber = 0;
        }
    }

    private static int initializeNumbers(int[] numbers){
        int turn = 1;
        for (int i=0; i<numbers.length; i++){
            if (i == numbers.length-1){
                lastNumber = numbers[i];
            }
            else{
                mapping.put(numbers[i], turn);
            }
            turn ++;
        }
        return turn;
    }
}
