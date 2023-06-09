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
    void addToGame(GameLevel g);

    /**
     * Remove a thing from the game.<br>
     *
     * @param g the game object.
     */
    void removeFromGame(GameLevel g);
}