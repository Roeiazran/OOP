import java.util.Map;
/**
 * Provides method for the logicical Not operator.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class Not extends UnaryExpression {

    /**
     * constructor.
     * @param exp the expression.
     */
    public Not(Expression exp) {
        super(exp, "~");
    }

    /**
     * simplifies the expression.
     * @return simplified expression.
     */
    public Expression simplify() {

        /* simplify the expression further */
        Expression simple = getExpression().simplify();

        try {

            /* try to evaluate for value */
            boolean val = simple.evaluate();

            /* if secceeded return the value notted */
            return new Val(!val);
        } catch (Exception e) {

            /* if evaluation did not secceed return the original expression */
            return new Not(simple);
        }
    }

    /**
     * Returns the expression tree resulting from converting all the
     * operations to the logical Nor operation.
     * @return norified expression.
     */
    public Expression norify() {
        Expression exp = getExpression().norify();

        return new Nor(exp, exp);
    }

    /**
     * Returns the expression tree resulting from converting all the
     * operations to the logical Nand operation.
     * @return nandified expression.
     */
    public Expression nandify() {
        Expression exp = getExpression().nandify();

        return new Nand(exp, exp);
    }

    /**
     * A convenience method to evaluate expression.
     * @return true or false as the expression evaluated to.
     */
    public Boolean evaluate() throws Exception {
        return !getExpression().evaluate();
    }

    /**
     * evalute the true value of the expression from a given variables
     * assignment array.
     * @param assignment assignments array.
     * @return the true or false according to the expression value.
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !getExpression().evaluate(assignment);
    }

    /**
     * setter to the expression.
     * @param newExpression the value to update expression.
     * @return new setted instace.
     */
    public Expression setExpression(Expression newExpression) {
        return new Not(newExpression);
    }
}
