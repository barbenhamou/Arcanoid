package AbstractShapes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * A class that represent a Rectangle object.
 */
public class Rectangle {
    private Point upperLeft;
    private final double width;
    private final double height;

    /**
     * Constructor.
     *
     * @param upperLeft the upper left point of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Getter for the upper left point of the rectangle.<br>
     *
     * @return the upper left point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Setter for upper left point.
     *
     * @param p the new point.
     */
    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }

    /**
     * Getter for the upper left right of the rectangle.<br>
     *
     * @return the upper right point.
     */
    public Point getUpperRight() {
        return new Point(upperLeft.getX() + width, upperLeft.getY());
    }

    /**
     * Getter for the lower left point of the rectangle.<br>
     *
     * @return the lower left point.
     */
    public Point getLowerLeft() {
        return new Point(upperLeft.getX(), upperLeft.getY() + height);
    }

    /**
     * Getter for the lower right point of the rectangle.<br>
     *
     * @return the lower right point.
     */
    public Point getLowerRight() {
        return new Point(upperLeft.getX() + width, upperLeft.getY() + height);
    }

    /**
     * Getter for the height of the rectangle.<br>
     *
     * @return the height of the rectangle.
     */
    public double height() {
        return height;
    }

    /**
     * Getter for the width of the rectangle.<br>
     *
     * @return the width of the rectangle.
     */
    public double width() {
        return width;
    }

    /**
     * name: intersectionPoints.<br>
     *
     * @param line the line that is getting checked for intersections.
     * @return list of all intersections.
     */
    public List<Point> intersectionPoints(Line line) {
        Line upper = new Line(upperLeft, getUpperRight());
        Line lower = new Line(getLowerLeft(), getLowerRight());
        Line left = new Line(upperLeft, getLowerLeft());
        Line right = new Line(getUpperRight(), getLowerRight());

        List<Point> intersectionPoints = new ArrayList<Point>();
        intersectionPoints.add(upper.intersectionWith(line));
        intersectionPoints.add(lower.intersectionWith(line));
        intersectionPoints.add(right.intersectionWith(line));
        intersectionPoints.add(left.intersectionWith(line));

        return intersectionPoints.stream().
                filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * Checks if a point is on the left side.
     *
     * @param p the point we check.
     * @return true or false.
     */
    public boolean isOnLeft(Point p) {
        return p.getX() == upperLeft.getX()
                && p.getY() <= upperLeft.getY() && p.getY() >= getLowerLeft().getY();
    }

    /**
     * Checks if a point is on the right side.
     *
     * @param p the point we check.
     * @return true or false.
     */
    public boolean isOnRight(Point p) {
        return p.getX() == getUpperRight().getX()
                && p.getY() <= upperLeft.getY() && p.getY() >= getLowerLeft().getY();
    }

    /**
     * Checks if a point is on the upper side.
     *
     * @param p the point we check.
     * @return true or false.
     */
    public boolean isOnUpper(Point p) {
        return p.getX() == upperLeft.getY()
                && p.getX() <= upperLeft.getX() && p.getY() >= getUpperRight().getX();
    }

    /**
     * Checks if a point is on the lower side.
     *
     * @param p the point we check.
     * @return true or false.
     */
    public boolean isOnLower(Point p) {
        return p.getX() == getLowerLeft().getY()
                && p.getX() <= upperLeft.getX() && p.getY() >= getUpperRight().getX();
    }
}