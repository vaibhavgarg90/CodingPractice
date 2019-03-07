package algorithms;

import util.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidSudoku {

    private static boolean checkValid(int[] nums) {
        Set<Integer> ints = new HashSet<>();

        for (int num : nums) {
            if (num == 0) {
                continue;
            }

            if (ints.contains(num)) {
                return false;
            }

            ints.add(num);
        }

        return true;
    }

    private static int[] getRow(int[][] sudoku, int i, int j) {
        int[] nums = new int[9];

        for (j = 0; j < 9; j++) {
            nums[j] = sudoku[i][j];
        }

        return nums;
    }

    private static int[] getCol(int[][] sudoku, int i, int j) {
        int[] nums = new int[9];

        for (i = 0; i < 9; i++) {
            nums[i] = sudoku[i][j];
        }

        return nums;
    }

    private static int[] getGrid(int[][] sudoku, int i, int j) {
        int[] nums = new int[9];

        int minX;
        int maxX;
        int minY;
        int maxY;

        if (i <= 2) {
            minX = 0;
            maxX = 2;
        } else if (i <= 5) {
            minX = 3;
            maxX = 5;
        } else {
            minX = 6;
            maxX = 8;
        }

        if (j <= 2) {
            minY = 0;
            maxY = 2;
        } else if (j <= 5) {
            minY = 3;
            maxY = 5;
        } else {
            minY = 6;
            maxY = 8;
        }

        int k = 0;

        for (i = minX; i <= maxX; i++) {
            for (j = minY; j <= maxY; j++) {
                nums[k] = sudoku[i][j];
                k++;
            }
        }

        return nums;
    }

    private static int isValidSudoku(final List<String> A) {
        int[][] sudoku = new int[9][9];

        int i, j;

        for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                String s = A.get(i);
                char ch = s.charAt(j);
                int num;

                if (ch == '.') {
                    num = 0;
                } else {
                    num = ch - '0';
                }

                sudoku[i][j] = num;
            }
        }

        for (i = 0; i < 9; i++) {
            Util.printArray(sudoku[i], "\t");
            System.out.println();
        }

        for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                int[] row = getRow(sudoku, i, j);
                if (!checkValid(row)) {
                    return 0;
                }

                int[] col = getCol(sudoku, i, j);
                if (!checkValid(col)) {
                    return 0;
                }

                int[] grid = getGrid(sudoku, i, j);
                if (!checkValid(grid)) {
                    return 0;
                }
            }
        }

        return 1;
    }

    public static void main(String[] args) {
        List<String> A = new ArrayList<>();

        A.add("....5..1.");
        A.add(".4.3.....");
        A.add(".....3..1");
        A.add("8......2.");
        A.add("..2.7....");
        A.add(".15......");
        A.add(".....2...");
        A.add(".2.9.....");
        A.add("..4......");

        System.out.println(isValidSudoku(A));
    }
}
