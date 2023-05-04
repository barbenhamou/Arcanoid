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

    @Override
    public Rectangle getCollisionRectangle() {
        return block;
    }

    @Override
    public Velocity hit(Point collisionPoint,
                        Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        if (UtilsFunctions.approxiEquals(collisionPoint.getX(),
                block.getUpperLeft().getX())
                || UtilsFunctions.approxiEquals(collisionPoint.getX() ,
                block.getUpperRight().getX())) {
            dx *= -1;
        } else if (UtilsFunctions.approxiEquals(collisionPoint.getY(),
                block.getUpperLeft().getY())
                || UtilsFunctions.approxiEquals(collisionPoint.getY(),
                block.getLowerLeft().getY())) {
            dy *= -1;
        }
        return new Velocity(dx, dy);
    }

    @Override
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

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}