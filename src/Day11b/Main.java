package Day11b;

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

            int result = -1;
            int distance = 1;
            while (result < 0){
                result = checkTopLeft(i, distance);
                distance++;
            }
            neighbours+=result;

            result = -1;
            distance = 1;
            while (result < 0){
                result = checkTop(i, distance);
                distance++;
            }
            neighbours+=result;

            result = -1;
            distance = 1;
            while (result < 0){
                result = checkTopRight(i, distance);
                distance++;
            }
            neighbours+=result;

            result = -1;
            distance = 1;
            while (result < 0){
                result = checkLeft(i, distance);
                distance++;
            }
            neighbours+=result;

            result = -1;
            distance = 1;
            while (result < 0){
                result = checkRight(i, distance);
                distance++;
            }
            neighbours+=result;

            result = -1;
            distance = 1;
            while (result < 0){
                result = checkBottomLeft(i, distance);
                distance++;
            }
            neighbours+=result;

            result = -1;
            distance = 1;
            while (result < 0){
                result = checkBottom(i, distance);
                distance++;
            }
            neighbours+=result;

            result = -1;
            distance = 1;
            while (result < 0){
                result = checkBottomRight(i, distance);
                distance++;
            }
            neighbours+=result;

            if (oldSeats.get(i) == 'L' && neighbours == 0){
                seats.put(i, '#');
                changed = true;
            }
            else if (oldSeats.get(i) == '#' && neighbours >= 5){
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


    private static int checkTopLeft(int i, int distance){
        if (isNotLeftSide(i, distance) && i-lineWidth*distance-distance >= 0){
            Character toCheck = oldSeats.get(i-lineWidth*distance-distance);
            if (toCheck == '#'){
                return 1;
            }
            else if(toCheck == '.'){
                return -1;
            }
        }
        return 0;
    }

    private static int checkTop(int i, int distance){
        if (i-lineWidth*distance >= 0){
            Character toCheck = oldSeats.get(i-lineWidth*distance);
            if (toCheck == '#'){
                return 1;
            }
            else if(toCheck == '.'){
                return -1;
            }
        }
        return 0;
    }

    private static int checkTopRight(int i, int distance){
        if (i-lineWidth*distance+distance >= 0 && isNotRightSide(i, distance)){
            Character toCheck = oldSeats.get(i-lineWidth*distance+distance);
            if (toCheck == '#'){
                return 1;
            }
            else if(toCheck == '.'){
                return -1;
            }
        }
        return 0;
    }

    private static int checkLeft(int i, int distance){
        if (i-distance >= 0 && isNotLeftSide(i, distance)){
            Character toCheck = oldSeats.get(i-distance);
            if (toCheck == '#'){
                return 1;
            }
            else if(toCheck == '.'){
                return -1;
            }
        }
        return 0;
    }

    private static int checkRight(int i, int distance){
        if (i+distance<maxSize && isNotRightSide(i, distance)){
            Character toCheck = oldSeats.get(i+distance);
            if (toCheck == '#'){
                return 1;
            }
            else if(toCheck == '.'){
                return -1;
            }
        }
        return 0;
    }

    private static int checkBottomLeft(int i, int distance){
        if (i+lineWidth*distance-distance < maxSize && isNotLeftSide(i, distance)){
            Character toCheck = oldSeats.get(i+lineWidth*distance-distance);
            if (toCheck == '#'){
                return 1;
            }
            else if(toCheck == '.'){
                return -1;
            }
        }
        return 0;
    }

    private static int checkBottom(int i, int distance){
        if (i+lineWidth*distance < maxSize){
            Character toCheck = oldSeats.get(i+lineWidth*distance);
            if (toCheck == '#'){
                return 1;
            }
            else if(toCheck == '.'){
                return -1;
            }
        }
        return 0;
    }

    private static int checkBottomRight(int i, int distance){
        if (i+lineWidth*distance+distance < maxSize && isNotRightSide(i, distance)){
            Character toCheck = oldSeats.get(i+lineWidth*distance+distance);
            if (toCheck == '#'){
                return 1;
            }
            else if(toCheck == '.'){
                return -1;
            }
        }
        return 0;
    }

    private static boolean isNotRightSide(int i, int distance){
        return !(i%lineWidth+distance >= lineWidth);
        //return !((i-lineWidth+1)%lineWidth==0);
    }

    private static boolean isNotLeftSide(int i, int distance){
        return !(i%lineWidth-distance < 0);
        //return !(i%lineWidth==0);
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
