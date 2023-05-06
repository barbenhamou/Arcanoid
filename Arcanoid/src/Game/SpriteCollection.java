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
     * Notify all sprite that time has passed.
     */
    public void notifyAllTimePassed() {
        sprites.forEach(Sprite::timePassed);
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