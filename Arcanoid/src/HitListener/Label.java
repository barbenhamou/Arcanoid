package HitListener;

import Objects.Sprite;
import Utils.Constants;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * The label class.
 */
public class Label implements Sprite {
    private String content;
    private int xValue;
    private String label;

    /**
     * Constructor.<br>
     *
     * @param label  the text label.
     * @param content What to present.
     * @param xValue the horizontal position.
     */
    public Label(String content, int xValue, String label) {
        this.content = content;
        this.xValue = xValue;
        this.label = label;

    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(xValue, Constants.Y_STATS, label + ": " + content,
                Constants.FONT);
    }

    @Override
    public void timePassed() {

    }
}