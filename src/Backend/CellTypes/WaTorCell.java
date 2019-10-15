package Backend.CellTypes;

import Backend.Node;

/**
 * Purpose: General WaTorCell type. This class is intentionally not abstract as it can be used to hold the space of an empty
 * cell with infinity time lef to reproduce. This is used in the Wa-Tor cellular automata.
 * Dependencies: Node
 * Example:
 * WaTorCell wc = new WaTorCell(10, 12, WaTorStates.EMPTY.getState(), 10);
 *
 * @author - Benjamin Lawrence, bcl19
 */
public class WaTorCell extends Node {

    private int timeLeftToReproduce;

    /**
     * Constructor for Wa-To cell type.
     *
     * @param r     - Integer row location of the cell
     * @param c     - Integer column location of the cell
     * @param state - Integer initial state of the cell. Can by any state from the WaTorStates enumerators
     * @param time  - Integer time left until the cell can reproduce.
     */
    public WaTorCell(int r, int c, int state, int time) {
        super(r, c, state);

        this.timeLeftToReproduce = time;
    }

    /**
     * @return - Integer time left until the cell can reproduce
     */
    public int getTimeLeftToReproduce() {
        return timeLeftToReproduce;
    }

    /**
     * @param newTime - Sets the integer time left until the cell can reproduce.
     */
    public void setReproductionTime(int newTime) {
        timeLeftToReproduce = newTime;
    }
}
