// 204058366 Roei Azran
import java.awt.Color;
import java.util.Random;
import biuoop.DrawSurface;
import biuoop.GUI;
/**
 * Provides method and displays balls and rectangles animation.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class MultipleFramesBouncingBallsAnimation {

    /* contants */
    private static final int SCREEN_HEIGHT = 600;
    private static final int SCREEN_WIDTH = 1000;

    /* the rectangle edges points */
    private static Point grayUpLeft = new Point(50, 50);
    private static Point grayUpRight = new Point(500, 50);
    private static Point grayDownLeft = new Point(50, 500);
    private static Point grayDownRight = new Point(500, 500);

    private static Point yellowUpLeft = new Point(450, 450);
    private static Point yellowUpRight = new Point(600, 450);
    private static Point yellowDownLeft = new Point(450, 600);
    private static Point yelloDownRight = new Point(600, 600);

    private static Point outPutUpLeft = new Point(600, 0);
    private static Point outPutUpRight = new Point(SCREEN_WIDTH, 0);
    private static Point outPutDownLeft = new Point(600, SCREEN_HEIGHT);
    private static Point outPutDownRight = new Point(SCREEN_WIDTH,
     SCREEN_HEIGHT);

    /* the rectangles instanciation */
    private static Rectangle grayRectangle = new Rectangle(grayUpLeft,
     grayUpRight, grayDownLeft, grayDownRight);
    private static Rectangle yellowRectangle = new Rectangle(yellowUpLeft,
     yellowUpRight, yellowDownLeft, yelloDownRight);
    private static Rectangle outPutRectangle = new Rectangle(outPutUpLeft,
     outPutUpRight, outPutDownLeft, outPutDownRight);

    /**
     * the main method.
     * @param args gets the balls radiuses.
     */
    public static void main(String[] args) {

        /* convert rediuses to ints */
        int[] sizes = Sort.stringsToInts(args, args.length);

        /* fill the balls array */
        Ball[] balls = generateBalls(sizes);

        /* prepare the balls and display them */
        prepareBalls(balls);
        displayBalls(balls);
    }

    /**
     * giving speed to the balls and angle.
     * @param balls the balls array.
     */
    public static void prepareBalls(Ball[] balls) {
        Random random = new Random();
        int fullCircle = 360;
        int maxSpeed = 3;

        for (Ball ball : balls) {

            /* generate random angle and spped */
            double randAngle = fullCircle * random.nextDouble();
            double speed = (maxSpeed - 2) * random.nextDouble() + 2;

            /* assing it to the ball */
            Velocity v = Velocity.fromAngleAndSpeed(randAngle, speed);
            ball.setVelocity(v);
        }
    }

    /**
     * method to fill the array with random balls.
     * @param sizes the sizes of the balls.
     * @return balls array.
     */
    public static Ball[] generateBalls(int[] sizes) {
        Ball[] balls = new Ball[sizes.length];
        Random random = new Random();
        double x;
        double y;

        /* set variables to check the start random values */
        int outputResult = 0;
        int grayResult = 0;

        for (int i = 0; i < sizes.length; i++) {

            if (i < (sizes.length / 2)) {

                /* repeat all while the balls generated outside rectanlges */
                do {
                    x = (grayUpRight.getX() -  grayUpLeft.getX())
                        * random.nextDouble() +  grayUpLeft.getX();
                    y = (grayDownLeft.getY() - grayUpLeft.getY())
                        * random.nextDouble() + grayUpLeft.getY();

                    balls[i] = new Ball(new Point(x, y),
                    sizes[i], Color.BLACK);
                    grayResult = balls[i].
                    doBallIntersectRectangle(grayRectangle);
                } while (grayResult != Ball.NO_INTERSECTION);
            } else {

                /* repeat all while the balls generated inside rectanlges */
                do {

                x = (outPutUpRight.getX() - outPutUpLeft.getX())
                    * random.nextDouble() + outPutUpLeft.getX();
                y = (outPutDownLeft.getY() - outPutUpLeft.getY())
                    * random.nextDouble() + outPutUpLeft.getY();

                balls[i] = new Ball(new Point(x, y), sizes[i], Color.BLACK);
                outputResult = balls[i]
                .doBallIntersectRectangle(outPutRectangle);
                } while (outputResult != Ball.NO_INTERSECTION);
            }
        }
        return balls;
    }

    /**
     * method do display the balls on the screen.
     * @param balls the balls array.
     */
    public static void displayBalls(Ball[] balls) {
        GUI gui = new GUI("Mult", SCREEN_WIDTH, SCREEN_HEIGHT);
        DrawSurface d = gui.getDrawSurface();
        biuoop.Sleeper sleeper = new biuoop.Sleeper();

        while (true) {

            /* draw the gray rectangle */
            d.setColor(Color.GRAY);
            d.fillRectangle(50, 50, 450, 450);
            d.setColor(Color.BLACK);

            /* loop for ecery ball */
            for (int i = 0; i < balls.length; i++) {

                /* check bounds with yellow rectanlge */
                if (i >= balls.length / 2) {
                    balls[i].limitBallAndRectangle(yellowRectangle);
                }
                /* check bounds with gray and screen rectanlges */
                balls[i].limitBallAndRectangle(grayRectangle);
                balls[i].moveOneStep();

                balls[i].drawOn(d);
            }

            /* draw the yellow rectangle */
            d.setColor(Color.YELLOW);
            d.fillRectangle(450, 450, 150, 150);
            d.setColor(Color.BLACK);

            gui.show(d);
            d = gui.getDrawSurface();
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
