import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * Provides method for the Value instances.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class Val implements Expression {

    private boolean val;

    /**
     * constructor.
     * @param val the value.
     */
    public Val(boolean val) {
        this.val = val;
    }

    /**
     * simplifies the expression.
     * @return simplified expression.
     */
    public Expression simplify() {
        return new Val(val);
    }

    /**
     * Returns the expression tree resulting from converting all the
     * operations to the logical Nor operation.
     * @return norified expression.
     */
    public Expression norify() {
        return new Val(val);
    }

    /**
     * Returns the expression tree resulting from converting all the
     * operations to the logical Nand operation.
     * @return nandified expression.
     */
    public Expression nandify() {
        return new Val(val);
    }

    /**
     * A convenience method to evaluate expression.
     * @return true or false as the expression evaluated to.
     */
    public Boolean evaluate() throws Exception {
        return val;
    }

    /**
     * evalute the true value of the expression from a given variables
     * assignment array.
     * @param assignment assignments array.
     * @return the true or false according to the expression value.
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return val;
    }

    /**
     * implenetation override on Expression interface.
     * @return empty list.
     */
    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    /**
     * @return string representation of the value.
     */
    @Override
    public String toString() {

        if (val) {
            return "(T)";
        }
        return "(F)";
    }

    /**
     * @param expression the expression to assign.
     * @param var the variable to assign to.
     * @return a copy of the value..
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Val(val);
    }

}
