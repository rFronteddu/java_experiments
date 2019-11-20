/*
 * @author  Roberto Fronteddu
 * @version 1.0
 * @since   2019-11-16
 *
 * This class solves the problem of percolation, namely that of detecting if there is a path between
 * the top and bottom of a grid. This class uses a CompressedWeightedUnion 4 times each time a new cell is opened which
 * maintains a complexity ~ 4 log n in the worst case.
 * 2 virtual (a top and a bottom one) nodes are used to test percolation.
 */

package rfronteddu.java_experiments.algorithms.unionfind.questions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rfronteddu.java_experiments.algorithms.unionfind.CompressedWeightedUnion;

public class Percolation
{
    private static final Logger logger = LoggerFactory.getLogger (Percolation.class);

    private int numberOfOpenSites;
    // percolation test will try to union top and bottom
    private Cell virtualTopCell;
    private Cell virtualBottomCell;

    // support structure for the IDs
    private CompressedWeightedUnion cwu;

    // y x
    // 0,0 top-left
    // n,n bottom-right
    private Cell [][] site;

    private int TOP_ROW = 0;
    private int BOTTOM_ROW;

    // n * n
    private int numberOfCells;
    // < ------------------------------------------------------------------------------- >

    /**
     * Creates n-by-n grid, with all sites initially blocked
     * @param n nxn grid
     */
    public Percolation (int n) {
        this.numberOfCells = n;
        BOTTOM_ROW = n - 1;
        cwu = new CompressedWeightedUnion();
        boolean ok = cwu.init (n * n);
        if (!ok) {
            return;
        }
        initGrid (n);
    }

    /**
     * opens the site (row, col) if it is not open already
     * @param row y coordinate of the site
     * @param col x coordinate of the site
     */
    public void open (int row, int col) {
    logger.info ("Opening cell (y, x) = ({}, {}): ", row, col);
        if (!site[row][col].isOpen) {
            site[row][col].isOpen = true;
            numberOfOpenSites++;
            allUnion (row, col);
        }
    }

    /**
     * @param row y coordinate of the site
     * @param col x coordinate of the site
     * @return true if the site is open
     */
    public boolean isOpen (int row, int col) {
        return site[row][col].isOpen;
    }

    /**
     * @param row y coordinate of the site
     * @param col x coordinate of the site
     * @return true if the site is closed
     */
    public boolean isFull (int row, int col) {
        return !site[row][col].isOpen;
    }

    /**
     * @return number of open sites, does not take the virtual ones into account
     */
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    /**
     * Tested as connection between top and bottom virtual nodes (O(1))
     * @return true if the grid percolates
     */
    public boolean percolates() {
        logger.info ("VT: {} VB: {}", virtualTopCell.id, virtualBottomCell.id);
        return cwu.areConnected (virtualTopCell.id, virtualBottomCell.id);
    }

    /**
     * Simple percolation test on a 4x4 grid.
     * V V X X
     * X V V X
     * X X V X
     */
    public void percolateTest() {
        open (0,0);
        open (0,1);
        open (1,1);
        open (1,2);
        open (2,2);
        open (3,2);
    }

    /**
     * Prints grid status
     */
    public void printStatus() {
        logger.info ("*********************************************************");
        logger.info ("Number of open sites: " + numberOfOpenSites);
        logger.info ("Percolates? " + percolates());
        for (int i = 0; i < numberOfCells; i++) {
            for (int j = 0; j < numberOfCells; j++) {
                logger.info ("Cell ({},\t{}) \t= {}", i, j, isOpen (i, j));
            }
        }
    }

    // ###############################################################################

    private void allUnion (int row, int col) {
        // need to do union 4 sides
        conditionedUnion (Direction.TOP,     row, col);
        conditionedUnion (Direction.BOTTOM,  row, col);
        conditionedUnion (Direction.LEFT,    row, col);
        conditionedUnion (Direction.RIGHT,   row, col);

        if (row == TOP_ROW) {
            if (!cwu.areConnected (site[row][col].id, virtualTopCell.id)) {
                cwu.union (site[row][col].id, virtualTopCell.id);
            }
        } else if (row == BOTTOM_ROW) {
            if (!cwu.areConnected (site[row][col].id, virtualBottomCell.id)) {
                cwu.union (site[row][col].id, virtualBottomCell.id);
            }
        }
    }

    private void conditionedUnion (Direction direction, int row, int col) {
        int pID = site[row][col].id;
        int qY;
        int qX;
        int qID;
        switch (direction) {
            case TOP:
                if (row == 0) {
                    return;
                }
                qY = row - 1;
                qX = col;
                break;
            case LEFT:
                if (col == 0) {
                    return;
                }
                qY = row;
                qX = col - 1;
                break;
            case BOTTOM:
                if (row ==  numberOfCells - 1) {
                    return;
                }
                qY = row + 1;
                qX = col;
                break;
            case RIGHT:
                if (col ==  numberOfCells - 1) {
                    return;
                }
                qY = row;
                qX = col + 1;
                break;
            default:
                logger.warn ("Direction not supported");
                return;
        }

        if (!site[qY][qX].isOpen) {
            return;
        }

        qID = site[qY][qX].id;

        if (!cwu.areConnected (pID, qID)) {
            cwu.union (pID, qID);
        }
    }

    private void initGrid (int n) {
        virtualTopCell      = new Cell (true, n);
        virtualBottomCell   = new Cell (true, n + 1);
        site = new Cell[n][n];

        // as long as the id are different it doesn't really matter
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                site[i][j] = new Cell (false, j + i);
            }
        }
    }
}
