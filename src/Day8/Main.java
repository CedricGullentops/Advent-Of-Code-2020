package Day8;

import javafx.beans.binding.StringBinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

class Main {
    private static int accumulator = 0;
    private static int currentIndex = 0;

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

        Set<Integer> executedCommands = new HashSet<>();
        executedCommands.add(currentIndex);
        while(true){
            executeLine(entries.get(currentIndex));
            //System.out.println("CurrentIndex: " + currentIndex + " Accumulator: " + accumulator);
            if (executedCommands.contains(currentIndex)){
                break;
            }
            executedCommands.add(currentIndex);
        }

        System.out.println("Accumulator: "  + accumulator);
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
