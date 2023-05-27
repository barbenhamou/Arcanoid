package Objects;

import AbstractShapes.Point;
import AbstractShapes.Rectangle;
import Game.Game;
import HitListener.HitListener;
import HitListener.HitNotifier;
import Utils.UtilsFunctions;
import Utils.Velocity;
import biuoop.DrawSurface;
import Game.InGameObject;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * A representation of a block.
 */
public class Block implements Collidable, Sprite, InGameObject, HitNotifier {
    private Rectangle block;
    private Color color;

    private List<HitListener> hitListeners;

    /**
     * Constructor.<br>
     *
     * @param block
     * @param color
     */
    public Block(Rectangle block, Color color) {
        this.block = block;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return block;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        if (UtilsFunctions.approxiEquals(collisionPoint.getX(),
                block.getUpperLeft().getX())
                || UtilsFunctions.approxiEquals(collisionPoint.getX(),
                block.getUpperRight().getX())) {
            dx *= -1;
        } else if (UtilsFunctions.approxiEquals(collisionPoint.getY(),
                block.getUpperLeft().getY())
                || UtilsFunctions.approxiEquals(collisionPoint.getY(),
                block.getLowerLeft().getY())) {
            dy *= -1;
        }
        this.notifyHit(hitter);
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

    @Override
    public void removeFromGame(Game g) {
        g.removeCollidable(this);
        g.removeSprite(this);
        hitListeners.clear();
    }

    @Override
    public void addHitListener(HitListener h1) {
        hitListeners.add(h1);
    }

    @Override
    public void removeHitListener(HitListener h1) {
        hitListeners.remove(h1);
    }

    /**
     * Notify that a hit in this object occurred.<br>
     *
     * @param hitter the ball that hit this object.
     * */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);

        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}