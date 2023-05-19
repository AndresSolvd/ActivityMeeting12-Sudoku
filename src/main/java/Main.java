import java.util.*;

public class Main {
    public static void main(String[] args) {

        int[][] sudokuBoard = {
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };

        if (!checkSudoku(sudokuBoard, 3) | !checkRowsAndColumns(sudokuBoard)) {
            System.out.println("Sudoku wrong");
        } else {
            System.out.println("Sudoku ok");
        }
    }

    public static boolean checkIsValid(List<Integer> list) {
        Set<Integer> set = new HashSet<>();

        for (int number : list) {
            if (number != 0) {
                if (set.contains(number)) {
                    return false;
                } else {
                    set.add(number);
                }
            }
        }
        return true;
    }

    public static boolean checkRowsAndColumns(int[][] arr) {

        ArrayList<Integer> listRow = new ArrayList<>(Arrays.asList());
        ArrayList<Integer> listColumn = new ArrayList<>(Arrays.asList());

        for (int i = 0; i < arr.length; i++) {
            listRow.clear();
            listColumn.clear();

            for (int j = 0; j < arr[i].length; j++) {

                listRow.add(arr[i][j]);
                listColumn.add(arr[j][i]);
            }

            if (!checkIsValid(listRow) || !checkIsValid(listColumn)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkArrayByCoordinates(int[][] arr, int fromX, int fromY, int toX, int toY) {
        int[][] byThree = new int[3][3];

        for (fromX = 0; fromX < toX; fromX++) {
            for (fromY = 0; fromY < toY; fromY++) {
                byThree[fromX][fromY] = arr[fromX][fromY];
            }
        }
        if (!checkRowsAndColumns(byThree)) {
            System.out.println("Sudoku is not valid");
            return false;
        }
        return true;
    }

    public static boolean checkSudoku(int[][] array, int subGroupLength) {

        for (int i = 0; i < array.length; i = i + subGroupLength) {
            for (int j = 0; j < array.length; j = j + subGroupLength) {
                return checkArrayByCoordinates(array, i, j, i + subGroupLength, j + subGroupLength);
            }
        }
        return true;
    }
}

