package rfronteddu.java_experiments.algorithms.sort.quicksort;

/**
 * The goal of this sort is to speed up quick sort in the presence of duplicated elements.
 */
public class QuickSort3Way extends QuickSort
{
    public static void sort3W (Comparable[] a) {
        shuffle (a);
        sort3W (a, 0, a.length - 1);
    }

    // ###############################################

    private static void sort3W (Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        Comparable v = a[lo];
        int lt = lo;
        int gt = hi;
        int i = lo;
        while (i <= gt) {
            int cmp = a[i].compareTo (v);
            if (cmp < 0) {
                exchange (a, lt++, i++);
            } else if (cmp > 0) {
                exchange (a, i, gt--);
            } else {
                i++;
            }
        }
        sort3W (a, lo, lt -1);
        sort3W (a, gt + 1, hi);
    }
}
