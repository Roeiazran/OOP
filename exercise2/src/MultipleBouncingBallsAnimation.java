
import java.awt.Color;
import java.util.Random;
import biuoop.DrawSurface;
import biuoop.GUI;
/**
 * provides method to dislpay multiple balls on the screen.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class MultipleBouncingBallsAnimation {

    /* initialize contants */
    private static final int SCREEN_HEIGHT = 600;
    private static final int SCREEN_WIDTH = 1000;
    /**
     * main method gets the args array and generate the balls.
     * @param args
     */
    public static void main(String[] args) {

        /* convert to int and sort */
        int[] sizes = Sort.stringsToInts(args, args.length);
        Sort.bubbleSort(sizes);

        double maxSpeed = 10;

        /* generate speeds from sizes */
        double[] speeds = generateSpeedsArray(sizes, maxSpeed);

        /* generate balls with sized and speed and display them */
        Ball[] balls = generateBalls(sizes, speeds);
        prepareAndDisplayBalls(balls, speeds);

    }

    /**
     * method to prepare the balls and display them.
     * @param balls the balls array.
     * @param speeds the speeds array.
     */
    public static void prepareAndDisplayBalls(Ball[] balls, double[] speeds) {
        Random random = new Random();
        int fullCircle = 360;

        /* generate random angle and assign speed to each ball */
        for (int i = 0; i < balls.length; i++) {
            double randAngle = fullCircle * random.nextDouble();
            Velocity v = Velocity.fromAngleAndSpeed(randAngle, speeds[i]);
            balls[i].setVelocity(v);
        }

        /* display the balls */
        displayBalls(balls);
    }

    /**
     * method to display the balls.
     * @param balls the balls array.
     */
    public static void displayBalls(Ball[] balls) {
        GUI gui = new GUI("", SCREEN_WIDTH, SCREEN_HEIGHT);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        DrawSurface d;

        while (true) {
            d = gui.getDrawSurface();

            /* assing balls with new position according to its dx and dy */
            for (Ball ball : balls) {
                ball.moveOneStep();
                ball.drawOn(d);
            }

            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }


    /**
     * method to generate random balls.
     * @param sizes the sizes array.
     * @param speeds the speeds array.
     * @return balls array.
     */
    public static Ball[] generateBalls(int[] sizes, double[] speeds) {
        Ball[] balls = new Ball[sizes.length];
        Random random = new Random();
        double x;
        double y;
        int result;

        /* generate random x and y start coordinate  */
        for (int i = 0; i < sizes.length; i++) {

            /* repeate all while the point and readius outside the screen */
            do {
                x = SCREEN_WIDTH * random.nextDouble();
                y = SCREEN_HEIGHT * random.nextDouble();
                balls[i] = new Ball(new Point(x, y), sizes[i], Color.BLACK);
                result = balls[i].doBallIntersectRectangle(
                    BouncingBallAnimation.SCREEN);
                } while (result != Ball.NO_INTERSECTION);
        }

        return balls;
    }

    /**
     * method to generate sppeds array.
     * @param sizes the sizes array.
     * @param maxSpeed the max speed.
     * @return speeds array.
     */
    public static double[] generateSpeedsArray(int[] sizes, double maxSpeed) {
        double parameter = maxSpeed / sizes.length;
        double[] speeds = new double[sizes.length];
        double sameSpeedMinRadius = 50;

        /* set speed to each ball */
        for (int i = 0; i < sizes.length; i++) {
            speeds[i] = maxSpeed;

            /* reduce speed while the redius gets larger */
            if (sizes[i] <= sameSpeedMinRadius) {
                maxSpeed = maxSpeed - parameter;
            }
        }
        return speeds;
    }
}
