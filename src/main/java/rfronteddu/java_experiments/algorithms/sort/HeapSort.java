package rfronteddu.java_experiments.algorithms.sort;

public class HeapSort
{
    public static void sort (Comparable[] a) {
        int numEl = a.length;
        for (int k = numEl / 2; k >= 1; k--) {
            sink (a, k, numEl);
        }
        while (numEl > 1) {
            exchange (a, 1, numEl);
            sink (a, 1, --numEl);
        }
    }

    // #########################################################
    private static void sink (Comparable[] a, int k, int j) {
        while (k > 1 && a[k/2].compareTo (a[k]) < 0) {
            exchange (a, k, k / 2);
            k = k / 2;
        }
    }

    private static void exchange (Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
