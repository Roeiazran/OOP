package listeners;
import environment.Game;
import shapes.Ball;
import shapes.Block;

/**
 * Provides methods for the Ballremover whos is in charge of removing balls
 * from the game, as well as keeping count of the number of balls that remain.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class BallRemover implements HitListener {

    private Counter remainingBalls;
    private Game game;

    /**
     * contructor.
     * @param game the game object.
     * @param remainingBalls the counter of the remaining balls.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * removes balls that being hitting the buttom screen.
     * @param beingHit the block hitted.
     * @param hitter the hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {

        /* remove the ball from the game */
        hitter.removeFromGame(game);

        /* decrease the ball counter by one */
        remainingBalls.decrease(1);
    }

}
