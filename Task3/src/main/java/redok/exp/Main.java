package redok.exp;

import java.util.regex.Pattern;

public class Main {

    public static String variableNameTest = "camelCase";

    public static final String CAMEL_CASE_PATTERN = "^[a-z]+[A-Za-z]*";
    public static final String SNAKE_CASE_PATTERN = "^[a-z]+(?:_[a-z]+)*";

    public static void main(String[] args) {
        System.out.printf("%s -> ", variableNameTest);
        printOutCamelOrSnakeCase(variableNameTest);
    }

    public static void printOutCamelOrSnakeCase(String name) {
        if (Pattern.matches(CAMEL_CASE_PATTERN, name)) {
            System.out.print(convertToSnakeCase(name));
            return;
        }
        if (Pattern.matches(SNAKE_CASE_PATTERN, name)) {
            System.out.print(convertToCamelCase(name));
            return;
        }
        System.out.println("Error!");

    }

    public static String convertToCamelCase(String snakeCaseName) {
        return Pattern.compile("_([a-z])")
                .matcher(snakeCaseName)
                .replaceAll(matchResult -> matchResult.group(1).toUpperCase());
    }

    public static String convertToSnakeCase(String camelCaseName) {
        return camelCaseName.replaceAll("([a-z])([A-Z])+", "$1_$2").toLowerCase();
    }
}