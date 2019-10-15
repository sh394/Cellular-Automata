package Backend.CellTypes;

import Backend.Node;

/**
 * Purpose: Percolation cell class. This class does not have any special functionality outside of the node class.
 * Dependencies: Node
 * Example:
 * PercolationCell wc = new PercolationCell(10, 12, PercolationStates.PERCOLATED.getState());
 *
 * @author - Benjamin Lawrence, bcl19
 */
public class PercolationCell extends Node {

    /**
     * Constructor for percolation cell class.
     *
     * @param r     - Integer row position for the cell
     * @param c     - Integer column position for the cell
     * @param state - Integer initial state of the cell
     */
    public PercolationCell(int r, int c, int state) {
        super(r, c, state);
    }
}
