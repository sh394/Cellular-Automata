package Backend.CellTypes;

import Backend.Node;

/**
 * Purpose: Spreading fire cell class. This class does not have any special functionality outside of the node class.
 * Dependencies: Node
 * Example:
 * FireCell wc = new FireCell(10, 12, FireStates.TREE.getState());
 *
 * @author - Benjamin Lawrence, bcl19
 */
public class FireCell extends Node {

    /**
     * Constructor for spreading fire cell class.
     *
     * @param r     - Integer row position for the cell
     * @param c     - Integer column position for the cell
     * @param state - Integer initial state of the cell
     */
    public FireCell(int r, int c, int state) {
        super(r, c, state);
    }
}
