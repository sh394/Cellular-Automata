package Backend.Enumerators;

/**
 * Purpose: Enumerator for varying types of wa-tor simulation cells. The three types of cells are empty cell, shark cell,
 * and fish cell.
 * Dependencies: None
 * Example:
 * if (cell.getEnergy() == 0 && cell.getState() == WaTorStates.SHARK.getState()) {
 * cell.setState(WaTorStates.EMPTY.getState());
 * }
 *
 * @author - Benjamin Lawrence, bcl19
 */
public enum WaTorStates {
    EMPTY(0),
    SHARK(1),
    FISH(2);

    private int state;

    WaTorStates(int s) {
        state = s;
    }

    /**
     * @return - Returns the integer value representing the enumerator object's state
     */
    public int getState() {
        return state;
    }
}
