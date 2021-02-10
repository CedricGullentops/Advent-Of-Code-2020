package Day12b;

import java.util.HashMap;
import java.util.Scanner;

class Main {
    private static HashMap<String, Integer> position = new HashMap<>();
    private static HashMap<String, Integer> waypoint = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        position.put("Vertical" , 0);
        position.put("Horizontal" , 0);
        waypoint.put("Vertical" , 1);
        waypoint.put("Horizontal" , 10);

        while (sc.hasNextLine()){
            String line = sc.nextLine();
            if (line.contains("end")){
                break;
            }
            handleCommand(line);
        }

        System.out.println("Final position: V " + position.get("Vertical") + " H " + position.get("Horizontal"));
        System.out.println("Solution: " + (Math.abs(position.get("Vertical")) + Math.abs(position.get("Horizontal"))));
    }

    private static void handleCommand(String line){
        char direction = line.toCharArray()[0];
        Integer amount = Integer.parseInt(line.substring(1));
        if (direction == 'N'){
            waypoint.put("Vertical", waypoint.get("Vertical") + amount);
        }
        else if(direction == 'S'){
            waypoint.put("Vertical", waypoint.get("Vertical") - amount);
        }
        else if(direction == 'E'){
            waypoint.put("Horizontal", waypoint.get("Horizontal") + amount);
        }
        else if(direction == 'W'){
            waypoint.put("Horizontal", waypoint.get("Horizontal") - amount);
        }
        else if(direction == 'L'){
            int times = amount / 90;
            int x, y;
            x = waypoint.get("Horizontal");
            y = waypoint.get("Vertical");

            int oldX, oldY;
            for (int i=0; i<times; i++){
                oldX = x;
                oldY = y;
                x = -1*oldY;
                y = oldX;

            }

            waypoint.put("Horizontal", x);
            waypoint.put("Vertical", y);
        }
        else if(direction == 'R'){
            int times = amount / 90;
            int x, y;
            x = waypoint.get("Horizontal");
            y = waypoint.get("Vertical");

            int oldX, oldY;
            for (int i=0; i<times; i++){
                oldX = x;
                oldY = y;
                x = oldY;
                y = -1*oldX;

            }
            waypoint.put("Horizontal", x);
            waypoint.put("Vertical", y);
        }
        else if(direction == 'F'){
            position.put("Vertical", (position.get("Vertical") + amount*waypoint.get("Vertical")));
            position.put("Horizontal", (position.get("Horizontal") + amount*waypoint.get("Horizontal")));
        }
        else{
            System.out.println("Unknown command! " + direction);
        }
    }
}
