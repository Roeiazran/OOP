/**
 * Provides main method.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class ExpressionTest {

    /**
     * creates expressions and prints.
     * @param args the args array.
     */
    public static void main(String[] args) {

        Expression a = new Var("a");
        Expression b = new Var("b");
        Expression c = new Var("c");
        Expression d = new Var("d");
        
        Expression e = new Xor(
            new Or(
                new And(a,b), c
                ), new Not(d)
            );

        /* print */
        System.out.println(e);

        /* assign */
        e = e.assign("a", new Val(true));
        e = e.assign("b", new Val(true));
        e = e.assign("c", new Val(false));
        e = e.assign("d", new Val(false));
        System.out.println(e);

        /* evaluate */
        try {
            System.out.println(e.evaluate());
        } catch (Exception exp) {
            System.err.println(exp); 
        }
        
        /* simplify the assigned expression */
        System.out.println(e.simplify());


        // System.err.println(e.nandify());
        // System.err.println(e.norify());
    }
}
