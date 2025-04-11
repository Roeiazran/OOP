// 204058366 Roei Azran
import biuoop.DrawSurface;
/**
 *  Provides methods to displays balls and rectangle on the screen.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class Ball {

    /* intialize constants */
    public static final int BOTH_AXIS_INTERSECT = 1;
    public static final int HORIZONTAL_INTERSECT = 2;
    public static final int VERTICAL_INTERSECT = 3;
    public static final int NO_INTERSECTION = 0;

    /* initialize class fields */
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private Line horizontal;
    private Line vertical;
    private Point leftPoint;
    private Point rightPoint;
    private Point upPoint;
    private Point downPoint;

    /**
     * Constructor.
     * @param center the center of the ball.
     * @param r the radius of the ball.
     * @param color the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;

        /* initalzize the horizontal and vertical points */
        this.leftPoint = new Point(center.getX() - r, center.getY());
        this.rightPoint = new Point(center.getX() + r, center.getY());
        this.upPoint = new Point(center.getX(), center.getY() - r);
        this.downPoint = new Point(center.getX(), center.getY() + r);

        /* set the horizontal and vertical lines of the ball */
        this.horizontal = new Line(leftPoint, rightPoint);
        this.vertical = new Line(upPoint, downPoint);
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
     * method to chenge object direction when exisiting the screen borders.
     */
    public void moveOneStep() {

        /* limit the ball to the screen */
        limitBallAndRectangle(BouncingBallAnimation.SCREEN);
    }

    /**
     * method served as middle men to limit the ball to not get inside
     * of a rectangle border.
     * @param rect the rectangle to apply the borders on.
     */
    public void limitBallAndRectangle(Rectangle rect) {

        /* check if the ball violate with its position */
        int ballState = doBallIntersectRectangle(rect);

        /* change the direction if needed */
        chengeBallDirecrtion(ballState);

        /* update the rectangle trailing the ball */
        updateBallRectangle();
    }

    /**
     * method to update the rectangle traling the ball.
     */
    public void updateBallRectangle() {

        /* set new points to the ball trailing rectanlge  */
        this.leftPoint = new Point(center.getX() - r, center.getY());
        this.rightPoint = new Point(center.getX() + r, center.getY());
        this.upPoint = new Point(center.getX(), center.getY() - r);
        this.downPoint = new Point(center.getX(), center.getY() + r);

        /* update its horizontal and vertical lines */
        this.horizontal = new Line(leftPoint, rightPoint);
        this.vertical = new Line(upPoint, downPoint);
    }

    /**
     * method to chenge the ball velocity when it going outside and
     * inside of a rectangle.
     * @param ballState
     */
    public void chengeBallDirecrtion(int ballState) {

        switch (ballState) {
            case BOTH_AXIS_INTERSECT:
                setVelocity(-velocity.getDx(), -velocity.getDy());
                break;
            case HORIZONTAL_INTERSECT:
                setVelocity(-velocity.getDx(), velocity.getDy());
                break;
            case VERTICAL_INTERSECT:
                setVelocity(velocity.getDx(), -velocity.getDy());
                break;
            default:
                break;
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * checks if a given ball is inside a rectangle.
     * @param rect the rectanlge to bound ball inside.
     * @return the ball status in prespective of the ractangle.
     */
    public int doBallIntersectRectangle(Rectangle rect) {

        /* initalize intersecters */
        boolean isVerticalIntersect =
            vertical.isIntersecting(rect.getDownLine())
            || vertical.isIntersecting(rect.getUpLine());
        boolean isHorizontalIntersect =
            horizontal.isIntersecting(rect.getLeftLine())
            || horizontal.isIntersecting(rect.getRightLine());

        /* check for diferent type of intersections */
        if (isVerticalIntersect && isHorizontalIntersect) {
            return BOTH_AXIS_INTERSECT;
        }

        if (isHorizontalIntersect && !isVerticalIntersect) {
            return HORIZONTAL_INTERSECT;
        }

        if (isVerticalIntersect && !isHorizontalIntersect) {
            return VERTICAL_INTERSECT;
        }

        /* no intersection found */
        return NO_INTERSECTION;
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
    public void drawOn(DrawSurface surface) {
        int x = (int) getX();
        int y = (int) getY();
        surface.setColor(getColor());
        surface.fillCircle(x, y, r);
    }
}