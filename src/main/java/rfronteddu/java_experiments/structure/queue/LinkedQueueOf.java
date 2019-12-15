/*
 * @author  Roberto Fronteddu
 * @version 1.0
 * @since   2019-11-25
 *
 * Simple implementation of a generic linked list.
 * Takes constant time in worst case but uses extra time and space to deal with the links.
 * FIFO.
 */

package rfronteddu.java_experiments.structure.queue;

public class LinkedQueueOf <Item>
{
    private Node first;
    private Node last;
    // < ------------------------------------------------------------------------------- >

    public boolean isEmpty() {
        return first == null;
    }

    public void enqueue (Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }


    // ###################################################################################

    private class Node
    {
        Item item;
        Node next;
    }
}
