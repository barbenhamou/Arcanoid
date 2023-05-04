package AbstractShapes;

import java.util.Random;

/**
 * A representation of a point in R2.
 */
public class Point {
    private final double x;
    private final double y;

    static final Double COMPARISON_THRESHOLD = 0.000001;

    /**
     * Constructor.
     *
     * @param x
     * @param y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for x.
     *
     * @return x val of point
     */
    public double getX() {
        return x;
    }

    /**
     * Getter for y.
     *
     * @return y val of point
     */
    public double getY() {
        return y;
    }

    /**
     * Name: distance.<br>
     *
     * @param other the point we desire the distance from her.<br>
     * @return the distance between current point and another.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2)
                + Math.pow(this.y - other.y, 2));
    }

    /**
     * Name: equals.<br>
     *
     * @param other the point we desire to check.<br>
     * @return true if current is the {@param other}, otherwise false.
     */
    public boolean equals(Point other) {
        return doubleCompare(this.x, other.x) && doubleCompare(this.y, other.y);
    }

    /**
     * Compare two doubles with threshold.
     *
     * @param a
     * @param b
     * @return whether a<=b
     */
    public static boolean doubleCompare(double a, double b) {
        return Math.abs(a - b) <= COMPARISON_THRESHOLD;
    }

    /**
     * Creates a new point with the point-wise-added coordinates of the two points.
     *
     * @param x add horizontal.
     * @param y add vertical.
     * @return new point, the sum of two.
     */
    public Point addPoint(double x, double y) {
        return new Point(this.x + x, this.y + y);
    }

    /**
     * Generator for random AbstractShapes.Point according to the size of the screen.
     *
     * @param upperBound1 max horizontal value
     * @param upperBound2 max vertical value
     * @param lowerBound1 min horizontal value
     * @param lowerBound2 min vertical value
     * @return the new random point
     */
    public static Point generateRandomPoint(int upperBound1, int upperBound2,
                                            int lowerBound1, int lowerBound2) {
        Random rand = new Random();
        int x = rand.nextInt(upperBound1 - lowerBound1) + 1 + lowerBound1;
        int y = rand.nextInt(upperBound2 - lowerBound2) + 1 + lowerBound2;
        return new Point(x, y);
    }
}