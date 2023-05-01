package Objects;

import AbstractShapes.Line;
import AbstractShapes.Point;
import Game.GameEnvironment;
import Utils.Velocity;
import Utils.UtilsFunctions;

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * A representation of Ball.
 */
public class Ball {
    private final int r;
    private Point center;
    private Color color;
    private Velocity velocity;

    private final GameEnvironment gameEnvironment;

    /**
     * Constructor.
     *
     * @param center
     * @param r
     * @param color
     * @param gameEnvironment
     */
    public Ball(Point center, int r, Color color, GameEnvironment gameEnvironment) {
        this.r = r;
        this.center = center;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Setter for velocity using object.
     *
     * @param v - velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Setter for velocity using attributes.
     *
     * @param dx - horizontal speed
     * @param dy - vertical speed
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Setter for center of the ball.
     *
     * @param x
     * @param y
     */
    public void setCenter(double x, double y) {
        this.center = new Point(x, y);
    }

    /**
     * Setter for color of the ball.
     *
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Getter for the x of the center.
     *
     * @return the x value of center
     */
    public double getX() {
        return center.getX();
    }

    /**
     * Getter for the y of the center.
     *
     * @return the y value of center
     */
    public double getY() {
        return center.getY();
    }

    /**
     * Getter for the size of the ball(radius).
     *
     * @return the radius
     */
    public int getSize() {
        return r;
    }

    /**
     * Getter for the color of the ball.
     *
     * @return the color of the ball
     */
    public Color getColor() {
        return color;
    }

    /**
     * Getter for the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Getter for the speed of the ball.
     *
     * @return the speed of the ball
     */
    public double getSpeed() {
        return this.velocity.getSpeed();
    }

    /**
     * Drawing command of the ball.
     *
     * @param surface - the interface pointer that is used for the drawing
     */
    public void drawOn(DrawSurface surface) {
        surface.fillCircle((int) Math.round(getX()), (int) Math.round(getY()),
                Math.round(r));
    }

    /**
     * Changing positions of the ball.
     */
    public void moveOneStep() {
        this.center = velocity.applyToPoint(this.center);
    }

    /**
     * Changing positions of the ball, another function so the previous one
     * stamp wouldn't change.
     *
     * @param upperBound1 max horizontal value
     * @param upperBound2 max vertical value
     * @param lowerBound1 min horizontal value
     * @param lowerBound2 min vertical value
     */
    public void moveOneStepHelper(int upperBound1, int upperBound2,
                                  int lowerBound1, int lowerBound2) {
        double currDx = velocity.getDx();
        double currDy = velocity.getDy();

        if (UtilsFunctions.approxiEquals(getX() - r, lowerBound1)
                || UtilsFunctions.approxiEquals(upperBound1, getX() + r)) {
            velocity.setDx(-currDx);
        }

        if (UtilsFunctions.approxiEquals(getY() - r, lowerBound2)
                || UtilsFunctions.approxiEquals(upperBound2, getY() + r)) {
            velocity.setDy(-currDy);
        }

        moveOneStep();
    }

    /**
     * If the center is not well initialized, it gets fixed.
     *
     * @param upperBound min horizontal value
     * @param lowerBound min vertical value
     */
    public void fixCenter(int upperBound, int lowerBound) {
        if (getX() - lowerBound <= r || upperBound - getX() <= r) {
            setCenter((upperBound + lowerBound) / 2.0,
                    (upperBound + lowerBound) / 2.0);
        }

        if (getY() - lowerBound <= r || upperBound - getY() <= r) {
            setCenter((upperBound + lowerBound) / 2.0,
                    (upperBound + lowerBound) / 2.0);
        }
    }

    /**
     * adjusting the velocity.
     *
     * @param task the task number
     */

    public void adjustVelocity(int task) {
        double r = getSize();
        if (r > 50 && task == 3) {
            r = 50;
        }
        double dx = 15 + 1 / r;
        double dy = 15 + 1 / r;
        velocity = new Velocity(dx, dy);
    }

    public Line getTrajectory(int bound) {
        Point start = new Point(center.getX(), center.getY());
        if(velocity.getDy() == 0) {
        double dt = (bound - center.getY()) / velocity.getDy();
        double x = getX() + velocity.getDx() * dt;
        Point end = new Point(x, bound);
        return new Line(start, end);
    }
}