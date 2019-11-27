/*
 * @author  Roberto Fronteddu
 * @version 1.0
 * @since   2019-11-23
 *
 * The 3SUM problem asks if a given set of n real numbers contains three elements that sum to zero
 *
 * The following algorithm was taken from wikipedia. The idea is to test recursively
 * all the elements from an ordered array.
 *
 * If the sum is bigger than zero we try to get the next smaller from the end while if it's smaller
 * than zero we increase the smaller element not under evaluation.
 *
 * This algorithm takes O (n log(n))        + O(n)                          + O(n)
 *                      sorting       element under eval        while that check for greater/smaller
 *
 * And uses O(n) for memory (array used for sorting)
 *
 * I also used an HashSet to avoid duplicate solutions.
 */

package rfronteddu.java_experiments.algorithms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;

public class Sum3
{
    private int[] array;
    private HashSet<String> solutionSet;
    private int index;
    private static final Logger logger = LoggerFactory.getLogger (Sum3.class);
    // < ------------------------------------------------------------------------------->

    /**
     * @param n number of elements
     */
    public Sum3 (int n) {
        solutionSet = new HashSet<>();
        array = new int[n];
    }

    /**
     * Will throw RuntimeException if we try to add more than n elements
     * @param element new element
     */
    public void add (int element) {
        if (index == array.length) {
            throw new RuntimeException ("addInput: Trying to add more input than specified");
        }
        array[index] = element;
        index++;
    }

    /**
     * Will throw RuntimeException if there are less than 3 elements
     */
    public  HashSet<String> sum3() {
        if (index < array.length) {
            throw new RuntimeException ("Tried to call sum3 but there are not enough elements in the array: "
                    + index + " expecting " + array.length);
        }
        // O(n log(n))
        Arrays.sort (array);
        // array is now ordered from smaller to larger
        for (int i = 0; i < index - 1; i++) {
            // O(n)
            // first three are smaller, second smaller, bigger
            int a = array[i];
            int start = i + 1;
            int end = index - 1;

            while (start < end) {
                // O(n)
                int b = array[start];
                int c = array[end];
                if (a + b + c == 0) {
                    solutionSet.add ("(" + a + ", " + b + ", " + c + ")");
                    start++;
                    end--;
                } else if (a + b + c > 0) {
                    // if sum is greater let's pick next larger from the end
                    end--;
                } else {
                    // if smaller, let's pick next smaller from the beginning
                    start = start + 1;
                }
            }
        }
        return solutionSet;
    }
}
