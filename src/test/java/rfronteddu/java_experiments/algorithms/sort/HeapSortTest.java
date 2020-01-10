package rfronteddu.java_experiments.algorithms.sort;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static rfronteddu.java_experiments.algorithms.sort.TestNode.isSorted;

public class HeapSortTest
{
    private static final Logger logger = LoggerFactory.getLogger (HeapSortTest.class);

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
}
