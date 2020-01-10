package rfronteddu.java_experiments.algorithms.sort;

import org.jetbrains.annotations.NotNull;

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

    static boolean isSorted (Comparable[] a, int n) {
        for (int i = 1; i < n; i++) {
            if (a[i].compareTo (a[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }
}