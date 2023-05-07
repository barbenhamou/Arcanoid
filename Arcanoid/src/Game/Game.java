package Game;

import AbstractShapes.Point;
import AbstractShapes.Rectangle;
import Objects.Collidable;
import Objects.Sprite;
import Objects.Ball;
import Objects.Block;
import Objects.Paddle;
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
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;

    private static GUI gui;

    private static Sleeper sleeper;

    private static List<Color> colors;

    /**
     * Constructor.
     * */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    /**
     * Initialize the color list.
     * */
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
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        gui = new GUI("Game", Constants.WIDTH, Constants.HEIGHT);
        KeyboardSensor sensor = gui.getKeyboardSensor();

        //colors
        initialColors();

        //boundaries
        Rectangle up = new Rectangle(new Point(0, 0), Constants.WIDTH, Constants.BLOCK_THICKNESS);
        Rectangle down = new Rectangle(new Point(0, Constants.HEIGHT - Constants.BLOCK_THICKNESS),
                Constants.WIDTH, Constants.BLOCK_THICKNESS);
        Rectangle left = new Rectangle(new Point(0, Constants.BLOCK_THICKNESS),
                Constants.BLOCK_THICKNESS, Constants.HEIGHT - 2 * Constants.BLOCK_THICKNESS);
        Rectangle right = new Rectangle(new Point(Constants.WIDTH - Constants.BLOCK_THICKNESS,
                Constants.BLOCK_THICKNESS), Constants.BLOCK_THICKNESS,
                Constants.HEIGHT - 2 * Constants.BLOCK_THICKNESS);

        //background
        Rectangle back = new Rectangle(new Point(Constants.BLOCK_THICKNESS, Constants.BLOCK_THICKNESS),
                Constants.WIDTH - 2 * Constants.BLOCK_THICKNESS, Constants.HEIGHT - 2 * Constants.BLOCK_THICKNESS);

        Rectangle paddle = new Rectangle(new Point(370, 540), 60, 30);

        new Block(up, Color.gray).addToGame(this);
        new Block(down, Color.gray).addToGame(this);
        new Block(left, Color.gray).addToGame(this);
        new Block(right, Color.gray).addToGame(this);
        new Block(back, Color.BLUE).addToGame(this);
        new Paddle(sensor, paddle, Color.yellow, 5).addToGame(this);

        //ball1
        Ball ball1 = new Ball(new Point(400, 540), 10, Color.BLACK,
                environment);
        ball1.addToGame(this);
        ball1.setVelocity(5, 5);

        //ball2
        Ball ball2 = new Ball(new Point(140, 540), 10, Color.YELLOW,
                environment);
        ball2.addToGame(this);
        ball2.setVelocity(5, 5);

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
            }
            common += height + 1;

        }
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}