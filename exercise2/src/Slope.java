
/**
 * Slope class provides methods to reason about equations slopes.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class Slope {

    private double slope;

    /* variable do denote an undefined slope i.e vertical line */
    private boolean isUndefined = false;

    /**
     * Constructor get to point and assing the a slope.
     * @param pointA point on line.
     * @param pointB point on line.
     */
    public Slope(Point pointA, Point pointB) {

        /* check if thy both have the same x coordinate */
        if (pointA.getX() == pointB.getX()) {

            /* check for just a single point */
            if (pointA.getY() != pointB.getY()) {
                isUndefined = true;
            }

        /* the slope is denfined calculate it */
        } else {
            slope = calculateSlope(pointA, pointB);
        }
    }

    /**
     * method to calculate the slope between two points.
     * @param pointA one point.
     * @param pointB second point.
     * @return the slope using the math formula.
     */
    private double calculateSlope(Point pointA, Point pointB) {

        /* calculate the slope using the two points formula */
        return (pointA.getY() - pointB.getY())
        / (pointA.getX() - pointB.getX());
    }

    /**
     * @return this slope value.
     */
    public double getSlope() {
        return slope;
    }

    /**
     * @return true if this slope is undefined or false otherwise.
     */
    public boolean isUndefined() {
        return isUndefined;
    }
}
