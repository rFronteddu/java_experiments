package rfronteddu.java_experiments.algorithms.sort;

class SortUtil
{
    static void exchange (Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
