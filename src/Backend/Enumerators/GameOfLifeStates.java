package Backend.Enumerators;

/**
 * Purpose: Enumerator for varying types of game of life cells. The two types of cells are dead cell and living cell
 * Dependencies: None
 * Example:
 * if (cell.numNeighbors == 5 && cell.getState == GameOfLifeStates.ALIVE.getState()) {
 * cell.setState(GameOfLifeStates.DEAD.getState());
 * }
 *
 * @author - Benjamin Lawrence, bcl19
 */
public enum GameOfLifeStates {

    DEAD(0),
    ALIVE(1);

    private int state;

    GameOfLifeStates(int s) {
        state = s;
    }

    /**
     * @return - Returns the integer value representing the enumerator object's state
     */
    public int getState() {
        return state;
    }
}
