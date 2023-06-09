package Levels;

import Objects.Block;
import Objects.Sprite;
import Utils.Velocity;

import java.util.List;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * A level.
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 0;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return null;
    }

    @Override
    public int paddleSpeed() {
        return 0;
    }

    @Override
    public int paddleWidth() {
        return 0;
    }

    @Override
    public String levelName() {
        return null;
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public List<Block> blocks() {
        return null;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 0;
    }
}