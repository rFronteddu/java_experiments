/*
 * @author  Roberto Fronteddu
 * @version 1.0
 * @since   2019-11-16
 *
 * Randomized quicksort algorithm.
 * Randomized  quickselect, a quicksort variant which finds the kth smallest item in linear time.
 * 3-WAY quick sort, variant of quicksort that works especially well in the presence of duplciate keys
 *
 * Basic plan:
 * Shuffle array
 * Partition so that, for some j
 *  - Entry a[j] is in place
 *  - <= j, j, >= j
 * Sort each piece recursively
 *
 * Best case: Number of compares is ~ N*lg N
 * Worst case:                      ~ 1/2 N^2
 * AVg                              ~ 2N*ln(N)
 *
 * The random shuffle offers a probabilistic guarantee against worst case.
 *
 * Quick-sort is in-place -> uses constant space for partitioning, note that this is a great
 * advantage compared to merge sort which is not in place!
 *
 * and uses logarithmic extra space for recursion
 *
 * Quick-sort is not stable
 *
 * Possible improvements: * Use insertion sort for small sub arrays (~10 items)
 *                        * Pick best pivot point by estimating the true median of three random items

 * Note that implementation goes quadratic unless partitioning stops on equal keys!
 *
 * */

package rfronteddu.java_experiments.algorithms.sort.quicksort;

import java.util.Random;

public class QuickSort
{
    public static void sort (Comparable[] a) {
        shuffle (a);
        sort (a, 0, a.length - 1);
    }

    protected static void exchange (Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    /**
     * Before:
     * [v            ]
     * lo            hi
     *
     * During
     * [v, <= v |      | >= v]
     * lo,      i      j
     *
     * After
     * [ <= v |v| >= v]
     * lo, ...       ,hi
     *
     * Ensures that elements between lo and hi will be on the left of return value j
     * if they are smaller than a[j].
     * @param a input array
     * @param lo lower index
     * @param hi higher index
     * @return index of element in place
     */
    protected static int partition (Comparable[] a, int lo, int hi) {
        int i = lo; // starts at lo because the a[lo] is v
        int j = hi + 1; // starts at hi + 1 because the algorithm decrement and then assign
        while (true) {
            while (a[++i].compareTo (a[lo]) < 0) {
                // find item on the left to swap
                if (i == hi) {
                    break;
                }
            }

            while (a[lo].compareTo (a[--j]) < 0) {
                // find item on right to swap
                if (j == lo) {
                    break;
                }
            }

            // check if pointers cross
            if (i >= j) {
                break;
            }
            // swap
            exchange (a, i, j);
        }

        // swap with partitioning item
        exchange (a, lo, j);
        // return index of item now known to be in place
        return j;
    }

    protected static void shuffle (Comparable[] a) {
        Random rand = new Random ();
        for (int i = 0; i < a.length; i++) {
            int randomIndexToSwap = rand.nextInt (a.length);
            Comparable temp = a[randomIndexToSwap];
            a[randomIndexToSwap] = a[i];
            a[i] = temp;
        }
    }

    // ##################################################

    private static void sort (Comparable[] a, int lo, int hi) {
        // 0 or 1 element we are done
        if (hi <= lo) {
            return;
        }
        int j = partition (a, lo, hi);
        // j is in the right spot, all elements on his left are smaller than him and
        // the ones on its right bigger.
        sort (a, lo, j - 1);
        sort (a, j + 1, hi);
    }
}
