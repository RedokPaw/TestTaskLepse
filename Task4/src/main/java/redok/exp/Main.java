package redok.exp;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Main {
    public static String inputFileName = "input/input.txt";

    public static void main(String[] args) {
        int[][] edges = readSidesFromFile();

        Arrays.sort(edges, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(edges, Comparator.comparingInt(a -> a[1]));
        System.out.println(Arrays.deepToString(edges));

        //{1}
        int equalPairEdgesCount = 0;
        for (int i = 0; i < 6; i += 2) {
            if ((edges[i][0] == edges[i + 1][0] && edges[i][1] == edges[i + 1][1])) {
                equalPairEdgesCount++;
            }
        }

        if (equalPairEdgesCount == 3 && isPossibleToCombineSides(edges)) {
            writeToFile("POSSIBLE");
            System.out.println("POSSIBLE");
        } else {
            writeToFile("IMPOSSIBLE");
            System.out.println("IMPOSSIBLE");
        }
    }

    //{2}
    public static boolean isPossibleToCombineSides(int[][] edges) {
        HashMap<Integer, Integer> numberCount = new HashMap<>();
        //счетчик для квадратных сторон
        int squaresSidesCount = 0;

        //Ищем повторения чисел в матрице и складывает в мапу
        for (int i = 0; i < 6; i += 2) {
            numberCount.put(edges[i][0], numberCount.getOrDefault(edges[i][0], 0) + 1);
            numberCount.put(edges[i][1], numberCount.getOrDefault(edges[i][1], 0) + 1);
            if (edges[i][0] == edges[i][1]) {
                squaresSidesCount++;
            }
        }
        int numCombinations = numberCount.size();
        System.out.println(numberCount);
        return switch (numCombinations) {
            case 1 -> true;
            case 2 -> numberCount.values().stream().anyMatch(number -> number == 2)
                    && numberCount.values().stream().anyMatch(number -> number == 4) && squaresSidesCount == 1;
            case 3 -> numberCount.values().stream().allMatch(number -> number == 2) && squaresSidesCount == 0;
            default -> false;
        };
    }

    public static int[][] readSidesFromFile() {
        int[][] edges = new int[6][2];
        int rowsCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
            while (br.ready()) {
                String line = br.readLine();
                String[] sides = line.split(" ");
                int firstSide = Integer.parseInt(sides[0]);
                int secondSide = Integer.parseInt(sides[1]);
                if (firstSide > secondSide) {
                    edges[rowsCount][0] = firstSide;
                    edges[rowsCount][1] = secondSide;
                } else {
                    edges[rowsCount][0] = secondSide;
                    edges[rowsCount][1] = firstSide;
                }
                rowsCount += 1;
            }
        } catch (IOException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return edges;
    }

    public static void writeToFile(String text) {
        try (Writer fileWriter = new FileWriter("OUTPUT.TXT")) {
            fileWriter.write(text);
        } catch (IOException e) {}
    }
}