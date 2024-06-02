package redok.Task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Main {
    public static final String ONLY_BRACKETS_REGEX = "[^({\\[\\]})]";

    public static String inputFileName = "input/input.txt";

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName));) {
            while (br.ready()) {
                String input = br.readLine();
                String bracketsSequence = input.replaceAll(ONLY_BRACKETS_REGEX, "");
                if (isCorrectBracketsSequence(bracketsSequence)) {
                    System.out.printf("%s - правильная скобочная последовательность \n", input);
                } else {
                    System.out.printf("%s - неправильная скобочная последовательность \n", input);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean isCorrectBracketsSequence(String input) {
        Stack<Character> openBrackets = new Stack<>();
        for (char bracket : input.toCharArray()) {
            if (bracket == '(' || bracket == '[' || bracket == '{') {
                openBrackets.push(bracket);
            } else if (bracket == ')' || bracket == ']' || bracket == '}') {
                Character openBracket = convertToOpenBracket(bracket);
                if (openBrackets.isEmpty()) {
                    return false;
                }
                if (openBrackets.peek() == openBracket) {
                    openBrackets.pop();
                } else {
                    return false;
                }
            } else {
                throw new IllegalArgumentException("Invalid character: " + bracket);
            }
        }
        return openBrackets.isEmpty();
    }

    private static Character convertToOpenBracket(Character bracket) {
        return switch (bracket) {
            case ')' -> '(';
            case ']' -> '[';
            case '}' -> '{';
            default -> throw new IllegalArgumentException("Invalid bracket: " + bracket);
        };
    }
}