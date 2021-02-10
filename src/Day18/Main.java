package Day18;

import java.util.ArrayList;
import java.util.Scanner;

import java.util.function.Function;
import java.util.*;

class Day18 {
    public static void main(String[] args) {
        List<String[]> lines = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String line = sc.nextLine();
            if(line.contains("end")){
                break;
            }
            lines.add(line.replaceAll(" ", "").split(""));
        }

        List<Long> part1 = new ArrayList<>();
        List<Long> part2 = new ArrayList<>();
        for (String[] line : lines) {
            part1.add(run(line, Day18::evalLine));
            part2.add(run(line, Day18::evalLineWithPrecedence));
        }

        System.out.println("Part1: " +
                part1.stream().mapToLong(x -> x).sum());
        System.out.println("Part2: " +
                part2.stream().mapToLong(x -> x).sum());
    }


    public static long run(String[] line, Function<List<String>, Long> f) {
        Stack<List<String>> stack = new Stack<>();
        List<String> workingLine = new ArrayList<>();
        for (String token : line) {
            if (token.equals("(")) {
                stack.push(workingLine);
                workingLine = new ArrayList<String>();
            } else if (token.equals(")")) {
                stack.peek().add("" + f.apply(workingLine));
                workingLine = stack.pop();
            } else {
                workingLine.add(token);
            }
        }
        return f.apply(workingLine);
    }


    public static long evalLineWithPrecedence(List<String> line) {
        List<String> lineOnlyMulti = new ArrayList<>();
        for (int i = 0; i < line.size(); ++i) {
            String current = line.get(i);
            if (current.equals("+") || current.equals("-")) {
                int lastTokenIndex = lineOnlyMulti.size() - 1;
                long leftOperand = Long.parseLong(lineOnlyMulti.remove(lastTokenIndex));
                long rightOperand = Long.parseLong(line.get(i + 1));
                lineOnlyMulti.add("" + eval(leftOperand, rightOperand, current));
                ++i;
            } else {
                lineOnlyMulti.add(line.get(i));
            }
        }
        return evalLine(lineOnlyMulti);
    }


    public static long evalLine(List<String> line) {
        long total = Long.parseLong(line.get(0));
        for (int i = 1; i < line.size(); ++i) {
            String current = line.get(i);
            if (current.equals("+") || current.equals("-") || current.equals("*")) {
                long rightOperand = Long.parseLong(line.get(i + 1));
                total = eval(total, rightOperand, current);
            }
        }
        return total;
    }


    public static long eval(long left, long right, String operator) {
        if (operator.equals("+")) {
            return left + right;
        } else if (operator.equals("-")) {
            return left - right;
        }else if (operator.equals("*")) {
            return left * right;
        }

        throw new IllegalArgumentException();
    }
}
