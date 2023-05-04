package Objects;

import AbstractShapes.Line;
import AbstractShapes.Point;
import Game.CollisionInfo;
import Game.Game;
import Game.GameEnvironment;
import Game.InGameObject;
import Utils.Velocity;

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * A representation of Ball.
 */
public class Ball implements Sprite, InGameObject {
    private final int r;
    private Point center;
    private Color color;
    private Velocity velocity;

    private final GameEnvironment environment;

    /**
     * Constructor.
     *
     * @param center
     * @param r
     * @param color
     * @param environment
     */
    public Ball(Point center, int r, Color color, GameEnvironment environment) {
        this.r = r;
        this.center = center;
        this.color = color;
        this.environment = environment;
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

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) Math.round(getX()), (int) Math.round(getY()), Math.round(r));
    }

    /**
     * Changing positions of the ball.
     */
    public void moveOneStep() {
        Line trajectory = new Line(center, velocity.applyToPoint(center));
        CollisionInfo collisionInfo = environment.getClosestCollision(trajectory);
        if (collisionInfo != null) {
            velocity =
                    collisionInfo.collisionObject().
                            hit(collisionInfo.collisionPoint(), velocity);
        }
        this.center = velocity.applyToPoint(this.center);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * If the center is not well initialized, it gets fixed.
     *
     * @param upperBound min horizontal value
     * @param lowerBound min vertical value
     */
    public void fixCenter(int upperBound, int lowerBound) {
        if (getX() - lowerBound <= r || upperBound - getX() <= r) {
            setCenter((upperBound + lowerBound) / 2.0, (upperBound + lowerBound) / 2.0);
        }

        if (getY() - lowerBound <= r || upperBound - getY() <= r) {
            setCenter((upperBound + lowerBound) / 2.0, (upperBound + lowerBound) / 2.0);
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
}