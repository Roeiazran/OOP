
/**
 * provides instaciation of a rectanle and getter to its corners and lines.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class Rectangle {

    /* class fields */
    private Point upLeft;
    private Point upRight;
    private Point downLeft;
    private Point downRight;
    private Line leftLine;
    private Line rightLine;
    private Line upLine;
    private Line downLine;

    /**
     * construcor.
     * @param upLeft the upLeft corner of the rectangle.
     * @param upRight the upRight corner of the rectangle.
     * @param downLeft the downLeft corner of the rectangle.
     * @param downRight the downRight corner of the rectangle.
     */
    public Rectangle(Point upLeft, Point upRight, Point downLeft,
     Point downRight) {
        this.upLeft = upLeft;
        this.upRight = upRight;
        this.downLeft = downLeft;
        this.downRight = downRight;
        this.leftLine = new Line(downLeft, upLeft);
        this.upLine = new Line(upLeft, upRight);
        this.rightLine = new Line(upRight, downRight);
        this.downLine = new Line(downLeft, downRight);
    }

    /**
     * getter.
     * @return the upLeft.
     */
    public Point getUpLeft() {
        return upLeft;
    }

    /**
     * getter.
     * @return the upRight.
     */
    public Point getUpRight() {
        return upRight;
    }

    /**
     * getter.
     * @return the downLeft.
     */
    public Point getDownLeft() {
        return downLeft;
    }

    /**
     * getter.
     * @return the downRgiht.
     */
    public Point getDownRight() {
        return downRight;
    }

    /**
     * getter.
     * @return the left line.
     */
    public Line getLeftLine() {
        return leftLine;
    }

    /**
     * getter.
     * @return the right line.
     */
    public Line getRightLine() {
        return rightLine;
    }

    /**
     * getter.
     * @return the up line.
     */
    public Line getUpLine() {
        return upLine;
    }

    /**
     * getter.
     * @return the down line.
     */
    public Line getDownLine() {
        return downLine;
    }
}
