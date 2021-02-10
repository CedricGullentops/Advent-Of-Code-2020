package Day3;

import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> entries = new ArrayList<String>();
        ArrayList<String> totalMap;

        int slopex = 1, slopey = 2;

        while (sc.hasNextLine()){
            String entry = sc.nextLine();
            if (entry.equals("end")){
                break;
            }
            entries.add(entry);
        }

        totalMap = new ArrayList<>(entries);

        while (slopey*totalMap.get(0).length() < slopex*entries.size()+1){
            for (int i=0; i<totalMap.size(); i++){
                totalMap.set(i,totalMap.get(i).concat(entries.get(i)));
            }
        }

        int x=0, treecount = 0;
        for (int i=slopey; i<totalMap.size(); i+=slopey){
            System.out.println(totalMap.get(i));

            x+=slopex;

            if (totalMap.get(i).charAt(x) == '#'){
                treecount++;
            }
        }
        System.out.println("The total amount of passed trees: " + treecount);
    }
}
