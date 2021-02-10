package Day10b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

class Main {
    private static HashMap<Long, Long> mapping = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> entries = new ArrayList<Integer>();

        while (sc.hasNextInt()){
            entries.add(sc.nextInt());
        }

        entries.add(0);
        Collections.sort(entries);
        entries.add(entries.get(entries.size()-1)+3);

        int oneDiff = 0, threeDiff = 0;

        for (int i=1; i<entries.size(); i++){
            int diff = entries.get(i)-entries.get(i-1);
            if(diff == 1){
                oneDiff++;
            }
            else if(diff == 3){
                threeDiff++;
            }
        }

        System.out.println("Result: " + oneDiff*threeDiff);

        System.out.println("Amount: " + calcAmount(0, entries));
    }

    private static long calcAmount(long index, ArrayList<Integer> list){
        if (index == list.size()-1){
            return 1;
        }
        if (mapping.containsValue(index)){
            return mapping.get(index);
        }
        long ans = 0;
        for (long i=index+1; i<list.size(); i++){
            if (list.get((int) i) - list.get((int) index) <= 3){
                ans += calcAmount(i, list);
            }
        }
        mapping.put(index, ans);
        return ans;
    }
}
