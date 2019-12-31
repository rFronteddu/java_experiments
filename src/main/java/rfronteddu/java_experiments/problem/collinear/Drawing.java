package rfronteddu.java_experiments.problem.collinear;

import java.awt.*;
import java.util.HashSet;

public class Drawing extends Canvas
{
    private HashSet<Point> pointList = new HashSet<> ();
    private HashSet<Line> lineList = new HashSet<> ();
    // < --------------------------------------------------- >

    void drawPoint (int x, int y) {
        pointList.add (new Point (x, y));
    }

    void drawLine (int startX, int startY, int endX, int endY) {
        lineList.add (new Line (new Point (startX, startY), new Point (endX, endY)));
    }

    @Override public void paint (Graphics g) {
        for (Point p : pointList) {
            g.drawOval (p.x, p.y, 1, 1);
            g.drawString (p.toString (), p.x, p.y);
        }
        for (Line l : lineList) {
            g.drawLine (l.p.x, l.p.y, l.q.x, l.q.y);
        }
    }
}
