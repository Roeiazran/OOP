// 204058366 Roei Azran
import java.awt.Color;
import java.util.Random;
import biuoop.DrawSurface;
import biuoop.GUI;
/**
 * provides method to display a bouncing ball.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class BouncingBallAnimation {

    /* initialize contants */
    private static final int SCREEN_HEIGHT = 600;
    private static final int SCREEN_WIDTH = 1000;
    private static final int INVALID = 0;
    private static final int VALID = 1;
    private static int radius = 30;

    /* initalize class fields */
    private static Ball ball;
    private static Point screenUpLeft = new Point(0, 0);
    private static Point screenUpRight = new Point((double) SCREEN_WIDTH, 0);
    private static Point screenDownLeft = new Point(0,
    (double) SCREEN_HEIGHT);
    private static Point screenDownRight = new Point((double) SCREEN_WIDTH,
     (double) SCREEN_HEIGHT);
    public static final Rectangle SCREEN = new Rectangle(screenUpLeft,
     screenUpRight, screenDownLeft, screenDownRight);

    /**
     * main method.
     * @param args the args array
     */
    public static void main(String[] args) {

        /* get the args from the args array. */
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        double dx = Double.parseDouble(args[2]);
        double dy = Double.parseDouble(args[3]);

        /* initalize new ball and check if the point in screen range. */
        Point center = new Point(x, y);
        ball = new Ball(center, radius, Color.BLACK);
        ball.setVelocity(dx, dy);
        limitBallToScreen(ball);

        /* display the ball. */
        BouncingBallAnimation.drawAnimation(ball.getCenter(), dx, dy);
    }

    /**
     * method to correct the coordinates in case they outside the range.
     * @param ball the ball.
     */
    private static void limitBallToScreen(Ball ball) {

        Random random = new Random();
        double x = ball.getY();
        double y = ball.getY();
        int result;

        /* repeat all while point outside the range */
        while (true) {
            result = ball.doBallIntersectRectangle(SCREEN);
            if (result != Ball.NO_INTERSECTION
                || doUserCordsValid(x, y) == INVALID) {
                x =  (SCREEN_WIDTH - radius) * random.nextDouble() + radius;
                y = (SCREEN_HEIGHT - radius) * random.nextDouble() + radius;
                ball.setCenter(new Point(x, y));
                ball.updateBallRectangle();
            } else {
                break;
            }
        }
    }

    /**
     * method to validate the user coordinates.
     * @param x the x coordinate.
     * @param y the x coordinate.
     * @return valid and invalid.
     */
    private static int doUserCordsValid(double x, double y) {

        /* validate if coordinates outsiede the screen */
        if (x > SCREEN_WIDTH || x < 0 || y > SCREEN_HEIGHT || y < 0) {
            return INVALID;
        }
        return VALID;
    }

    /**
     * method to draw the animatiom.
     * @param start stating point of the ball.
     * @param dx the x distance.
     * @param dy the y distance.
     */
    private static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("Bouncing Ball Animation", SCREEN_WIDTH,
        SCREEN_HEIGHT);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        DrawSurface d;

        /* display it */
        while (true) {
            d = gui.getDrawSurface();
            ball.drawOn(d);
            ball.moveOneStep();
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
     }

}
