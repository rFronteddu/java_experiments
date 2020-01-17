package rfronteddu.java_experiments.structure.tree;

import org.jetbrains.annotations.NotNull;

public final class TestKey implements Comparable<TestKey>
{
    // finals ensure that equals cannot be used with inheritance (it would otherwise violate symmetry)
    // Equals requirements are the following:
    //  reflexive x.equals(x) is true
    //  symmetric x.equals(y) <=> y.equals(x)
    //  transitive x.equals(y) and y.equals(z) <=> x.equals (z)
    // non-null x.equals(null) is false

    private int someFiled;

    @Override public int compareTo (@NotNull TestKey o) {
        if (this.equals (o)) {
            return 0;
        }
        // +1 and -1 logic
        return this.someFiled > o.someFiled ? 1 : -1;
    }

    @Override public boolean equals (Object o) {
        if (o == this) return true;

        if (o == null) return false;

        // instanceof operator returns true, even if compared with subclass, for example,
        // Subclass instanceof Superclass is true, but with getClass() its false.
        // By using getClass() you ensure that your equals() implementation doesn't return
        // true if compared with subclass object.
        if (o.getClass () != this.getClass ()) {
            return false;
        }
        // class specific equality checks
        TestKey oKey = (TestKey) o;
        return oKey.someFiled == this.someFiled;
    }
}
