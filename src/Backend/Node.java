package Backend;

/**
 * Purpose: Abstract class to store the basic construction of a Node object. Node objects are used in the creation of
 * Graphs.
 * Dependencies: None
 * Example:
 * Node n1 = new GameOfLifeCell(1, 3, GameOfLifeStates.FULL.getState());
 * Additional Details: This class is abstract so it cannot itself be instantiated.
 *
 * @author - Benjamin Lawrence, bcl19
 */
public abstract class Node {
    private int row;
    private int col;
    private int state;

    /**
     * Constructor for the Node class.
     *
     * @param r            - integer row of the node in the 2D graph
     * @param c            - integer column of the node in the 2D graph
     * @param initialState - starting state of the node stored as an int
     */
    public Node(int r, int c, int initialState) {
        row = r;
        col = c;
        state = initialState;
    }

    /**
     * @return - returns the row of the node as an integer
     */
    public int getRow() {
        return row;
    }

    /**
     * @return - returns the column of the node as an integer
     */
    public int getCol() {
        return col;
    }

    /**
     * @return - returns the state of the node as an integer
     */
    public int getState() {
        return state;
    }
}
