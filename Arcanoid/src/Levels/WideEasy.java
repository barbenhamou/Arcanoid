package Levels;

import AbstractShapes.Point;
import AbstractShapes.Rectangle;
import Objects.Block;
import Objects.Sprite;
import Utils.Constants;
import Utils.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * A level.
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            velocities.add(Velocity.fromAngleAndSpeed(-75 + 15 * (i + 1), 5));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return (int) (0.85 * (Constants.WIDTH - 2 * Constants.BLOCK_THICKNESS));
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new WideEasyBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color c;
        for (int i = 0; i < 15; ++i) {
            if (14 - i < 2) {
                c = Color.cyan;
            } else if (14 - i < 4) {
                c = Color.pink;
            } else if (14 - i < 6) {
                c = Color.BLUE;
            } else if (14 - i < 9) {
                c = Color.green;
            } else if (14 - i < 11) {
                c = Color.yellow;
            } else if (14 - i < 13) {
                c = Color.orange;
            } else {
                c = Color.red;
            }
            blocks.add(new Block(new Rectangle(new Point(30 + 49.333 * i, 250),
                    49.333, 25),
                    c));
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

    @Override
    public List<Point> centers() {
        List<Point> centers = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); ++i) {
            centers.add(new Point(400, 522));
        }
        return centers;
    }

    @Override
    public Point paddle() {
        return new Point(0.075 * (Constants.WIDTH - 2 * Constants.BLOCK_THICKNESS), 530);
    }

    @Override
    public Point death() {
        return new Point(0, 545);
    }

    @Override
    public int deathHeight() {
        return 10;
    }
}