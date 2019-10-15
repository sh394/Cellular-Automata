package Backend.Graphs;

import Backend.CellTypes.SegregationCell;
import Backend.Enumerators.Segregation.SegregationAgentStates;
import Backend.Enumerators.Segregation.SegregationCellStates;
import Backend.Graph;
import Rules.SimulationRules.SegregationRules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Purpose: To hold the Segregation cells, update the Segregation cells, and return the current state of the entire graph
 * to the front end.
 * Dependencies: ArrayList, Collection, List, SegregationCell, Graph, SegregationRules, SegregationCellStates, SegregationAgentStates
 *
 * @author - Benjamin Lawrence, bcl19
 */
public class SegregationGraph implements Graph {
    private ArrayList<ArrayList<SegregationCell>> cells;
    private SegregationRules rule;

    /**
     * Constructor for the Segregation graph
     */
    public SegregationGraph() {
        rule = new SegregationRules();
        cells = new ArrayList<>();
    }

    /**
     * Adds a node to the Segregation graph which consists of a Segregation cell
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
        ArrayList<SegregationCell> rowNodes = cells.get(row);
        while (rowNodes.size() - 1 < col) {
            rowNodes.add(null);
        }
        rowNodes.set(col, new SegregationCell(row, col, state));
    }

    /**
     * Updates all of the cells in the graph to their new states based on the current states. All of the new states are
     * calculated and then implemented only after every state has been calculated.
     */
    @Override
    public void updateGraph() {
        ArrayList<ArrayList<SegregationCell>> updatedNodes = new ArrayList<>();
        for (int row = 0; row < cells.size(); row++) {
            updatedNodes.add(new ArrayList<>(Collections.nCopies(cells.get(row).size(), null)));
            for (int col = 0; col < cells.get(row).size(); col++) {
                ArrayList<Integer> neighborStates = neighborStates(cells.get(row).get(col));
                int updatedState = rule.nextGeneration(cells.get(row).get(col).getState(), neighborStates);
                if (updatedState == SegregationAgentStates.DISATISFIED.getState()) {
                    updatedState = updatedState * cells.get(row).get(col).getState() + updatedState;
                }
                SegregationCell updatedCell = new SegregationCell(row, col, updatedState);
                updatedNodes.get(row).set(col, updatedCell);
            }
        }
        moveDissatisfied(updatedNodes);
        cells = updatedNodes;
    }

    /**
     *
     * @return - Returns current state of all the cells as strings in a list
     */
    @Override
    public List<String> getCellsAsList() {
        List<String> listOfStates = new ArrayList<>();
        for (ArrayList<SegregationCell> row : cells) {
            for (SegregationCell col : row) {
                listOfStates.add("" + col.getState());
            }
        }
        return listOfStates;
    }

    /**
     *  Updates the threshold value for how many similar neighbors and agent wants
     *
     * @param newThresh - Double value ratio of similar neighbors required by agent
     */
    @Override
    public void updateStat(double newThresh) {
        rule.updateThreshold(newThresh);
    }

    private ArrayList<Integer> neighborStates(SegregationCell cell) {
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

    private void moveDissatisfied(ArrayList<ArrayList<SegregationCell>> updatedNodes) {
        ArrayList<SegregationCell> emptyCells = findEmpty(updatedNodes);
        ArrayList<SegregationCell> dissatisfiedCells = findDissatisfied(updatedNodes);

        while (dissatisfiedCells.size() != 0 && emptyCells.size() != 0) {
            int dissatisfiedIndex = (int) (Math.random() * dissatisfiedCells.size());
            SegregationCell sc = dissatisfiedCells.get(dissatisfiedIndex);
            int emptyCell = (int) (Math.random() * emptyCells.size());
            int agentState = (sc.getState() - SegregationAgentStates.DISATISFIED.getState()) / SegregationAgentStates.DISATISFIED.getState();
            updatedNodes.get(emptyCells.get(emptyCell).getRow()).set(emptyCells.get(emptyCell).getCol(), new SegregationCell(sc.getRow(), sc.getCol(), agentState));
            updatedNodes.get(sc.getRow()).set(sc.getCol(), new SegregationCell(sc.getRow(), sc.getCol(), SegregationCellStates.EMPTY.getState()));
            emptyCells.remove(emptyCell);
            dissatisfiedCells.remove(dissatisfiedIndex);
        }
    }

    private ArrayList<SegregationCell> findEmpty(ArrayList<ArrayList<SegregationCell>> updatedNodes) {
        ArrayList<SegregationCell> emptyCells = new ArrayList<>();
        for (ArrayList<SegregationCell> row : updatedNodes) {
            for (SegregationCell col : row) {
                if (col.getState() == SegregationCellStates.EMPTY.getState()) {
                    emptyCells.add(col);
                }
            }
        }
        return emptyCells;
    }

    private ArrayList<SegregationCell> findDissatisfied(ArrayList<ArrayList<SegregationCell>> updatedNodes) {
        ArrayList<SegregationCell> dissatisfiedCells = new ArrayList<>();
        for (ArrayList<SegregationCell> row : updatedNodes) {
            for (SegregationCell col : row) {
                if (col.getState() < 0) {
                    dissatisfiedCells.add(col);
                }
            }
        }
        return dissatisfiedCells;
    }
}
