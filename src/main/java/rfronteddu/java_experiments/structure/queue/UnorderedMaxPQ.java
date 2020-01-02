package rfronteddu.java_experiments.structure.queue;

/**
 * Stack: remove the item most recently added
 * Queue: Remove the item least recently added
 * Random queue: Remove a random item
 * Priority queue: Remove the largest (or smallest) item.
 *
 * Order of growth of finding the largest M in a stream of N items:
 *                       time            space
 * sort                 N lg N             N
 * elementary PQ          MN               M
 * binary heap          N lg M             M
 * best in theory         N                M
 */
public class UnorderedMaxPQ<Key extends Comparable<Key>>
{
    private Key[] pq;
    private int numberOfElements;
    // < ----------------------------------------------- >

    public UnorderedMaxPQ (int capacity) {
        // no generic array creation
        pq = (Key[]) new Comparable[capacity];
    }

    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    public void insert (Key x) {
        pq[numberOfElements++] = x;
    }

    public Key delMax() {
        int max = 0;
        for (int i = 1; i < numberOfElements; i++) {
            if (pq[max].compareTo (pq[i]) < 0) {
               max = i;
            }
        }
        Key tmp = pq[max];
        pq[max] = pq[numberOfElements - 1];
        pq[numberOfElements - 1] = tmp;

        Key retKey = pq[--numberOfElements];
        pq[numberOfElements] = null; // null out entry to prevent loitering
        return retKey;
    }
}
