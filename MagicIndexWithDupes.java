public class MagicIndexWithDupes {
    public int findMagicIndex(final int[] arr) {
        return findMagicIndex(arr, 0, arr.length - 1);
    }

    private int findMagicIndex(final int[] arr, final int start, final int end) {

        if (end < start) {
            return -1;
        }

        final int mid = start + end / 2;
        if (arr[mid] == mid) {
            return mid;
        }

        final int left = findMagicIndex(arr, start, Math.min(mid - 1, arr[mid]));
        if (left >= 0) {
            return left;
        }

        final int right = Math.max(mid + 1, arr[mid]);
        return right;
    }

    public static void main(final String args[]) {
        final MagicIndexWithDupes magic = new MagicIndexWithDupes();
        final int[] arr = new int[] { -10, -5, 2, 2, 2, 3, 4, 7, 9, 12, 13 };
        System.out.println(magic.findMagicIndex(arr));
    }
}
