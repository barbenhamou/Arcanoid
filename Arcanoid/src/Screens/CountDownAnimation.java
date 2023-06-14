package Screens;

import Game.Animation;
import Game.SpriteCollection;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * Count down.
 */
public class CountDownAnimation implements Animation {
    private double numOfSeconds;
    private int current;
    private int countFrom;
    private SpriteCollection game;

    /**
     * Constructor.<br>
     *
     * @param game         the sprite collection.
     * @param countFrom    from when to start.
     * @param numOfSeconds how much time should it take.
     */
    public CountDownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection game) {
        this.numOfSeconds = numOfSeconds;
        this.current = countFrom;
        this.countFrom = countFrom;
        this.game = game;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        game.drawAllOn(d);
        d.setColor(Color.blue);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, current-- + "...", 32);
    }

    @Override
    public boolean shouldStop() {
        return current <= 0;
    }

    @Override
    public double framePerSecond() {
        return countFrom / numOfSeconds;
    }
}