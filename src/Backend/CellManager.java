package Backend;

import Backend.Graphs.*;

import java.util.List;
import java.util.Map;

/**
 * Purpose: Manages the different cell graph types along with initializing all backend values. This class keeps the backend
 * graph data structure from being directly accessible from the controller class.
 * Dependencies: List, Map, BackendConstants
 * Example:
 * CellManager cm = new CellManager("WaTor", {{0, 0}, {1, 1}});
 *
 * @author - Benjamin Lawrence, bcl19
 */
public class CellManager {

    private Graph cells;

    /**
     * Constructor for Cell Manager class
     *
     * @param simulationType - simulation type given as a string value. All types are listed in BackendConstants.java
     * @param initialStates  - Initial states of all the nodes in the grid stored as integers
     * @param args           - Varaiable arguments. TODO: use to pass in initial states of WaTor reproduction rates and regeneration times
     */
    public CellManager(String simulationType, int[][] initialStates, Object... args) {
        if (initialStates == null || initialStates.length == 0 || initialStates[0].length == 0) {
            cells = null;
        }

        Map<String, Graph> possibleGraphs = Map.ofEntries(
                Map.entry(BackendConstants.GAME_OF_LIFE_SIMULATION_NAME, new GameOfLifeGraph()),
                Map.entry(BackendConstants.PREDATORY_PREY_SIMULATION_NAME, new WaTorGraph()),
                Map.entry(BackendConstants.PERCOLATION_SIMULATION_NAME, new PercolationGraph()),
                Map.entry(BackendConstants.SEGREGATION_SIMULATION_NAME, new SegregationGraph()),
                Map.entry(BackendConstants.FIRE_SIMULATION_NAME, new FireGraph())
        );

        cells = possibleGraphs.get(simulationType);
        initializeGraph(initialStates);
    }

    /**
     * Tells the cell manager to update its graph to the next iteration. This method takes in no arguments and has no return
     * value.
     */
    public void updateCurrentGraphState() {
        cells.updateGraph();
    }

    /**
     * Is used to update a single cell in the grid. Typical implementation would be for the user to click on a cell and
     * choose its state.
     *
     * @param row      - The integer row of the cell
     * @param col      - The integer column of the cell
     */
    public void updateCellState(int row, int col) {
        // TODO: change cell state upon user input
    }

    /**
     * Allows the user to change the statistic parameter of the front end. Most graphs are based on a single statistic and
     * this allows the user to vary that statistic.
     *
     * @param stat - The double value for the new statistic
     */
    public void updateSimStat(double stat) {
        cells.updateStat(stat);
    }

    /**
     * @return - Returns the states of all the cells as a single list. All the cells' states are stored as strings. This
     * requires the front end to know the dimensions of the grid.
     */
    public List<String> getStatesAsList() {
        return cells.getCellsAsList();
    }


    private void initializeGraph(int[][] initialStates) {
        for (int row = 0; row < initialStates.length; row++) {
            for (int col = 0; col < initialStates[0].length; col++) {
                cells.addNode(row, col, initialStates[row][col]);
            }
        }
    }

}
