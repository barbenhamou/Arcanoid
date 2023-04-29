package AbstractShapes;
import java.util.List;

/**
 * A representation of a AbstractShapes.Line in R2.
 */
public class Line {
    private Point start;
    private Point end;

    static final int COLLINEAR = 0, RIGHT = 1, LEFT = 2;

    /**
     * Constructor with points.
     *
     * @param start
     * @param end
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor with values.
     *
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Getter for the start point.
     *
     * @return the start point of the line
     */
    public Point getStart() {
        return this.start;
    }

    /**
     * Getter for the end point.
     *
     * @return the middle point of the line
     */
    public Point getEnd() {
        return this.end;
    }

    /**
     * Getter for the middle point.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double newX = (start.getX() + end.getX()) / 2;
        double newY = (start.getY() + end.getY()) / 2;
        return new Point(newX, newY);
    }

    /**
     * Name: length.<br>
     *
     * @return length of current line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Name: equals.<br>
     *
     * @param other - The other line for check.
     * @return whether the current and the other lines are the same.
     */
    public boolean equals(Line other) {
        return start.equals(other.start) && end.equals(other.end)
                || start.equals(other.end) && end.equals(other.start);
    }

    /**
     * Name: isOnLine.<br>
     *
     * @param p - The point we'd like to examine.
     * @return whether {@param p} is on the current line.
     */
    public boolean isOnLine(Point p) {
        return p.getX() <= Math.max(start.getX(), end.getX()) && p.getX()
                >= Math.min(start.getX(), end.getX())
                && p.getY() <= Math.max(start.getY(), end.getY()) && p.getY()
                >= Math.min(start.getY(), end.getY());
    }

    /**
     * Name: relativity.<br>
     * Calculation: <br>
     * <p>
     * Calculate the slopes between the edges of the current line and the
     * {@param p}. After common denominator
     * </p>
     *
     * @param p - The point we'd like to examine.
     * @return the sort of relativity exist, 0 - collinear, 1 - clockwise,
     * 2 - opposite of clockwise
     */
    public int relativity(Point p) {
        double slopeWithStart =
                (p.getY() - start.getY()) * (p.getX() - end.getX());
        double slopeWithEnd =
                (p.getY() - end.getY()) * (p.getX() - start.getX());

        if (slopeWithStart - slopeWithEnd == 0) {
            return COLLINEAR;
        }

        return slopeWithStart - slopeWithEnd > 0 ? RIGHT : LEFT;
    }


    /**
     * Name: isIntersecting.<br>
     * Action:<br>
     * <p>
     * From the formula, calculate the denominator of the fracture that
     * represents the s and y of the intersection.
     * Present the problem as a system of 3 points each time.
     * Check the relativity of each edge in comparison to
     * other two points, which represent one of the lines. For example,
     * the point other.start relative to the
     * current line.
     * </p>
     *
     * @param other - The line we'd like to examine.
     * @return whether the current line and {@param other} intersects.
     */
    public boolean isIntersecting(Line other) {
        if (start.equals(end)) {
            return other.isOnLine(start);
        }
        if (other.start.equals(other.end)) {
            return isOnLine(other.end);
        }

        double denominator = (start.getX() - end.getX())
                * (other.start.getY() - other.end.getY())
                - (start.getY() - end.getY())
                * (other.start.getX() - other.end.getX());

        if (denominator == 0) {
            if (other.isOnLine(start) || other.isOnLine(end)) {
                return true;
            }
            return isOnLine(other.start) || isOnLine(other.end);
        }

        int relativity1 = relativity(other.start);
        int relativity2 = relativity(other.end);
        int relativity3 = other.relativity(start);
        int relativity4 = other.relativity(end);

        return relativity1 != relativity2 && relativity3 != relativity4;
    }

    /**
     * Name: intersectionWith.<br>
     * Action:<br>
     * <p>
     * Using the formula to calculate the intersection point.
     * See: <a href="https://en.wikipedia.org/wiki/Line%E2%80%93
     * line_intersection">...</a>
     * </p>
     *
     * @param other - The line we'd like to examine.
     * @return The point of intersection.
     */
    public Point intersectionWith(Line other) {
        if (!isIntersecting(other)) {
            return null;
        }
        //Checking whether one of the lines is a point
        if (start.equals(end)) {
            return start;
        }
        if (other.start.equals(other.end)) {
            return other.start;
        }

        //Checking whether the intersection is one of the edges
        if (other.start.equals(end)) {
            return other.start;
        }

        if (start.equals(other.end)) {
            return start;
        }

        double denominator = (start.getX() - end.getX())
                * (other.start.getY() - other.end.getY())
                - (start.getY() - end.getY())
                * (other.start.getX() - other.end.getX());

        double numeratorX =
                (start.getX() * end.getY() - start.getY() * end.getX())
                        * (other.start.getX() - other.end.getX())
                        - (start.getX() - end.getX())
                        * (other.start.getX() * other.end.getY()
                        - other.start.getY() * other.end.getX());

        double numeratorY =
                (start.getX() * end.getY() - start.getY() * end.getX())
                        * (other.start.getY() - other.end.getY())
                        - (start.getY() - end.getY())
                        * (other.start.getX() * other.end.getY()
                        - other.start.getY() * other.end.getX());

        return new Point(numeratorX / denominator, numeratorY / denominator);
    }

    /**
     * name: closestIntersectionPoint.<br>
     *
     * @param rect the rectangle that is being examined.
     * @return the intersection point that is the closest to the start.
     * */
    public Point closestIntersectionPoint(Rectangle rect) {
        List<Point> intersections = rect.intersectionPoints(this);
        double minDistance = rect.getUpperLeft().distance(rect.getLowerRight());
        Point minDistanceP = null;
        for(Point p: intersections) {
            if(p.distance(start) <= minDistance) {
                minDistanceP = p;
                minDistance = p.distance(start);
            }
        }
        return minDistanceP;
    }
}