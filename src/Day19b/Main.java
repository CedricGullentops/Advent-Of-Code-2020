package Day19b;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

class Main {
    public static HashMap<Integer, Rule> rules = new HashMap<>();
    public static HashMap<Integer, Integer> allowedTimes = new HashMap<>();

    public static void main(String[] args) {
        ArrayList<String> toCheck = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String line = sc.nextLine();
            if(line.contains("end")){
                break;
            }
            if(line.contains(":")){
                Rule newRule = new Rule();
                String[] splitLine = line.split(":");
                if (splitLine[1].contains("\"")){
                    newRule.options.add(splitLine[1].substring(splitLine[1].indexOf("\"")+1, splitLine[1].lastIndexOf("\"")));
                }
                else{
                    String[] rightSide = splitLine[1].split("\\|");
                    for (String s: rightSide){
                        String[] subRules = s.split(" ");
                        ArrayList<Integer> subRulesList = new ArrayList<>();
                        for (String rule: subRules){
                            if (!rule.equals("")){
                                subRulesList.add(Integer.parseInt(rule));
                            }
                        }
                        newRule.subRules.add(subRulesList);
                    }
                }
                rules.put(Integer.parseInt(splitLine[0]), newRule);
            }
            else {
                toCheck.add(line);
            }
        }

        ArrayList<String> validAnswers = findSubStrings(0);

        int count = 0;
        for (String test: toCheck){
            if (validAnswers.contains(test)){
                count ++;
            }
        }

        System.out.println("Part1: " + count);
    }

    public static ArrayList<String> findSubStrings(int key){
        Rule rule = rules.get(key);
        if (rule.options.size() == 0){
            ArrayList<String> newOptions = new ArrayList<>();
            for (ArrayList<Integer> iList: rule.subRules){
                HashSet<String> newStrings = new HashSet<>();
                for (Integer integer : iList) {
                    if (integer == key){
                        if (allowedTimes.containsKey(key)){
                            if (allowedTimes.get(key) >= 1){
                                continue;
                            }
                            else{
                                allowedTimes.put(key, allowedTimes.get(key) + 1);
                            }
                        }
                        else {
                            allowedTimes.put(key, 0);
                        }
                    }
                    ArrayList<String> temp = findSubStrings(integer);
                    if (newStrings.isEmpty()) {
                        newStrings.addAll(temp);
                    } else {
                        HashSet<String> newSet = new HashSet<>();
                        for (String str: newStrings){
                            for (String tmp: temp){
                                newSet.add(str.concat(tmp));
                            }
                        }
                        newStrings = newSet;
                    }
                }
                newOptions.addAll(newStrings);
            }
            rule.options = newOptions;
        }
        return rule.options;
    }
}

class Rule {
    ArrayList<ArrayList<Integer>> subRules = new ArrayList<>();
    ArrayList<String> options = new ArrayList<>();
}
