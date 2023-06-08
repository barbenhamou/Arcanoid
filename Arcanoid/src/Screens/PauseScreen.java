package Screens;

import Game.Animation;
import biuoop.DrawSurface;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * The pause screen.
 * */
public class PauseScreen implements Animation {
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight()/2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}