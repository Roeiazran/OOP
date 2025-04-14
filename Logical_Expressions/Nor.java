import java.util.Map;
/**
 * Provides method for the logicical Nor operator.
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class Nor extends BinaryExpression {

    /**
     * constructor.
     * @param left the left expression.
     * @param right the right expression.
     */
    public Nor(Expression left, Expression right) {
        super(left, right, "V");
    }

    /**
     * simplifies the expression.
     * @return simplified expression.
     */
    public Expression simplify() {
        Expression left = getLeft().simplify();
        Expression right = getRight().simplify();

        /* get the evaluations map */
        Map<String, Boolean[]> evalMap = tryEvaluateExpression(left, right);
        boolean leftVal;
        boolean rightVal;

        /* check for both evaluated */
        if (evalMap.get("left")[0] && evalMap.get("right")[0]) {
            leftVal = evalMap.get("left")[1];
            rightVal = evalMap.get("right")[1];

            return new Val(!(leftVal || rightVal));
        } else {

            /* check if left evaluated */
            if (evalMap.get("left")[0]) {
                leftVal = evalMap.get("left")[1];

                /* return according to the value */
                if (leftVal) {
                    return new Val(false);
                }

                return new Not(right);

                /* check if right evaluated */
            } else if (evalMap.get("right")[0]) {
                rightVal = evalMap.get("right")[1];

                /* return according to the value */
                if (rightVal) {
                    return new Val(false);
                }

                return new Not(left);
            }
        }

        /* both do not evaluate try simplify the same expression */
        if (left.toString().equals(right.toString())) {

            /* return left arbitrarly*/
            return new Not(left);
        }

        /* can't simplify any further */
        return new Nor(left, right);
    }

    /**
     * Returns the expression tree resulting from converting all the
     * operations to the logical Nor operation.
     * @return norified expression.
     */
    public Expression norify() {
        Expression left = getLeft().norify();
        Expression right = getRight().norify();

        return new Nor(left, right);
    }

    /**
     * Returns the expression tree resulting from converting all the
     * operations to the logical Nand operation.
     * @return nandified expression.
     */
    public Expression nandify() {
        Expression left = getLeft().nandify();
        Expression right = getRight().nandify();

        Nand nAnd = new Nand(new Nand(left, left), new Nand(right, right));

        return new Nand(nAnd, nAnd);
    }

    /**
     * A convenience method to evaluate expression.
     * @return true or false as the expression evaluated to.
     */
    public Boolean evaluate() throws Exception {
        Expression left = getLeft();
        Expression right = getRight();

        return !(left.evaluate() || right.evaluate());
    }

    /**
     * evalute the true value of the expression from a given variables
     * assignment array.
     * @param assignment assignments array.
     * @return the true or false according to the expression value.
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {

        Expression left = getLeft();
        Expression right = getRight();
        return !(left.evaluate(assignment) || right.evaluate(assignment));
    }

    /**
     * returnes new instance of And class.
     * @param left the new left expression.
     * @param right the new right expression.
     * @return the new left and right in new And instace.
     */
    public Expression setLeftAndRight(Expression left, Expression right) {
        return new Nor(left, right);
    }
}
