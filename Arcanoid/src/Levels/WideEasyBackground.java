package Levels;

import AbstractShapes.Line;
import Objects.Sprite;
import Utils.Constants;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * Background to the one with the sun.
 */
public class WideEasyBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(Constants.BLOCK_THICKNESS,
                2 * Constants.BLOCK_THICKNESS,
                Constants.WIDTH - 2 * Constants.BLOCK_THICKNESS,
                Constants.HEIGHT - 3 * Constants.BLOCK_THICKNESS);
        d.setColor(Color.YELLOW);
        d.fillCircle(125, 150, 60);
        for (int i = 0; i < 15 * 6; ++i) {
            d.drawLine(125, 150, 30 + 8 * i, 250);
        }
        d.setColor(Color.ORANGE);
        d.fillCircle(125, 150, 50);
        d.setColor(Color.red);
        d.fillCircle(125, 150, 40);
    }

    @Override
    public void timePassed() {

    }
}