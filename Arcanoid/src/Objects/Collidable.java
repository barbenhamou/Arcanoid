package Objects;
import AbstractShapes.Rectangle;
import AbstractShapes.Point;
import Utils.Velocity;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * A thing that the ball can collide with.
 * */
public interface Collidable  {
    /**
     * @return the shape the ball had collided with.
     * */
    Rectangle getCollisionRectangle();

    /**
     * @param hitter the ball that hit the block.
     * @param collisionPoint point of collision between the objects.
     * @param currentVelocity the current velocity of the ball.
     * @return the new velocity after collision.
     **/
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}