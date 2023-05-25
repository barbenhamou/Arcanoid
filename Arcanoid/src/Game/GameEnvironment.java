package Game;

import AbstractShapes.Line;
import AbstractShapes.Point;
import Objects.Collidable;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * The game environment.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Constructor.
     */
    public GameEnvironment() {
        collidables = new ArrayList<>();
    }

    /**
     * Adding new collidable to the game environment.
     *
     * @param c the new collidable.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Removing new collidable from the game environment.
     *
     * @param c the wanted collidable.
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Getter for the collidable list.
     *
     * @return list of collidables.
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * @param trajectory the trajectory of the ball.
     * @return collisionInfo - the closest collision point and object.
     */

    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestPoint = null, closestToCollidable;
        Collidable closest = null;

        for (Collidable collidable : collidables) {
            closestToCollidable = trajectory.closestIntersectionPoint(collidable.getCollisionRectangle());
            if (closestToCollidable == null) {
                continue;
            }

            if (closestPoint == null
                    || trajectory.getStart().distance(closestToCollidable)
                    < trajectory.getStart().distance(closestPoint)) {
                closestPoint = closestToCollidable;
                closest = collidable;
            }
        }
        if (closestPoint == null) {
            return null;
        }
        return new CollisionInfo(closestPoint, closest);
    }
}