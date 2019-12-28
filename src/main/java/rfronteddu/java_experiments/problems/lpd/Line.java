package rfronteddu.java_experiments.problems.lpd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

class Line
{
    final Point p, q;
    private static final Logger logger = LoggerFactory.getLogger (Line.class);
    // < ------------------------------------------------------------------------------------- >

    /**
     * Examines an array of collinear points and extract the longest segment
     * @param combination
     * @return
     */
    public static Line getBiggerLine (LinkedList<Point> combination) {
        Point smaller = combination.getFirst ();
        Point bigger  = combination.getFirst ();
        for (Point point : combination) {
            if (point.compareTo (smaller) < 0) {
                smaller = point;
            }
            if (point.compareTo (bigger) > 0) {
                bigger = point;
            }
        }
        logger.trace ("Got a line: " + smaller.toString (), bigger.toString ());
        return new Line (smaller, bigger);
    }

    Line (Point p, Point q) {
        this.p = p;
        this.q = q;
    }
}
