// 204058366 Roei Azran
/**
 * The Factorial Class provides factorial methods on numbers.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class Factorial {
    /**
     * The main function.
     * @param args strings array input from the command line.
     */
    public static void main(String[] args) {
        long n = Integer.parseInt(args[0]);

        System.out.println("recursive: " + factorialRecursive(n));
        System.out.println("iterative: " + factorialIter(n));
    }

    /**
     * Calculate n factorial iteratively.
     * @param n
     * @return n factorial.
     */
    public static long factorialIter(long n) {
        long factorial = 1;

        /* calculate the factorial */
        for (int i = 2; i <= n; i++) {
            factorial = factorial * i;
        }
        return factorial;
    }

    /**
     * Calculate n factorial reursively.
     * @param n
     * @return n factorial.
     */
    public static long factorialRecursive(long n) {
        if (n == 1) {
            return 1;
        }
        return n * factorialRecursive(n - 1);
    }
}
