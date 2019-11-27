/*
 * @author  Roberto Fronteddu
 * @version 1.0
 * @since   2019-11-23
 * A bitonic array is comprised onf an increasing sequence of integers followed immediately by a decreasing sequence of integers
 */

package rfronteddu.java_experiments.algorithms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class BitonicSearch
{
    private int[] array;
    private int index = 0;
    private boolean found = false;
    private int tipping;
    private static final Logger logger = LoggerFactory.getLogger (BitonicSearch.class);
    // < ------------------------------------------------------------------------------- >
    // < ------------------------------------------------------------------------------- >

    public BitonicSearch (int n) {
        array = new int[n];
    }

    public void add (int number) {
        if (index == array.length) {
            throw new RuntimeException ("Trying to add more elements than supported");
        }

        if (!bitonicCheck (number)) {
            throw new RuntimeException ("Array input is not bitonic");
        }

        tippingPoint (number);
        array[index] = number;
        index++;
    }

    public boolean search (int number) {
        if (number > array[tipping]) {
            // half1
            int[] half1 = Arrays.copyOfRange (array, 0, tipping);
            int possibleIndex = Arrays.binarySearch (half1, number);
            return half1[possibleIndex] == number;
        } else {
            logger.info ("Number is smaller searching in second half");
            // half2
            int[] half2 = Arrays.copyOfRange (array, tipping, index);
            Arrays.sort (half2);
            int possibleIndex = Arrays.binarySearch (half2, number);
            return half2[possibleIndex] == number;
        }
    }

    // ##########################################################################

    private boolean bitonicCheck (int number) {
        // the only thing that is forbidden is decreasing and then increasing
        if (index > 2) {
            int el1 = array[index - 1];
            int el2 = array[index - 2];
            logger.info ("EL2: {} EL1 {} NUM {}", el2, el1, number);
            boolean decrease = el2 > el1;
            boolean increase = number > el1;
            return !(decrease && increase);
        }
        return true;
    }

    private void tippingPoint (int number) {
        if (found || index == 0) {
            return;
        }

        if (number < array[index - 1]) {
            found = true;
            tipping = index;
            logger.info ("Found tipping point: {} at index {}", number, index);
        }
    }
}
