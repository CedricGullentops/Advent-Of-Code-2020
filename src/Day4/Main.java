package Day4;

import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> entries = new ArrayList<String>();
        ArrayList<String> entriesCombined = new ArrayList<String>();

        while (sc.hasNextLine()){
            String entry = sc.nextLine();
            if (entry.equals("end")){
                break;
            }
            entries.add(entry);
        }

        int count = 0;
        for (String entry : entries){
            if (!entry.equals("")){
                if (entriesCombined.size() < count + 1){
                    entriesCombined.add(entry);
                }
                else{
                    entriesCombined.set(count, entriesCombined.get(count).concat(" ").concat(entry));
                }
            }
            else{
                count++;
            }
        }

        int validPassCount = 0, validElementCount = 0;
        for (String entry: entriesCombined){
            String[] splitEntry = entry.split(" ");
            for (String split : splitEntry){
                if (isValid(split)){
                    validElementCount++;
                }
            }
            if (validElementCount >= 7){
                validPassCount++;
            }
            validElementCount = 0;
        }
        System.out.println(validPassCount);
    }

    enum EyeColor {
        amb,
        blu,
        brn,
        gry,
        grn,
        hzl,
        oth
    }

    public static boolean isValid(String input){
        String[] items = input.split(":");
        if (items[0].equals("byr")){
            if (items[1].length()==4){
                int byr = Integer.parseInt(items[1]);
                return byr >= 1920 && byr <= 2002;
            }
        }
        else if(items[0].equals("iyr")){
            if (items[1].length()==4){
                int iyr = Integer.parseInt(items[1]);
                return iyr >= 2010 && iyr <= 2020;
            }
        }else if(items[0].equals("eyr")){
            if (items[1].length()==4){
                int eyr = Integer.parseInt(items[1]);
                return eyr >= 2020 && eyr <= 2030;
            }
        }else if(items[0].equals("hgt")){
            String[] hgt = items[1].split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
            if (hgt.length != 2){
                return false;
            }
            int height = Integer.parseInt(hgt[0]);
            if (hgt[1].equals("in")){
                return height >= 59 && height <= 76;
            }
            else if(hgt[1].equals("cm")){
                return height >= 150 && height <= 193;
            }
        }else if(items[0].equals("hcl")){
            if (items[1].length()==7){
                if (items[1].charAt(0)=='#'){
                    for (Character character : items[1].substring(1).toCharArray()){
                        if ((character < 48 || character > 57) && (character < 97 || character > 102)){
                            return false;
                        }
                    }
                    return true;
                }
            }

        }else if(items[0].equals("ecl")){
            return contains(items[1]);

        }else if(items[0].equals("pid")){
            return items[1].length()==9;
        }
        return false;
    }

    public static boolean contains(String test) {
        for (EyeColor c : EyeColor.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
}
