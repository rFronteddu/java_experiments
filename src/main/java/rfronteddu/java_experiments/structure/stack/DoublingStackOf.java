/*
 * @author  Roberto Fronteddu
 * @version 1.0
 * @since   2019-11-25
 *
 * The resize (increase) is implemented by doubling the max size each time,
 * in this way adding the first N elements at each resize costs:
 * N + (2 + 4 + 8 + ... + N/2 + N) ~ 3N instead of N * N (each resize, copy each element)
 *
 * Resize (decrease) we cannot use the same method
 * because in the worst case if we pop push pop just after increasing the size it takes N each time
 * so we resize when array is one quarter full
 *
 * Best and average are O(1) while worst cases are O(N) for both push and pop.
 */

package rfronteddu.java_experiments.structure.stack;

import java.lang.reflect.Array;

public class DoublingStackOf <Item> implements Stack<Item>
{
    private Object[] stack;
    private int index;
    // < ------------------------------------------------------------------------------- >

    public DoublingStackOf (int initialCapacity) {
        // warning is there because operation will happen at runtime
        stack = new Object[initialCapacity];
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
        if (index > 0 && index == stack.length / 4) {
            resize (stack.length);
        }
        return item;
    }

    @Override public void push (Item item) {
        if (index == stack.length) {
            resize (2 * stack.length);
        }
        stack[index++] = item;
    }

    // ###################################################################################
    private void resize (int newCapacity) {
        // warning is there because operation will happen at runtime
        Object[] copy = new Object[newCapacity];
        if (index >= 0) {
            System.arraycopy (stack, 0, copy, 0, index);
        }
        stack = copy;
    }
}
