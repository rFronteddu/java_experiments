/*
 * @author  Roberto Fronteddu
 * @version 1.0
 * @since   2019-12-22
 *
 * This class extract from a list of collinear points the biggest segments.
 */

package rfronteddu.java_experiments.problems.lpd;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class LinePatternsDetector
{
    private JFrame frame = new JFrame ("Debug window");
    private Drawing drawing = new Drawing ();
    // < -------------------------------------------- >

    public boolean init (int canvasWidth, int canvasHeight) {
        drawing.setSize (canvasWidth, canvasHeight);
        frame.add (drawing);
        frame.pack();
        frame.setVisible (true);
        return true;
    }

    public void collinearDetector (List<Point> points, boolean bruteForce) {
        Point[] pointsArray = new Point[points.size ()];
        for (int i = 0; i < points.size (); i++) {
            pointsArray[i] = points.get (i);
        }
        CollinearPoints collinearPoints = bruteForce ? new BruteCollinearPoints (pointsArray) : new FastCollinearPoints (pointsArray);
        LinkedList<Line> segments = collinearPoints.getLines ();
        for (Line l : segments) {
            addLine  (l.p.x, l.p.y, l.q.x, l.q.y);
            addPoint (l.p.x, l.p.y);
            addPoint (l.q.x, l.q.y);
        }
    }

    // #################################################################
    private void addPoint (int x, int y) {
        drawing.drawPoint (x, y);
        drawing.repaint ();
    }

    private void addLine (int startX, int startY, int endX, int endY) {
        drawing.drawLine (startX, startY, endX, endY);
        drawing.repaint ();
    }
}
