package HitListener;

import Game.Game;
import Objects.Ball;
import Objects.Block;
import Utils.Counter;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * A listener for remove a block.
 */
public class BlockRemover implements HitListener{
    private Game game;
    private Counter remainedBlocks;

    /**
     * A constructor.<br>
     *
     * @param game the game object of the program.
     * @param remainedBlocks the number of blocks remained.
     * */
    public BlockRemover(Game game, Counter remainedBlocks) {
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
     * */
    public int getAmountRemained() {
        return remainedBlocks.getValue();
    }
}