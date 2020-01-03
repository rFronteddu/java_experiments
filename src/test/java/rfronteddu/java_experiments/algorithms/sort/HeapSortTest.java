package rfronteddu.java_experiments.algorithms.sort;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeapSortTest
{
    private static final Logger logger = LoggerFactory.getLogger (HeapSortTest.class);

    public class TestNode implements Comparable<HeapSortTest.TestNode>
    {
        final private int rank;
        final private String nodeName;

        TestNode (int rank, String name) {
            this.rank = rank;
            this.nodeName = name;
        }

        @Override public int compareTo (@NotNull HeapSortTest.TestNode o) {
            return this.rank - o.rank;
        }

        @Override public String toString() {
            return rank + "";
        }
    }

    @Test public void outOfOrderInsert () {
        TestNode[] a = new TestNode[8];
        a[0] = new TestNode (8, "8");
        a[1] = new TestNode (7, "7");
        a[2] = new TestNode (4, "4");
        a[3] = new TestNode (5, "5");
        a[4] = new TestNode (6, "6");
        a[5] = new TestNode (3, "3");
        a[6] = new TestNode (2, "2");
        a[7] = new TestNode (1, "1");
        HeapSort.sort (a, 8);
        assert (isSorted (a, 8));
    }

    private static boolean isSorted (TestNode[] a, int n) {
        for (int i = 1; i < n; i++) {
            if (a[i].compareTo (a[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }
}
