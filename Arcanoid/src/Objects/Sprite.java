package Objects;

import biuoop.DrawSurface;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
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