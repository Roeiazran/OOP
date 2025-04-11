// 204058366 Roei Azran
/**
 * The DescribeNumbers Class provides methods on array numbers.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class DescribeNumbers {
    /**
     * The main function.
     * @param args strings array input from the command line.
     */
    public static void main(String[] args) {
        int[] numbers = stringsToInts(args);

        System.err.println("min: " + min(numbers));
        System.err.println("max: " + max(numbers));
        System.err.println("avg: " + avg(numbers));
    }

    /**
     * Returns the minimun value in a given Integers array.
     * @param numbers Integers array.
     * @return the minimum value in the array.
     */
    public static int min(int[] numbers) {
        int min = numbers[0];

        /* iterate and compare */
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        return min;
    }

    /**
     * Returns the maximun value in a given Integers array.
     * @param numbers Integers array.
     * @return the maximum value in the array.
     */
    public static int max(int[] numbers) {
        int max = numbers[0];

        /* iterate and compare */
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        return max;
    }

    /**
     * Returns the average value from Integers array.
     * @param numbers the input array.
     * @return total sum of the array devided by the total count.
     */
    public static float avg(int[] numbers) {
        float sum = 0;
        int count = numbers.length;

        /* calculate total array sum */
        for (Integer item : numbers) {
            sum += item;
        }
        return sum / count;
    }

    /**
     * Converts String array of digits into Integers array.
     * @param numbers the Strings array.
     * @return Integers array.
     */
    public static int[] stringsToInts(String[] numbers) {
        int len = numbers.length;
        int[] resultArray = new int[len];

        /* iterate and parse */
        for (int i = 0; i < len; i++) {
            resultArray[i] = Integer.parseInt(numbers[i]);
        }
        return resultArray;
    }
}
