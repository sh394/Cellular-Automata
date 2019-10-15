package GUI.Cell.Placers;


import GUI.Cell.CellTypes.CellUI;
import GUI.Cell.Generators.CellGenerator;

import java.util.List;

/**
 * @author riley
 * this class extends the CellPlacer class and is used to place cells that have a rectangle shape. It depends on the CellPlacer
 * class. use it to place rectangle shapes.
 */
public class RecCellPlacer extends CellPlacer {

    private double CELLWIDTH;
    private double CELLHEIGHT;

    /**
     * initializes the class
     * @param numColumns
     * @param numRows
     * @param simulationWidth
     * @param simulationHeight
     */
    public RecCellPlacer(int numColumns, int numRows, double simulationWidth, double simulationHeight) {
        super(numColumns, numRows, simulationHeight, simulationWidth);
        CELLWIDTH = decideCellWidth(getColumns(), getSimulationWidth());
        CELLHEIGHT = decideCellHeight(getRows(), getSimulationHeight());

    }

    /**
     * Overides the create cell list.
     * @param cellList
     * @param myCellGenerator
     * @return a list of cells that are placed correctly
     */

    @Override
    public List<CellUI> createCellList(List<String> cellList, CellGenerator myCellGenerator){
        for(int i =0; i < getRows(); i++){
            for(int j = 0; j < getColumns(); j++){
                createCellAndAddToList(j,i, myCellGenerator, cellList.get(i*getColumns()+j));
            }
        }
        return getMyCells();
    }

    private void createCellAndAddToList(int row, int column, CellGenerator myCellGenerator, String s) {
        int state = Integer.parseInt(s);
        CellUI toAdd = myCellGenerator.generateCell(CELLWIDTH, CELLHEIGHT, decideXPosition(row), decideYPosition(column), column, row, state);
        addToMyCells(toAdd);
    }

    private double decideYPosition(int column) {
        return column*CELLHEIGHT;
    }

    private double decideXPosition(int row) {
        return row*CELLWIDTH;
    }

    private double decideCellWidth(int columns, double simulationWidth) {
        return simulationWidth/columns;
    }

    private double decideCellHeight(int rows, double simulationHeight) {
        return simulationHeight/rows;
    }

}
