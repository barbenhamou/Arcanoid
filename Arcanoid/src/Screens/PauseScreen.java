package Screens;

import Game.Animation;
import Game.SpriteCollection;
import biuoop.DrawSurface;

import java.awt.*;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * The pause screen.
 * */
public class PauseScreen implements Animation {
    private SpriteCollection game;

    /**
     * Constructor.<br>
     *
     * @param game the sprite collection.
     * */
    public PauseScreen(SpriteCollection game) {
        this.game = game;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        game.drawAllOn(d);
        d.setColor(Color.WHITE);
        d.drawText(10, d.getHeight()/2, "paused -- press space to continue", 32);
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