package redok.exp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static String inputFileName = "input/input.txt";

    public static void main(String[] args) {
        int[][] edges = readSidesFromFile();

        Arrays.sort(edges, Comparator.comparingInt(a -> a[0]));
        System.out.println(Arrays.deepToString(edges));

        int equalEdgesCount = 0;
        for (int i = 0; i < 6; i += 2) {
            if (edges[i][0] == edges[i + 1][0] && edges[i][1] == edges[i + 1][1]) {
                equalEdgesCount++;
            }
        }
        if (equalEdgesCount == 3) {
            System.out.println("Возможно");
        } else {
            System.out.println("Невозможно");
        }
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
}