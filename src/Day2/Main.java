package Day2;

import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> entries = new ArrayList<String>();

        while (sc.hasNextLine()){
            String entry = sc.nextLine();
            if (entry.equals("end")){
                break;
            }
            entries.add(entry);
        }

        int correctPasswordCount = 0;
        for(String entry : entries){
            String[] splitByColon = entry.split(":");
            String[] splitBySpace = splitByColon[0].split(" ");
            String[] splitByDash = splitBySpace[0].split("-");

            Character charToCheck = splitBySpace[1].charAt(0);
            char[] charArray = splitByColon[1].substring(1).toCharArray();

            if(charToCheck.equals(charArray[Integer.parseInt(splitByDash[0]) - 1]) ^ charToCheck.equals(charArray[Integer.parseInt(splitByDash[1]) - 1])){
                correctPasswordCount++;
            }
        }
        System.out.println("The correct amount of passwords is: " + correctPasswordCount);
    }
}
