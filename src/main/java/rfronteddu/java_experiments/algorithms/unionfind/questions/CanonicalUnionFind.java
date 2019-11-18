/*
 * @author  Roberto Fronteddu
 * @version 1.0
 * @since   2019-11-17
 *
 * Union-find with specific canonical element. Add a method find() to the union-find data type so that find(i)
 * returns the largest element in the connected component containing i. The operations, union(), connected(),
 * and find() should all take logarithmic time or better.
 *
 * For example, if one of the connected components is {1, 2, 6, 9},
 * then the find() method should return 9 for each of the four elements in the connected components.
 *
 */

package rfronteddu.java_experiments.algorithms.unionfind.questions;

import rfronteddu.java_experiments.algorithms.unionfind.CompressedWeightedUnion;

import java.util.Arrays;

public class CanonicalUnionFind extends CompressedWeightedUnion
{
    private int[] max;
    // < ------------------------------------------------------------------------------- >

    /**
     * @param i element of the component we are looking for
     * @return largest element in the connected component containing i
     */
    public int find (int i) {
        int root = super.findRoot (i);
        return max[root];
    }

    @Override public boolean init (int N) {
        super.init (N);
        max = new int[N];
        // init to -1 so I don't have to check for initialization
        Arrays.fill (max, -1);
        return true;
    }

    @Override public void union (int p, int q) {
        int currentMax = Math.max (max[array[p]], max[array[q]]);
        int inputMax = Math.max (p, q);
        super.union (p, q);
        max[array[p]] = Math.max (inputMax, currentMax);
    }
}


