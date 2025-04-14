
/**
 * Line class provids method on lines.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class Line {

    private Point start;
    private Point end;
    private Equation equation;

    /**
    * Constructor get the starting and end points of a line.
    * @param start the starting point of the line.
    * @param end the ending point of the line.
    */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;

        /* assings new equation instance to the line */
        equation = new Equation(this);
    }

    /**
     * Constructor gets the coordinate raw.
     * @param x1 x coordinate of point 1.
     * @param y1 x coordinate of point 2.
     * @param x2 y coordinate of point 1.
     * @param y2 y coordinate of point 2.
     */
    public Line(double x1, double y1, double x2, double y2) {

        /* setting the points manually and assins equation */
        start = new Point(x1, y1);
        end = new Point(x2, y2);
        equation = new Equation(this);
    }

    /**
    * @return the length of the line.
    */
    public double length() {

        /* calculate the difference */
        double xDiff = start.getX() - end.getX();
        double yDiff = start.getY() - end.getY();

        /* return the length using the math formula */
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    /**
     * @return the middle point of the line.
     */
    public Point middle() {

        /* calculate the middle point and returns new point */
        double midY = (start.getY() + end.getY()) / 2;
        double midX = (start.getX() + end.getX()) / 2;
        return new Point(midX, midY);
    }

    /**
    * @return the start point of this line.
    */
    public Point start() {
        return start;
    }

    /**
     * @return the end point of this line.
     */
    public Point end() {
        return end;
    }

    /**
     * method to check if two lines intersect.
     * @param other the other line to check with.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        Point intersectPoint;

        /* if lines are parallels */
        if (equation.isParallels(other.equation)) {

            return doParallesIntersect(other);
        } else {
            /* get the suspected interesection point */
            intersectPoint = findIntersectionPoint(other);

            /* check if the point is on both lines */
            return doLinesIntersect(other, intersectPoint);
        }
    }

    /**
     * method to validate that bounded lines intersecting.
     * @param other the other line to check with.
     * @param intersectPoint the suspected intersection point.
     * @return true if they have common point of false otherwise.
     */
    private boolean doLinesIntersect(Line other, Point intersectPoint) {

        /* check for the my line */
        if (!intersectPoint.doPointBetween(start, end)) {
            return false;
        }

        /* check for the other line */
        if (!intersectPoint.doPointBetween(other.start, other.end)) {
            return false;
        }
        return true;
    }

    /**
     * method to check if two parallels lines are interesecting i.e have
     * at least one shared point.
     * @param other the other line
     * @return true if thay are and false otherwise.
     */
    private boolean doParallesIntersect(Line other) {

        /* if they have a common point they interesect */
        if (equals(other)) {
            return true;
        }

        /* if the b in the y = mx + b isn't the same they cannot intersect */
        if (equation.getYAxisValue() != other.equation.getYAxisValue()) {
            return false;
        }

        /* check if there is congruence between the lines
         * by checking if a point lies between the others.
         */
        if (start.doPointBetween(other.start, other.end)) {
            return true;
        }
        if (end.doPointBetween(other.start, other.end)) {
            return true;
        }
        return false;
    }

    /**
     * method to check if three lines intersecting two with this line.
     * @param other1 line 1
     * @param other2 line 2
     * @return true if the two intersect with this line and false otherwise.
     */
    public boolean isIntersecting(Line other1, Line other2) {
            return isIntersecting(other1) && isIntersecting(other2);
    }



    /**
     * method to return the intersection point of two lines given that
     * they intersect.
     * @param other the other line
     * @return the point if they intersect and null otherwise.
     */
    public Point intersectionWith(Line other) {

        /* check thay may not intersect at all */
        if (isIntersecting(other)) {

            /* check the case for parallels */
            if (equation.isParallels(other.equation)) {
                return findParallelsIntersectionPoint(other);

            /* check the case for not parallels */
            } else {
                return findIntersectionPoint(other);
            }
        }
        return null;
    }

     /**
     * method to get the intersection point between two unparallels equations.
     * @param other the equation to check with
     * @return the intersection point.
     */
    private Point findIntersectionPoint(Line other) {
        double x;
        double y;
        Equation otherEqn = other.equation;

        /* check if one of the line is vertical and if so get its x
         * value from its point then get the y value by assingnig the x value
         * to the other equation.
         */
        if (equation.getSlope().isUndefined()) {
            x = start.getX();
            y = otherEqn.getSlopeVal() * x + otherEqn.getYAxisValue();
        } else if (otherEqn.getSlope().isUndefined()) {
            x = other.start.getX();
            y = equation.getSlopeVal() * x + equation.getYAxisValue();
        } else {

            /* if no line is vertical just calculate the x
             * by comparing the eqautions and isolating the x value
             * finding the y value.
             */
            x = (otherEqn.getYAxisValue() - equation.getYAxisValue())
            / (equation.getSlopeVal() - otherEqn.getSlopeVal());
            y = otherEqn.getSlopeVal() * x + otherEqn.getYAxisValue();
        }
        return new Point(x, y);
    }

    /**
     * method to get the intersection point of two parallels lines includes
     * undefined slopes. this method being called on intertseting lines.
     * @param other the other line
     * @return the intersection point.
     */
    private Point findParallelsIntersectionPoint(Line other) {
        int counter = 0;
        Point intersectionPoint = null;

        /* two parallels lines intersect if they equals with only one
         * point.
         */
        if (start.equals(other.start)) {

            /* increment the equal points counter */
            counter++;

            /* set the intersecting point to the point founded */
            intersectionPoint = new Point(start.getX(), start.getY());
        }
        if (start.equals(other.end)) {
            counter++;
            intersectionPoint = new Point(start.getX(), start.getY());
        }
        if (end.equals(other.start)) {
            counter++;
            intersectionPoint = new Point(end.getX(), end.getY());
        }
        if (end.equals(other.end)) {
            counter++;
            intersectionPoint = new Point(end.getX(), end.getY());
        }
        return counter == 1 ? intersectionPoint : null;
    }

    /**
    * method to check if two lines are equals i.e they have at least one point
    * in common.
    * @param other the other line to check with
    * @return true if the lines equal and false otherwise.
    */
    public boolean equals(Line other) {

        /* compare every combination of two point */
            if (start.equals(other.start) || start.equals(other.end)
            || end.equals(other.start) || end.equals(other.end)) {
                return true;
            }
        return false;
    }

    /**
     * getter for the equation.
     * @return the equation.
     */
    public Equation getEquation() {
        return equation;
    }
}
