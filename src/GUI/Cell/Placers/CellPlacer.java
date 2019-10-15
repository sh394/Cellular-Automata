package GUI.Cell.Placers;

import GUI.Cell.CellTypes.CellUI;
import GUI.Cell.Generators.CellGenerator;

import java.util.ArrayList;
import java.util.List;


/**
 * @author riley
 * This class is the most abstract in its hierarchy and places cells based on the size of the grid and
 * the number of rows and columns. For example, when creating a new simulation, use an object that extends this
 * class to get the correct UI positions of the cells.
 */

public abstract class CellPlacer {
    private int COLUMNS;
    private int ROWS;
    private double SIMULATIONWIDTH;
    private double SIMULATIONHEIGHT;


    private ArrayList<CellUI> myCells;

    /**
     * Initializes the object
     * @param numColumns
     * @param numRows
     * @param simulationHeight
     * @param simulationWidth
     */
    public CellPlacer(int numColumns, int numRows, double simulationHeight, double simulationWidth){
        ROWS = numRows;
        COLUMNS = numColumns;
        SIMULATIONHEIGHT=simulationHeight;
        SIMULATIONWIDTH=simulationWidth;
        myCells = new ArrayList<CellUI>();
    }

    protected int getColumns(){return COLUMNS;}
    protected int getRows(){return ROWS;}
    protected double getSimulationHeight(){return SIMULATIONHEIGHT;}
    protected double getSimulationWidth(){return SIMULATIONWIDTH;}
    protected List<CellUI> getMyCells(){return myCells;}

    protected void addToMyCells(CellUI cellsToAdd){
        myCells.add(cellsToAdd);
    }

    /**
     * Dummy method that all sub-classes override
     */
    public List<CellUI> createCellList(List<String> cellList, CellGenerator myCellGenerator){
        return null;
    }


}
