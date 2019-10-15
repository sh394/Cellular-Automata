package Backend.Graphs;


import Backend.CellTypes.WaTorCell;
import Backend.CellTypes.WaTorCells.FishCell;
import Backend.CellTypes.WaTorCells.SharkCell;
import Backend.Enumerators.WaTorStates;
import Backend.Graph;
import Rules.SimulationRules.WaTorRules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Purpose: To hold the WaTor cells, update the WaTor cells, and return the current state of the entire graph
 * to the front end.
 * Dependencies: ArrayList, Collection, List, WaTorCell, FishCell, SharkCell, WaTorStates, Graph, WaTorRules
 *
 * @author - Benjamin Lawrence, bcl19
 */
public class WaTorGraph implements Graph {
    private ArrayList<ArrayList<WaTorCell>> cells;
    private WaTorRules rule;
    private int reproductionTime;
    private int energy;

    /**
     * Constructor for WaTor graph
     */
    public WaTorGraph() {
        rule = new WaTorRules();
        cells = new ArrayList<>();
        this.reproductionTime = 10;
        this.energy = 10;
    }

    /**
     * Adds a node to WaTor graph which consists of a WaTor cell
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
        ArrayList<WaTorCell> rowNodes = cells.get(row);
        while (rowNodes.size() - 1 < col) {
            rowNodes.add(null);
        }

        rowNodes.set(col, getNewCell(row, col, state));
    }

    /**
     * Updates all of the cells in the graph to their new states based on the current states. All of the new states are
     * calculated and then implemented only after every state has been calculated.
     */
    @Override
    public void updateGraph() {
        ArrayList<ArrayList<WaTorCell>> updatedNodes = initializeNewNodes();
        this.moveSharks(updatedNodes);
        this.moveFish(updatedNodes);
        this.fillInWithEmpty(updatedNodes);

        cells = updatedNodes;
    }

    /**
     *
     * @return - Returns current state of all the cells as strings in a list
     */
    @Override
    public List<String> getCellsAsList() {
        List<String> listOfStates = new ArrayList<>();
        for (ArrayList<WaTorCell> row : cells) {
            for (WaTorCell col : row) {
                listOfStates.add("" + col.getState());
            }
        }
        return listOfStates;
    }

    /**
     *  Since WaTor is not statistic or probability based, this does nothing
     *
     * @param newStat - Double value for new statistic
     */
    @Override
    public void updateStat(double newStat) {
        // Method intentionally left blank as there is nothing to update for percolation
        return;
    }

    private void fillInWithEmpty(ArrayList<ArrayList<WaTorCell>> updatedNodes) {
        for (int row = 0; row < updatedNodes.size(); row++) {
            for (int col = 0; col < updatedNodes.get(row).size(); col++) {
                if (updatedNodes.get(row).get(col) == null) {
                    updatedNodes.get(row).set(col, getNewCell(row, col, WaTorStates.EMPTY.getState()));
                }
            }
        }
    }

    private ArrayList<ArrayList<WaTorCell>> initializeNewNodes() {
        ArrayList<ArrayList<WaTorCell>> updatedNodes = new ArrayList<>();
        for (int row = 0; row < cells.size(); row++) {
            updatedNodes.add(new ArrayList<>(Collections.nCopies(cells.get(row).size(), null)));
        }
        return updatedNodes;
    }

    private void moveSharks(ArrayList<ArrayList<WaTorCell>> updatedNodes) {

        for (ArrayList<WaTorCell> wcList : cells) {
            for (WaTorCell waterCell : wcList) {
                if (waterCell.getState() != WaTorStates.SHARK.getState() || updatedNodes.get(waterCell.getRow()).get(waterCell.getCol()) != null)
                    continue;
                SharkCell sc = (SharkCell) waterCell;

                int row = sc.getRow();
                int col = sc.getCol();

                if (sc.updateEnergy() == 0) {
                    WaTorCell emptyCell = getNewCell(row, col, WaTorStates.EMPTY.getState());
                    updatedNodes.get(row).set(col, emptyCell);
                    continue;
                }

                ArrayList<WaTorCell> neighborCells = getNeighborCells(sc);
                boolean moved = false;
                for (WaTorCell wc : neighborCells) {
                    if (wc.getState() == WaTorStates.FISH.getState() && updatedNodes.get(wc.getRow()).get(wc.getCol()) == null) {
                        SharkCell sharkCell = (SharkCell) getNewCell(wc.getRow(), wc.getCol(), WaTorStates.SHARK.getState());
                        if (sc.getTimeLeftToReproduce() == 0) {
                            SharkCell reproducedShark = (SharkCell) getNewCell(row, col, WaTorStates.SHARK.getState());
                            updatedNodes.get(row).set(col, reproducedShark);
                            sc.setReproductionTime(reproductionTime);

                        } else {
                            WaTorCell emptyCell = getNewCell(row, col, WaTorStates.EMPTY.getState());
                            updatedNodes.get(row).set(col, emptyCell);
                        }

                        sharkCell.setReproductionTime(sc.getTimeLeftToReproduce() - 1);
                        updatedNodes.get(wc.getRow()).set(wc.getCol(), sharkCell);

                        moved = true;
                        break;
                    }
                }

                if (moved)
                    continue;

                for (WaTorCell wc : neighborCells) {
                    if (wc.getState() == WaTorStates.EMPTY.getState() && updatedNodes.get(wc.getRow()).get(wc.getCol()) == null) {
                        SharkCell sharkCell = (SharkCell) getNewCell(wc.getRow(), wc.getCol(), WaTorStates.SHARK.getState());
                        sharkCell.setEnergy(sc.getEnergy());
                        if (sc.getTimeLeftToReproduce() == 0) {
                            SharkCell reproducedShark = (SharkCell) getNewCell(row, col, WaTorStates.SHARK.getState());
                            reproducedShark.setReproductionTime(reproductionTime);
                            updatedNodes.get(row).set(col, reproducedShark);
                            sc.setReproductionTime(reproductionTime);
                        } else {
                            WaTorCell emptyCell = getNewCell(row, col, WaTorStates.EMPTY.getState());
                            updatedNodes.get(row).set(col, emptyCell);
                        }

                        sharkCell.setReproductionTime(sc.getTimeLeftToReproduce() - 1);
                        updatedNodes.get(wc.getRow()).set(wc.getCol(), sharkCell);

                        moved = true;
                        break;
                    }
                }

                if (moved)
                    continue;

                SharkCell sharkCell = (SharkCell) getNewCell(row, col, WaTorStates.SHARK.getState());
                sharkCell.setEnergy(sc.getEnergy());
                sharkCell.setReproductionTime(sc.getTimeLeftToReproduce() - 1);
                updatedNodes.get(row).set(col, sharkCell);
            }

        }

    }

    private void moveFish(ArrayList<ArrayList<WaTorCell>> updatedNodes) {
        for (ArrayList<WaTorCell> wcList : cells) {
            for (WaTorCell waterCell : wcList) {
                if (waterCell.getState() != WaTorStates.FISH.getState() || updatedNodes.get(waterCell.getRow()).get(waterCell.getCol()) != null)
                    continue;
                FishCell fc = (FishCell) waterCell;

                int row = fc.getRow();
                int col = fc.getCol();
                int repoTime = fc.getTimeLeftToReproduce();
                ArrayList<WaTorCell> neighborCells = getNeighborCells(fc);
                boolean moved = false;

                for (WaTorCell wc : neighborCells) {
                    if (wc.getState() == WaTorStates.EMPTY.getState() && updatedNodes.get(wc.getRow()).get(wc.getCol()) == null) {
                        FishCell fishCell = (FishCell) getNewCell(wc.getRow(), wc.getCol(), WaTorStates.FISH.getState());
                        if (fc.getTimeLeftToReproduce() == 0) {
                            FishCell reproducedFish = (FishCell) getNewCell(row, col, WaTorStates.FISH.getState());
                            updatedNodes.get(row).set(col, reproducedFish);
                            fc.setReproductionTime(reproductionTime / 2);

                        } else {
                            WaTorCell emptyCell = getNewCell(row, col, WaTorStates.EMPTY.getState());
                            updatedNodes.get(row).set(col, emptyCell);
                        }

                        fishCell.setReproductionTime(fc.getTimeLeftToReproduce() - 1);
                        updatedNodes.get(wc.getRow()).set(wc.getCol(), fishCell);

                        moved = true;
                        break;
                    }
                }

                if (moved)
                    continue;

                FishCell fishCell = (FishCell) getNewCell(row, col, WaTorStates.FISH.getState());
                fishCell.setReproductionTime(fc.getTimeLeftToReproduce() - 1);
                updatedNodes.get(row).set(col, fishCell);
            }

        }
    }

    private WaTorCell getNewCell(int row, int col, int state) {
        Map<Integer, WaTorCell> cellType = Map.ofEntries(
                Map.entry(WaTorStates.SHARK.getState(), new SharkCell(row, col, state, energy, reproductionTime)),
                Map.entry(WaTorStates.FISH.getState(), new FishCell(row, col, state, reproductionTime / 2)),
                Map.entry(WaTorStates.EMPTY.getState(), new WaTorCell(row, col, state, reproductionTime))
        );
        return cellType.get(state);
    }

    private ArrayList<WaTorCell> getNeighborCells(WaTorCell cell) {
        ArrayList<WaTorCell> neighborStates = new ArrayList<>();
        if (cell.getRow() - 1 >= 0) {
            neighborStates.add(cells.get(cell.getRow() - 1).get(cell.getCol()));
        }
        if (cell.getRow() + 1 < cells.size()) {
            neighborStates.add(cells.get(cell.getRow() + 1).get(cell.getCol()));
        }
        if (cell.getCol() - 1 >= 0) {
            neighborStates.add(cells.get(cell.getRow()).get(cell.getCol() - 1));
        }
        if (cell.getCol() + 1 < cells.get(cell.getRow()).size()) {
            neighborStates.add(cells.get(cell.getRow()).get(cell.getCol() + 1));
        }

        Collections.shuffle(neighborStates);

        return neighborStates;
    }
}
