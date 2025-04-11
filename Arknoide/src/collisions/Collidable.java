//204058366
package collisions;
import geometry.Point;
import geometry.Rectangle;
import shapes.Ball;
/**
 * Provides interface for collidables objects in the game.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public interface Collidable {

    /* intialize constants */
    int HORIZONTAL_INTERSECT = 1;
    int VERTICAL_INTERSECT = 2;
    int NO_INTERSECTION = 0;

    /**
     * return the collision shape of the object.
     * @return the collision shape.
     */
    Rectangle getCollisionRectangle();

    /**
     * notify the object that we collide with it at collisionPoint with
     * a given velocity.
     * @param collisionPoint the collision point he hit us.
     * @param currentVelocity the velocity he hit us.
     * @param hitter the ball hittiing.
     * @return the velocity the hitting object should have.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
