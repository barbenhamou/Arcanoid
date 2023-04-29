import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * Implementing the objects Ball and Velocity with graphics, creating two
 * surfaces in different colors that have some bouncing ball within their limits.
 */
public class MultipleFramesBouncingBallsAnimation {

    static final int WIDTH = 800, HEIGHT = 600, DELAY = 50,
            GRAY_DEFAULT_SIZE = 150, YELLOW_DEFAULT_SIZE = 35;
    static final int[] GRAY_SCREEN = {50, 500}, YELLOW_SCREEN = {450, 600};
    static final Color[] COLORS = {Color.cyan, Color.BLUE, Color.BLACK, Color.red,
            Color.orange, Color.GREEN, Color.pink};
    static final Random RANDOM = new Random();

    /**
     * @return a random color.
     */
    public static Color generateRandomColor() {
        int i = RANDOM.nextInt(COLORS.length);
        return COLORS[i];
    }

    /**
     * Name: draw.<br>
     * Action:<p>
     * Initialize the gui and the screen, than draw the ball and make him jump
     * </p>
     *
     * @param sizes - the array of the radius's of the balls
     */
    public static void draw(int[] sizes) {
        GUI gui = new GUI("title", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();

        Ball[] balls = new Ball[sizes.length];
        int r;
        for (int i = 0; i < balls.length / 2; ++i) {
            r = sizes[i];
            balls[i] = new Ball(Point.generateRandomPoint(GRAY_SCREEN[1] - r,
                    GRAY_SCREEN[1] - r,
                    GRAY_SCREEN[0] + r,
                    GRAY_SCREEN[0] + r), r,
                    generateRandomColor());
        }

        for (int i = balls.length / 2; i < balls.length; ++i) {
            r = sizes[i];
            balls[i] = new Ball(Point.generateRandomPoint(YELLOW_SCREEN[1] - r,
                    YELLOW_SCREEN[1] - r,
                    YELLOW_SCREEN[0] + r,
                    YELLOW_SCREEN[0] + r), r,
                    generateRandomColor());
        }

        //Creating the balls array with the necessary tests
        for (int i = 0; i < balls.length / 2; ++i) {
            balls[i].fixCenter(GRAY_SCREEN[1], GRAY_SCREEN[0]);
        }

        for (int i = balls.length / 2; i < balls.length; ++i) {
            balls[i].fixCenter(YELLOW_SCREEN[1], YELLOW_SCREEN[0]);
        }

        //Giving each ball random speed

        for (Ball ball : balls) {
            ball.adjustVelocity(4);
        }

        while (true) {
            DrawSurface d = gui.getDrawSurface();

            d.setColor(Color.gray);
            d.fillRectangle(GRAY_SCREEN[0], GRAY_SCREEN[0],
                    GRAY_SCREEN[1] - GRAY_SCREEN[0],
                    GRAY_SCREEN[1] - GRAY_SCREEN[0]);

            //Drawing each ball
            for (int i = 0; i < balls.length / 2; ++i) {
                balls[i].moveOneStepHelper(GRAY_SCREEN[1], GRAY_SCREEN[1],
                        GRAY_SCREEN[0], GRAY_SCREEN[0]);
                d.setColor(balls[i].getColor());
                balls[i].drawOn(d);
            }

            d.setColor(Color.yellow);
            d.fillRectangle(YELLOW_SCREEN[0], YELLOW_SCREEN[0],
                    YELLOW_SCREEN[1] - YELLOW_SCREEN[0],
                    YELLOW_SCREEN[1] - YELLOW_SCREEN[0]);

            for (int i = balls.length / 2; i < balls.length; ++i) {
                balls[i].moveOneStepHelper(YELLOW_SCREEN[1], YELLOW_SCREEN[1],
                        YELLOW_SCREEN[0], YELLOW_SCREEN[0]);
                d.setColor(balls[i].getColor());
                balls[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(DELAY);  // wait for 50 milliseconds.
        }
    }

    /**
     * The main function, calling and running the previous function,
     * receiving arguments.
     *
     * @param args - args from cmd
     */
    public static void main(String[] args) {
        int[] sizes = new int[args.length];
        for (int i = 0; i < args.length / 2; ++i) {
            sizes[i] = Integer.parseInt(args[i]);
            if (sizes[i] <= 0
                    || sizes[i] >= (GRAY_SCREEN[1] - GRAY_SCREEN[0]) / 2 - 10) {
                sizes[i] = GRAY_DEFAULT_SIZE;
            }
        }

        for (int i = args.length / 2; i < args.length; ++i) {
            sizes[i] = Integer.parseInt(args[i]);
            if (sizes[i] <= 0
                    || sizes[i] >= (YELLOW_SCREEN[1] - YELLOW_SCREEN[0]) / 2 - 10) {
                sizes[i] = YELLOW_DEFAULT_SIZE;
            }
        }
        draw(sizes);
    }
}