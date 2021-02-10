package Day8b;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Main {
    private static int accumulator;
    private static int currentIndex;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> entries = new ArrayList<String>();

        while (sc.hasNextLine()){
            String entry = sc.nextLine();
            if (entry.equals("end")) {
                break;
            }
            entries.add(entry);
        }

        boolean done = false;
        for (int i=0; i<entries.size(); i++){
            if (!entries.get(i).contains("nop") && !entries.get(i).contains("jmp") ){
                continue;
            }
            ArrayList<String> mutatedEntries = new ArrayList<>(entries);
            mutatedEntries.set(i, mutateEntry(mutatedEntries.get(i)));

            currentIndex = 0;
            accumulator = 0;

            Set<Integer> executedCommands = new HashSet<>();
            executedCommands.add(currentIndex);
            while(true){
                executeLine(mutatedEntries.get(currentIndex));
                if (executedCommands.contains(currentIndex)){
                    break;
                }
                if (currentIndex == mutatedEntries.size()){
                    done = true;
                    break;
                }
                executedCommands.add(currentIndex);
            }
            if (done){
                break;
            }
        }

        System.out.println("Accumulator: "  + accumulator);
    }

    private static String mutateEntry(String entry){
        StringBuilder sb = new StringBuilder();
        if (entry.contains("nop")){
            sb.append("jmp");
            for (int i=3; i<entry.length(); i++){
                sb.append(entry.charAt(i));
            }
        }
        else{
            sb.append("nop");
            for (int i=3; i<entry.length(); i++){
                sb.append(entry.charAt(i));
            }
        }
        return sb.toString();
    }

    private static void executeLine(String line){
        if (line.contains("nop")){
            currentIndex++;
        }
        else {
            StringBuilder sb = new StringBuilder();
            if (line.contains("acc")){
                for (int i = 5; i<line.length(); i++){
                    sb.append(line.charAt(i));
                }
                int accValue = Integer.parseInt(sb.toString());

                if (line.charAt(4) == '+'){
                    accumulator += accValue;
                }
                else{
                    accumulator -= accValue;
                }
                currentIndex++;
            }
            else{
                for (int i = 5; i<line.length(); i++){
                    sb.append(line.charAt(i));
                }
                int indexValue = Integer.parseInt(sb.toString());

                if (line.charAt(4) == '+'){
                    currentIndex += indexValue;
                }
                else{
                    currentIndex -= indexValue;
                }
            }
        }
    }
}
