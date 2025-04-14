
/**
 * Point Class made to reason about point of the form (x,y).
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class Point {

    private double x;
    private double y;
    private static double epsilon = 1;

    /**
    * Constructor mathod.
    * @param x the x coordinate of the point.
    * @param y the y coordinate of the point.
    */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * this method made to check if a suspected intersection point lies
     * on both the lines containing it and being called on the
     * intersection point with the start and end point of each line.
     * @param p1 point b.
     * @param p2 point c.
     * @return true if the point between false otherwise.
     */
    public boolean doPointBetween(Point p1, Point p2) {

        /* get the edges of the lines */
        double maxY = Math.max(p1.y, p2.y);
        double minY = maxY == p1.y ? p2.y : p1.y;
        double maxX = Math.max(p1.x, p2.x);
        double minX = maxX == p1.x ? p2.x : p1.x;


        /* check if the point is outside the y range */
        if ((x - epsilon) > maxX || (x + epsilon) < minX) {
            return false;
        }

        /* check if the point is outside the x range */
        if ((y - epsilon) > maxY || (y + epsilon) < minY) {
            return false;
        }

        return true;
    }

    /**
     * method to calculate the distance between to points.
    * @param other the other point.
    * @return the distance of this point to the other point.
    */
    public double distance(Point other) {
        double x1 = other.getX();
        double y1 = other.getY();

        /* using the math distance formula */
        return Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));
    }


   /**
    * method to check if to point are the same.
    * @param other the othe point.
    * @return return true is the points are equal, false otherwise.
    */
    public boolean equals(Point other) {

        /* check the epsilon space around x and y */
        if ((x + epsilon) >= other.getX()
        && (x - epsilon) <= other.getX()
        && (y + epsilon) >= other.getY()
        && (y - epsilon) <= other.getY()) {
            return true;
        }
        return false;
    }

    /**
     *  @return the x coordinate of this object.
     */
    public double getX() {
        return x;
    }

    /**
     * @return the x coordinate of this object.
     */
    public double getY() {
        return y;
    }

    /**
     * sets the x coordinate.
     * @param x the new value.
     *  */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * sets the y coordinate.
     * @param y the new value.
     */
    public void setY(double y) {
        this.y = y;
    }
}
