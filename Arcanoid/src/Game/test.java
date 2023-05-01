package Game;

import AbstractShapes.Point;
import AbstractShapes.Rectangle;
import Objects.Ball;
import Objects.Block;
import biuoop.*;

import java.awt.*;

public class test {
    public static void main(String[] a) {
        Sleeper sleeper = new Sleeper();
        GUI gui = new GUI("test", 600, 600);
        GameEnvironment game = new GameEnvironment();
        Rectangle up = new Rectangle(new Point(0, 0), 600, 50);
        Rectangle down = new Rectangle(new Point(0, 550), 600, 50);
        Rectangle left = new Rectangle(new Point(0, 50), 50, 500);
        Rectangle right = new Rectangle(new Point(550, 50), 50, 500);
        game.addCollidable(new Block(up, Color.BLACK));
        game.addCollidable(new Block(down, Color.RED));
        game.addCollidable(new Block(left, Color.BLUE));
        game.addCollidable(new Block(right, Color.YELLOW));
        Ball ball = new Ball(new Point(300, 300), 5, Color.cyan, game);
        ball.setVelocity(3, 4);

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            game.getCollidables().stream().filter(Block.class::isInstance).forEach(x -> x.draw(d));
            ball.moveOneStep();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);
        }
    }
}