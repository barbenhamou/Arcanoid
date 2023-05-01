package Objects;
import AbstractShapes.Point;
import AbstractShapes.Rectangle;
import Utils.UtilsFunctions;
import Utils.Velocity;
import biuoop.DrawSurface;

import java.awt.Color;

public class Block implements Collidable{
    private Rectangle block;
    private Color color;

    /**
     * Constructor.
     *  */
    public Block(Rectangle block, Color color) {
        this.block = block;
        this.color = color;
    }

    /**
     * Return the rectangle of the block.
     * */
    @Override
    public Rectangle getCollisionRectangle() {
        return block;
    }

    /**
     * @param collisionPoint point of collision between the objects.
     * @param currentVelocity the current velocity of the ball.
     * @return the new velocity after collision.
     **/
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        if(UtilsFunctions.approxiEquals(collisionPoint.getX(),
                block.getUpperLeft().getX() + block.width())
            || UtilsFunctions.approxiEquals(collisionPoint.getX(),
                block.getUpperRight().getX() - block.width())) {
            dx *= -1;
            System.out.println("in x");
            System.out.println(collisionPoint.getX());
            System.out.println(block.getUpperRight().getX());
        }

        if(UtilsFunctions.approxiEquals(collisionPoint.getY(),
                block.getUpperLeft().getY() + block.height())
                || UtilsFunctions.approxiEquals(collisionPoint.getY(),
                block.getLowerLeft().getY() - block.height())) {
            dy *= -1;
            System.out.println("in y");
            System.out.println(collisionPoint.getY());
            System.out.println(block.getLowerLeft().getY());
        }
        return new Velocity(dx, dy);
    }

    public void draw(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int)block.getUpperLeft().getX(),
                (int)block.getUpperLeft().getY(), (int)block.width(),
                (int)block.height());

    }
}