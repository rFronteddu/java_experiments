package rfronteddu.java_experiments.algorithms.unionfind;

public interface UnionFindInterface
{
    /**
     * @param N Number of element
     * @return true if init OK
     */
    boolean init (int N);

    /**
     * Connect two objects
     * @param p object uuid
     * @param q object uuid
     */
    void union (int p, int q);

    /**
     * @param p
     * @param q
     * @return true if there is a path connecting p and q
     */
    boolean areConnected (int p, int q);
}
