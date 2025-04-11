// 204058366
import java.util.Map;
/**
 * Provides method for the logicical Xnor operator.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class Xnor extends BinaryExpression {

    /**
     * constructor.
     * @param left left expression.
     * @param right right expression.
     */
    public Xnor(Expression left, Expression right) {
        super(left, right, "#");
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

            return new Val(!((leftVal && !rightVal)
                || (!leftVal && rightVal)));
        }
        /* both do not evaluate try simplify the same expression */
        if (left.toString().equals(right.toString())) {

            /* return x#x = 1*/
            return new Val(true);
        }

        /* can't simplify any further */
        return new Xnor(left, right);
    }

    /**
     * Returns the expression tree resulting from converting all the
     * operations to the logical Nor operation.
     * @return norified expression.
     */
    public Expression norify() {
        Expression left = getLeft().norify();
        Expression right = getRight().norify();

        Nor leftNor = new Nor(left, new Nor(left, right));
        Nor rightNor = new Nor(right, new Nor(left, right));

        return new Nor(leftNor, rightNor);
    }

    /**
     * Returns the expression tree resulting from converting all the
     * operations to the logical Nand operation.
     * @return nandified expression.
     */
    public Expression nandify() {
        Expression left = getLeft().nandify();
        Expression right = getRight().nandify();

        Nand leftNan = new Nand(new Nand(left, left), new Nand(right, right));
        Nand rightNan = new Nand(left, right);

        return new Nand(leftNan, rightNan);
    }

    /**
     * A convenience method to evaluate expression.
     * @return true or false as the expression evaluated to.
     */
    public Boolean evaluate() throws Exception {
        Expression left = getLeft();
        Expression right = getRight();
        return !((left.evaluate() && !right.evaluate())
            || (!left.evaluate() && right.evaluate()));
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
        return !((left.evaluate(assignment) && !right.evaluate(assignment))
            || (!left.evaluate(assignment) && right.evaluate(assignment)));
    }

    /**
     * returnes new instance of And class.
     * @param left the new left expression.
     * @param right the new right expression.
     * @return the new left and right in new And instace.
     */
    public Expression setLeftAndRight(Expression left, Expression right) {
        return new Xnor(left, right);
    }
}
