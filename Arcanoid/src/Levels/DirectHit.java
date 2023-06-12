package Levels;

import AbstractShapes.Point;
import AbstractShapes.Rectangle;
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
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(0, 5));
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
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new DirectHitBackGround();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(new Rectangle(new Point(360, 240), 20, 20),
                Color.red));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public List<Point> centers() {
        List<Point> centers = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); ++i) {
            centers.add(new Point(370, 500));
        }
        return centers;
    }

    @Override
    public Point paddle() {
        return new Point(340, 500);
    }

    @Override
    public Point death() {
        return new Point(0, Constants.Y_DEATH_RANGE);
    }

    @Override
    public int deathHeight() {
        return Constants.BLOCK_THICKNESS;
    }
}