package HitListener;

import Game.GameLevel;
import Objects.Ball;
import Objects.Block;
import Utils.Counter;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * A listener for remove a block.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainedBlocks;

    /**
     * A constructor.<br>
     *
     * @param game           the game object of the program.
     * @param remainedBlocks the number of blocks remained.
     */
    public BlockRemover(GameLevel game, Counter remainedBlocks) {
        this.game = game;
        this.remainedBlocks = remainedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        remainedBlocks.decrease(1);
    }

    /**
     * Getter for the remained block num.
     *
     * @return the amount of blocks left.
     */
    public int getAmountRemained() {
        return remainedBlocks.getValue();
    }
}