package recursion;

/**
 * A Maze is given as N*N binary matrix of blocks where source block is the upper left most
 * block i.e., maze[0][0] and destination block is lower rightmost block i.e., maze[N-1][N-1].
 * A rat starts from source and has to reach the destination. The rat can move only in two
 * directions: forward and down.
 * In the maze matrix, 0 means the block is a dead end and 1 means the block can be used in
 * the path from source to destination. Note that this is a simple version of the typical Maze
 * problem. For example, a more complex version can be that the rat can move in 4 directions
 * and a more complex version can be with a limited number of moves.
 *
 * @author vaibhav
 */
public class Maze {

    private static boolean pathExistsFrom(int[][] maze, int n, int i, int j) {
        if (i == (n - 1) && (j == n - 1)) {
            return true;
        }

        boolean rightAllowed = ((j + 1) <= (n - 1)) && (maze[i][j + 1] == 1);
        boolean downAllowed = ((i + 1) <= (n - 1)) && (maze[i + 1][j] == 1);

        if (!rightAllowed && !downAllowed) {
            return false;
        }

        if (rightAllowed && downAllowed) {
            return pathExistsFrom(maze, n, i, j + 1) || pathExistsFrom(maze, n, i + 1, j);
        }

        if (rightAllowed) {
            return pathExistsFrom(maze, n, i, j + 1);
        }

        return pathExistsFrom(maze, n, i + 1, j);
    }

    private static boolean pathExists(int[][] maze, int n) {
        if (maze[n - 1][n - 1] == 0) {
            return false;
        }

        return pathExistsFrom(maze, n, 0, 0);
    }

    public static void main(String[] args) {
        int[][] maze = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1},
        };

        int n = 4;

        System.out.println(pathExists(maze, n));
    }
}
