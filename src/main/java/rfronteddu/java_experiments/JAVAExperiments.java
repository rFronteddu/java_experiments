/**
 * @author  Roberto Fronteddu
 * @version 1.0
 * @since   2019-11-16
 *
 * A simple application to test a number of algorithms' implementations and to show how I write code.
 */

package rfronteddu.java_experiments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rfronteddu.java_experiments.algorithms.unionfind.UnionFind;
import rfronteddu.java_experiments.prompt.Prompt;

public class JAVAExperiments
{
    public static void main (String[] args) {
        switch (Prompt.selectExperiment()) {
            case UNION_FIND:
                UnionFind uf = new UnionFind (Prompt.getUnionFindAlgorithm(), Prompt.getNumberOfElements());
                do {
                    int[] pair = Prompt.getPair();
                    uf.unionFind (pair[0], pair[1]);
                }  while (Prompt.keepGoing());
                break;
        }
        logger.info ("Termination requested");
        System.exit (0);

    }

    // < ------------------------------------------------------------------------------- >
    private static final Logger logger = LoggerFactory.getLogger (JAVAExperiments.class);
}
