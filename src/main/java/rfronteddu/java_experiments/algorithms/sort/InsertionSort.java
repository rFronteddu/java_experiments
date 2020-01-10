package rfronteddu.java_experiments.algorithms.sort;

/**
 * At interation i, swap a[i] with each larger entry to its left
 *
 * Insertion sort uses ~1/4 N ^ 2 compares and exchanges on average
 *
 * Note that if array is in ascending order, insertion sort makes N - 1 compares and 0 exchanges. Conversely
 * if it is in reverse order it does quadratic compares ~ 1/2 N ^ 2.
 *
 * If an array is partially sorted (number of inversion is <= c * N), insertion sort runs in linear time.
 */
public class InsertionSort
{
    public static void sort (Comparable[] a, int N) {
        for (int i = 0;  i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j].compareTo (a[j - 1]) < 0) {
                    SortUtil.exchange (a, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

}
