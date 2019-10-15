package Backend.Enumerators;

/**
 * Purpose: Enumerator for varying types of fire simulation cells. The three types of cells are empty cell, tree (or
 * foliage), and burning.
 * Dependencies: None
 * Example:
 * if (cell.checkNeighborsForFire() && cell.getState == FireStates.TREE.getState()) {
 * cell.setState(FireStates.BURNING.getState());
 * }
 *
 * @author - Benjamin Lawrence, bcl19
 */
public enum FireStates {

    EMPTY(0),
    TREE(1),
    BURNING(2);

    private int state;

    FireStates(int s) {
        state = s;
    }

    /**
     * @return - Returns the integer value representing the enumerator object's state
     */
    public int getState() {
        return state;
    }

}
