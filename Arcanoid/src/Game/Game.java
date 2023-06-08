package Game;

import AbstractShapes.Point;
import AbstractShapes.Rectangle;
import HitListener.BallRemover;
import HitListener.BlockRemover;
import HitListener.Indicator;
import HitListener.ScoreTrackingListener;
import Objects.Collidable;
import Objects.Sprite;
import Objects.Ball;
import Objects.Block;
import Objects.Paddle;
import Screens.CountDownAnimation;
import Screens.EndScreen;
import Screens.KeyPressedStoppableAnimation;
import Screens.PauseScreen;
import Utils.Counter;
import biuoop.DrawSurface;
import biuoop.GUI;
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
public class Game implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;

    private BlockRemover blockRemover;

    private BallRemover ballRemover;

    private ScoreTrackingListener scoreTrackingListener;

    private Counter blocksCounter;

    private Counter lives;

    private Counter score;

    private GUI gui;

    private Sleeper sleeper;

    private List<Color> colors;

    private boolean running;

    private AnimationRunner runner;

    private KeyboardSensor sensor;

    /**
     * Constructor.
     */
    public Game() {
        this.gui = new GUI("Game", Constants.WIDTH, Constants.HEIGHT);
        this.sensor = gui.getKeyboardSensor();
        this.sleeper = new Sleeper();
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksCounter = new Counter(Constants.INITIAL_NUM_BLOCKS);
        this.blockRemover = new BlockRemover(this, blocksCounter);
        this.lives = new Counter(Constants.INITIAL_NUM_BALLS);
        this.ballRemover = new BallRemover(this, lives);
        this.score = new Counter(0);
        this.scoreTrackingListener = new ScoreTrackingListener(score);
        this.running = true;
        this.runner = new AnimationRunner(gui);
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
        //ball1
        Ball ball1 = new Ball(new Point(400, 480), Constants.R, Color.BLACK,
                environment);
        ball1.addToGame(this);
        ball1.setVelocity(Constants.BALL_SPEED[0], Constants.BALL_SPEED[1]);

        //ball2
        Ball ball2 = new Ball(new Point(360, 480), Constants.R, Color.YELLOW,
                environment);
        ball2.addToGame(this);
        ball2.setVelocity(Constants.BALL_SPEED[0], Constants.BALL_SPEED[1]);

        //ball3
        Ball ball3 = new Ball(new Point(350, 480), Constants.R, Color.RED,
                environment);
        ball3.addToGame(this);
        ball3.setVelocity(Constants.BALL_SPEED[0], Constants.BALL_SPEED[1]);
    }

    public void initialize() {
        //colors
        initialColors();

        //boundaries and death
        addBoundariesAndDeath();

        //background - rectangle
        Rectangle back = new Rectangle(new Point(Constants.BLOCK_THICKNESS,
                2 * Constants.BLOCK_THICKNESS),
                Constants.WIDTH - 2 * Constants.BLOCK_THICKNESS,
                Constants.HEIGHT - 3 * Constants.BLOCK_THICKNESS);
        new Block(back, Color.BLUE).addToGame(this);

        //paddle - rectangle
        Rectangle paddle = new Rectangle(new Point(370, 500), 120, 15);
        new Paddle(sensor, paddle, Color.yellow, Constants.PADDLE_SPEED).addToGame(this);

        //Score
        Indicator scoreIndicator = new Indicator(score, Constants.X_SCORE, "SCORE");
        addSprite(scoreIndicator);

        Color c;
        //blocks
        int common = 150, width = 50, height = 30;
        for (int i = 12; i > 6; --i) {
            c = colors.remove(0);
            for (int j = 0; j < i; ++j) {
                Rectangle rect = new Rectangle(
                        new Point(Constants.WIDTH - 30 - width - 1 - j * (width + 1), common), width, height);
                Block block = new Block(rect, c);
                block.addToGame(this);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackingListener);
            }
            common += height + 1;

        }
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountDownAnimation(3, 3, sprites));
        createBalls();
        this.runner.run(this);
        if (lives.getValue() == 0) {
            this.runner.run(new EndScreen("Game Over.", score.getValue(),
                            sensor));
        } else {
            this.runner.run(new EndScreen("You Win!", score.getValue(),
                    sensor));
        }
        gui.close();
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
                    new PauseScreen()));
        }
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