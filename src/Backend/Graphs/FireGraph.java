package Backend.Graphs;

import Backend.CellTypes.FireCell;
import Backend.Graph;
import Rules.SimulationRules.SpreadingFireRules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Purpose: To hold all of the cells and update the cells as the graph progresses throughout time in the fire simulation
 * Dependencies: ArrayList, Collection, List, FireCell, Graph, SpreadingFireRules
 *
 * @author - Benjamin Lawrence, bcl19
 */
public class FireGraph implements Graph {
    private ArrayList<ArrayList<FireCell>> cells;
    private SpreadingFireRules rule;

    /**
     * Constructor for fire graph
     */
    public FireGraph() {
        rule = new SpreadingFireRules();
        cells = new ArrayList<>();
    }

    /**
     * Adds a node/cell to the graph (called cell in the front end and node in the backend)
     *
     * @param row   - integer row value of new node
     * @param col   - integer column value of new node
     * @param state - integer initial state value of node being added to graph
     */
    @Override
    public void addNode(int row, int col, int state) {
        while (cells.size() - 1 < row) {
            cells.add(new ArrayList<>());
        }
        ArrayList<FireCell> rowNodes = cells.get(row);
        while (rowNodes.size() - 1 < col) {
            rowNodes.add(null);
        }
        rowNodes.set(col, new FireCell(row, col, state));
    }

    /**
     * Updates all of the cells in the graph to their new states based on the current states. All of the new states are
     * calculated and then implemented only after every state has been calculated.
     */
    @Override
    public void updateGraph() {
        ArrayList<ArrayList<FireCell>> updatedNodes = new ArrayList<>();
        for (int row = 0; row < cells.size(); row++) {
            updatedNodes.add(new ArrayList<>(Collections.nCopies(cells.get(row).size(), null)));
            for (int col = 0; col < cells.get(row).size(); col++) {
                ArrayList<Integer> neighborStates = neighborStates(cells.get(row).get(col));
                int updatedState = rule.nextGeneration(cells.get(row).get(col).getState(), neighborStates);
                FireCell updatedCell = new FireCell(row, col, updatedState);
                updatedNodes.get(row).set(col, updatedCell);
            }
        }
        cells = updatedNodes;
    }

    /**
     *
     * @return - Returns current state of all the cells as strings in a list
     */
    @Override
    public List<String> getCellsAsList() {
        List<String> listOfStates = new ArrayList<>();
        for (ArrayList<FireCell> row : cells) {
            for (FireCell col : row) {
                listOfStates.add("" + col.getState());
            }
        }
        return listOfStates;
    }

    /**
     *  Updates the probability of catching fire to the new probability
     *
     * @param newProb - Double new probability value for catching fire
     */
    @Override
    public void updateStat(double newProb) {
        rule.updateProbCatch(newProb);
    }

    private ArrayList<Integer> neighborStates(FireCell cell) {
        ArrayList<Integer> neighborStates = new ArrayList<>();
        if (cell.getRow() - 1 >= 0) {
            neighborStates.add(cells.get(cell.getRow() - 1).get(cell.getCol()).getState());
        }
        if (cell.getRow() + 1 < cells.size()) {
            neighborStates.add(cells.get(cell.getRow() + 1).get(cell.getCol()).getState());
        }
        if (cell.getCol() - 1 >= 0) {
            neighborStates.add(cells.get(cell.getRow()).get(cell.getCol() - 1).getState());
        }
        if (cell.getCol() + 1 < cells.get(cell.getRow()).size()) {
            neighborStates.add(cells.get(cell.getRow()).get(cell.getCol() + 1).getState());
        }

        return neighborStates;
    }
}
