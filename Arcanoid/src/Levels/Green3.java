package Levels;

import AbstractShapes.Point;
import Objects.Block;
import Objects.Sprite;
import Utils.Constants;
import Utils.Velocity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * A level.
 */
public class Green3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(5, 5));
        velocities.add(new Velocity(-5, 5));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 60;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Green3Background(Color.BLACK);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] c = {Color.gray, Color.red, Color.yellow, Color.blue,
                Color.white};
        //blocks
        int common = 150, width = 45, height = 20;
        for (int i = 10; i > 5; --i) {
            for (int j = 0; j < i; ++j) {
                AbstractShapes.Rectangle rect = new AbstractShapes.Rectangle(
                        new Point(Constants.WIDTH - 30 - width - 1 - j * (width + 1), common), width, height);
                blocks.add(new Block(rect, c[10 - i]));
            }
            common += height + 1;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}