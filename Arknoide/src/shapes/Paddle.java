
package shapes;
import biuoop.DrawSurface;
import collisions.Collidable;
import collisions.Velocity;
import environment.Consts;
import environment.Game;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;
/**
 * Provides method to move and handle the paddle.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class Paddle implements Sprite, Collidable {

    /* initalize constants for circular paddle */
    private static final int CROSSED_LL = 1;
    private static final int CROSSED_LR = 2;
    private static final int CROSSED_RR = 3;
    private static final int CROSSED_RL = 4;
    private static final int NOT_CROSSED = 0;

    /* initialize constants for deviding the screen to 5 parts */
    private static final int PART1 = Consts.SCREEN_WIDTH / 5;
    private static final int PART2 = 2 * PART1;
    private static final int PART3 = 3 * PART1;
    private static final int PART4 = 4 * PART1;
    private static final int DISTANCE = 10;

    /* initialize fields */
    private boolean isOnScreen;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private Paddle other;
    private Game game;

    /**
     * Ctor.
     * @param rect the paddle rectanlge
     * @param keyboard the keyboard sensor.
     * @param game instace
     */
    public Paddle(Rectangle rect, biuoop.KeyboardSensor keyboard, Game game) {
        this.rect = rect;
        this.isOnScreen = false;
        this.keyboard = keyboard;
        this.game = game;
    }

    /**
     * handles the left move of the paddle.
     */
    public void handleMoveLeft() {
        moveLeft();
        handlePaddleStatus();
    }

    /**
     * handles the right move of the paddle.
     */
    public void handleMoveRight() {
        moveRight();
        handlePaddleStatus();
    }

    /**
     * move the paddle to the left.
     */
    public void moveLeft()  {

        /* initialize new upperLeft point */
        Point upperLeft = new Point(rect.getUpperLeft().getX() - DISTANCE,
            rect.getUpperLeft().getY());
        rect.setUpperLeft(upperLeft);
    }

    /**
     * method to handle the different paddle position.
     */
    private void handlePaddleStatus() {

        /* get the paddle status */
        int paddleStatus = getPaddleStatus();
        Point upperLeft;

        switch (paddleStatus) {
            case CROSSED_LL:

                /* add other paddle to the right of the screen */
                if (!other.isOnScreen) {
                    upperLeft = new Point(Consts.SCREEN_WIDTH - 1,
                        Consts.PADDLE_START_Y);

                    addOtherPaddleOnScreen(upperLeft);
                }
                break;
            case CROSSED_RR:

                /* add other paddle to the left of the screen */
                if (!other.isOnScreen) {
                    upperLeft = new Point(0 - Consts.PADDLE_WIDTH,
                        Consts.PADDLE_START_Y);
                    addOtherPaddleOnScreen(upperLeft);
                }
                break;
            case CROSSED_LR:

                /* remove the paddle positioned outside of the screen */
                removeFromGame(game);
                break;
            case CROSSED_RL:

                /* remove the paddle positioned outside of the screen */
                removeFromGame(game);
                break;
            default:
                break;
        }
    }

    /**
     * adds the other paddle which not on the screen to the screen.
     * @param upperLeft the upperleft point to add the other paddle.
     */
    private void addOtherPaddleOnScreen(Point upperLeft) {
        other.getCollisionRectangle().setUpperLeft(upperLeft);
        other.addToGame(game);
    }

    /**
     * gets according to the position of the paddle it status
     * in respect of the screen.
     * @return the status as enum.
     */
    private int getPaddleStatus() {

        /* get the vetical edges of the paddle */
        double leftX = rect.getUpperLeft().getX();
        double rightX = leftX + Consts.PADDLE_WIDTH;

        /* check if the paddle crossed with its left */
        if (leftX < 0) {

            /* check if the paddle crossed also with its right */
            if (rightX < 0) {
                return CROSSED_LR;
            }
            return CROSSED_LL;
        }

        /* check if the paddle crossed with its right */
        if (rightX > Consts.SCREEN_WIDTH) {

            /* check if the paddle crossed also with its left */
            if (leftX > Consts.SCREEN_WIDTH) {
                return CROSSED_RL;
            }
            return CROSSED_RR;
        }

        /* return not crossed */
        return NOT_CROSSED;
    }

    /**
     * move paddle to the right.
     */
    public void moveRight() {
        /* initialize new upperLeft point */
        Point upperLeft = new Point(rect.getUpperLeft().getX() + DISTANCE,
               rect.getUpperLeft().getY());
        rect.setUpperLeft(upperLeft);
    }

    /**
     * method to be called on each time interval to notify that its passed.
     */
    public void timePassed() {

        /* if the left pressed handle left */
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            if (isOnScreen) {

                /* if the paddle is on screen i.e in the arrays move it */
                handleMoveLeft();
            }
        }
        /* if the right pressed handle right */
        if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            if (isOnScreen) {

                /* if the paddle is on screen i.e in the arrays move it */
                handleMoveRight();
            }
        }
    }

    /**
     * draw the paddle on the screen.
     * @param surface the surface to draw on.
     */
    public void drawOn(DrawSurface surface) {

        /* set the color */
        surface.setColor(Consts.PADDLE_COLOR);

        /* get the rectangle points and width and height*/
        int upLeftX = (int) rect.getUpperLeft().getX();
        int upLeftY = (int) rect.getUpperLeft().getY();
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();
        surface.fillRectangle(upLeftX, upLeftY, width, height);
    }

    /**
     * getter.
     * @return the rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    /**
     * method calculate and return new velocity for the ball hitting the
     * paddle.
     * @param collisionPoint closest point on wich the ball hits the paddle.
     * @param currentVelocity the current valocity of the ball.
     * @param hitter the ball hittiing.
     * @return valocity object.
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
        Velocity currentVelocity) {

        double x = collisionPoint.getX();
        double speed = currentVelocity.getSpeed();

        /* check for each part and return the coresponding angle for that
         * particular part.
         */
        if (0 <= x && x <= PART1) {
            return Velocity.fromAngleAndSpeed(300, speed);
        } else if (PART1 < x && x <= PART2) {
            return Velocity.fromAngleAndSpeed(330, speed);
        } else if (PART2 < x && x <= PART3) {
            return Velocity.fromAngleAndSpeed(0, speed);
        } else if (PART3 < x && x <= PART4) {
            return Velocity.fromAngleAndSpeed(30, speed);
        } else {
            return Velocity.fromAngleAndSpeed(60, speed);
        }
    }

    /**
     * getter.
     * @return is the paddle in screen.
     */
    public boolean isOnScreen() {
        return isOnScreen;
    }

    /**
     * add this paddle to the game.
     * @param g the game object.
     */
    public void addToGame(Game g) {

        /* add the collidable to the arrays */
        isOnScreen = true;
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * remove this paddle from the game.
     * @param g the game instance.
     */
    public void removeFromGame(Game g) {

        /* remove it from the collidables and sprites arrays */
        isOnScreen = false;
        g.removeCollidable(this);
        g.removeSprite(this);
    }

    /**
     * setter for the other paddle.
     * @param other the other paddle.
     */
    public void setOther(Paddle other) {
        this.other = other;
    }

 }
