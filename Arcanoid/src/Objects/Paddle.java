package Objects;

import AbstractShapes.Point;
import AbstractShapes.Rectangle;
import Game.GameLevel;
import Utils.Constants;
import Utils.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import Game.InGameObject;

import java.awt.Color;

/**
 * The paddle of the arkanoid game.
 */
public class Paddle implements Sprite, Collidable, InGameObject {
    private KeyboardSensor sensor;
    private Rectangle rect;
    private Color color;

    private int dx;

    /**
     * Constructor.
     *
     * @param rect   the block of the paddle.
     * @param sensor the move sensor of the paddle.
     * @param color  the color of the paddle.
     * @param dx     the speed of the paddle.
     */
    public Paddle(KeyboardSensor sensor, Rectangle rect, Color color, int dx) {
        this.sensor = sensor;
        this.rect = rect;
        this.color = color;
        this.dx = dx;
    }

    /**
     * Moves the paddle left.
     */
    public void moveLeft() {
        Point newUpperLeft = rect.getUpperLeft().addPoint(-dx, 0);
        if (newUpperLeft.getX() < Constants.BLOCK_THICKNESS) {
            return;
        }
        rect.setUpperLeft(newUpperLeft);
    }

    /**
     * Moves the paddle right.
     */
    public void moveRight() {
        Point newUpperRight = rect.getUpperRight().addPoint(dx, 0);
        if (newUpperRight.getX() > Constants.WIDTH - Constants.BLOCK_THICKNESS) {
            return;
        }
        rect.setUpperLeft(rect.getUpperLeft().addPoint(dx, 0));
    }

    @Override
    public void timePassed() {
        if (sensor.isPressed("a")) {
            moveLeft();
            return;
        }
        if (sensor.isPressed("d")) {
            moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.yellow);
        d.drawRectangle((int) rect.getUpperLeft().getX() - 1,
                (int) rect.getUpperLeft().getY() - 1, (int) rect.width() + 1,
                (int) rect.height() + 1);
        d.setColor(color);
        d.fillRectangle((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(), (int) rect.width(),
                (int) rect.height());
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Region of the collision.
     *
     * @param x the collision point x.
     * @return the region of the hit
     */
    public int region(double x) {
        if (x == rect.getUpperLeft().getX()) {
            return 1;
        }

        if (x <= rect.getUpperLeft().getX() + rect.width() / 5) {
            return 2;
        }

        if (x <= rect.getUpperLeft().getX() + rect.width() / 2) {
            return 3;
        }

        if (x <= rect.getUpperLeft().getX() + 4 * rect.width() / 5) {
            return 2;
        }

        if (x == rect.getUpperRight().getX()) {
            return 5;
        }
        return 0;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        //if the ball hits the paddle side, mirror his movement.
        if (collisionPoint.getY() <= rect.getUpperLeft().getY()
                || collisionPoint.getX() < rect.getUpperLeft().getX()
                || collisionPoint.getX() > rect.getUpperRight().getX()) {
            return new Velocity(-1 * currentVelocity.getDx(),
                    -1 * currentVelocity.getDy());
        }
        int region = region(collisionPoint.getX());
        int rotateByDeg = 180;
        if (region == 1) {
            rotateByDeg = -60;
        } else if (region == 2) {
            rotateByDeg = -30;
        } else if (region == 3) {
            return new Velocity(currentVelocity.getDx(),
                    -1 * currentVelocity.getDy());
        } else if (region == 4) {
            rotateByDeg = 30;
        } else if (region == 5) {
            rotateByDeg = 60;
        }
        return currentVelocity.rotateByDeg(-rotateByDeg);
    }

}