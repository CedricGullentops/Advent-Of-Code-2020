package Day14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

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

        StringBuilder sb = new StringBuilder(Long.toBinaryString(value));
        for (int i=sb.length(); i<MEMSIZE; i++){
            sb.insert(0,'0');
        }

        char[] maskCharacters = mask.toCharArray();
        for (int i=0; i<MEMSIZE; i++){
            if (maskCharacters[i] == '0'){
                sb.setCharAt(i, '0');
            }
            else if (maskCharacters[i] == '1'){
                sb.setCharAt(i, '1');
            }
        }

        memory.put(key, Long.parseLong(sb.toString(), 2));
    }
}
