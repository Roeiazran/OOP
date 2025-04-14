
/**
 * The Sort Class provides methods to sort integers array.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class Sort {

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
     */
    public static void bubbleSort(int[] numbers) {
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
