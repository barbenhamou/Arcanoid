package Objects;

import AbstractShapes.Point;
import AbstractShapes.Rectangle;
import Game.Game;
import Utils.UtilsFunctions;
import Utils.Velocity;
import biuoop.DrawSurface;
import Game.InGameObject;

import java.awt.Color;

/**
 * A representation of a block.
 */
public class Block implements Collidable, Sprite, InGameObject {
    private Rectangle block;
    private Color color;

    /**
     * Constructor.<br>
     *
     * @param block
     * @param color
     */
    public Block(Rectangle block, Color color) {
        this.block = block;
        this.color = color;
    }

    /**
     * Return the rectangle of the block.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return block;
    }

    /**
     * @param collisionPoint  point of collision between the objects.
     * @param currentVelocity the current velocity of the ball.
     * @return the new velocity after collision.
     **/
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        if (UtilsFunctions.approxiEquals(collisionPoint.getX() ,
                block.getUpperLeft().getX() + block.width())
                || UtilsFunctions.approxiEquals(collisionPoint.getX(),
                block.getUpperRight().getX() - block.width())) {
            dx *= -1;
        }

        else if (UtilsFunctions.approxiEquals(collisionPoint.getY(),
                block.getUpperLeft().getY() + block.height())
                || UtilsFunctions.approxiEquals(collisionPoint.getY(),
                block.getLowerLeft().getY() - block.height())) {
            dy *= -1;
        }
        return new Velocity(dx, dy);
    }

    public Velocity hit1(Ball b, Point collisionPoint,
                        Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        if (UtilsFunctions.approxiEquals(collisionPoint.getX(),
                block.getUpperLeft().getX() + block.width())
                || UtilsFunctions.approxiEquals(collisionPoint.getX(),
                block.getUpperRight().getX() - block.width())) {
            dx *= -1;
        }

        else if (UtilsFunctions.approxiEquals(collisionPoint.getY(),
                block.getUpperLeft().getY() + block.height())
                || UtilsFunctions.approxiEquals(collisionPoint.getY(),
                block.getLowerLeft().getY() - block.height())) {
            dy *= -1;
        }
        return new Velocity(dx, dy);
    }

    /**
     * Drawing the ball.<br>
     *
     * @param d drawing tool.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawRectangle((int) block.getUpperLeft().getX() - 1,
                (int) block.getUpperLeft().getY() - 1, (int) block.width() + 1,
                (int) block.height() + 1);
        d.setColor(color);
        d.fillRectangle((int) block.getUpperLeft().getX(),
                (int) block.getUpperLeft().getY(), (int) block.width(),
                (int) block.height());

    }

    /**
     * The block get notified that time has passed and do something.
     */
    public void timePassed() {
    }

    /**
     * Add the block to the sprite list and collidable list.<br>
     *
     * @param g the game object.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}