import java.util.Arrays;

public class TripleStep {

    // recursion
    public int countWaysRecursion(final int n) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        }
        return countWaysRecursion(n - 3) + countWaysRecursion(n - 2) + countWaysRecursion(n - 1);
    }

    // DP
    public int countWaysDP(final int n) {
        final int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return countWaysDP(n, memo);
    }

    private int countWaysDP(final int n, final int[] memo) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else if (memo[n] > -1) {
            return memo[n];
        }
        memo[n] = countWaysDP(n - 3, memo) + countWaysDP(n - 2, memo) + countWaysDP(n - 1, memo);
        return memo[n];
    }

    public static void main(final String args[]) {
        final TripleStep step = new TripleStep();
        System.out.println(step.countWaysRecursion(5));
        System.out.println(step.countWaysDP(5));
    }
}
