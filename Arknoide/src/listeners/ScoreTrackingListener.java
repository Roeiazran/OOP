
package listeners;
import shapes.Ball;
import shapes.Block;

/**
 * Provides methods for the ScoreTracking listener whos is in charge of
 * the game score.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    /**
     * Ctor.
     * @param scoreCounter the game score variable.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
       this.currentScore = scoreCounter;
    }

    /**
     * @param beingHit the block hitted.
     * @param hitter the ball hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {

        /* increse the score by the block worth points */
        currentScore.increase(5);
    }

}
