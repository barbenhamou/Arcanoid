package HitListener;

import Objects.Sprite;
import Utils.Constants;
import Utils.Counter;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * The score indicator.
 */
public class Indicator implements Sprite {
    private Counter score;
    private int xValue;
    private String label;

    /**
     * Constructor.<br>
     *
     * @param label  the text label.
     * @param score  the score.
     * @param xValue the horizontal position.
     */
    public Indicator(Counter score, int xValue, String label) {
        this.score = score;
        this.xValue = xValue;
        this.label = label;

    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(xValue, Constants.Y_SCORE, label + ": " + score.getValue(),
                Constants.FONT);
    }

    @Override
    public void timePassed() {

    }
}