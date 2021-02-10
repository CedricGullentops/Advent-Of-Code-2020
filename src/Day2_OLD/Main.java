package Day2_OLD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        List<String> items = Arrays.asList(sc.nextLine().split("\\s*,\\s*"));
        ArrayList<Integer> intList = new ArrayList<Integer>();
        for(String s : items) {
            intList.add(Integer.valueOf(s));
        }
        ArrayList<Integer> originalList = new ArrayList<Integer>(intList);

        for (int noun = 0; noun<100; noun++){
            for (int verb = 0; verb<100; verb++){
                intList.set(1,noun);
                intList.set(2,verb);
                int index = 0;
                do {
                    if (intList.get(index) == 1){
                        intList.set(intList.get(index+3), intList.get(intList.get(index + 1)) + intList.get(intList.get(index + 2)));
                    }
                    else if(intList.get(index) == 2){
                        intList.set(intList.get(index+3), intList.get(intList.get(index+1)) * intList.get(intList.get(index+2)));
                    }
                    else{
                        break;
                    }
                    index += 4;
                    System.out.println(intList.get(0));
                } while(intList.get(index) == 1 || intList.get(index) == 2);

                if (intList.get(0) == 19690720){
                    System.out.println("NOUN and VERB are " + noun + ", " + verb);
                    System.exit(0);
                }

                intList = new ArrayList<Integer>(originalList);
            }
        }
    }
}
