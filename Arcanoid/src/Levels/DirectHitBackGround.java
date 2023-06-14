package Levels;

import Objects.Sprite;
import Utils.Constants;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * Background Direct.
 */
public class DirectHitBackGround implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(Constants.BLOCK_THICKNESS,
                2 * Constants.BLOCK_THICKNESS,
                Constants.WIDTH - 2 * Constants.BLOCK_THICKNESS,
                Constants.HEIGHT - 3 * Constants.BLOCK_THICKNESS);
        d.setColor(Color.BLUE);
        d.drawCircle(370, 250, 100);
        d.drawCircle(370, 250, 75);
        d.drawCircle(370, 250, 50);
        d.drawLine(370, 240, 370, 150);
        d.drawLine(370, 260, 370, 330);
        d.drawLine(270, 250, 360, 250);
        d.drawLine(380, 250, 470, 250);
    }

    @Override
    public void timePassed() {

    }
}