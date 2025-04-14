
package listeners;
/**
 *  Provides methods for the block counter.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class Counter {

    private int counter;

    /**
     * Ctor.
     * @param initalValue of the counter.
     */
    public Counter(int initalValue) {
        this.counter = initalValue;
    }

    /**
     * adds number to the counter.
     * @param number the number to add.
     */
    public void increase(int number) {
        counter += number;
    }

    /**
     * subtract number from current count.
     * @param number the number to subtract.
     */
    public void decrease(int number) {
        counter -= number;
    }

    /**
     * getter.
     * @return a copy of this counter.
     */
    public int getValue() {
        int copy = counter;
        return copy;
    }
 }
