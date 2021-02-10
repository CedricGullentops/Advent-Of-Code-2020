package Day12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

class Main {
    private static HashMap<String, Integer> position = new HashMap<>();
    private static int angle = 0;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        position.put("Vertical" , 0);
        position.put("Horizontal" , 0);

        while (sc.hasNextLine()){
            String line = sc.nextLine();
            if (line.contains("end")){
                break;
            }
            handleCommand(line);
            System.out.println("Position: V " + position.get("Vertical") + " H " + position.get("Horizontal"));
        }

        System.out.println("Final position: V " + position.get("Vertical") + " H " + position.get("Horizontal"));
        System.out.println("Solution: " + (Math.abs(position.get("Vertical")) + Math.abs(position.get("Horizontal"))));
    }

    private static void handleCommand(String line){
        char direction = line.toCharArray()[0];
        Integer amount = Integer.parseInt(line.substring(1));
        if (direction == 'N'){
            position.put("Vertical", position.get("Vertical") + amount);
        }
        else if(direction == 'S'){
            position.put("Vertical", position.get("Vertical") - amount);
        }
        else if(direction == 'E'){
            position.put("Horizontal", position.get("Horizontal") + amount);
        }
        else if(direction == 'W'){
            position.put("Horizontal", position.get("Horizontal") - amount);
        }
        else if(direction == 'L'){
            angle += amount;
            correctAngle();
        }
        else if(direction == 'R'){
            angle -= amount;
            correctAngle();
        }
        else if(direction == 'F'){
            if (angle == 90){
                position.put("Vertical", position.get("Vertical") + amount);
            }
            else if (angle == 180){
                position.put("Horizontal", position.get("Horizontal") - amount);
            }
            else if (angle == 270){
                position.put("Vertical", position.get("Vertical") - amount);
            }
            else if (angle == 0){
                position.put("Horizontal", position.get("Horizontal") + amount);
            }
        }
        else{
            System.out.println("Unknown command! " + direction);
        }
    }

    private static void correctAngle(){
        while(angle < 0 || angle >= 360){
            if (angle < 0){
                angle += 360;
            }
            else {
                angle -= 360;
            }
        }
    }
}
