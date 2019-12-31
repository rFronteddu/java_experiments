package rfronteddu.java_experiments.algorithms.combinatorial;

import org.slf4j.LoggerFactory;
import rfronteddu.java_experiments.problem.collinear.Point;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * This class will implement some combinatorial utilities
 */
public class CombinationUtil
{
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger (CombinationUtil.class);
    // < ------------------------------------------------- >

    /**
     * Note that this method does not check for duplicates in the input array
     * @param points input array
     * @param combinationSize size of the output combinations
     * @return a list of combinations of combinationSize elements
     */
    public static LinkedList<LinkedList<Point>> getCombinations (Point[] points, int combinationSize) {
        Point[] currentComb = new Point[combinationSize];
        LinkedList<LinkedList<Point>> output = new LinkedList<> ();
        recursiveComb (points, currentComb, output, 0, points.length - 1, 0 , combinationSize);
        return output;
    }

    /**
     * @param points input array
     * @param currentComb current comb
     * @param output output array
     * @param start ending index in points
     * @param end ending of combination
     * @param index index of the value being considered
     * @param r size of combination
     */
    private static void recursiveComb (Point[] points, Point[] currentComb,  LinkedList<LinkedList<Point>> output, int start, int end, int index, int r) {
        if (index == r) {
            LinkedList<Point> combList = new LinkedList<> (Arrays.asList (currentComb).subList (0, r));
            output.add (combList);
            logger.trace ("Got a new combination: " + Arrays.toString (currentComb));
            return;
        }
        // i must also be smaller than the last index I changed to avoid duplications
        for (int i = start; i <= end && i <= end - (r - index - 1); i++) {
            currentComb[index] = points[i];
            recursiveComb (points, currentComb, output, i + 1, end, index + 1, r);
        }
    }
}
