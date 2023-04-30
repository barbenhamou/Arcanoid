package Game;

import AbstractShapes.Point;
import Objects.Collidable;

public class CollisionInfo {
    private Point collisionPoint;

    private Collidable collisionObj;

    public CollisionInfo(Point collisionPoint, Collidable collidable) {
        this.collisionPoint = collisionPoint;
        this.collisionObj = collidable;
    }
    // the point at which the collision occurs.
    public Point collisionPoint() {
        return collisionPoint;
    }

    // the collidable object involved in the collision.
    public Collidable collisionObject() {
        return collisionObj;
    }
}