/*
 * @author  Roberto Fronteddu
 * @version 1.0
 * @since   2019-11-16
 *
 * Given a set of N objects
 * Assumption: "is connected to" is an equivalence relation:
 *   Reflexive: p is connected to p
 *   Symmetric: if p is connected to q, then q is connected to p
 *   Transitive: if p is connected to q and q is connected to r, then p is connected to r.
 *
 * Assumption: N (number of objects) can be huge, M (number of operations) can be huge, find and union commands may be intermixed
 *
 * Component: List of nodes connected to each other
 *
 * Useful in Dynamic connectivity or percolate calculation.
 */

package rfronteddu.java_experiments.algorithms.unionfind;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnionFind
{
    private static final Logger logger = LoggerFactory.getLogger (UnionFind.class);
    private int numberOfElements;
    private UnionFindInterface unionFind;
    // < ------------------------------------------------------------------------------- >

    /**
     * Initialize union-find data structure
     * @param numberOfElements number of objects that will be contained
     * @param algorithm select the algorithm that will be used for the UnionFind
     */
    public UnionFind (Algorithm algorithm, int numberOfElements) {
        switch (algorithm) {
            case QUICK_FIND:
                unionFind = new QuickFind();
                break;
            case QUICK_UNION:
                unionFind = new QuickUnion();
                break;
            case WEIGHTED_QUICK_UNION:
                unionFind = new WeightedQuickUnion();
                break;
            case WQU_PATH_COMPRESSED:
                unionFind = new CompressedWeightedUnion();
                break;
        }
        this.numberOfElements = numberOfElements;
        boolean ok = unionFind.init (numberOfElements);
        if (!ok) {
            logger.error ("Critical error while initializing UnionFind, number of elements: {}, algorithm: {} ",
                    numberOfElements,
                    algorithm);
            System.exit (-1);
        }
    }

    /**
     * Are p and q in the same component?
     * @param p element
     * @param q element
     * @return true if p and q are connected
     */
    private boolean areConnected (int p, int q) {
        if (p == q) {
            return true;
        }
        return unionFind.areConnected (p, q);
    }

    /**
     * if p and q are not yet connected, connect them and print out pair
     * @param p first element
     * @param q second element
     * @return true if a new connection was added
     */
    public boolean unionFind (int p, int q) {
        if (p >= numberOfElements) {
            logger.error ("p >= N.");
            return false;
        }

        if (q >= numberOfElements) {
            logger.error ("q was >= N.");
            return false;
        }

        if (!areConnected (p, q)) {
            unionFind.union (p, q);
            return true;
        }
        return false;
    }
}
