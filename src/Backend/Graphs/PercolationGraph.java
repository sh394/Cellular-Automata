package Backend.Graphs;

import Backend.CellTypes.PercolationCell;
import Backend.Graph;
import Rules.Rules;
import Rules.SimulationRules.PercolationRules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Purpose: To hold the percolation cells, update the percolation cells, and return the current state of the entire graph
 * to the front end.
 * Dependencies: ArrayList, Collection, List, PercolationCell, Graph, PercolationRules
 *
 * @author - Benjamin Lawrence, bcl19
 */
public class PercolationGraph implements Graph {
    private ArrayList<ArrayList<PercolationCell>> cells;
    private Rules rule;

    /**
     * Constructor for Percolation graph
     */
    public PercolationGraph() {
        rule = new PercolationRules();
        cells = new ArrayList<>();
    }

    /**
     * Adds a node to Percolation graph which consists of a Percolation cell
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
        ArrayList<PercolationCell> rowNodes = cells.get(row);
        while (rowNodes.size() - 1 < col) {
            rowNodes.add(null);
        }
        rowNodes.set(col, new PercolationCell(row, col, state));
    }

    /**
     * Updates all of the cells in the graph to their new states based on the current states. All of the new states are
     * calculated and then implemented only after every state has been calculated.
     */
    @Override
    public void updateGraph() {
        ArrayList<ArrayList<PercolationCell>> updatedNodes = new ArrayList<>();
        for (int row = 0; row < cells.size(); row++) {
            updatedNodes.add(new ArrayList<>(Collections.nCopies(cells.get(row).size(), null)));
            for (int col = 0; col < cells.get(row).size(); col++) {
                ArrayList<Integer> neighborStates = neighborStates(cells.get(row).get(col));
                int updatedState = rule.nextGeneration(cells.get(row).get(col).getState(), neighborStates);
                PercolationCell updatedCell = new PercolationCell(row, col, updatedState);
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
        for (ArrayList<PercolationCell> row : cells) {
            for (PercolationCell col : row) {
                listOfStates.add("" + col.getState());
            }
        }
        return listOfStates;
    }

    /**
     *  Since percolation is not statistic or probability based, this does nothing
     *
     * @param newStat - Double value for new statistic
     */
    @Override
    public void updateStat(double newStat) {
        // Method intentionally left blank as there is nothing to update for percolation
        return;
    }

    private ArrayList<Integer> neighborStates(PercolationCell cell) {
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
        if (cell.getRow() - 1 >= 0 && cell.getCol() - 1 >= 0) {
            neighborStates.add(cells.get(cell.getRow() - 1).get(cell.getCol() - 1).getState());
        }
        if (cell.getRow() - 1 >= 0 && cell.getCol() + 1 < cells.get(cell.getRow() - 1).size()) {
            neighborStates.add(cells.get(cell.getRow() - 1).get(cell.getCol() + 1).getState());
        }
        if (cell.getRow() + 1 < cells.size() && cell.getCol() - 1 >= 0) {
            neighborStates.add(cells.get(cell.getRow() + 1).get(cell.getCol() - 1).getState());
        }
        if (cell.getRow() + 1 < cells.size() && cell.getCol() + 1 < cells.get(cell.getRow() + 1).size()) {
            neighborStates.add(cells.get(cell.getRow() + 1).get(cell.getCol() + 1).getState());
        }

        return neighborStates;
    }
}
