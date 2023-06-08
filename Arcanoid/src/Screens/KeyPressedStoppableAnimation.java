package Screens;

import Game.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * The pause screen activate.
 */
public class KeyPressedStoppableAnimation implements Animation {
    private String key;
    private KeyboardSensor sensor;
    private Animation animation;
    private boolean shouldStop;
    private boolean isPressed;

    /**
     * Constructor.<br>
     *
     * @param key       the key to stop.
     * @param sensor    the sensor from keyboard.
     * @param animation the animation to draw.
     */
    public KeyPressedStoppableAnimation(String key, KeyboardSensor sensor,
                                        Animation animation) {
        this.key = key;
        this.sensor = sensor;
        this.animation = animation;
        this.shouldStop = false;
        this.isPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (sensor.isPressed(key)) {
            shouldStop = !isPressed;
        } else {
            isPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return shouldStop || animation.shouldStop();
    }

    @Override
    public double framePerSecond() {
        return animation.framePerSecond();
    }
}