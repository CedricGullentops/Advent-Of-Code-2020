package Day9;

import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> entries = new ArrayList<Integer>();

        int preambleLength = 25, firstUnvalid = -1;

        while (sc.hasNextInt()){
            entries.add(sc.nextInt());
        }

        ArrayList<Integer> validOptions = new ArrayList<>(entries.subList(0,preambleLength));

        for (int i=preambleLength; i<entries.size(); i++){
            boolean sum = false;
            for (int first = 0; first<preambleLength; first++){
                for (int second = 0; second<preambleLength; second++){
                    if (second == first){
                        continue;
                    }
                    if (validOptions.get(first) + validOptions.get(second) == entries.get(i)){
                        sum = true;
                        break;
                    }
                }
                if (sum){
                    break;
                }
            }
            if (sum){
                validOptions.remove(0);
                validOptions.add(entries.get(i));
            }
            else{
                firstUnvalid = i;
                break;
            }
        }
        System.out.println("Accumulator: " + entries.get(firstUnvalid));

        boolean done = false;
        for (int first = 0; first<firstUnvalid; first++){
            for (int second = first+1; second<firstUnvalid; second++) {
                int totalSum = 0;
                for (int i = first; i <= second; i++) {
                    totalSum += entries.get(i);
                }
                if (totalSum == entries.get(firstUnvalid)) {
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    for (int i = first; i <= second; i++) {
                        list.add(entries.get(i));
                    }
                    System.out.println("Largest value: " + Collections.max(list) + " Smallest value: " + Collections.min(list));
                    done = true;
                    break;
                }
            }
            if (done){
                break;
            }
        }
    }
}
