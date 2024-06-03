package redok.exp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Main {
    public static String inputFileName = "input/input.txt";

    public static void main(String[] args) {
        int[][] edges = readSidesFromFile();
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
            System.out.println("Возможно");
        } else {
            System.out.println("Невозможно");
        }
    }

    //{2}
    public static boolean isPossibleToCombineSides(int[][] edges) {
        HashMap<Integer, Integer> numberCount = new HashMap<>();
        //Ищем повторения чисел в матрице и складывает в мапу
        for (int i = 0; i < 6; i += 2) {
            numberCount.put(edges[i][0], numberCount.getOrDefault(edges[i][0], 0) + 1);
            numberCount.put(edges[i][1], numberCount.getOrDefault(edges[i][1], 0) + 1);
        }
        //Сравнение количества "измерений", а так же исключение для куба
        return (numberCount.size() == 3 && numberCount.values().stream().allMatch(number -> number == 2)) ||
                numberCount.size() == 1 && numberCount.values().stream().allMatch(number -> number == 6);
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

/*           if (equalPairEdgesCount == 3) {
            if (((edges[0][0] == edges[2][0] || edges[0][0] == edges[2][1]) ||
                    (edges[0][0] == edges[4][0] || edges[0][0] == edges[4][1])) &&
                    ((edges[0][1] == edges[2][0] || edges[0][1] == edges[2][1]) ||
                    (edges[0][1] == edges[4][0] || edges[0][1] == edges[4][1])) &&
                    ((edges[2][0] == edges[0][0] || edges[2][0] == edges[0][1]) ||
                    (edges[2][0] == edges[4][0] || edges[2][0] == edges[4][1])) &&
                    ((edges[2][1] == edges[0][0] || edges[2][1] == edges[0][1]) ||
                    (edges[2][1] == edges[4][0] || edges [2][1] == edges[4][1])) &&
                    ((edges[4][0] == edges[0][0] || edges[4][0] == edges[0][1]) ||
                    (edges[4][0]== edges[2][0] || edges[4][0] == edges[2][1])) &&
                    ((edges[4][1] == edges[0][0] || edges[4][1] == edges[0][1]) ||
                    (edges[4][1] == edges[2][0] || edges[4][1] == edges[2][1]))

            ) {
                System.out.println("Возможно");
            }
            else {
                System.out.println("Невозможно");
            }
        }*/
}