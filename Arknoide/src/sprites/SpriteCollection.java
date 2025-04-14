
package sprites;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
/**
 * Provides methods on object apeering on the screen.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class SpriteCollection {

    private List<Sprite> sprites = new ArrayList<Sprite>();

    /**
     * adds sprite object to the sprites.
     * @param s the sprite to add.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * removes sprite from the game.
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * call timePassed on all sprites.
     */
    public void notifyAllTimePassed() {

        /* the list can't change from timePassed so no adding or removing
         * objects from sprites if calls upon list, so we convert it to
         * array first.
         */
        Sprite[] spritesArray = sprites.toArray(new Sprite[0]);
        for (Sprite sprite: spritesArray) {
            sprite.timePassed();
        }
    }

    /**
     * call drawOn on all sprites.
     * @param d the surface variable.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite: sprites) {
            sprite.drawOn(d);
        }
    }

}
