package listeners;
import biuoop.DrawSurface;
import environment.Game;
import sprites.Sprite;
/**
 * Provides methods for displaying the score.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class ScoreIndicator implements Sprite {

    private Counter scoreCounter;

    /**
     * Ctor.
     * @param scoreCounter the score reference.
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    /**
     * displaying the score on the screen.
     * @param d the display.
     */
    @Override
    public void drawOn(DrawSurface d) {

        /* get the score as string */
        String score = Integer.toString(scoreCounter.getValue());

        /* display the score */
        d.drawText(400, 20, score, 15);
    }

    /**
     *
     */
    @Override
    public void timePassed() {

    }

    /**
     * adding this object to the game environment.
     * @param g the game instance.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
