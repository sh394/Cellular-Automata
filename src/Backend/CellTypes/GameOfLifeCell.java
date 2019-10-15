package Backend.CellTypes;

import Backend.Node;

/**
 * Purpose: Game of Life cell class. This class does not have any special functionality outside of the node class.
 * Dependencies: Node
 * Example:
 * GameOfLifeCell wc = new GameOfLifeCell(10, 12, GameOfLifeStates.ALIVE.getState());
 *
 * @author - Benjamin Lawrence, bcl19
 */
public class GameOfLifeCell extends Node {

    /**
     * Constructor for Game of Life cell class.
     *
     * @param r     - Integer row position for the cell
     * @param c     - Integer column position for the cell
     * @param state - Integer initial state of the cell
     */
    public GameOfLifeCell(int r, int c, int state) {
        super(r, c, state);
    }
}
