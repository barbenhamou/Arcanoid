package Objects;

import Game.Game;
import biuoop.DrawSurface;

/**
 * A model to a sprite.
 * */
public interface Sprite {
    /**
     * Draw the object.
     *
     * @param d the drawing tool.
     **/
    void drawOn(DrawSurface d);


    /**
     * Notify the sprite that time has passed.
     * */
    void timePassed();
}