package Day13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Main {
    private static ArrayList<Bus> busses = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer startTime = Integer.parseInt(sc.nextLine());
        String line = sc.nextLine();
        parseBusses(line);

        int smallest = 2000000000;
        int smallestId = 0;

        for (Bus bus: busses){
            int first = bus.nextTimeAtPort(startTime);
            if (first < smallest){
                smallest = first;
                smallestId = bus.id;
            }
        }

        System.out.println("BusId: " + smallestId + " time: " + smallest + " result: " + (smallest-startTime)*smallestId);
    }

    private static void parseBusses(String line){
        for (String sub : line.split(",")){
            if (sub.length() == 1 && sub.toCharArray()[0] == 'x'){
                continue;
            }
            busses.add(new Bus(Integer.parseInt(sub)));
        }
    }
}

class Bus{
    public int id;

    public Bus(int id){
        this.id = id;
    }

    public int nextTimeAtPort(int depart){
        return (int) Math.ceil((double) depart/ (double) this.id)*this.id;
    }
}
