package Game;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * A model for all in game objects.
 * */
public interface InGameObject {
    /**
     * Add a thing to the game.<br>
     *
     * @param g the game object.
     */
    void addToGame(Game g);

    /**
     * Remove a thing from the game.<br>
     *
     * @param g the game object.
     */
    void removeFromGame(Game g);
}