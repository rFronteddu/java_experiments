/*
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
    public static void enterNumberToTerminate() {
        TextIO textIO = TextIoFactory.getTextIO ();
        textIO.newByteInputReader().read ("Enter a number to terminate");
    }

    public static int getNextEl (String msg) {
        TextIO textIO = TextIoFactory.getTextIO();
        return textIO.newIntInputReader().read (msg);
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

    public static int getMaxEl() {
        TextIO textIO = TextIoFactory.getTextIO();
        return textIO.newIntInputReader().read ("Please input max number of elements");
    }

    public static Algorithm getUnionFindAlgorithm() {
        TextIO textIO = TextIoFactory.getTextIO ();
        return textIO.newEnumInputReader (Algorithm.class).read ("Select algorithm:");
    }

    public static boolean keepGoing() {
        TextIO textIO = TextIoFactory.getTextIO();
        return textIO.newBooleanInputReader().read ("Keep Going?");
    }

    public static Experiment selectExperiment() {
        TextIO textIO = TextIoFactory.getTextIO();
        return textIO.newEnumInputReader (Experiment.class).read ("  Select experiment");
    }

    public static void println (String msg) {
        TextIoFactory.getTextIO().getTextTerminal().println (msg);
    }

    public static boolean getImproved () {
        TextIO textIO = TextIoFactory.getTextIO();
        return textIO.newBooleanInputReader ().read ("  Do you want the improved version?");

    }
}
