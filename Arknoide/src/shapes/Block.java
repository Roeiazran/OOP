
package shapes;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import collisions.Collidable;
import collisions.CollisionInfo;
import collisions.Velocity;
import environment.Game;
import geometry.Point;
import geometry.Rectangle;
import listeners.HitListener;
import listeners.HitNotifier;
import sprites.Sprite;
/**
 * Provides methods and instanciation of blocks.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle rect;
    private Color color;

    private List<HitListener> hitListeners;
    /**
     * constructor.
     * @param rect the recatangle describing the block.
     * @param color the block color.
     */
    public Block(Rectangle rect, Color color) {
        this.hitListeners = new ArrayList<HitListener>();
        this.rect = rect;
        this.color = color;
    }

    /**
     * getter.
     * @return this rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    /**
     * calcuate and return new velocity for the ball hitting this block.
     * @param collisionPoint the closest collission point.
     * @param currentVelocity the current velocity of the ball.
     * @param hitter the ball hitter.
     * @return new velocity object.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
        Velocity currentVelocity) {

        /* check if the color not match */
        if (!ballColorMatch(hitter)) {
                notifyHit(hitter);
        }

        /* find on which tzela of rect the collision point is */
        int ballState = rect.getSpecificIntersectionLine(collisionPoint);

        /* check for horizontal and vertical */
        switch (ballState) {
            case CollisionInfo.HORIZONTAL_INTERSECT:
                return new Velocity(-currentVelocity.getDx(),
                    currentVelocity.getDy());
            case CollisionInfo.VERTICAL_INTERSECT:
                return new Velocity(currentVelocity.getDx(),
                    -currentVelocity.getDy());
            default:
                break;
        }

        return null;
    }

    /**
     * method to draw rectangle on the display.
     * @param surface the surface.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        int upLeftX = (int) rect.getUpperLeft().getX();
        int upLeftY = (int) rect.getUpperLeft().getY();
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();
        surface.fillRectangle(upLeftX, upLeftY, width, height);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * adds this block to the game.
     * @param g
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * notifying all the listerners that a hit accured.
     * @param hitter the ball hitting this block.
     */
    private void notifyHit(Ball hitter) {

        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners =
            new ArrayList<HitListener>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * checks if the object hitting this block has the same color as this.
     * @param ball the hitting ball.
     * @return true if it has the same color and false otherwise.
     */
    public boolean ballColorMatch(Ball ball) {
        if (color.equals(ball.getColor())) {
            return true;
        }
        return false;
    }

    /**
     * adds the listener registered to this block.
     * @param hl the listener.
     */
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * removes the listener registered to this block.
     * @param hl the listener.
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * removes this block from the game.
     * @param game the game object.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * getter.
     * @return the color.
     */
    public Color getColor() {
        return color;
    }

}
