//204058366
package collisions;
import geometry.Point;
/**
 * Provides instances of collisions detalis of collision that occures in game.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class CollisionInfo {

    /* initalize constants */
    public static final int HORIZONTAL_INTERSECT = 1;
    public static final int VERTICAL_INTERSECT = 2;
    public static final int NO_INTERSECT = 0;

    private Collidable collisionObject;
    private Point collisionPoint;

    /**
     * constructor.
     * @param collisionObject the object we collide with.
     * @param collisionPoint the closest point in which we collide.
     */
    public CollisionInfo(Collidable collisionObject, Point collisionPoint) {
        this.collisionObject = collisionObject;
        this.collisionPoint = collisionPoint;
    }

    /**
     * getter.
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * getter.
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }

}
