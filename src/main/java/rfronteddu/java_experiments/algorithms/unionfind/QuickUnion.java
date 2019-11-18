/*
 * @author  Roberto Fronteddu
 * @version 1.0
 * @since   2019-11-16
 *
 * Quick Union is an algorithm that works by having elements that are connected having the same root connection ID.
 * First we initialize the array: N operations
 * Then, for each new pair we have 1 access to test equality of connection ID
 * Then, we check the root of each R
 * Finally, if they are connected we change the root of p into that of q
 *
 * In the worst case takes ~N access
 *
 * Initialize:      N
 * Union:           N
 * AreConnected :   N
 * WC:              MN
 * M is the union-find operations on a set of N objects
 */

package rfronteddu.java_experiments.algorithms.unionfind;

public class QuickUnion implements UnionFindInterface
{
    protected int[] array;
    // created these two variables to maintain interface compatibility, must call are connected before union!
    int pRoot;
    int qRoot;
    // < ------------------------------------------------------------------------------- >

    @Override public boolean areConnected (int p, int q) {
        pRoot = findRoot (p);
        qRoot = findRoot (q);
        return pRoot == qRoot;
    }

    @Override public boolean init (int N) {
        if (N <= 0) {
            return false;
        }
        array = new int[N];
        // Initialize array so we don't have to check every time for initialization
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        return true;
    }

    @Override public void union (int p, int q) {
        array[pRoot] = qRoot;
    }

    // ##################################################################################

    int findRoot (int i) {
        while (i != array[i]) {
            i = array[i];
        }
        return i;
    }
}
