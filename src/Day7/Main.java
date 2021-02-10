package Day7;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> entries = new ArrayList<>();

        String ourType = "shiny gold";

        while (sc.hasNextLine()){
            String entry = sc.nextLine();
            if (entry.equals("end")) {
                break;
            }
            entries.add(entry);
        }

        Set<String> subEntries = validOptions(entries, ourType);
        Set<String> newEntries = new HashSet<>();
        Set<String> lastEntries = new HashSet<>(subEntries);

        while (!lastEntries.isEmpty()){
            for (String entry : lastEntries){
                newEntries.addAll(validOptions(entries, entry.split(" bags contain")[0]));
            }
            subEntries.addAll(newEntries);
            lastEntries = new HashSet<>(newEntries);
            newEntries.clear();
        }
        System.out.println("Total amount of options: " + subEntries.size());
    }

    private static Set<String> validOptions(ArrayList<String> entries, String bag){
        return entries.stream().filter(s -> s.split("bags contain")[1].contains(bag)).collect(Collectors.toSet());
    }
}
