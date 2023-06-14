package Screens;

import Game.Animation;
import Game.SpriteCollection;
import biuoop.DrawSurface;

import java.awt.*;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * The pause screen.
 */
public class PauseScreen implements Animation {
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(165, d.getHeight() / 2, "paused -- press space to continue",
                32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }

    @Override
    public double framePerSecond() {
        return 60;
    }
}