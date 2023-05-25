package HitListener;

import Objects.Ball;
import Objects.Block;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * Listener.
 */
public interface HitListener {

    /**
     * Called when the hitter ball hit the beingHit object.<br>
     *
     * @param beingHit the block being hit.
     * @param hitter the ball hitter.
     * */
    void hitEvent(Block beingHit, Ball hitter);
}