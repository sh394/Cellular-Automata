package Backend.Enumerators;

/**
 * Purpose: Enumerator for varying types of percolation simulation cells. The three types of cells are blocked cell,
 * percolated, and open
 * Dependencies: None
 * Example:
 * if (cell.checkNeighborsForPercolated() && cell.getState == PercolationStates.OPEN.getState()) {
 * cell.setState(PercolationStates.PERCOLATED.getState());
 * }
 *
 * @author - Benjamin Lawrence, bcl19
 */
public enum PercolationStates {

    BLOCKED(0),
    PERCOLATED(1),
    OPEN(2);

    private int state;

    PercolationStates(int s) {
        state = s;
    }

    /**
     * @return - Returns the integer value representing the enumerator object's state
     */
    public int getState() {
        return state;
    }
}
