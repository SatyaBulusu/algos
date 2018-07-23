import java.util.HashMap;
import java.util.Map;

import javafx.util.Pair;

public class RobotInAGrid {

    public static String findPaths(final boolean[][] maze) {
        final int r = maze.length;
        final int c = maze[0].length;
        final StringBuilder sb = new StringBuilder();
        final Map<Pair<Integer, Integer>, Boolean> map = new HashMap<>();
        findPaths(r - 1, c - 1, maze, sb, map);
        return sb.toString();
    }

    private static boolean findPaths(final int r, final int c, final boolean[][] maze, final StringBuilder sb,
        final Map<Pair<Integer, Integer>, Boolean> map) {
        if (r < 0 || c < 0 || !maze[r][c]) {
            return false;
        }

        final Pair<Integer, Integer> pair = new Pair<>(r, c);
        if (map.containsKey(pair)) {
            return map.get(pair);
        }

        if ((r == 0 && c == 0) || findPaths(r - 1, c, maze, sb, map) || findPaths(r, c - 1, maze, sb, map)) {
            sb.append("(").append(r).append(",").append(c).append(") --> ");
            return true;
        }
        return false;
    }

    public static void main(final String[] args) {
        final int size = 5;
        final boolean[][] maze = randomBooleanMatrix(size, size, 70);
        printMatrix(maze);
        System.out.println(findPaths(maze));
    }

    public static boolean[][] randomBooleanMatrix(final int M, final int N, final int percentTrue) {
        final boolean[][] matrix = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = randomBoolean(percentTrue);
            }
        }
        return matrix;
    }

    public static int randomInt(final int n) {
        return (int) (Math.random() * n);
    }

    public static int randomIntInRange(final int min, final int max) {
        return randomInt(max + 1 - min) + min;
    }

    public static boolean randomBoolean() {
        return randomIntInRange(0, 1) == 0;
    }

    public static boolean randomBoolean(final int percentTrue) {
        return randomIntInRange(1, 100) <= percentTrue;
    }

    public static void printMatrix(final boolean[][] matrix) {
        for (final boolean[] element : matrix) {
            for (final boolean element2 : element) {
                if (element2) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
    }

}
