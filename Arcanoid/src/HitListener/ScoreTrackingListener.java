package HitListener;

import Objects.Ball;
import Objects.Block;
import Utils.Constants;
import Utils.Counter;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * A listener for updating the score.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * A constructor.<br>
     *
     * @param scoreCounter the counter of points in game.
     * */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(Constants.SCORE_PER_HIT);
    }

    /**
     * Getter for the remained block num.
     * */
    public int getAmountRemained() {
        return currentScore.getValue();
    }
}