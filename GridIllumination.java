import java.util.HashSet;
import java.util.Set;

public class GridIllumination {

    int N; // board size
    Set<Integer> illuminated_x = new HashSet<>();
    Set<Integer> illuminated_y = new HashSet<>();
    Set<Integer> illuminated_diag0 = new HashSet();
    Set<Integer> illuminated_diag1 = new HashSet();

    // @param lamps - a list of (x,y) locations of lamps
    public GridIllumination(final int N, final int[][] lamps) {
        this.N = N;
        for (final int[] lamp : lamps) { // this lamp illuminates 4 lines of cells
            illuminated_x.add(lamp[0]); // the entire column
            illuminated_y.add(lamp[1]); // the entire row
            illuminated_diag0.add(lamp[1] - lamp[0]); // diagonal line with a slope of 1
            illuminated_diag1.add(lamp[0] + lamp[1]); // diagonal lines with a slope of -1
        }
    }

    public boolean is_illuminated(final int x, final int y) {
        if (illuminated_x.contains(x) || illuminated_y.contains(y) || illuminated_diag0.contains(y - x)
            || illuminated_diag1.contains(x + y)) {
            return true;
        }
        return false;
    }

    public static void main(final String args[]) {
        final int[][] lamps = new int[2][2];
        lamps[0][0] = 1;
        lamps[0][1] = 1;
        final GridIllumination gi = new GridIllumination(4, lamps);
        System.out.println(gi.is_illuminated(2, 3));
    }
}
