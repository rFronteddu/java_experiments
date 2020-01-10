package rfronteddu.java_experiments.algorithms.sort;

/**
 * Move entries more than one position at a time by h-sorting the array
 * It is insertion sort, with stride length h
 *
 * Worst case with 3 x + 1 is O(N ^ 3/2) and linear increments
 */
public class ShellSort
{
    public static void sort (Comparable[] a, int N) {
        int h = 1;
        while (h < N / 3) {
            // 3x + 1 empirical sequence increment
            h = 3 * h + 1;
        }
        // h sort array
        while (h >= 1) {
            // insertion sort
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && a[j].compareTo (a[j - h]) < 0; j -= h) {
                    SortUtil.exchange (a, j, j - h);
                }
            }
            // move to next increment
            h = h / 3;
        }
    }
}
