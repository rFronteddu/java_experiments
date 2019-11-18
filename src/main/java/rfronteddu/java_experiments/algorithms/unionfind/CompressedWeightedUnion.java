/*
 * @author  Roberto Fronteddu
 * @version 1.0
 * @since   2019-11-16
 *
 * Modify Weighted quick union to set the id of each node to point to that root
 *
 * Initialize:      N
 * Union:           lg N    base 2
 * AreConnected :   lg N    base 2
 * WC:              N + M lg* N (iterative logarithm)
 * M is the union-find operations on a set of N objects
 */

package rfronteddu.java_experiments.algorithms.unionfind;

public class CompressedWeightedUnion extends  WeightedQuickUnion
{
    public @Override int findRoot (int i) {
        while (i != array[i]) {
            // Make every other node in path point to its
            // grandparent (thereby halving path length).
            array[i] = array[array[i]];
            i = array[i];
        }
        return i;
    }
}
