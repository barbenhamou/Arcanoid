package AbstractShapes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A class that represent a Rectangle object.
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;

    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Getter for the upper left point of the rectangle.
     * */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Getter for the upper left right of the rectangle.
     * */
    public Point getUpperRight() {
        return new Point(upperLeft.getX() + width, upperLeft.getY());
    }

    /**
     * Getter for the lower left point of the rectangle.
     * */
    public Point getLowerLeft() {
        return new Point(upperLeft.getX(), upperLeft.getY() + height);
    }

    /**
     * Getter for the lower right point of the rectangle.
     * */
    public Point getLowerRight() {
        return new Point(upperLeft.getX() + width, upperLeft.getY() + height);
    }

    /**
     * name: intersectionPoints.<br>
     * @param line the line that is getting checked for intersections.
     * @return list of all intersections.
     * */
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
}