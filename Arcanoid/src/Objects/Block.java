package Objects;
import AbstractShapes.Point;
import AbstractShapes.Rectangle;
import Utils.UtilsFunctions;
import Utils.Velocity;

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

        if(UtilsFunctions.approxiEquals(collisionPoint.getX(), block.getUpperLeft().getX())
            || UtilsFunctions.approxiEquals(collisionPoint.getX(), block.getUpperRight().getX())) {
            dx *= -1;
        }

        if(UtilsFunctions.approxiEquals(collisionPoint.getY(), block.getUpperLeft().getY())
                || UtilsFunctions.approxiEquals(collisionPoint.getY(), block.getLowerLeft().getY())) {
            dy *= -1;
        }
        return new Velocity(dx, dy);
    }
}
