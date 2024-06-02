package redok.exp;

import java.util.Arrays;

public class Main {

    public static int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {0, 8, 9}};

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(matrix));

        findSaddlePointOfMatrix(matrix);

    }

    public static void findSaddlePointOfMatrix(int[][] matrix) {
        int matrixSize = matrix.length;
        for (int i = 0; i < matrixSize; i++) {
            int min_row = matrix[i][0];
            int min_index = 0;

            for (int j = 0; j < matrixSize; j++) {
                if (matrix[i][j] < min_row) {
                    min_row = matrix[i][j];
                    min_index = j;
                }
            }
            boolean isLoopFinished = true;
            for (int j = 0; j < matrixSize; j++) {
                if (min_row < matrix[j][min_index]) {
                    isLoopFinished = false;
                    break;
                }
            }
            if (isLoopFinished) {
                System.out.printf("Седловая точка - %s, ее координаты: [%s, %s]", min_row, i, min_index);
                return;
            }

        }
        System.out.println("Седловая точка не найдена");
    }
}