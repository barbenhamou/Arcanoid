package HitListener;

import Game.Game;
import Objects.Ball;
import Objects.Block;
import Utils.Counter;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * A listener for remove a ball.
 */
public class BallRemover implements HitListener{
    private Game game;
    private Counter remainedBalls;

    /**
     * A constructor.<br>
     *
     * @param game the game object of the program.
     * @param remainedBalls the number of blocks remained.
     * */
    public BallRemover(Game game, Counter remainedBalls) {
        this.game = game;
        this.remainedBalls = remainedBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainedBalls.decrease(1);
    }

    /**
     * Getter for the remained balls num.
     * */
    public int getAmountRemained() {
        return remainedBalls.getValue();
    }
}