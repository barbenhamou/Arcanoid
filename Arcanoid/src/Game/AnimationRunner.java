package Game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * The animation runner.
 */
public class AnimationRunner {
    private GUI gui;
    private Sleeper sleeper;

    /**
     * Constructor.<br>
     *
     * @param gui the gui object.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.sleeper = new Sleeper();
    }

    /**
     * Run the animation.<br>
     *
     * @param animation the animation we want to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / (int) animation.framePerSecond();
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}