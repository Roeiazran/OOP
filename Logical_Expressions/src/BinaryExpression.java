// 204058366
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Provides abstract methods to all the binary expressions classes.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public abstract class BinaryExpression extends BaseExpression {

    private Expression left;
    private Expression right;
    private String sign;

    /* abstract methods */
    abstract Expression setLeftAndRight(Expression leftEx, Expression rightEx);

    /**
     * constructor.
     * @param left the left expression.
     * @param right the right expression.
     * @param sign sign of the expression.
     */
    public BinaryExpression(Expression left, Expression right, String sign) {
        this.left = left;
        this.right = right;
        this.sign = sign;
    }

    /**
     * getter.
     * @return left.
     */
    public Expression getLeft() {
        Expression leftCopy = left;
        return leftCopy;
    }

    /**
     * getter.
     * @return right.
     */
    public Expression getRight() {
        Expression rightCopy = right;
        return rightCopy;
    }

    /**
     * getter.
     * @return sign.
     */
    public String getSign() {
        String singCopy = sign;
        return singCopy;
    }

    /**
     * get the expression variable as List.
     * @return variables array.
     */
    public List<String> getVariables() {

        /* call recursively for the left expression */
        List<String> leftVarList = left.getVariables();

        /* call recursively for the right expression */
        List<String> rightVarList = right.getVariables();

        /* add the variables to the global array */
        addtoVarsWithoutDuplicates(leftVarList);
        addtoVarsWithoutDuplicates(rightVarList);

        return getVariablesArray();
    }

    /**
     * @return string form of the expression.
     */
    @Override
    public String toString() {

        /* get the left and right strings recursively */
        String leftString = left.toString();
        String rightString = right.toString();

        /* check for singly wrapped expression and unwrappe it*/
        if (doOneWreppedExpression(rightString)) {
            rightString =  stripParenthesis(rightString);
        }

        if (doOneWreppedExpression(leftString)) {
            leftString = stripParenthesis(leftString);
        }

        /* return the string */
        return new String().concat("(" + leftString
            + " " + sign + " " + rightString + ")");
    }

    /**
     * removes paranthesis from single wrapped expression.
     * @param exp the wrapped expression.
     * @return substring of the original string.
     */
    private String stripParenthesis(String exp) {
        return exp.substring(1, 2);
    }

    /**
     * assings expression in place of a variable and returns a copy.
     * @param var the variable to replace
     * @param expression the expression to replace var with.
     * @return a copy of the expression assigned.
     */
    @Override
    public Expression assign(String var, Expression expression) {

        /* assign the variable left and right recursively */
        Expression leftExp = getLeft().assign(var, expression);
        Expression rightExp = getRight().assign(var, expression);

        /* return the new expression */
        return setLeftAndRight(leftExp, rightExp);
    }

    /**
     * tries to evaluate the subleft and subright expression.
     * @param left left expression.
     * @param right right expression.
     * @return a map containing the result.
     */
    public Map<String, Boolean[]> tryEvaluateExpression(Expression left,
     Expression right) {

        /* initialize the <key ("left" or "right"), [do evaluates, value]> */
        Map<String, Boolean[]> result = new HashMap<>();
        boolean leftVal;
        boolean rightVal;
        try {

            /* try evaluate left expression */
            leftVal = left.evaluate();

            /* evaluated */
            result.put("left", new Boolean[]{true, leftVal});

            /* try evaluate right expression */
            try {
                rightVal = right.evaluate();

                /* evaluated */
                result.put("right", new Boolean[]{true, rightVal});
                return result;
            } catch (Exception e) {

                /* right don't evaluate */
                result.put("right", new Boolean[]{false});
                return result;
            }
        } catch (Exception e) {

            /* left don't evaluates try evaluate right */
            result.put("left", new Boolean[]{false});

            try {
                rightVal = right.evaluate();

                /* evaluated */
                result.put("right", new Boolean[]{true, rightVal});
                return result;

            } catch (Exception e2) {

                /* both do not evaluates */
                result.put("left", new Boolean[]{false});
                result.put("right", new Boolean[]{false});
                return result;
            }
        }
    }

}
