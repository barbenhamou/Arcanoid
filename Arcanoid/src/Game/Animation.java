package Game;

import biuoop.DrawSurface;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * The animation model.
 */
public interface Animation {
    /**
     * Drawing one frame.<br>
     *
     * @param d the drawing tool.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Determining whether the animation should stop.
     */
    boolean shouldStop();
}