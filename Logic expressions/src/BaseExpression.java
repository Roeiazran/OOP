import java.util.ArrayList;
import java.util.List;
/**
 * Provides abstact class for the binary and unary expressions.
 */
public abstract class BaseExpression implements Expression {

    /* list for the expression variables */
    private List<String> variables = new ArrayList<>();

    /**
     * getter.
     * @return the variables array.
     */
    public List<String> getVariablesArray() {
        List<String> copy = new ArrayList<>();
        copy.addAll(variables);
        return copy;
    }

    /**
     * adds to the variables list if the value not in it already.
     * @param toAdd the list to add to variables.
     */
    public void addtoVarsWithoutDuplicates(List<String> toAdd) {

        /* loop for each item in toAdd */
        for (int i = 0; i < toAdd.size(); i++) {

            /* if variables does'nt contains item insert it */
            if (!variables.contains(toAdd.get(i))) {
                variables.add(toAdd.get(i));
            }
        }
    }

    /**
     * checkes if a given string is a single wrapped expression.
     * @param exp the expression string.
     * @return true if the expression single wrapped and false otherwise.
     */
    public boolean doOneWreppedExpression(String exp) {
        final int maxStringPossible = 3;
        final char leftParenthesis = '(';
        final char rightParenthesis = ')';

        /* try do determine from the length */
        if (exp.length() > maxStringPossible) {
            return false;
        }

        /* check for the charactes to match */
        if (exp.charAt(0) == leftParenthesis
            && exp.charAt(2) == rightParenthesis) {
            return true;
        }

        /* no match */
        return false;
    }
}
