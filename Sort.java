// 204058366 Roei Azran
/**
 * The Sort Class provides methods to sort in descending or ascending order.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class Sort {
    /**
     * The main function.
     * @param args strings array input from the command line.
     */
    public static void main(String[] args) {

        /* the args numbers sub array length */
        int numbersArrayLength = args.length - 1;

        /* parse to numbers array */
        int[] numbers = stringsToInts(args, numbersArrayLength);

        /* sort the array */
        bubbleSort(numbers);

        /* check if reversing needed */
        if (args[0].equals("desc")) {
            reverseArray(numbers);
        }

        /* print the resulted array */
        printNumbers(numbers);
    }

    /**
     * Printing given Integers array.
     * @param numbers the input array.
     */
    public static void printNumbers(int[] numbers) {
        int len = numbers.length;

        for (int i = 0; i < len; i++) {
            System.err.print(numbers[i]);

            /* checks for one last space */
            if (i != len - 1) {
                System.err.print(" ");
            }
        }
    }

    /**
     * Swaping two slots i and j in a given input array.
     * @param numbers the input array.
     * @param i the index to swap.
     * @param j the index to swap.
     */
    public static void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];

        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    /**
     * Sorting given array in ascending order.
     * @param numbers
     * @return sorted array.
     */
    public static int[] bubbleSort(int[] numbers) {
        int n = numbers.length;
        boolean isSwapped = true;

        /* repeat until array is sorted */
        while (isSwapped) {
            isSwapped = false;

            for (int i = 0; i < n - 1; i++) {

                /* check if swap is needed */
                if (numbers[i] > numbers[i + 1]) {
                    isSwapped = true;
                    swap(numbers, i, i + 1);
                }
            }

            /* check for sorted array */
            if (!isSwapped) {
                break;
            }

            /* continue on the remaining subarray */
            n--;
        }
        return numbers;
    }

    /**
     * Reversing given Integers array.
     * @param numbers the input array.
     * @return the input array in reversed order.
     */
    public static int[] reverseArray(int[] numbers) {
        int n = numbers.length - 1;
        int i = 0;

        /* two pointers technique */
        while (i < n) {
            swap(numbers, i, n);
            i++;
            n--;
        }
        return numbers;
    }

    /**
     * Converts String array of digits into Integers array.
     * @param numbers the Strings array.
     * @param len the length of the array to parse.
     * @return Integers array.
     */
    public static int[] stringsToInts(String[] numbers, int len) {
        int[] resultArray = new int[len];
        int indexIntoArray = 0;

        for (int i = 0; i < numbers.length; i++) {

            /* avoids any non digits characters*/
            if (numbers[i].matches("[0-9]+")) {
                resultArray[indexIntoArray] = Integer.parseInt(numbers[i]);
                indexIntoArray++;
            }
        }
        return resultArray;
    }
}