package rfronteddu.java_experiments.problems.lpd;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * This implementation works by first ordering points
 * by slope and then checking if the adjacent points are collinear
 */
public class FastCollinearPoints implements CollinearPoints
{
    private LinkedList<Line> outputList = new LinkedList<> ();
    // < ---------------------------------------------------- >

    public FastCollinearPoints (Point[] points) {
        Arrays.sort (points);
        LinkedList<LinkedList<Point>> collinearSegments = new LinkedList<> ();
        for (int i = 0; i < points.length - 2; i++) {
            LinkedList<Point> currentCollinearCombination = new LinkedList<>();
            currentCollinearCombination.add (points[i]);
            currentCollinearCombination.add (points[i + 1]);
            double currentSlope = points[i].slopeTo (points[i + 1]);
            for (int offset = i + 2; offset < points.length - 1; offset++) {
                if (currentSlope == points[i].slopeTo (points[offset])) {
                    currentCollinearCombination.add (points[offset]);
                }
            }
            if (currentCollinearCombination.size () > 2) { // avoid lines of two points
                collinearSegments.add (currentCollinearCombination);
                i = i + currentCollinearCombination.size () - 1; // avoid repetitions
            }
        }

        for (LinkedList<Point> collPoints : collinearSegments) {
            Line l = Line.getBiggerLine (collPoints);
            outputList.add (l);
        }
    }

    @Override public LinkedList<Line> getLines() {
        return outputList;
    }
}
