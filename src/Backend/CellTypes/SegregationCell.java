package Backend.CellTypes;

import Backend.Node;

/**
 * Purpose: Segregation cell class. This class does not have any special functionality outside of the node class.
 * Dependencies: Node
 * Example:
 * SegregationCell wc = new SegregationCell(10, 12, SegregationCellStates.AGENT_2.getState());
 *
 * @author - Benjamin Lawrence, bcl19
 */
public class SegregationCell extends Node {

    /**
     * Constructor for segregation cell class.
     *
     * @param r     - Integer row position for the cell
     * @param c     - Integer column position for the cell
     * @param state - Integer initial state of the cell
     */
    public SegregationCell(int r, int c, int state) {
        super(r, c, state);
    }
}
