package rfronteddu.java_experiments.algorithms.sort;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleSorts
{
    private static final Logger logger = LoggerFactory.getLogger (SimpleSorts.class);

    @Test public void selectionSort () {
        TestNode[] a = new TestNode[8];
        a[0] = new TestNode (8, "8");
        a[1] = new TestNode (7, "7");
        a[2] = new TestNode (4, "4");
        a[3] = new TestNode (5, "5");
        a[4] = new TestNode (6, "6");
        a[5] = new TestNode (3, "3");
        a[6] = new TestNode (2, "2");
        a[7] = new TestNode (1, "1");
        SelectionSort.sort (a, 8);
        assert (TestNode.isSorted (a, 8));
    }

    @Test public void insertionSort () {
        TestNode[] a = new TestNode[8];
        a[0] = new TestNode (8, "8");
        a[1] = new TestNode (7, "7");
        a[2] = new TestNode (4, "4");
        a[3] = new TestNode (5, "5");
        a[4] = new TestNode (6, "6");
        a[5] = new TestNode (3, "3");
        a[6] = new TestNode (2, "2");
        a[7] = new TestNode (1, "1");
        InsertionSort.sort (a, 8);
        assert (TestNode.isSorted (a, 8));
    }

    @Test public void shellSort () {
        TestNode[] a = new TestNode[8];
        a[0] = new TestNode (8, "8");
        a[1] = new TestNode (7, "7");
        a[2] = new TestNode (4, "4");
        a[3] = new TestNode (5, "5");
        a[4] = new TestNode (6, "6");
        a[5] = new TestNode (3, "3");
        a[6] = new TestNode (2, "2");
        a[7] = new TestNode (1, "1");
        ShellSort.sort (a, 8);
        assert (TestNode.isSorted (a, 8));
    }
}
