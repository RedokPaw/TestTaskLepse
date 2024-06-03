package redok.exp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static int[][] matrix = {{4, 1, 8, 9, 2},
                                    {4, 4, 7, 9, 6},
                                    {3, 1, 8, 4, 7}};

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(matrix));

        findSaddlePointOfMatrix(matrix);

    }

    public static void findSaddlePointOfMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean isFound = false;
        for (int i = 0; i < rows; i++) {
            int minNumInRow = matrix[i][0];
            List<Integer> minIndexes = new ArrayList<>();
            //ищем минимумы и запоминаем индексы
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] < minNumInRow) {
                    minIndexes.clear();
                    minNumInRow = matrix[i][j];
                    minIndexes.add(j);
                } else if (matrix[i][j] == minNumInRow) {
                    minIndexes.add(j);
                }
            }
            /*
            проходимся по столбцу с мин. индексом, если есть другие элементы больше - выходим из цикла,
            т-к нужен наибольший элемент в столбце, иначе выводим эту точку
            */
            boolean isLoopFinished = true;
            for (Integer minIndex : minIndexes) {
                for (int j = 0; j < rows; j++) {
                    if (minNumInRow < matrix[j][minIndex]) {
                        isLoopFinished = false;
                        break;
                    }
                }
                if (isLoopFinished) {
                    System.out.printf("Седловая точка - %s, ее координаты: [%s, %s] \n", minNumInRow , i + 1, minIndex + 1);
                    isFound = true;
                }
            }

        }
        if (!isFound) {
            System.out.println("Седловая точка не найдена");
        }
    }
}