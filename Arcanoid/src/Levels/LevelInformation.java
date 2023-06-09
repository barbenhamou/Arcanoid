package Levels;

import Objects.Block;
import Objects.Sprite;
import Utils.Velocity;

import java.util.List;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * The level model.
 * */
public interface LevelInformation {
    /**
     * @return the amount of balls in level.
     * */
    int numberOfBalls();

    /**
     * @return a list of the balls velocities.
     * */
    List<Velocity> initialBallVelocities();

    /**
     * @return the paddle's speed.
     * */
    int paddleSpeed();


    /**
     * @return the paddle's width.
     * */
    int paddleWidth();

    /**
     * @return level's name.
     * */
    String levelName();

    /**
     * @return a background sprite.
     * */
    Sprite getBackground();

    /**
     * @return all the blocks in the game prepared.
     * */
    // The Blocks that make up this level, each block contains
    // its size, color and location.
    List<Block> blocks();

    /**
     * @return the number of blocks that considered clear.
     * */
    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    int numberOfBlocksToRemove();
}