package geometry;
import java.util.ArrayList;
import java.util.List;

import collisions.CollisionInfo;
/**
 * Rectangle shape class provides method on rectangles.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class Rectangle {

    private double width;
    private double height;
    private Point upperLeft;
    private Line upLine;
    private Line downLine;
    private Line leftLine;
    private Line rightLine;

    /**
     * Ctor Create a new rectangle with location and width/height.
     * @param upperLeft upperLeft point
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;

        /* assigning the outer lines of the rectangle */
        setRectangleLines();
    }

    private void setRectangleLines() {

        /* initialzie the start and end points of the lines */
        Point upperRight = new Point(upperLeft.getX() + width,
         upperLeft.getY());
        Point downRight = new Point(upperRight.getX(),
         upperLeft.getY() + height);
        Point downLeft = new Point(upperLeft.getX(),
         upperLeft.getY() + height);

        /* instaciate the lines */
        upLine = new Line(upperLeft, upperRight);
        downLine = new Line(downLeft, downRight);
        leftLine = new Line(downLeft, upperLeft);
        rightLine = new Line(downRight, upperRight);
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     * @param line the possibly intersecting line.
     * @return intersection list.
     */
    public List<Point> intersectionPoints(Line line) {

        /* initialize the intersection points array */
        List<Point> intersctions = new ArrayList<Point>();

        /* check each line */
        if (upLine.isIntersecting(line)) {

            /* add the intersection point */
            intersctions.add(upLine.intersectionWith(line));
        }

        if (downLine.isIntersecting(line)) {
            intersctions.add(downLine.intersectionWith(line));
        }

        if (rightLine.isIntersecting(line)) {
            intersctions.add(rightLine.intersectionWith(line));
        }

        if (leftLine.isIntersecting(line)) {
            intersctions.add(leftLine.intersectionWith(line));
        }

        return intersctions;
    }

    /**
     * method that given intersection point return if the point intersect
     * with the vertical lines or the horizontal lines.
     * @param intersection intersection point.
     * @return 1, 2 in case of vertical or horizontal intersection 0 otherwise.
     */
    public int getSpecificIntersectionLine(Point intersection) {

        /* check for vertical intersection with up and down lines */
        if (upLine.pointOnLine(intersection)
            || downLine.pointOnLine(intersection)) {
                return CollisionInfo.VERTICAL_INTERSECT;
            /* check for horizontal intersection with left and right lines */
        } else if (rightLine.pointOnLine(intersection)
            || leftLine.pointOnLine(intersection)) {
                return CollisionInfo.HORIZONTAL_INTERSECT;
        }

        /* no intersection detected */
        return CollisionInfo.NO_INTERSECT;
    }

    /**
     * getter.
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return width;
    }

    /**
     * getter.
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return height;
    }

    /**
     * getter.
     * @return the upperleft point.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * setter for the upperleft point and updates the rectangle lines.
     * @param upperLeft
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
        setRectangleLines();
    }
 }
