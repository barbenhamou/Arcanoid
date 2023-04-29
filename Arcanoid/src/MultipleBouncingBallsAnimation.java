import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.DrawSurface;

import java.awt.Color;
/**
 * Implementing the objects Ball and Velocity with graphics, creating nice bouncing balls with some
 * unique qualities.
 */
public class MultipleBouncingBallsAnimation {

    static final int WIDTH = 600, HEIGHT = 600, DELAY = 50, DEFAULT_SIZE = 150;

    /**
     * Name: drawAnimation.<br>
     * Action:<p>
     * Initialize the gui and the screen, than draw the ball and make him jump
     * </p>
     *
     * @param sizes - The array of the ball sizes.
     */
    private static void drawAnimations(int[] sizes) {
        GUI gui = new GUI("title", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();

        Ball[] balls = new Ball[sizes.length];
        Point start;

        //Creating the balls array with the necessary tests
        for (int i = 0; i < balls.length; ++i) {
            start = Point.generateRandomPoint(WIDTH, HEIGHT, 0, 0);
            balls[i] = new Ball(start, sizes[i], Color.BLACK);
            if (start.getX() <= balls[i].getSize()
                    || WIDTH - start.getX() <= balls[i].getSize()) {
                balls[i].setCenter(5 + balls[i].getSize(), balls[i].getY());
            }

            if (start.getY() <= balls[i].getSize()
                    || HEIGHT - start.getY() <= balls[i].getSize()) {
                balls[i].setCenter(balls[i].getX(), 5 + balls[i].getSize());
            }
        }

        //Giving each ball random speed according to the following rule -
        // the bigger the ball the slowest,
        // above 50 in size the speed is constant

        for (Ball ball : balls) {
            ball.adjustVelocity(3);
        }

        while (true) {
            DrawSurface d = gui.getDrawSurface();

            //Drawing each ball
            for (Ball ball : balls) {
                ball.moveOneStepHelper(WIDTH, HEIGHT, 0, 0);
                ball.drawOn(d);
            }

            gui.show(d);
            sleeper.sleepFor(DELAY);  // wait for 50 milliseconds.
        }
    }

    /**
     * The main function, calling and running the previous function,
     * receiving arguments.
     *
     * @param args - arguments from cmd
     */
    public static void main(String[] args) {
        int[] sizes = new int[args.length];
        for (int i = 0; i < args.length; ++i) {
            sizes[i] = Integer.parseInt(args[i]);
            if (sizes[i] <= 0
                    || sizes[i] >= WIDTH / 2 - 10) {
                sizes[i] = DEFAULT_SIZE;
            }
        }
        drawAnimations(sizes);
    }

}