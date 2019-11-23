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
import rfronteddu.java_experiments.algorithms.BitonicSearch;
import rfronteddu.java_experiments.algorithms.Sum3;
import rfronteddu.java_experiments.algorithms.unionfind.UnionFind;
import rfronteddu.java_experiments.algorithms.unionfind.questions.CanonicalUnionFind;
import rfronteddu.java_experiments.algorithms.unionfind.questions.Percolation;
import rfronteddu.java_experiments.algorithms.unionfind.questions.SocialNetwork1;
import rfronteddu.java_experiments.prompt.Prompt;

import java.time.Duration;
import java.time.Instant;

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
            case SUM3:
                sum3();
                break;
            case BITONIC_SEARCH:
                bitonicSearch();
                break;
        }

        Prompt.enterNumberToTerminate();
        logger.debug ("Termination requested");
        System.exit (0);
    }

    // ###################################################################################

    private static void bitonicSearch() {
        int n = Prompt.getSum3MaxElements();
        BitonicSearch bs = new BitonicSearch (n);
        for (int i = 0; i < n; i++) {
            int el = Prompt.getNextEl ("Enter number");
            bs.addEl (el);
        }
        boolean found = bs.search (Prompt.getNextEl ("Enter number to search"));
        if (found) {
            logger.info ("Element was found");
        } else {
            logger.info ("Element was NOT found");
        }
    }

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
        Instant start = Instant.now();
        Percolation p = new Percolation (4);
        p.percolateTest();
        Instant finish = Instant.now();
        logger.info ("Percolation took: " + Duration.between (start, finish).toMillis() + "ms");
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

    private static void sum3() {
        int n = Prompt.getSum3MaxElements();
        Sum3 sum3 = new Sum3 (n);

        for (int i = 0; i < n; i++) {
            int el = Prompt.getNextEl ("Enter number");
            sum3.add (el);
        }
        sum3.sum3();
    }

    private static void unionFind() {
        UnionFind uf = new UnionFind (Prompt.getUnionFindAlgorithm(), Prompt.getNumberOfElements());
        do {
            int[] pair = Prompt.getPair();
            uf.unionFind (pair[0], pair[1]);
        }  while (Prompt.keepGoing());
    }
}
