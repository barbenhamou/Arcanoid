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
     * @return whether the animation should stop.
     */
    boolean shouldStop();

    /**
     * @return The frame per second of the animation.
     */
    double framePerSecond();
}