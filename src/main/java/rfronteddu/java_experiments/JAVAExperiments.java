/*
 * @author  Roberto Fronteddu
 * @version 1.0
 * @since   2019-11-16
 *
 *
 */

package rfronteddu.java_experiments;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rfronteddu.java_experiments.algorithms.unionfind.UnionFind;
import rfronteddu.java_experiments.algorithms.unionfind.questions.CanonicalUnionFind;
import rfronteddu.java_experiments.algorithms.unionfind.questions.Percolation;
import rfronteddu.java_experiments.algorithms.unionfind.questions.SocialNetwork1;
import rfronteddu.java_experiments.prompt.Prompt;

public class JAVAExperiments
{
    private static final Logger logger = LoggerFactory.getLogger (JAVAExperiments.class);
    // < ------------------------------------------------------------------------------- >

    public static void main (String[] args) {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        lc.getLogger ("ROOT").setLevel (Level.INFO);

        switch (Prompt.selectExperiment()) {
            case UNION_FIND:
                unionFind();
                break;
            case SOCIAL_NETWORK_1:
                socialNetwork();
                break;
            case CANONICAL_UNION_FIND:
                canonicalUnionFind();
                break;
            case PERCOLATION:
                percolation();
                break;
        }

        Prompt.enterNumberToTerminate();
        logger.debug ("Termination requested");
        System.exit (0);
    }

    // ###################################################################################

    private static void canonicalUnionFind() {
        CanonicalUnionFind cuf = new CanonicalUnionFind();
        cuf.init (10);
        if (!cuf.areConnected (1, 2)) {
            cuf.union (1, 2);
        }
        if (!cuf.areConnected (4, 5)) {
            cuf.union (4, 5);
        }
        if (!cuf.areConnected (2, 3)) {
            cuf.union (2, 3);
        }
        if (!cuf.areConnected (3, 4)) {
            cuf.union (3, 4);
        }
        logger.info ("Largest element: " + cuf.find (1));
    }

    private static void percolation() {
        Percolation p = new Percolation (4);
        long start = System.nanoTime();
        p.percolateTest();
        logger.info ("Percolation took: " + (System.nanoTime() - start)+ "ns");
        p.printStatus();
    }

    private static void socialNetwork() {
        SocialNetwork1 socialNetwork1 = new SocialNetwork1();
        long timestamp = socialNetwork1.socialNetworkConnectivity ("testFiles\\social_network_1.txt");
        if (timestamp != -1) {
            logger.info ("Earliest timestamp: " + timestamp);
        } else {
            logger.info ("Full connection was not detected");
        }
    }

    private static void unionFind() {
        UnionFind uf = new UnionFind (Prompt.getUnionFindAlgorithm(), Prompt.getNumberOfElements());
        do {
            int[] pair = Prompt.getPair();
            uf.unionFind (pair[0], pair[1]);
        }  while (Prompt.keepGoing());
    }
}
