package Day11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

class Main {
    private static HashMap<Integer, Character> seats = new HashMap<>();
    private static HashMap<Integer, Character> oldSeats;
    private static int lineWidth;
    private static int height;
    private static int maxSize;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> entries = new ArrayList<>();

        while (sc.hasNextLine()){
            String line = sc.nextLine();
            if (line.contains("end")){
                break;
            }
            entries.add(line);
        }
        lineWidth = entries.get(0).length();
        height = entries.size();
        maxSize = lineWidth*height;
        initializeSeats(entries);
        oldSeats = new HashMap<>(seats);

        updateSeats();

        System.out.println("Amount: " + Collections.frequency(new ArrayList<>(seats.values()), '#'));
    }

    private static void updateSeats(){
        boolean changed = false;
        for (int i = 0; i < maxSize; i++){
            if (oldSeats.get(i) == '.'){
                continue;
            }

            int neighbours = 0;
            if (i-lineWidth-1 >= 0 && !(i%lineWidth==0) && oldSeats.get(i-lineWidth-1) == '#'){
                neighbours++;
            }
            if (i-lineWidth >= 0 && oldSeats.get(i-lineWidth) == '#'){
                neighbours++;
            }
            if (i-lineWidth+1 >= 0 && !((i-lineWidth+1)%lineWidth==0) && oldSeats.get(i-lineWidth+1) == '#'){
                neighbours++;
            }

            if (i-1 >= 0 && !(i%lineWidth==0) &&  oldSeats.get(i-1) == '#'){
                neighbours++;
            }
            if (i+1<maxSize && !((i-lineWidth+1)%lineWidth==0) && oldSeats.get(i+1) == '#'){
                neighbours++;
            }

            if (i+lineWidth-1 < maxSize && !(i%lineWidth==0) && oldSeats.get(i+lineWidth-1) == '#'){
                neighbours++;
            }
            if (i+lineWidth < maxSize && oldSeats.get(i+lineWidth) == '#'){
                neighbours++;
            }
            if (i+lineWidth+1 < maxSize && !((i-lineWidth+1)%lineWidth==0) && oldSeats.get(i+lineWidth+1) == '#'){
                neighbours++;
            }

            if (oldSeats.get(i) == 'L' && neighbours == 0){
                seats.put(i, '#');
                changed = true;
            }
            else if (oldSeats.get(i) == '#' && neighbours >= 4){
                seats.put(i, 'L');
                changed = true;
            }
        }
        System.out.println(mapToString());
        System.out.println();
        if (changed){
            oldSeats = new HashMap<>(seats);
            updateSeats();
        }
    }

    private static String mapToString(){
        StringBuilder toPrint = new StringBuilder();
        for(int i=0; i<maxSize; i++){
            toPrint.append(seats.get(i));
            if ((i+1)%lineWidth==0){
                toPrint.append('\n');
            }
        }
        return toPrint.toString();
    }

    private static void initializeSeats(ArrayList<String> entries){
        for (int i = 0; i < height; i++){
            char[] characters = entries.get(i).toCharArray();
            for (int j = 0; j < lineWidth; j++){
                seats.put(calcPosition(i,j), characters[j]);
            }
        }
    }

    private static int calcPosition(int x, int y){
        return x*lineWidth+y;
    }
}
