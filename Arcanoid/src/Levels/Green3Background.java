package Levels;

import Objects.Sprite;
import Utils.Constants;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * Background green.
 */
public class Green3Background implements Sprite {

    private Color colorObj;
    private Color colorBack;

    /**
     * Constructor.<br>
     *
     * @param colorObj color for other objects.
     * */
    public Green3Background(Color colorObj) {
        this.colorBack = Color.green;
        this.colorObj = colorObj;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(colorBack);
        d.fillRectangle(Constants.BLOCK_THICKNESS,
                2 * Constants.BLOCK_THICKNESS,
                Constants.WIDTH - 2 * Constants.BLOCK_THICKNESS,
                Constants.HEIGHT - 3 * Constants.BLOCK_THICKNESS);
        d.setColor(Color.GRAY);
        d.fillRectangle(80, 390, 100, 180);
        d.setColor(Color.black);
        d.drawRectangle(80, 390, 100, 180);
        d.setColor(Color.WHITE);
        for (int i = 0; i <= 4; ++i) {
            for (int j = 0; j < 5; ++j) {
                d.fillRectangle(85 + j * 19, 405 + 33 * i, 14,
                        28);
                d.setColor(Color.black);
                d.drawRectangle(85 + j * 19, 405 + 33 * i, 14,
                        28);
                d.setColor(Color.WHITE);
            }
        }
        d.setColor(Color.GRAY);
        d.fillRectangle(105, 350, 50, 40);
        d.setColor(Color.black);
        d.drawRectangle(105, 350, 50, 40);
        d.setColor(Color.GRAY);
        d.fillRectangle(125, 200, 10, 150);
        d.setColor(Color.black);
        d.drawRectangle(125, 200, 10, 150);
        d.setColor(Color.red);
        d.fillCircle(130, 195, 16);
        d.setColor(Color.ORANGE);
        d.fillCircle(130, 195, 10);
        d.setColor(Color.YELLOW);
        d.fillCircle(130, 195, 7);
    }

    @Override
    public void timePassed() {

    }
}