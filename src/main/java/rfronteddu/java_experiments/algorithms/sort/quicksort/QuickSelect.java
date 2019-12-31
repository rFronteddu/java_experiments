package rfronteddu.java_experiments.algorithms.sort.quicksort;

/**
 * This quick select works by partitioning each value a[j] so that no larger entry is on the left of j and no smaller
 * to the right. It finishes when j equals k. Each time, if k is bigger than j we examine the right array,
 * if it is smaller the left one.
 *
 * Note that quick select takes linear time on average. Intuitively, each partitioning step splits the array approximately in half
 * so there are N + N/2 + N/4 + ... + 1 ~ 2N compares.
 */
public class QuickSelect extends QuickSort
{
    public static Comparable select (Comparable[] a, int k) {
        shuffle (a);
        int lo = 0;
        int hi = a.length - 1;
        while (hi > lo) {
            int j = partition (a, lo, hi);
            if (j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = k - 1;
            } else {
                return a[k];
            }
        }
        return a[k];
    }
}
