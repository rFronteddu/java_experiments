/*
 * @author  Roberto Fronteddu
 * @version 1.0
 * @since   2019-12-22
 *
 * Immutable data type Point that represents a point in the plane
 *
 */

package rfronteddu.java_experiments.problems.lpd;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class Point implements Comparable<Point>
{
    final int x;
    final int y;

    // < ------------------------------------------------- >

    /**
     * Initializes a new point.
     * @throw assert exception if x,y are not between [0 ,32767]
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point (int x, int y) {
        assert (x >= 0 && x <= 32767);
        assert (y >= 0 && y <= 32767);
        this.x = x;
        this.y = y;
    }

    /**
     * Compare this and that points by y-coordinates, breaking ties by x-coordinates
     * @param that compared point
     * @return -1 if this is < than that
     *          0       assert ERROR
     *          1            >
     */
    @Override public int compareTo (@NotNull Point that) {
        if (this.y < that.y) {
            return -1;
        }
        if (this.y > that.y) {
            return 1;
        }
        assert this.x != that.x;
        return this.x < that.x ? -1 : 1;
    }

    @Override public boolean equals (Object that) {
        if (that == this) {
            return true;
        }
        if (!(that instanceof Point)) {
            return false;
        }
        Point p = (Point)that;
        return p.y == this.y && p.x == this.x;
    }

    /**
     * @return a comparator that compares its two argument points by the slopes they make
     * with the invoking point. point1 is less than point2 if the slope that point1 makes with this
     * is smaller than the one this makes with point2
     */
    public Comparator<Point> slopeOrder () {
        return (point1, point2) -> {
            double slope1 = slopeTo (point1);
            double slope2 = slopeTo (point2);
            if(slope1 == slope2) {
                return 0;
            }
            return slope1 < slope1 ? -1 : 1;
        };
    }

    /**
     * Compute the slope between this point and that point;
     * @param that point that makes a line with this point
     * @return
     *          vertical line segment: positive infinity;
     *          degenerate           : negative infinity.
     *          horizontal           : 0
     */
    public double slopeTo (Point that) {
        // degenerate
        if (this.equals (that)) {
            return -Double.MAX_VALUE;
        }

        if (that.x == this.x) {
            // vertical
            return Double.MAX_VALUE;
        }

        if (that.y == this.y) {
            // horizontal
            return 0;
        }
        return (double)(that.y - this.y) / (double)(that.x - this.x);
    }

    @Override public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
