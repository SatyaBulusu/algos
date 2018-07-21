
public class CountAllPathsMatrixRightDown {
    static int numberOfPaths(final int m, final int n) {
        final int matrix[][] = new int[m][n];

        // to reach any cell in first column is 1
        for (int i = 0; i < m; i++) {
            matrix[i][0] = 1;
        }

        // to reach any cell in first row is 2
        for (int i = 0; i < n; i++) {
            matrix[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
            }
        }

        return matrix[m - 1][n - 1];
    }

    public static void main(final String args[]) {
        System.out.println(numberOfPaths(3, 3));
    }
}
