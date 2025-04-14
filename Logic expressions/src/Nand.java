import java.util.Map;
/**
 * Provides method for the Not and binary operator.
* @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class Nand extends BinaryExpression  {

    /**
     * constructor.
     * @param left the left expression.
     * @param right the right expression.
     */
    public Nand(Expression left, Expression right) {
        super(left, right, "A");
    }

    /**
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

            return new Val(!(leftVal && rightVal));
        } else {

            /* check if left evaluated */
            if (evalMap.get("left")[0]) {
                leftVal = evalMap.get("left")[1];

                /* return according to the value */
                if (leftVal) {
                    return new Not(right);
                }

                return new Val(true);

                /* check if right evaluated */
            } else if (evalMap.get("right")[0]) {
                rightVal = evalMap.get("right")[1];

                /* return according to the value */
                if (rightVal) {
                    return new Not(left);
                }

                return new Val(true);
            }
        }

        /* both do not evaluate try simplify the same expression */
        if (left.toString().equals(right.toString())) {

            /* return left arbitrarly*/
            return new Not(left);
        }

        /* can't simplify any further */
        return new Nand(left, right);
    }

    /**
     * @return the expression norify.
     */
    public Expression norify() {
        Expression left = getLeft().norify();
        Expression right = getRight().norify();

        Expression nor = new Nor(new Nor(left, left), new Nor(right, right));

        return new Nor(nor, nor);
    }

    /**
     * @return the expression nandify.
     */
    public Expression nandify() {
        Expression left = getLeft().nandify();
        Expression right = getRight().nandify();

        return new Nand(left, right);
    }

    /**
     * @return the truth value of the expression.
     */
    public Boolean evaluate() throws Exception {

        Expression left = getLeft();
        Expression right = getRight();
        return !(left.evaluate() && right.evaluate());
    }

    /**
     * @param assignment variables truth values array.
     * @return the truth value of the expression.
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        Expression left = getLeft();
        Expression right = getRight();

        return !(left.evaluate(assignment) && right.evaluate(assignment));
    }

    /**
     * setter.
     * @param left the left expression.
     * @param right the right expresssion.
     * @return new instance from the new expressions.
     */
    public Expression setLeftAndRight(Expression left, Expression right) {
        return new Nand(left, right);
    }
}
