package collisions;
import geometry.Point;
/**
 * The velocity class provides method to apply on a moving ball.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class Velocity {

    private double dx;
    private double dy;

    /**
     * y distance getter.
     * @return dy.
     */
    public double getDy() {
        return dy;
    }

    /**
     * x distance getter.
     * @return dx.
     */
    public double getDx() {
        return dx;
    }

    /**
     * get the speed from the dx and dy components.
     * @return the speed vector.
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * method to create new velocity instace from angle and speed.
     * @param angle the angle in which the ball will move.
     * @param speed the speed in which the ball will move.
     * @return new velocity instace.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {

        /* the angle need's to shifted to match the convention */
        final int shiftAngleDeg = 90;
        angle = shiftAngleDeg - angle;

        /* we reducing the angle to the range -360 to 360 */
        angle = reduceAngle(angle);

        /* using the sin and cos to get the velocity dx and dy vectors */
        double dx = speed * Math.cos(Math.toRadians(angle));
        double dy = speed * Math.sin(Math.toRadians(angle));

        /* setting the dx and dy and returning */
        dx = setXsign(angle, dx);
        dy = setYSign(angle, dy);

        return new Velocity(dx, dy);
    }

    /**
     * method do deal with the negatives angles because cos produces
     * the same values to diferent angle we need to set the angle manualy.
     * @param angle the angle input.
     * @param x the dx varible.
     * @return the new dx value.
     */
    private static double setXsign(double angle, double x) {

        /* check if x is in the first or the fourth quarter */
        if ((270 <= angle && angle <= 360)
        || (0 <= angle && angle <= 90)) {
            x = Math.abs(x);

            /* check for the same quarter but for negative angle */
        } else if ((-90 <= angle && angle <= 0)
        || (-360 <= angle && angle <= -270)) {
            x = Math.abs(x);

            /* if x not in those quarters then it is nagative */
        } else {
            x = -Math.abs(x);
        }
        return x;
    }

    /**
     * this methos is the same as above but for sin.
     * @param angle the angle input.
     * @param y the dy value.
     * @return the new dy value.
     */
    private static double setYSign(double angle, double y) {

        /* check if y is in the first or the second quarter */
        if ((0 <= angle && angle <= 180) || (-360 <= angle && angle <= -180)) {
            y = Math.abs(y);

            /* if not y is negative */
        } else {
            y = -Math.abs(y);
        }
        return y;
    }

    /**
     * method to reduce the angle given into the range -360 to 360
     * since cos and sin are cyclic function both are the same.
     * @param angle the angle input.
     * @return the reduced angle.
     */
    private static double reduceAngle(double angle) {
        final int fullCycle = 360;

        /* reduce to only one cycle  */
        while (angle > fullCycle) {
            angle -= fullCycle;
        }
        while (angle < -fullCycle) {
            angle += fullCycle;
        }
        return angle;
    }

    /**
     * the consrtuctor gets x and y distances of a ball.
     * @param dx the x distance
     * @param dy the y distance
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * method to move the point dx and dy amounts on the x and the y axis.
     * @param p the point to aplly the new position.
     * @return the moved point.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() - dy);
    }
}
