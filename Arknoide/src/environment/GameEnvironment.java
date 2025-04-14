package environment;
import java.util.ArrayList;
import java.util.List;

import collisions.Collidable;
import collisions.CollisionInfo;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
/**
 * Provides environment variable to the game with collidables array.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class GameEnvironment {

     private List<Collidable> collidables;

     /**
      * Ctor.
      */
     public GameEnvironment() {
          collidables = new ArrayList<Collidable>();
     }

     /**
      * add collidable object to collidables array.
      * @param c the collidable.
      */
     public void addCollidable(Collidable c) {
          collidables.add(c);
     }

     /**
      * remove a collidable from the game.
      * @param c the collidable to remove.
      */
     public void removeCollidable(Collidable c) {
          collidables.remove(c);
     }

     /**
      * return the information about the closest collision that is going
      * to occur, assuming an object moving from line.start() to line.end().
      * @param trajectory trajectory line.
      * @return collisionInfo if collision exsits null otherwise.
      */
     public CollisionInfo getClosestCollision(Line trajectory) {

          /* initialize array for each closest point its collidable */
          List<Point> intersections = new ArrayList<Point>();
          List<Collidable> collObjects = new ArrayList<Collidable>();
          Point closest = null;

          /* loop for each collidable */
          for (Collidable col : collidables) {

               /* get the rectangle object and its closest itersection point */
               Rectangle rect = col.getCollisionRectangle();
               closest = trajectory.closestIntersectionToStartOfLine(rect);

               /* if exists intersection add it to the array and
                * the collidable object to the collidables array.
                */
               if (closest != null) {
                    intersections.add(closest);
                    collObjects.add(col);
               }
          }

          /* return the collisionInfo object */
          return trajectory.closestCollisionInfo(intersections, collObjects);
     }
}
