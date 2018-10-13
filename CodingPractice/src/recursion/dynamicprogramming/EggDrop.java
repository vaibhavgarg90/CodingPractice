package recursion.dynamicprogramming;

/**
 * A dynamic programming based generic solution of the famous egg-drop puzzle.
 */
public class EggDrop {

    private static int solve(int n, int k) {
        int[][] drops = new int[n + 1][k + 1];
        int i, j, x;

        for (j = 0; j <= k; j++) {
            drops[0][j] = 0;
            drops[1][j] = j;
        }

        drops[0][0] = 0;

        for (i = 1; i <= n; i++) {
            drops[i][0] = 0;
            drops[i][1] = 1;
        }

        for (i = 2; i <= n; i++) {
            for (j = 2; j <= k; j++) {
                int min = Integer.MAX_VALUE;

                for (x = 1; x <= j; x++) {
                    min = Math.min(min, 1 + Math.max(drops[i - 1][x - 1], drops[i][j - x]));
                }

                drops[i][j] = min;
            }
        }

        for (i = 1; i <= n; i++) {
            System.out.print(i + "\t");

            for (j = 1; j <= k; j++) {
                System.out.print(drops[i][j] + "\t");
            }

            System.out.println();
        }

        return drops[n][k];
    }

    public static void main(String[] args) {
        solve(10, 20);
    }
}
