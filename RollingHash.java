import java.util.ArrayList;
import java.util.List;

public class RollingHash {

    List<Integer> res = new ArrayList<>();

    int prime = 101;

    public static void main(final String args[]) {
        final int[] A = new int[] { 3, 4, 5, 3 };
        final int[] B = new int[] { 24, 3, 4, 5, 3, 4, 5, 3, 9, 255 };
        final RollingHash rh = new RollingHash();
        rh.findMatchIndex(A, B);
    }

    private void findMatchIndex(final int[] A, final int[] B) {
        final int m = A.length;
        final int n = B.length;

        final long hashA = calculateHash(A, m - 1);
        long hashB = calculateHash(B, m - 1);

        for (int i = 1; i < n - m + 1; i++) {
            if (hashA == hashB) {
                res.add(i - 1);
            }
            // reclaculate hash
            hashB = recalculateHash(B, i - 1, i + m - 1, hashB, m);
        }

        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }

    }

    private long calculateHash(final int[] C, final int len) {
        long hash = 0;
        for (int i = 0; i <= len; i++) {
            hash += C[i] * Math.pow(prime, i);
        }
        return hash;
    }

    private long recalculateHash(final int[] B, final int dropIdx, final int newIdx, final long oldHash,
        final int patternLen) {
        // subtract integer at dropIdx from the old hash
        long newHash = oldHash - B[dropIdx];
        // divide oldHash by the prime
        newHash = newHash / prime;
        // add the hash for integer at new idx
        newHash += B[newIdx] * Math.pow(prime, patternLen - 1);
        return newHash;
    }
}
