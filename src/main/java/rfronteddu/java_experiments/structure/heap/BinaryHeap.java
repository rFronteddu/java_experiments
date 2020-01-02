package rfronteddu.java_experiments.structure.heap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Binary tree: Empty or node with links to left and right binary trees.
 * Complete tree: Perfectly balanced tree, except for bottom level
 *
 * Height of complete tree with N nodes is the floor of lg N, in fact, the height only
 * increases when N is a power of 2;
 *
 * The binary heap is an array representation of a heap-ordered complete binary tree.
 *
 * A Heap ordered binary tree has paren'ts key no smaller than children's keys. The root index
 * is 1.
 *
 * Note that the largest key is a[1] which is the root of the binary tree.
 *
 * Also note that it is possible to use array indices to move through the array, in fact,
 * a parent node at k is at k/2, children node at k are 2k and 2k + 1
 *
 * Heapify: If a child's key becomes larger than it's parent's key, we need to heapify ->
 * exchange key in child with key in parent until heap order is restored.
 *
 *
 * Heap sort: Create an heap with all N keys, then repeatedly remove the maximum key.
 *
 * Heap sort is an in-place sorting algorithm with N log N worst-case. It is optimal for time and space
 * but makes poor use of cache memory and it is not stable.
 */
public class BinaryHeap<T extends Comparable<T>>
{
    private T[] array;
    private int elementsNum;
    private static final Logger logger = LoggerFactory.getLogger (BinaryHeap.class);

    // < --------------------------------------- >
    public BinaryHeap (int capacity) {
        array = (T[]) new Comparable[capacity];
    }

    /**
     * at most 1 + lg N compares
     * @param x new element to add
     */
    public void insert (T x) {
        // for simplicity size is fixed
        // place element at the bottom
        int index = elementsNum;
        array[index] = x;
        heapify (index);
        elementsNum++;
    }

    public boolean isEmpty () {
        return elementsNum == 0;
    }

    /**
     * Exchange root with node at end, then sink it down.
     * At most 2 lg N compares
     * @return max element or null if no element was present
     */
    public T deleteMax() {
        logger.trace ("Element number: " + elementsNum);
        if (elementsNum == 0) {
            return null;
        }
        T max = array[0];
        elementsNum--;
        logger.trace ("Exchanging: " + array[elementsNum]);
        exchange (0, elementsNum);
        demote (0);
        array[elementsNum] = null; // prevent loitering
        return max;
    }

    public int elements () {
        return elementsNum;
    }

    public void print() {
        logger.info ("Elements: ");
        for (int i = 0; i < elementsNum; i++) {
            logger.info (array[i].toString ());
        }
    }

    // #############################################

    /**
     * If a key becomes smaller, we have to do the inverse of heapify
     * @param k key to demote
     */
    private void demote (int k) {
        // left child 2k + 1
        // r    child 2k + 2
        logger.trace ("L child: " + array[2 * k + 1]);
        logger.trace ("R child: " + array[2 * k + 2]);

        while (2 * k + 1 < elementsNum) {
            int j = 2 * k + 1;
            // j < elementsNum - 1 check if we only have the root and his children left
            if (j < elementsNum - 1 && array[j].compareTo (array[j + 1]) < 0) {
                // I want the larger children key
                j++;
            }

            if (array[k].compareTo (array[j]) > 0) {
                break;
            }

            exchange (k, j);
            k = j;
        }
    }

    private void exchange (int a, int b) {
        T tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    /**
     * This verifies that the keys verify the heap property. If the children
     * is detected bigger than the father, the children is moved up
     * @param k key under exam
     */
    private void heapify (int k) {
        // (k - 1) / 2 is the parent index
        // k > 0 == has parent
        while (k > 0 && array[(k - 1) / 2].compareTo (array[k]) < 0) {
            // parent must be bigger than children -> swap them
            exchange (k, (k - 1) / 2);
            k = (k - 1) / 2;
        }
    }
}
