import static java.lang.Math.max;

public class FindMaxPath {

    // Function to calculate max path in matrix
    static int findMaxPath(final int mat[][]) {
        final int m = mat.length, n = mat[0].length;
        // To find max val in first row
        int res = 0;

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0 && j < n - 1) {
                    mat[i][j] = mat[i][j] + max(mat[i - 1][j - 1], max(mat[i - 1][j], mat[i - 1][j + 1]));
                } else if (j > 0) {
                    mat[i][j] = mat[i][j] + max(mat[i - 1][j - 1], mat[i - 1][j]);
                } else if (j < n - 1) {
                    mat[i][j] = mat[i][j] + max(mat[i - 1][j], mat[i - 1][j + 1]);
                }
                res = max(res, mat[i][j]);
            }
        }

        return res;
    }

    // driver program
    public static void main(final String[] args) {
        final int mat[][] =
            { { 10, 10, 2, 0, 20, 4 }, { 1, 0, 0, 30, 2, 5 }, { 0, 10, 4, 0, 2, 0 }, { 1, 0, 2, 20, 0, 4 } };

        System.out.println(findMaxPath(mat));
    }
}
