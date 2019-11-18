/*
 * @author  Roberto Fronteddu
 * @version 1.0
 * @since   2019-11-16
 *
 * Modify quick-union to avoid tall trees.
 * Keep track of size of each tree (number of objects).
 * Balance by linking root of smaller tree to root of lar
 * Ger tree.
 *
 * Adds an array to count number of objects in the tree rooted at i. The idea is that by keeping track
 * of the number of the size of each tree we can avoid adding the biggest tree to the smallest one.
 *
 * It is important to note that the depth of a tree is at most lg N
 *
 * Initialize:      N
 * Union:           lg N    base 2
 * AreConnected :   lg N    base 2
 * WC:             N + M lg N
 * M is the union-find operations on a set of N objects
 */

package rfronteddu.java_experiments.algorithms.unionfind;

public class WeightedQuickUnion extends QuickUnion
{
    // count number of objects in the tree rooted at i.
    private int[] size;
    private boolean fullyConnected = false;
    // < ------------------------------------------------------------------------------- >

    @Override public boolean init (int N) {
        super.init (N);
        size = new int[N];
        // Initialize array so we don't have to check every time for initialization
        for (int i = 0; i < array.length; i++) {
            size[i] = 1;
        }
        return true;
    }

    /**
     * Used in the social network 1 exercise
     * @return true if the nodes are all connected (in that case they all have the same root)
     */
    public boolean isFullyConnected() {
        return fullyConnected;
    }

    @Override public void union (int p, int q) {
        if (pRoot == qRoot) {
            return;
        }

        if (size[pRoot] < size[qRoot]) {
            array[pRoot]    = qRoot;
            size[qRoot]     += size[pRoot];
            fullyConnected = size[qRoot] == size.length;
        } else {
            array[qRoot]    = pRoot;
            size[pRoot]     += size[qRoot];
            fullyConnected = size[pRoot] == size.length;
        }
    }
}
