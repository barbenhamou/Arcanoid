package Game;

import Levels.LevelInformation;
import Screens.EndScreen;
import Utils.Counter;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * The game continuous.
 */
public class GameFlow {
    private KeyboardSensor sensor;
    private Counter score;
    private Counter lives;
    private AnimationRunner runner;

    /**
     * Constructor.<br>
     *
     * @param sensor the keyboard input sensor.
     * @param runner the animation runner.
     */
    public GameFlow(KeyboardSensor sensor, AnimationRunner runner) {
        this.runner = runner;
        this.sensor = sensor;
        this.lives = new Counter(0);
        this.score = new Counter(0);
    }

    /**
     * Runs the levels.<br>
     *
     * @param levels the levels od the game.
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean won = true;
        for (LevelInformation levelInfo : levels) {
            lives.setValue(levelInfo.numberOfBalls());
            GameLevel level = new GameLevel(levelInfo, sensor, runner, score,
                    lives);
            level.initialize();
            level.run();
            if (level.lives() == 0) {
                won = false;
                break;
            }
        }
        if (!won) {
            this.runner.run(new EndScreen("Game Over.", score.getValue(),
                    sensor));
        } else {
            this.runner.run(new EndScreen("You Win!", score.getValue(),
                    sensor));
        }
    }


}