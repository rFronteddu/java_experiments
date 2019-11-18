/*
 * @author  Roberto Fronteddu
 * @version 1.0
 * @since   2019-11-17
 *
 * Social network connectivity.
 * Given a social network containing n members and a
 * log file containing m timestamps at which times pairs
 * of members formed friendships, design an algorithm
 * to determine the earliest time at which all members
 * are connected (i.e., every member is a friend of a
 * friend of a friend ... of a friend).
 *
 * Assume that the log file is sorted by timestamp and
 * that friendship is an equivalence relation.
 * The running time of your algorithm should be m log n
 * or better and use extra space proportional to n.
 *
 * I assume the file is ordered in term of timestamp (from older to newer)
 */

package rfronteddu.java_experiments.algorithms.unionfind.questions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rfronteddu.java_experiments.algorithms.unionfind.WeightedQuickUnion;

import java.io.*;

public class SocialNetwork1
{
    private static final Logger logger = LoggerFactory.getLogger (SocialNetwork1.class);
    private WeightedQuickUnion wqu;
    private long earlyConnectionTimestamp = -1;
    private int numberOfElements;
    // < ------------------------------------------------------------------------------- >

    /**
     * @param path
     * @return -1 if never fully connected, earliest timestamp otherwise
     */
    public long socialNetworkConnectivity (String path) {
        long start = System.currentTimeMillis();

        File file = new File (path);
        try {
            BufferedReader br = new BufferedReader (new FileReader (file));
            readAndManageFile (br);
        } catch (FileNotFoundException e) {
            logger.error ("It was not possible to find the file " + path);
            return -1;
        }
        logger.info ("socialNetworkConnectivity with {} elements took: {}",
                numberOfElements,
                (System.currentTimeMillis() - start) + "ms");
        return earlyConnectionTimestamp;
    }

    // ###################################################################################

    /**
     * @param splitLine a string[] containing: timestamp p q
     * @return true when completed
     */
    private boolean manageLine (String[] splitLine) {
        long timestamp = Long.parseLong (splitLine[0]);
        int p = Integer.parseInt (splitLine[1]);
        int q = Integer.parseInt (splitLine[2]);

        logger.debug ("timestamp: " + timestamp);
        logger.debug ("p:         " + p);
        logger.debug ("q:         " + q);
        if(!wqu.areConnected (p, q)) {
            wqu.union (p, q);
            logger.debug ("Adding connection between: " + p + " " + q);
        }

        if (wqu.isFullyConnected()) {
            earlyConnectionTimestamp = timestamp;
            return true;
        }
        return false;
    }

    private void readAndManageFile (BufferedReader br) {
        wqu = new WeightedQuickUnion();
        try {
            String n = br.readLine();
            if (n == null) {
                logger.error ("Something is wrong in the specified file");
                return;
            }
            numberOfElements = Integer.parseInt (n);
            if (!wqu.init (numberOfElements)) {
                return;
            }

            // do not keep reading if we found the timestamp
            do {
                String line = br.readLine();
                if (line == null) {
                    logger.info ("Last line was read");
                    return;
                }
                String[] splitLine = line.split (" ");
                if (splitLine.length != 3) {
                    logger.error ("Line was not formatted correctly: " + splitLine.toString ());
                    return;
                }
                manageLine (splitLine);
            } while (earlyConnectionTimestamp == -1);
        } catch (IOException e) {
            logger.error ("IO exception while reading line: " + e.toString());
        }
    }
}
