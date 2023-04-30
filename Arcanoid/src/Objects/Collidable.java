package Objects;
import AbstractShapes.Rectangle;
import AbstractShapes.Point;
import Utils.Velocity;
/**
 * A thing that the ball can collide with.
 * */
public interface Collidable  {
    /**
     * @return the shape the ball had collided with.
     * */
    Rectangle getCollisionRectangle();

    /**
     *
     * @param collisionPoint point of collision between the objects.
     * @param currentVelocity the current velocity of the ball.
     * @return the new velocity after collision.
     **/
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}