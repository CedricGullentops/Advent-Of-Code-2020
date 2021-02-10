package Day14b;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Main {
    private static HashMap<Long, Long> memory = new HashMap<>();
    private static final int MEMSIZE = 36;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String currentmask = null;

        while (sc.hasNextLine()){
            String line = sc.nextLine();
            if (line.contains("end")){
                break;
            }
            else if (line.contains("mask")){
                currentmask = line.split(" = ")[1];
            }
            else {
                assignMemory(currentmask, line);
            }
        }

        final Long[] endValue = {0L};
        memory.values()
                .forEach(p -> endValue[0] +=p);

        System.out.println("EndValue: " + endValue[0]);
    }

    private static void assignMemory(String mask, String line){
        long value, key;
        String[] split = line.split("] = ");
        value = Long.parseLong(split[1]);
        key = Long.parseLong(split[0].split("mem\\[")[1]);

        StringBuilder sb = new StringBuilder(Long.toBinaryString(key));
        for (int i=sb.length(); i<MEMSIZE; i++){
            sb.insert(0,'0');
        }

        char[] maskCharacters = mask.toCharArray();
        for (int i=0; i<MEMSIZE; i++){
            if (maskCharacters[i] == 'X'){
                sb.setCharAt(i, 'X');
            }
            else if (maskCharacters[i] == '1'){
                sb.setCharAt(i, '1');
            }
        }

        ArrayList<StringBuilder> strings = new ArrayList<>();
        strings.add(sb);
        ArrayList<StringBuilder> newStrings = new ArrayList<>();

        for (int i=0; i<MEMSIZE; i++){
            if (strings.get(0).charAt(i) == 'X'){
                for (StringBuilder stringsB: strings){
                    StringBuilder temp = new StringBuilder(stringsB);
                    temp.setCharAt(i, '0');
                    newStrings.add(temp);
                    StringBuilder stemp = new StringBuilder(stringsB);
                    stemp.setCharAt(i, '1');
                    newStrings.add(stemp);
                }
                strings = new ArrayList<>(newStrings);
                newStrings.clear();
            }
        }

        for (StringBuilder sbn : strings){
            memory.put(Long.parseLong(sbn.toString(), 2), value);
        }
    }
}
