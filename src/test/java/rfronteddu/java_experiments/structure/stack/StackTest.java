/*
 * @author  Roberto Fronteddu
 * @version 1.0
 * @since   2019-11-27
 *
 * Simple tests to verify that the classes DoublingStackOf and FixedCapacityStackOff work correctly
 */


package rfronteddu.java_experiments.structure.stack;

import org.junit.Assert;
import org.junit.Test;

public class StackTest
{
    @Test public void DoublingStackOfTest() {
        Stack<String> stack = new DoublingStackOf<> (10);
        stackOperations (stack);
    }

    @Test public void FixedCapStackOfTest() {
        Stack<String> stack = new FixedCapacityStackOfStrings<> (10);
        stackOperations (stack);
    }

    private void stackOperations (Stack<String> stack) {
        Assert.assertTrue (stack.isEmpty ());
        stack.push ("hi");
        Assert.assertFalse (stack.isEmpty ());
        Assert.assertEquals ("hi", stack.pop ());
        Assert.assertTrue (stack.isEmpty ());
        stack.push ("this");
        stack.push ("is");
        stack.push ("weird");
        Assert.assertEquals ("weird", stack.pop ());
        Assert.assertEquals ("is", stack.pop ());
        Assert.assertEquals ("this", stack.pop ());
        Assert.assertTrue (stack.isEmpty ());
    }
}

