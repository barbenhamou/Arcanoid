import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * Sort of tester class, GUI class, all the graphics of geometry.
 */
public class AbstractArtDrawing {

    static final int WIDTH = 400, HEIGHT = 300, AMOUNT = 10, R = 3;

    /**
     * Name: lineDrawingFrame<br>
     * Action :<p>
     * Initialize screen, drawing 10 lines,
     * color their middle points and their intersection points.
     * Using the biuoop library.
     * </p>
     */
    public void lineDrawingFrame() {
        Random rand = new Random();
        Line[] lines = new Line[AMOUNT];
        GUI screen = new GUI("Line Drawing Frame", WIDTH, HEIGHT);
        DrawSurface tool = screen.getDrawSurface();
        int x1, y1, x2, y2;
        Point middle, intersection;

        for (int i = 0; i < 10; ++i) {
            x1 = rand.nextInt(WIDTH) + 1; // get "integer" in range 1-800
            x2 = rand.nextInt(WIDTH) + 1; // get "integer" in range 1-800
            y2 = rand.nextInt(HEIGHT) + 1; // get "integer" in range 1-800
            y1 = rand.nextInt(HEIGHT) + 1; // get "integer" in range 1-800
            lines[i] = new Line(x1, y1, x2, y2);

            tool.setColor(Color.BLACK);
            tool.drawLine(x1, y1, x2, y2);

            tool.setColor(Color.BLUE);
            middle = lines[i].middle();

            tool.fillCircle((int) (Math.round(middle.getX())),
                    (int) (Math.round(middle.getY())), R);
        }

        for (Line line : lines) {
            for (Line line1 : lines) {
                intersection = line.intersectionWith(line1);
                if (intersection == null || line.equals(line1)) {
                    continue;
                }
                tool.setColor(Color.RED);
                tool.fillCircle((int) (Math.round(intersection.getX())),
                        (int) (Math.round(intersection.getY())), R);
            }
        }
        screen.show(tool);
    }

    /**
     * Running the program and calling the function that initializes everything.
     * @param args - the cmd arguments
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.lineDrawingFrame();
    }
}