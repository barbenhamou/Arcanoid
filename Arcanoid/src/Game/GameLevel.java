package Game;

import AbstractShapes.Point;
import AbstractShapes.Rectangle;
import HitListener.*;
import Levels.LevelInformation;
import Objects.Collidable;
import Objects.Sprite;
import Objects.Ball;
import Objects.Block;
import Objects.Paddle;
import Screens.CountDownAnimation;
import Screens.KeyPressedStoppableAnimation;
import Screens.PauseScreen;
import Utils.Counter;
import Utils.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import Utils.Constants;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * The game main program.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;

    private BlockRemover blockRemover;

    private BallRemover ballRemover;

    private ScoreTrackingListener scoreTrackingListener;

    private LevelInformation currentLevel;

    private Counter blocksCounter;

    private Counter lives;

    private Counter score;

    private Sleeper sleeper;

    private List<Color> colors;

    private boolean running;

    private AnimationRunner runner;

    private KeyboardSensor sensor;

    /**
     * Constructor.
     */
    public GameLevel(LevelInformation currentLevel, KeyboardSensor sensor,
                     AnimationRunner runner, Counter score, Counter lives) {
        this.sensor = sensor;
        this.sleeper = new Sleeper();
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.currentLevel = currentLevel;
        this.blocksCounter = new Counter(currentLevel.numberOfBlocksToRemove());
        this.blockRemover = new BlockRemover(this, blocksCounter);
        this.lives = lives;
        this.ballRemover = new BallRemover(this, lives);
        this.score = score;
        this.scoreTrackingListener = new ScoreTrackingListener(score);
        this.running = true;
        this.runner = runner;
    }

    /**
     * Initialize the color list.
     */
    public void initialColors() {
        colors = new ArrayList<>();
        colors.add(Color.green);
        colors.add(Color.pink);
        colors.add(Color.blue);
        colors.add(Color.yellow);
        colors.add(Color.red);
        colors.add(Color.gray);
    }

    /**
     * Adding new collidable to the game environment.
     *
     * @param c the new collidable.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add sprite to the list of all sprites.<br>
     *
     * @param s the wanted sprite.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Removing a collidable from the game environment.
     *
     * @param c the collidable.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Removing a sprite from the game environment.
     *
     * @param s the sprite.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */

    public void addBoundariesAndDeath() {
        //death block
        Rectangle death = new Rectangle(new Point(0, Constants.Y_DEATH_RANGE),
                Constants.WIDTH - 2 * Constants.BLOCK_THICKNESS,
                Constants.BLOCK_THICKNESS);
        Block deathBlock = new Block(death, Color.blue);
        deathBlock.addToGame(this);
        deathBlock.addHitListener(ballRemover);

        //gray boundaries
        Rectangle up = new Rectangle(new Point(Constants.BLOCK_THICKNESS,
                Constants.BLOCK_THICKNESS), Constants.WIDTH - Constants.BLOCK_THICKNESS,
                Constants.BLOCK_THICKNESS);
        Rectangle down = new Rectangle(new Point(0, Constants.HEIGHT - Constants.BLOCK_THICKNESS),
                Constants.WIDTH, Constants.BLOCK_THICKNESS);
        Rectangle left = new Rectangle(new Point(0, Constants.BLOCK_THICKNESS),
                Constants.BLOCK_THICKNESS, Constants.HEIGHT - 2 * Constants.BLOCK_THICKNESS);
        Rectangle right = new Rectangle(new Point(Constants.WIDTH - Constants.BLOCK_THICKNESS,
                Constants.BLOCK_THICKNESS), Constants.BLOCK_THICKNESS,
                Constants.HEIGHT - 2 * Constants.BLOCK_THICKNESS);

        new Block(up, Color.gray).addToGame(this);
        new Block(down, Color.gray).addToGame(this);
        new Block(left, Color.gray).addToGame(this);
        new Block(right, Color.gray).addToGame(this);
    }

    public void createBalls() {
        List<Velocity> velocities = currentLevel.initialBallVelocities();
        List<Ball> balls = new ArrayList<>();
        for (int i = 0; i < currentLevel.numberOfBalls(); ++i) {
            balls.add(new Ball(new Point(370, 480), Constants.R, Color.WHITE,
                    environment));
        }
        for (int i = 0; i < currentLevel.numberOfBalls(); ++i) {
            balls.get(i).setVelocity(velocities.get(i));
            balls.get(i).addToGame(this);
        }
    }

    public void initialize() {
        //colors
        initialColors();

        //boundaries and death
        addBoundariesAndDeath();

        addSprite(currentLevel.getBackground());

        //paddle - rectangle
        Rectangle paddle = new Rectangle(new Point(340, 500),
                currentLevel.paddleWidth(),
                15);
        new Paddle(sensor, paddle, Color.yellow, currentLevel.paddleSpeed()).addToGame(this);

        //Score
        Indicator scoreIndicator = new Indicator(score, Constants.X_SCORE, "SCORE");
        addSprite(scoreIndicator);

        //Lives
        Indicator livesIndicator = new Indicator(lives, Constants.X_LIVES,
                "LIVES");
        addSprite(livesIndicator);

        //Level name
        Label levelNameLabel = new Label(currentLevel.levelName(),
                Constants.X_NAME, "LEVEL NAME");
        addSprite(levelNameLabel);

        List<Block> blocks = currentLevel.blocks();
        for (Block block: blocks) {
            if (!block.getColor().equals(Color.gray)) {
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackingListener);
            }
            block.addToGame(this);
        }
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountDownAnimation(3, 3, sprites));
        createBalls();
        this.runner.run(this);

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (blockRemover.getAmountRemained() == 0) {
            score.increase(100);
            this.running = false;
        }

        if (ballRemover.getAmountRemained() == 0) {
            this.running = false;
        }

        if (sensor.isPressed("p")) {
            runner.run(new KeyPressedStoppableAnimation("k", sensor,
                    new PauseScreen(sprites)));
        }
    }

    /**
     * @return amount of balls remained.
     * */
    public int lives() {
        return lives.getValue();
    }

    /**
     * @return amount of blocks remained.
     * */
    public int blocksNum() {
        return blocksCounter.getValue();
    }
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public double framePerSecond() {
        return 60;
    }
}