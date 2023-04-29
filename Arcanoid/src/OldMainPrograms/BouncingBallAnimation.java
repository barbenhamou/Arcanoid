package OldMainPrograms;

import Objects.Ball;
import AbstractShapes.Point;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.DrawSurface;

/**
 * Implementing the Objects.Ball and Utils.Velocity with graphics, creating nice
 * bouncing ball.
 */
public class BouncingBallAnimation {

    static final int WIDTH = 200, HEIGHT = 200, R = 30, DELAY = 50;
    /**
     * Name: drawAnimation.<br>
     * Action:<p>
     * Initialize the gui and the screen, than draw the ball and make him jump
     * </p>
     *
     * @param start - AbstractShapes.Point of beginning.
     * @param dx    - x-axis speed.
     * @param dy    - y-axis speed.
     */
    private static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(new Point(start.getX(), start.getY()), R,
                java.awt.Color.BLACK);

        //These testings are In case where a part of the ball is beyond the screen
        //which can lead to program crash or stuck at certain point.
        if (start.getX() <= ball.getSize() || WIDTH - start.getX() <= ball.getSize()) {
            ball.setCenter(5 + ball.getSize(), ball.getY());
        }

        if (start.getY() <= ball.getSize() || HEIGHT - start.getY() <= ball.getSize()) {
            ball.setCenter(ball.getX(), 5 + ball.getSize());
        }

        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStepHelper(WIDTH, HEIGHT, 0, 0);
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(DELAY);  // wait for 50 milliseconds.
        }
    }

    /**
     * The main function, calling and running the previous function,
     * receiving arguments.
     * @param args - arguments from cmd
     */
    public static void main(String[] args) {
        drawAnimation(new Point(Double.parseDouble(args[0]),
                        Double.parseDouble(args[1])), Double.parseDouble(args[2]),
                Double.parseDouble(args[3]));
    }
}