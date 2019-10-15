package Backend.CellTypes.WaTorCells;

import Backend.CellTypes.WaTorCell;
import Backend.Enumerators.WaTorStates;

/**
 * Purpose: Fish cell class. This class extends the WaTorCell (which itself extends Node). This cell has no functionality
 * outside of the WaTor cell and exists for readability reasons so that it can be easily distinguished from the Shark cell
 * (which does have special functionality).
 * Dependencies: WaTorCell
 * Example:
 * FishCell wc = new FishCell(10, 12, WaTorStates.FISH.getState(), 5);
 *
 * @author - Benjamin Lawrence, bcl19
 */
public class FishCell extends WaTorCell {

    /**
     * Constructor for the Fish Cell class.
     *
     * @param r     - Integer row location of the cell
     * @param c     - Integer column location of the cell
     * @param state - Integer initial state of the cell. Not needed since this will always be a fish type
     * @param time  - Integer initial time left to reproduce for the fish
     */
    public FishCell(int r, int c, int state, int time) {
        super(r, c, WaTorStates.FISH.getState(), time);
    }
}
