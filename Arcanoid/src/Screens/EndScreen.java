package Screens;

import Game.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class EndScreen implements Animation {
    private String status;
    private int score;

    private KeyboardSensor sensor;

    /**
     * Constructor.<br>
     *
     * @param score  the score at the end.
     * @param status what cause.
     */
    public EndScreen(String status, int score, KeyboardSensor sensor) {
        this.status = status;
        this.score = score;
        this.sensor = sensor;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(d.getWidth() / 5, d.getHeight() / 2, status+" Your score " +
                "is "+score, 32);
    }

    @Override
    public boolean shouldStop() {
        return sensor.isPressed(KeyboardSensor.SPACE_KEY);
    }

    @Override
    public double framePerSecond() {
        return 60;
    }
}