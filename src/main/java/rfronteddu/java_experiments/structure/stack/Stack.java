/*
 * @author  Roberto Fronteddu
 * @version 1.0
 * @since   2019-11-25
 *
 */

package rfronteddu.java_experiments.structure.stack;

public interface Stack <Item>
{
    boolean isEmpty();
    Item pop();
    void push (Item item);
}
