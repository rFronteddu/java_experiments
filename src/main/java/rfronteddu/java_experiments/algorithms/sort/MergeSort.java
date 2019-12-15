/*
 * @author  Roberto Fronteddu
 * @version 1.0
 * @since   2019-12-14
 *
 * Recursive and non recursive simple implementations of merge sort.
 *
 * Guarantees to sort any array of n items with at most n lg n compares. It can also be proven that
 * any compare-based sorting algorithm must make at least !n lg n compares in the worst case.
 *
 * Proposition: Merge sort uses at most N lg N compares and 6 N lg N array accesses to sort any array of size N.
 *
 * Prof:
 *                    left half       right half    merges
 * Compares: C(N) <= C[ceil(N/2)] + C(floor(N/2)) +   N     for N > 1, with C(1) = 0
 * Proof by expansion
 * -> D(N) = 2 * D(N/2) + N with D(1) = 0
 * -> D(N) / N = 2*D(N/2) / N + 1
 *             =   D(N/2) / (N/2) + 1
 *             = (2*D(N/4) + (N/2)) / (N/2) + 1
 *             = D(N/4)/(N/4) + 1 + 1
 *             = D(N/8)/(N/8) + 1 + 1 + 1
 *             ....
 *             = D(N/N)/(N/N) + 1 + ... + 1 oss D(N/N) = 0
 *             = lg N
 * -> D(N) = N lg N
 *
 * Note that Merge sort uses auxiliary space proportional to N, for the last merge it needs an array of size N
 *
 * Note that an Algorithm is called "in-place" if it uses <= c log N extra memory
 *
 * 1) Divide array into two halves
 * 2) Recursively sort each half
 * 3) Merge two halves
 *
 */

package rfronteddu.java_experiments.algorithms.sort;

public class MergeSort
{
    /**
     * Recursive merge sort, saves time by avoiding copying data to auxiliary array
     * and by checking for partially ordered sub arrays.
     * @param a array of len a.length to order
     */
    public static void sort (int[] a) {
        // time improvement -> remove copy to aux, saves time but not space
        int[] aux = new int[a.length];
        System.arraycopy (a, 0, aux, 0, a.length);
        sort (a, aux, 0, a.length - 1);
    }

    /**
     * Slower bottom up non recursive version of merge sort
     * @param a array of len a.length to order
     */
    public static void nonRecursiveSort (int[] a) {

    }

    // ##################################################################################

    private static boolean isSorted (int[] a, int lo, int hi) {
        assert (lo < hi);
        for (int i = lo; i < hi; i++) {
            if (a[i] > a[i++]) {
                return false;
            }
        }
        return true;
    }

    private static void merge (int[] array, int[] aux, int low, int mid, int high) {
        // array must be composed of two sorted sub arrays
        assert (isSorted (aux, low, mid));
        assert (isSorted (aux, mid + 1, high));

        // merge two sub array
        int lSubIt = low;
        int rSubIt = mid + 1;
        for (int i = low; i <= high; i++) {
            if (lSubIt > mid) { // no more elements in the left sub
                aux[i] = array[rSubIt++];
            } else if (rSubIt > high) { // no more elements in the right sub
                aux[i] = array[lSubIt++];
            } else { // general case in which I am iterating both sub arrays
                aux[i] = array[rSubIt] < array[lSubIt] ? array[rSubIt++] : array[lSubIt++];
            }
        }
        assert (isSorted (aux, low, high));
    }

    private static void sort (int[] array, int[] auxArray, int low, int high) {
        if (low >= high) {
            return;
        }

        // Merge sort is expensive for small sets, we could use insertion sort here if when the arrays
        // are under a certain threshold (~10 el...)
        // if (hi <= low + THRESHOLD - 1) {
        //      Insertion.sort (a, lo, hi)
        //      return;
        // }

        // time improvement -> remove copy to aux
        int mid = low + (high - low) / 2;
        sort  (auxArray, array, low, mid);
        sort  (auxArray, array, mid + 1, high);
        // improvement for partially ordered array -> check if biggest of first half is <= smallest second half
        assert (isSorted (auxArray, low, mid));
        assert (isSorted (auxArray, mid + 1, high));
        if (auxArray[mid] < auxArray[mid + 1]) {
            return;
        }
        merge (auxArray, array, low, mid, high);
    }
}
