package Backend;

import java.util.List;

/**
 * Purpose: To provide the interface structure for Graph classes
 * Dependencies: List
 * Example: N/A
 * Additional Details: This is an interface so it is not meant to be implemented on its own. Typical graphs include their
 * own sets of rules and behaviors by which they operate.
 *
 * @author - Benjamin Lawrence, bcl19
 */
public interface Graph {

    /**
     * Adds a node to the graph structure at the specified row and column with the corresponding state.
     *
     * @param row   - integer row value of new node
     * @param col   - integer column value of new node
     * @param state - integer initial state value of node being added to graph
     */
    void addNode(int row, int col, int state);

    /**
     * Tells the graph to update its state. The exact implementation of this is left to the class implementing the graph
     * interface. This should be called every frame if the graph is storing a cellular automata.
     */
    void updateGraph();

    /**
     * Returns the state of the graph as a list with string values for the states of the cells. This is done so that the
     * caller of this getter cannot modify the data used.
     *
     * @return - list of strings that represent the states of each node
     */
    List<String> getCellsAsList();

    /**
     * Updates the statistic used by the graph to the given value
     *
     * @param newStat - new double value for statistic used in determining graph behavior
     */
    void updateStat(double newStat);
}
