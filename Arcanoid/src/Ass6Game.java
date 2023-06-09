import Game.AnimationRunner;
import Game.GameFlow;
import Game.GameLevel;
import Levels.Green3;
import Levels.LevelInformation;
import Utils.Constants;
import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;

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
        GUI gui = new GUI("Arcanoid", Constants.WIDTH, Constants.HEIGHT);
        AnimationRunner runner = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(gui.getKeyboardSensor(), runner);
        LevelInformation[] levelTypes = {new Green3()};
        List<LevelInformation> levels = new ArrayList<>();
        for (String s: a) {
            try {
                int number = Integer.parseInt(s);
                levels.add(levelTypes[number - 1]);
            } catch (NumberFormatException | IndexOutOfBoundsException ignore2) {}
        }
        gameFlow.runLevels(levels);
        gui.close();
    }
}