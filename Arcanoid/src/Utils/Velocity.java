package Utils;

import AbstractShapes.Point;

/**
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
     * Setter for x-axis speed.
     *
     * @param dx
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Setter for y-axis speed.
     *
     * @param dy
     */
    public void setDy(double dy) {
        this.dy = dy;
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
     * Rotating the velocity.<br>
     * @param rotateByDeg the angles.
     * @return the new velocity after rotation.
     * */
    public Velocity rotateByDeg(int rotateByDeg) {
        double size = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        double angle = Math.atan2(dy, dx);
        double newAngle = angle + (rotateByDeg * Math.PI / 180.);
        return new Velocity(size * Math.cos(newAngle), size * Math.sin(newAngle));
    }

    /**
     * Applier dx.
     *
     * @param p
     * @return the new point after applying dx upon her
     */
    public Point applyXToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY());
    }

    /**
     * Applier dy.
     *
     * @param p
     * @return the new point after applying dy upon her
     */
    public Point applyYToPoint(Point p) {
        return new Point(p.getX(), p.getY() + dy);
    }

    /**
     * Getter for the velocity by speed and angle in degrees.
     *
     * @param speed
     * @param angle
     * @return the velocity using angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(Math.toRadians(angle)) * speed;
        double dy = Math.sin(Math.toRadians(angle)) * speed;

        return new Velocity(dx, dy);
    }


}