//204058366
package shapes;
import biuoop.DrawSurface;
import collisions.CollisionInfo;
import collisions.Velocity;
import environment.Game;
import environment.GameEnvironment;
import geometry.Line;
import geometry.Point;
import sprites.Sprite;
/**
 *  Provides methods to displays balls and rectangle on the screen.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class Ball implements Sprite {

    /* intialize constants */
    public static final double COLL_DISTANCE = 10;

    /* initialize class fields */
    private Point center;
    private int r;
    private java.awt.Color color;

    private Velocity velocity;
    private Line trajectory;
    private GameEnvironment gameEnvironment;

    /**
     * Constructor.
     * @param center the center of the ball.
     * @param r the radius of the ball.
     * @param color the color of the ball.
     * @param gameEnvironment the Game game environment.
     */
    public Ball(Point center, int r, java.awt.Color color,
        GameEnvironment gameEnvironment) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * setter for the valocity variable.
     * @param v the new valocity.
     */
    public void setVelocity(Velocity v) {
        velocity = v;
    }

    /**
     * another setter to the valocity.
     * @param dx the x distance.
     * @param dy the y distance.
     */
    public void setVelocity(double dx, double dy) {

        /* create and assign new velocity instace */
        velocity = new Velocity(dx, dy);
    }

    /**
     * getter to the velocity mathod.
     * @return the velocity.
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * updating the trajactory line.
     */
    private void updateTrajectory() {

        /* calculate its end point and update it */
        Point end = new Point(center.getX() +  velocity.getDx(),
            center.getY() - velocity.getDy());

        trajectory = new Line(center, end);
    }

    /**
     * method to chenge object direction when exisiting the screen borders.
     */
    public void moveOneStep() {
        CollisionInfo info;
        double distanceFromCollision;

        /* update the trajectory line on the move */
        updateTrajectory();

        /* get the collision info object */
        info = gameEnvironment.getClosestCollision(trajectory);

        /* check if there is a collision */
        if (info != null) {
            /* assuming ball hits the surface if its center hits it,
             * thus check the collision distance from the center point.
             */
            distanceFromCollision = info.collisionPoint().distance(center);

            /* check if we too close */
            if (distanceFromCollision <= COLL_DISTANCE) {

                /* get the velocity the veloicty to  */
                Velocity v = info.collisionObject()
                    .hit(this, info.collisionPoint(), velocity);

                /* chenge the ball velocity */
                setVelocity(v);
            }
        }
        center = velocity.applyToPoint(center);
    }

    /**
     *  center x getter.
     * @return center x coordinate.
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     *  center y getter.
     * @return center y coordinate.
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * radius getter.
     * @return the ball radius.
     */
    public int getSize() {
        return r;
    }

    /**
     * the center point getter.
     * @return the center point.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * the center point setter.
     * @param center
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * color getter.
     * @return the ball color.
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * method to draw the ball on the display.
     * @param surface the surface vairable.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        int x = (int) getX();
        int y = (int) getY();

        surface.setColor(color);
        surface.fillCircle(x, y, r);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * add to game this.
     * @param g the game instance.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * remove from game.
     * @param g the game instance.
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }

    /**
     * getter.
     * @return trajectory line;
     */
    public Line getTrajectory() {
        return trajectory;
    }

    /**
     * getter.
     * @return gameEnvironment;
     */
    public GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }

    /**
     * setter.
     * @param color the new color.
     */
    public void setColor(java.awt.Color color) {
        this.color = color;
    }
}