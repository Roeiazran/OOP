
/**
 * Equation class provides method to reason about linear equations.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class Equation {

    private Slope slope;
    private double slopeVal;
    private double yAxisValue;

    /**
     * Constuctor gets the line and calculate its slope and int y axis
     * intersection value.
     * @param line the line to evalute eqution for.
     */
    public Equation(Line line) {
        Point start = line.start();
        Point end = line.end();

        /* calculate the slope */
        slope = new Slope(start, end);
        slopeVal = slope.getSlope();

        /* setting the b value of y = mx + b */
        yAxisValue = start.getY() - slope.getSlope() * start.getX();
    }


    /**
     * check if two equation lines are parallels.
     * @param other the other equations.
     * @return true if lines parallels or false otherwise.
     */
    public boolean isParallels(Equation other) {
        Slope slope1 = slope;
        Slope slope2 = other.slope;

        /* check if noth slopes undefined */
        if (isBothSlopesUndefined(slope1, slope2)) {
            return true;
        }

        /* if only one of them is undefied */
        if (slope1.isUndefined() && !slope2.isUndefined()
        || !slope1.isUndefined() && slope2.isUndefined()) {
            return false;
        }

        /* or perhaps equal */
        if (slopeVal == other.slopeVal) {
            return true;
        }
        return false;
    }

    /**
     * method to check if a given two slopes are both undefined.
     * @param slope1 the slope.
     * @param slope2 the slope.
     * @return true if the are and false otherwise.
     */
    public boolean isBothSlopesUndefined(Slope slope1, Slope slope2) {

        if (slope1.isUndefined() && slope2.isUndefined()) {
            return true;
        }
        return false;
    }

    /**
     * @return the y intersection coordinate with the y Axis.
     */
    public double getYAxisValue() {
        return yAxisValue;
    }

    /**
     * @return the slope value of the equation.
     */
    public double getSlopeVal() {
        return slopeVal;
    }

    /**
     * @return the slope instance.
     */
    public Slope getSlope() {
        return slope;
    }
}
