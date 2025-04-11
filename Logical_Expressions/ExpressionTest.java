//204058366
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
        Expression x = new Var("x");
        Expression y = new Var("y");
        Expression z = new Var("z");
        Expression ex = new Xnor(new Nand(x, new Val(false)),
                new Not(new And(new Or(x, y),
                        new Xor(new Val(true), z))));

        System.err.println(ex);
        System.err.println(ex.nandify());
        System.err.println(ex.norify());
        System.err.println(ex.simplify());
    }
}
