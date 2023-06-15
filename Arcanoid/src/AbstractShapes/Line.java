package AbstractShapes;

import java.util.List;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * A representation of a AbstractShapes.Line in R2.
 */
public class Line {
    private Point start;
    private Point end;

    static final double INF = Double.POSITIVE_INFINITY;

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
     * @return the slope, if it doesn't exist then infinity.
     */
    public double getSlope() {
        return end.getX() == start.getX() ? INF
                : (end.getY() - start.getY()) / (end.getX() - start.getX());
    }

    /**
     * @return y intersection without taking into consideration the
     * limits of the line.
     */
    public double getYIntersection() {
        double slope = getSlope();
        if (slope == INF) {
            return INF;
        }
        return start.getY() - slope * start.getX();
    }

    /**
     * @param p point to check.<br>
     * @return whether the given p is withing the line range.
     */
    public boolean betweenEdges(Point p) {
        double maxX = Math.max(start.getX(), end.getX());
        double maxY = Math.max(start.getY(), end.getY());
        double minX = Math.min(start.getX(), end.getX());
        double minY = Math.min(start.getY(), end.getY());
        return p.getX() <= maxX && p.getX() >= minX && p.getY() <= maxY && p.getY() >= minY;
    }

    /**
     * Assigning the given x into the line equation.<br>
     *
     * @param x x value
     * @return the outcome of the calculation.
     */
    public double assign(double x) {
        return x * getSlope() + getYIntersection();
    }

    /**
     * @param other the other line to check with.<br>
     * @return whether the two lines intersect with each other.
     */
    public boolean isIntersecting(Line other) {
        return intersectionWith(other) != null;
    }

    /**
     * @param other line to get intersection point with.<br>
     * @return the intersection point with the given other line.
     */
    public Point intersectionWith(Line other) {
        double intersectionX, intersectionY;
        if (getSlope() == other.getSlope()) {
            // if the lines coincide, try to find the collision point between them
            if (assign(other.start.getX()) == other.start.getY()) {
                if (end.getX() < other.start.getX()) {
                    return null;
                }
                intersectionX = Math.max(end.getX(), other.start.getX());
                return new Point(intersectionX, assign(intersectionX));
            }
            return null;
        }

        if (getSlope() == INF || other.getSlope() == INF) {
            // if one of the lines are parallel to y.
            if (getSlope() == INF) {
                intersectionX = start.getX();
                intersectionY = other.assign(intersectionX);
            } else {
                intersectionX = other.start.getX();
                intersectionY = assign(intersectionX);
            }
        } else {
            intersectionX = (other.getYIntersection() - getYIntersection()) / (getSlope() - other.getSlope());
            intersectionY = assign(intersectionX);
        }

        // check if the point is in range of both line segments
        Point intersection = new Point(intersectionX, intersectionY);
        if (betweenEdges(intersection) && other.betweenEdges(intersection)) {
            return intersection;
        } else {
            return null;
        }
    }

    /**
     * name: closestIntersectionPoint.<br>
     *
     * @param rect the rectangle that is being examined.
     * @return the intersection point that is the closest to the start.
     */
    public Point closestIntersectionPoint(Rectangle rect) {
        List<Point> intersections = rect.intersectionPoints(this);
        if (intersections.isEmpty()) {
            return null;
        }
        double minDistance = this.start.distance(intersections.get(0));
        Point minDistanceP = null;
        for (Point p : intersections) {
            if (p.distance(start) <= minDistance) {
                minDistanceP = p;
                minDistance = p.distance(start);
            }
        }
        return minDistanceP;
    }
}