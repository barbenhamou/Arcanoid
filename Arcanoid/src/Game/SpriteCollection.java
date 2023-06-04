package Game;

import Objects.Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * Holds all the sprites in the game.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Constructor.
     */
    public SpriteCollection() {
        sprites = new ArrayList<>();
    }

    /**
     * Add sprite to the list of all sprites.<br>
     *
     * @param s the wanted sprite.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Removing new sprite from the game environment.
     *
     * @param s the wanted sprite.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * Notify all sprite that time has passed.
     */
    public void notifyAllTimePassed() {
        for (int i = sprites.size() - 1; i >= 0; --i) {
            sprites.get(i).timePassed();
        }
    }

    /**
     * Draw all the sprites.<br>
     *
     * @param d the drawing tool.
     */
    public void drawAllOn(DrawSurface d) {
        sprites.forEach(x -> x.drawOn(d));
    }
}