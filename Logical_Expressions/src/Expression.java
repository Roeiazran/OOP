// 204058366
import java.util.List;
import java.util.Map;
/**
 * Provides interface for the logicical expressions.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public interface Expression {

  /**
   * evalute the true value of the expression from a given variables
   * assignment array.
   * @param assignment assignments array.
   * @return the true or false according to the expression value.
   */
  Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

  /**
   * A convenience method to evaluate expression.
   * @return true or false as the expression evaluated to.
   */
  Boolean evaluate() throws Exception;

  /**
   * get the expression variable as List.
   * @return variables array.
   */
  List<String> getVariables();

  /**
   * @return string form of the expression.
   */
  @Override
  String toString();

    /**
     * assings expression in place of a variable and returns a copy.
     * @param var the variable to replace
     * @param expression the expression to replace var with.
     * @return a copy of the expression assigned.
     */
  Expression assign(String var, Expression expression);

  /**
   * Returns the expression tree resulting from converting all the
   * operations to the logical Nand operation.
   * @return nandified expression.
   */
  Expression nandify();

  /**
   * Returns the expression tree resulting from converting all the
   * operations to the logical Nor operation.
   * @return norified expression.
   */
  Expression norify();

  /**
   * simplifies the expression.
   * @return simplified expression.
   */
  Expression simplify();
}