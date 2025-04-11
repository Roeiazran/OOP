//204058366
package listeners;
import environment.Game;
import shapes.Ball;
import shapes.Block;

/**
 * Provides methods for the BlockRemover whos is in charge of removing blocks
 * from the game, as well as keeping count of the number of blocks that remain.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class BlockRemover implements HitListener {

    private Game game;
    private Counter remainingBlocks;

    /**
     * Ctor.
     * @param game the game object.
     * @param remainingBlocks counter for the block in the game.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * removes block that being hitted from the game.
     * @param beingHit the block hitted.
     * @param hitter the hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {

        /* unregister the listerner to the block */
        beingHit.removeHitListener(this);

        /* decrease 1 from the block counter */
        remainingBlocks.decrease(1);

        /* chenge the ball color to the hitted block color */
        hitter.setColor(beingHit.getColor());

        /* remove the block instace from the game. */
        beingHit.removeFromGame(game);
    }
}