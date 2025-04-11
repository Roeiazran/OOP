// 204058366
import java.util.List;
/**
 * Provides abstract methods to all the unary expressions classes.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public abstract class UnaryExpression extends BaseExpression {

    private Expression exp;
    private String sign;
    abstract Expression setExpression(Expression exp);

    /**
     * constructor.
     * @param exp
     * @param sign
     */
    public UnaryExpression(Expression exp, String sign) {
        this.sign = sign;
        this.exp = exp;
    }

    /**
     * get the expression variable as List.
     * @return variables array.
     */
    public List<String> getVariables() {

        List<String> varList = exp.getVariables();

        return varList;
    }

    /**
     * getter for the expression.
     * @return copy of the expression.
     */
    public Expression getExpression() {
        Expression copyExpression = exp;
        return copyExpression;
    }

    @Override
    public String toString() {

        /* check if the expression is in form of (exp) */
        if (doOneWreppedExpression(exp.toString())) {

            /* only add the sign */
            return new String().concat(sign + exp.toString());
        }

        /* in any other case wrap the expression with parenthesis */
        return new String().concat(sign + "(" + exp.toString() + ")");
    }


    /**
     * assings expression in place of a variable and returns a copy.
     * @param var the variable to replace
     * @param expression the expression to replace var with.
     * @return a copy of the expression assigned.
     */
    @Override
    public Expression assign(String var, Expression expression) {

        Expression expAssigned = exp.assign(var, expression);

        return setExpression(expAssigned);
    }

}
