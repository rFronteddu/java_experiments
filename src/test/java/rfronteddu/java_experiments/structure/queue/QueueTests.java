/*
 * @author  Roberto Fronteddu
 * @version 1.0
 * @since   2019-11-27
 *
 * Simple tests to verify that the class LinkedQueueOf works correctly
 */


package rfronteddu.java_experiments.structure.queue;

import org.junit.Assert;
import org.junit.Test;

public class QueueTests
{
    @Test public void linkedQueueOfTest() {
        LinkedQueueOf<String> queue = new LinkedQueueOf<>();
        Assert.assertTrue (queue.isEmpty ());
        queue.enqueue ("first");
        queue.enqueue ("second");
        queue.enqueue ("third");
        Assert.assertEquals ("first", queue.dequeue ());
        Assert.assertFalse (queue.isEmpty ());
        Assert.assertEquals ("second", queue.dequeue ());
        Assert.assertEquals ("third", queue.dequeue ());
    }
}
