
package listeners;
import shapes.Ball;
import shapes.Block;

/**
 *  Provides interface for the listeners.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public interface HitListener {

    /**
     *  This method is called whenever the beingHit object is hit.
     * @param beingHit the block being hitted.
     * @param hitter the ball hitting the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
 }
