package Day5;

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

        int maxID = 0;
        int minID = 200;
        int sumID = 0;
        for (String entry: entries){
            int cID = calcSeatId(entry);
            sumID += cID;
            if (cID > maxID){
                maxID = cID;
            }
            if (cID < minID){
                minID = cID;
            }
        }

        int totalID = 0;
        for (int i=minID; i<= maxID; i++){
            totalID += i;
        }

        System.out.println("MaxID: "  + maxID);
        System.out.println("MinID: "  + minID);
        System.out.println("MyID: "  + (totalID - sumID));
    }

    private static int calcSeatId(String entry){
        int maxRow = 127, minRow = 0, diff = 64, rowID;
        int maxCol = 7, minCol = 0, cdiff = 4, colID;
        int ID;

        char[] characters = entry.toCharArray();
        for (int i=0; i<10; i++){
            if (i <= 6){
                if (characters[i]=='F'){
                    maxRow -= diff;
                }
                else{
                    minRow += diff;
                }
                diff /= 2;
            }
            else {
                if (characters[i] == 'L') {
                    maxCol -= cdiff;
                } else {
                    minCol += cdiff;
                }
                cdiff /= 2;
            }
        }
        //System.out.println("MaxRow: " + maxRow + " MinRow: " + minRow);
        //System.out.println("MaxCol: " + maxCol + " MinCol: " + minCol);
        ID = maxRow*8+maxCol;
        //System.out.println("ID: " + ID);
        return ID;
    }
}
