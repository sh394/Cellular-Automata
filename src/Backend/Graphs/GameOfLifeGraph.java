package Backend.Graphs;

import Backend.CellTypes.GameOfLifeCell;
import Backend.Graph;
import Rules.SimulationRules.GameOfLifeRules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Purpose: To hold the game of life cells, update the game of life cells, and return the current state of the entire graph
 * to the front end.
 * Dependencies: ArrayList, Collection, List, GameOfLifeCell, Graph, GameOfLifeRules
 *
 * @author - Benjamin Lawrence, bcl19
 */
public class GameOfLifeGraph implements Graph {
    private ArrayList<ArrayList<GameOfLifeCell>> cells;
    private GameOfLifeRules rule;

    /**
     * Constructor for Game Of Life graph
     */
    public GameOfLifeGraph() {
        rule = new GameOfLifeRules();
        cells = new ArrayList<>();
    }

    /**
     * Adds a node to game of life graph which consists of a game of life cell
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
        ArrayList<GameOfLifeCell> rowNodes = cells.get(row);
        while (rowNodes.size() - 1 < col) {
            rowNodes.add(null);
        }
        rowNodes.set(col, new GameOfLifeCell(row, col, state));
    }

    /**
     * Updates all of the cells in the graph to their new states based on the current states. All of the new states are
     * calculated and then implemented only after every state has been calculated.
     */
    @Override
    public void updateGraph() {
        ArrayList<ArrayList<GameOfLifeCell>> updatedNodes = new ArrayList<>();

        for (int row = 0; row < cells.size(); row++) {
            updatedNodes.add(new ArrayList<>(Collections.nCopies(cells.get(row).size(), null)));
            for (int col = 0; col < cells.get(row).size(); col++) {
                ArrayList<Integer> neighborStates = neighborStates(cells.get(row).get(col));
                int updatedState = rule.nextGeneration(cells.get(row).get(col).getState(), neighborStates);
                GameOfLifeCell updatedCell = new GameOfLifeCell(row, col, updatedState);
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
        for (ArrayList<GameOfLifeCell> row : cells) {
            for (GameOfLifeCell col : row) {
                listOfStates.add("" + col.getState());
            }
        }
        return listOfStates;
    }

    /**
     *  Updates the number of neighbors that can kill a cell or bring it back to life
     *
     * @param newNumNeighbors - Double value for new number of neighbors
     */
    @Override
    public void updateStat(double newNumNeighbors) {
        rule.updateNumNeighbors(newNumNeighbors);
    }

    private ArrayList<Integer> neighborStates(GameOfLifeCell cell) {
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
