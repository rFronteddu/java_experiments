package rfronteddu.java_experiments.problem.collinear;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rfronteddu.java_experiments.algorithms.combinatorial.CombinationUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Examines 4 points at a time and checks whether they all lie on the same line segment,
 * returning all such line segments.
 */
public class BruteCollinearPoints implements CollinearPoints
{
    private static final Logger logger = LoggerFactory.getLogger (BruteCollinearPoints.class);
    private LinkedList<Line> outputList = new LinkedList<> ();
    // < ------------------------------------------------------------------------------------------------------------->

    public BruteCollinearPoints (Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException ("points was null");
        }

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException ("points at index " + i + " was null");
            }
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                if (points[j] == null) {
                    throw new IllegalArgumentException ("points at index " + j + " was null");
                }

                if (points[i].equals (points[j])) {
                    throw new IllegalArgumentException ("point " + i + " and point " + j + " are the same");
                }
            }
        }

        long expectedCombinations = getFactorial (points.length) / (getFactorial (4)  * getFactorial (points.length - 4));
        LinkedList<LinkedList<Point>> combinations = CombinationUtil.getCombinations (points, 4);
        logger.trace ("Found {} combinations, expecting {}", combinations.size (), expectedCombinations);

        for (LinkedList<Point> combination : combinations) {
            if (isCollinear (combination)) {
                Line l = Line.getBiggerLine (combination);
                outputList.add (l);
            }
        }
    }

    @Override public LinkedList<Line> getLines() {
        return outputList;
    }

    // ##################################################################

    private long getFactorial (int number) {
        long result = 1;
        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }
        return result;
    }

    /**
     * 4 points are collinear if the slopes between them are all the same.
     * p, q, r, s are collinear if slope (p, q) == slope (p, r) == slope (p, s)
     *
     * @param combination input array of 4 points.
     * @return true if the 4 points are collinear
     */
    private boolean isCollinear (List<Point> combination) {
        double slope01 = combination.get (0).slopeTo (combination.get (1));
        double slope02 = combination.get (0).slopeTo (combination.get (2));
        if (slope01 != slope02) {
            return false;
        }
        double slope03 = combination.get (0).slopeTo (combination.get (3));
        if (slope02 == slope03) {
            logger.trace ("Found new collinear combination: {} with slope {}", combination.toString (), slope01);
            return true;
        }
        return false;
    }
}
