/*
 * @author  Roberto Fronteddu
 * @version 1.0
 * @since   2019-11-23
 * A bitonic array is comprised onf an increasing sequence of integers followed immediately by a decreasing sequence of integers
 */


package rfronteddu.java_experiments.algorithms;

import java.util.Arrays;

public class BitonicSearch
{
    private int[] array;
    private int index = 0;
    private boolean notFound = true;
    private int tipping;
    // < ------------------------------------------------------------------------------- >

    public BitonicSearch (int n) {
        array = new int[n];
    }

    public void addEl (int number) {
        if (index == array.length) {
            throw new RuntimeException ("Trying to add more elements than supported");
        }

        if (!bitonicCheck (number)) {
            throw new RuntimeException ("Array was not bitonic");
        }

        array[index] = number;
        index++;
    }

    public boolean search (int number) {
        if (number > array[tipping]) {
            // half1
            int[] half1 = Arrays.copyOfRange (array, 0, tipping);
            int possibleIndex = Arrays.binarySearch (half1, number);
            return possibleIndex == number;
        } else {
            // half2
            int[] half2 = Arrays.copyOfRange (array, 0, tipping);
            Arrays.sort (half2);
            int possibleIndex = Arrays.binarySearch (half2, number);
            return possibleIndex == number;
        }
    }

    // ##########################################################################

    private boolean bitonicCheck (int number) {
        if (notFound && index > 1) {
            if (number < array[index - 1]) {
                notFound = false;
                tipping = index;
            }
        }


        if (index > 3) {
            int  el0 = number;
            int  el1 = array[index - 1];
            int  el2 = array[index - 2];
            int  el3 = array[index - 3];
            return (el0 > el1 && el1 > el2) || (el0 < el1 && el1 > el2 && el2 > el3) || (el0 < el1 && el1 < el2);
        }
        return true;
    }


}
