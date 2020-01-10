package rfronteddu.java_experiments.algorithms.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * For i that goes from 0 to N, in iteration i, find index min of smallest remaining entry and swap a[i] with
 * a[min]
 *
 * Selection sort uses (N - 1) + (N - 2) + ... + 1 + 0 ~ N ^2 / 2 compares and N exchanges
 *  Note that running time is insensitive to input and that data movement is minimal.
 */
public class SelectionSort
{
    private static final Logger logger = LoggerFactory.getLogger (SelectionSort.class);
    // < ---------------------------------------------------------------------------- >

    /**
     * Order array using selection sort.
     * @param a array to order,
     * @param N length of array
     */
    static public void sort (Comparable[] a, int N) {
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (a[j].compareTo (a[min]) < 0) {
                   min = j;
                }
            }
            if (i != min) {
                SortUtil.exchange (a, i, min);
            }
        }
    }
}
