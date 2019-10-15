package Backend.CellTypes.WaTorCells;

import Backend.CellTypes.WaTorCell;
import Backend.Enumerators.WaTorStates;

/**
 * Purpose: Shark cell class. This class extends the WaTorCell (which itself extends Node). This cell differs from the fish
 * in that it keeps track of the amount of energy it needs until it dies.
 * Dependencies: WaTorCell
 * Example:
 * SharkCell wc = new SharkCell(10, 12, WaTorStates.SHARK.getState(), 6, 5);
 *
 * @author - Benjamin Lawrence, bcl19
 */
public class SharkCell extends WaTorCell {

    private int energy;

    /**
     * Constructor for shark cell class.
     *
     * @param r      - Integer row location of the shark cell
     * @param c      - Integer column location of the shark cell
     * @param state  - Integer initial state of the cell. Never used because this will always be a shark
     * @param energy - Integer initial energy level of the cell until the shark dies
     * @param time   - Integer amount of time until the shark reproduces
     */
    public SharkCell(int r, int c, int state, int energy, int time) {
        super(r, c, WaTorStates.SHARK.getState(), time);

        this.energy = energy;
    }

    /**
     * @return - Integer amount of energy left until the shark is dead
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * @param newEnergy - Integer value to set the amount of energy the shark has until it dies
     */
    public void setEnergy(int newEnergy) {
        energy = newEnergy;
    }

    /**
     * @return - Decrements the amount of energy left by one then returns that amount
     */
    public int updateEnergy() {
        energy--;
        return energy;
    }
}
