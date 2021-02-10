package Day7b;

import java.util.*;
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

        String bagLine = getBagLine(ourType, entries);
        int result = calcBagAmount(bagLine, entries);

        System.out.println("Total amount of options: " + result);
    }

    private static int calcBagAmount(String line, ArrayList<String> entries){
        String rightHalf = line.split(" bags contain ")[1];
        if (rightHalf.contains("no other bag")){
            return 0;
        }
        if (rightHalf.contains(",")){
            String[] items = rightHalf.split(", ");
            int subTotal = 0;
            for (String item : items){
                subTotal += calcSingleBagAmount(item, entries);
            }
            return subTotal;
        }
        else{
            return calcSingleBagAmount(rightHalf, entries);
        }
    }

    private static int calcSingleBagAmount (String subLine, ArrayList<String> entries){
        int amount;
        String bagAmount = subLine.split(" bag")[0];
        String[] elements = bagAmount.split(" ");
        amount = Integer.parseInt(elements[0]);
        String getBagLine = getBagLine(elements[1] + " " + elements[2], entries);
        int bAmount = calcBagAmount(getBagLine, entries);
        return amount + amount * bAmount;
    }

    private static String getBagLine(String bag, ArrayList<String> entries){
        ArrayList<String> subBags = (ArrayList<String>) entries.stream().filter(s -> s.split(" bags contain ")[0].contains(bag)).collect(Collectors.toList());
        return subBags.get(0);
    }
}
