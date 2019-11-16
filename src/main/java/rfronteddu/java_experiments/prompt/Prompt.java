/**
 * @author  Roberto Fronteddu
 * @version 1.0
 * @since   2019-11-16
 *
 * Interact with the user
 */

package rfronteddu.java_experiments.prompt;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import rfronteddu.java_experiments.algorithms.unionfind.Algorithm;

public class Prompt
{
    public static Experiment selectExperiment() {
        TextIO textIO = TextIoFactory.getTextIO();
        return textIO.newEnumInputReader (Experiment.class).read ("  Select experiment");
    }

    public static int getNumberOfElements() {
        TextIO textIO = TextIoFactory.getTextIO();
        return textIO.newIntInputReader().read ("Enter number of elements");
    }

    public static int[] getPair() {
        int[] a = new int[2];
        TextIO textIO = TextIoFactory.getTextIO();
        a[0] = textIO.newIntInputReader().read ("Enter first pair member");
        a[1] = textIO.newIntInputReader().read ("Enter second pair member");
        return a;
    }

    public static boolean keepGoing() {
        TextIO textIO = TextIoFactory.getTextIO();
        return textIO.newBooleanInputReader().read ("Keep Going?");
    }

    public static Algorithm getUnionFindAlgorithm() {
        TextIO textIO = TextIoFactory.getTextIO ();
        return textIO.newEnumInputReader (Algorithm.class).read ("Select algorithm:");
    }
}
