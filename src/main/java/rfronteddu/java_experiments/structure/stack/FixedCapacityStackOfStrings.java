/*
 * @author  Roberto Fronteddu
 * @version 1.0
 * @since   2019-11-25
 *
 * A stack is a FIFO structure, I used postfix operator (x++) to get the value and then increment
 * and prefix operator (--x) to decrement and get. The null is used to avoid loitering.
 */

package rfronteddu.java_experiments.structure.stack;

public class FixedCapacityStackOfStrings<Item> implements Stack<Item>
{
    private Object[] stack;
    private int index;
    // < ------------------------------------------------------------------------------- >

    public FixedCapacityStackOfStrings (int capacity) {
        stack = new Object[capacity];
    }

    @Override public boolean isEmpty() {
        return index == 0;
    }

    @Override public Item pop() {
        if (index == 0) {
            return null;
        }
        Item item = (Item)stack[--index];
        stack[index] = null;
        return item;
    }

    @Override public void push (Item item) {
        if (index == stack.length) {
            return;
        }
        stack[index++] = item;
    }
}
