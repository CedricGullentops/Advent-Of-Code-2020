package Day15;

import java.util.*;
import java.util.stream.Stream;

class Main {
    private static HashMap<Integer, Integer> mapping = new HashMap<Integer, Integer>();
    private static final int MAX = 30000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        int[] numbers = Stream.of(line.split(",")).mapToInt(Integer::parseInt).toArray();
        int currentTurn = initializeNumbers(numbers);

        while (currentTurn != MAX+1){
            iterateNumbers(currentTurn);
            currentTurn++;
            if (currentTurn%100==0){
                System.out.println(currentTurn);
            }
        }

        System.out.println("EndValue: "  + mapping.get(MAX));
    }

    private static void iterateNumbers(int currentTurn){
        int lastValue = mapping.get(currentTurn-1);

        Map.Entry<Integer, Integer> newest = null;
        for (Map.Entry<Integer, Integer> entry : mapping.entrySet()) {
            if (entry.getValue() != lastValue || entry.getKey() == currentTurn-1){
                continue;
            }
            if (newest == null){
                newest = entry;
            }
            else if (entry.getKey() > newest.getKey()) {
                newest = entry;
            }
        }

        if (newest == null){
            mapping.put(currentTurn, 0);
        }
        else{
            mapping.put(currentTurn, currentTurn-1-newest.getKey());
        }
    }

    private static int initializeNumbers(int[] numbers){
        int turn = 1;
        for (Integer number : numbers){
            mapping.put(turn, number);
            turn ++;
        }
        return turn;
    }
}
