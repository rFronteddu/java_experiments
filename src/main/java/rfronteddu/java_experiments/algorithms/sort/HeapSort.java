package rfronteddu.java_experiments.algorithms.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Note that this implementation assumes index starting on 1 not on 0
 *
 * Heap construction uses <= 2 N compares and exchanges
 * heapsort uses <= 2 N lg N compares and exchanges
 *
 * -> In place sorting algorithm with N lg N worst case (quicksort is better on average
 * but has a quadratic worst case, merge sort uses linear extra space)
 *
 * Heapsort is not stable
 *
 */
public class HeapSort
{
    private static final Logger logger = LoggerFactory.getLogger (HeapSort.class);
    // < ---------------------------------------------------------------------------- >

    /**
     * This method will sort the array passed as input
     * @param a input array
     */
    public static void sort (Comparable[] a, int numEl) {
        // first pass: build max heap bottom up
        for (int k = numEl / 2 - 1; k >= 0; k--) {
            sink (a, k, numEl);
        }

        // second pass extract the max one at a time and leave it in the array instead of nulling out
        while (numEl > 0) {
            numEl--;
            exchange (a, 0, numEl);
            sink (a, 0, numEl);
        }
    }

    // #########################################################
    private static void sink (Comparable[] a, int k, int n) {
        while (2 * k + 1 < n) {
            int j = 2 * k + 1;
            if (j < n - 1 && a[j].compareTo (a[j + 1]) < 0) {
                j++;
            }

            if (a[k].compareTo (a[j]) > 0) {
                break;
            }

            exchange (a, k, j);
            k = j;
        }
    }

    private static void exchange (Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
