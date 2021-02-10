package Day13b;

import java.util.ArrayList;
import java.util.Scanner;

class Main {
    private static ArrayList<Bus> busses = new ArrayList<>();
    private static Long totalProduct;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line = sc.nextLine();
        parseBusses(line);

        Long totalSum = 0L;
        for (Bus bus: busses){
            bus.computeRest();
            bus.computePartialProduct(totalProduct);
            bus.computeInverse();
            bus.computeSum();
            totalSum += bus.sum;
        }

        System.out.println("StartTime: " + totalSum % totalProduct);
    }

    private static void parseBusses(String line){
        totalProduct = 1L;
        String[] bus = line.split(",");
        for (int i=0; i<bus.length; i++){
            if (bus[i].length() == 1 && bus[i].charAt(0) == 'x'){
                continue;
            }
            long busId = Long.parseLong(bus[i]);
            Bus newBus = new Bus(busId, i);
            busses.add(newBus);
            totalProduct*=busId;
        }
    }
}

class Bus{
    public long id;
    public int offset;
    public long partialProduct = 0L;
    public long inverse = 0L;
    public long sum = 0L;
    public long rest = 0L;

    public Bus(long id, int offset){
        this.id = id;
        this.offset = offset;
    }

    public void computeRest(){
        this.rest = (id-(offset%id))%id;
    }

    public void computePartialProduct(Long totalProduct){
        this.partialProduct = totalProduct/ this.id;
    }

    public void computeInverse()
    {
        long a = partialProduct;
        long b = id;

        if (b == 1){
            inverse = 0L;
            return;
        }

        long m = b, t, q;
        long x = 0, y = 1;

        while (a > 1)
        {
            q = a / b;
            t = b;

            b = a % b;
            a = t;

            t = x;
            x = y - q * x;
            y = t;
        }

        if (y < 0L){
            y += m;
        }

        inverse = y;
    }

    public void computeSum(){
        this.sum =  this.partialProduct*this.inverse*this.rest;
    }
}
