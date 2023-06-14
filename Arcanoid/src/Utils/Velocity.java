package Utils;

import AbstractShapes.Point;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * Utils.Velocity class, helper to the animation and physics of the ball.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor.
     *
     * @param dx
     * @param dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Getter for x-axis speed.
     *
     * @return x-axis speed
     */
    public double getDx() {
        return dx;
    }

    /**
     * Getter for y-axis speed.
     *
     * @return y-axis speed
     */
    public double getDy() {
        return dy;
    }

    /**
     * Getter for speed.
     *
     * @return speed
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /**
     * Applier dx and dy.
     *
     * @param p
     * @return the new point after applying speed upon her
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Getter for the velocity by speed and angle in degrees.
     *
     * @param speed
     * @param angle
     * @return the velocity using angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(Math.toRadians(angle - 90)) * speed;
        double dy = Math.sin(Math.toRadians(angle - 90)) * speed;

        return new Velocity(dx, dy);
    }
}