import Game.GameLevel;
import Levels.Green3;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * The game program.
 * */
public class Ass6Game {
    /**
     * The main program.<br>
     * @param a arguments from main.
     * */
    public static void main(String[] a) {
        GameLevel game = new GameLevel(new Green3());
        game.initialize();
        game.run();
    }
}