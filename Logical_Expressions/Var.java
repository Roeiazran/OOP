// 204058366
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * Provides method for the logicical Or operator.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class Var implements Expression {

    private String var;

    /**
     * constructor.
     * @param var the variable string.
     */
    public Var(String var) {
        this.var = var;
    }

    /**
     * simplifies the expression.
     * @return already simple expression.
     */
    public Expression simplify() {
        return new Var(var);
    }

    /**
     * Returns the expression tree resulting from converting all the
     * operations to the logical Nor operation.
     * @return norified expression.
     */
    public Expression norify() {
        return new Var(var);
    }

    /**
     * Returns the expression tree resulting from converting all the
     * operations to the logical Nand operation.
     * @return nandified expression.
     */
    public Expression nandify() {
        return new Var(var);
    }

    /**
     * A convenience method to evaluate expression.
     * @return true or false as the expression evaluated to.
     */
    @Override
    public Boolean evaluate() throws Exception  {
        throw new Exception("assignemt missing var");
    }

    /**
     * evalute the true value of the expression from a given variables
     * assignment array.
     * @param assignment assignments array.
     * @return the true or false according to the expression value.
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {

        if (!assignment.keySet().contains(var)) {
            throw new Exception("assignemt missing var");
        }

        return assignment.get(var);
    }

    /**
     * implenetation override on Expression interface.
     * @return list containg thiw variable.
     */
    @Override
    public List<String> getVariables() {
        List<String> varArray = new ArrayList<>();
        varArray.add(new Var(var).var);
        return varArray;
    }

    /**
     * @return string representation of the value.
     */
    @Override
    public String toString() {
        return "(" + new Var(var).var + ")";
    }

    /**
     * @param expression the expression to assign.
     * @param variable the variable to assign to.
     * @return a copy of the value..
     */
    @Override
    public Expression assign(String variable, Expression expression) {

        /* check if the variable match and replace it */
        if (var.equals(variable)) {
            return expression;
        }

        /* the variable do not match */
        return new Var(var);
    }
}
