import java.util.Arrays;

public class NCRPrime {

    public int nCrModp(final int n, final int r, final int p) {
        final int[] C = new int[r + 1];
        Arrays.fill(C, 0);

        C[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(i, r); j > 0; j--) {
                // mod prime is distributive
                C[j] = (C[j] + C[j - 1]) % p;
            }
        }

        return C[r];
    }

    public static void main(final String args[]) {
        final NCRPrime ncrp = new NCRPrime();
        System.out.println(ncrp.nCrModp(10, 2, 13));
    }

}
