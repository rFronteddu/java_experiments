/*
 * @author  Roberto Fronteddu
 * @version 1.0
 * @since   2019-11-16
 *
 * Quick find is an eager algorithm that works by having elements that are connected
 * having the same connection ID.
 * First we initialize the array: N operations
 * Then, for each new pair we have 1 access to test equality of connection ID
 * Finally, if they are connected we need to access the array 2N + 2 times to change all
 * connection ID from p to q.
 * This takes N^2 array accesses
 *
 * Initialize:      N
 * Union:           N
 * AreConnected :   1
 * WC:              MN
 * M is the union-find operations on a set of N objects
 *
 */

package rfronteddu.java_experiments.algorithms.unionfind;

public class QuickFind implements  UnionFindInterface
{
    private int[] array;
    // < ------------------------------------------------------------------------------- >
    @Override public boolean areConnected (int p, int q) {
        return array[p] == array[q];
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
        // change all connection id corresponding to p into q's connection id
        // at most 2N + 2 array accesses
        int pConnectionUUID = array[p];
        int qConnectionUUID = array[q];
        for (int i = 0; i < array.length; i++) {
            if (array[i] == pConnectionUUID) {
                array[i] = qConnectionUUID;
            }
        }
    }

}
