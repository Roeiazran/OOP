// 204058366 Roei Azran
package sprites;
import biuoop.DrawSurface;
/**
 * Provides interface for the sprites objects (displaying object) in the game.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public interface Sprite {

   /**
    * method to draw the object.
    * @param d the surface.
    */
   void drawOn(DrawSurface d);

   /**
    * method to notify the object every time interval.
    */
   void timePassed();
}
