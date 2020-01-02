package rfronteddu.java_experiments.structure.heap;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeapTest
{
    private static final Logger logger = LoggerFactory.getLogger (HeapTest.class);

    public class TestNode implements Comparable<TestNode>
    {
        final private int rank;
        final private String nodeName;

        TestNode (int rank, String name) {
            this.rank = rank;
            this.nodeName = name;
        }

        @Override public int compareTo (@NotNull TestNode o) {
            return this.rank - o.rank;
        }

        @Override public String toString() {
            return rank + "";
        }
    }

    @Test public void outOfOrderInsert () {
        logger.info ("outOfOrderInsert");
        BinaryHeap<TestNode> heap = new BinaryHeap<> (1000);
        heap.insert (new TestNode (5, "5"));
        heap.insert (new TestNode (4, "4"));
        heap.insert (new TestNode (3, "3"));
        heap.insert (new TestNode (6, "6"));
        TestNode last = null;
        while (!heap.isEmpty ()) {
            if (last == null) {
                last = heap.deleteMax ();
            } else {
                TestNode current = heap.deleteMax ();
                assert (last.rank > current.rank);
                last = current;
            }
        }
    }
}
