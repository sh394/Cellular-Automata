package GUI;


import GUI.Cell.CellManagerUI;
import GUI.Cell.Generators.*;
import GUI.Cell.Placers.CellPlacer;
import GUI.Cell.Placers.RecCellPlacer;

import java.util.List;

/**
 * @author riley
 * The simulation class is responsible for runnign simulations and displaying the UI to the user.
 * It depends on the cell society pane class. use this class as an instance var for the main window.
 */

public class Simulation extends CellSocietyPane {

    private CellManagerUI myCellManagerUI;

    private int SIMULATIONCOLUMNS;
    private int SIMULATIONROWS;
    /**
     * Initializes the portion of the screen that is responsible for running the simulation
     * @param width
     * @param height
     * @param xOffSet
     * @param yOffSet
     */
    public Simulation(int width, int height, int xOffSet, int yOffSet, int simulationColumns, int simulationRows){
        super(width, height, xOffSet, yOffSet);
        SIMULATIONCOLUMNS=simulationColumns;
        SIMULATIONROWS=simulationRows;
    }

    /**
     * This method initializes the cell manager object in the simulation pane
     * @param cellList
     * @param numColumns
     * @param numRows
     * @param typeOfSimulation
     */

    public void initializeCellManager(List<String> cellList, int numColumns, int numRows, String typeOfSimulation){
        removeAllPaneChildren();
        CellGenerator myCellGenerator = decideCellGenerator(typeOfSimulation);
        CellPlacer myCellPlacer = new RecCellPlacer(numColumns, numRows, super.getWidth(), super.getHeight());
        myCellManagerUI = new CellManagerUI(cellList, myCellGenerator, myCellPlacer, getMyPane(), SIMULATIONCOLUMNS, SIMULATIONROWS );
    }

    private CellGenerator decideCellGenerator(String typeOfSimulation) {
        if(typeOfSimulation.equals("GameOfLife")){
            return new RecGameOfLifeCellGenerator();
        }
        if(typeOfSimulation.equals("Segregation")){
            return new RecSegregationCellGenerator();
        }
        if(typeOfSimulation.equals("Percolation")){
            return new RecPercolationCellGenerator();
        }
        if(typeOfSimulation.equals("Fire")){
            return new RecFireCellGenerator();
        }
        if(typeOfSimulation.equals("WaTor")){
            return new RecWatorCellGenerator();
        }
        return null;
    }

    /**
     * Updates cell states based on a list of new cell state ints.
     * @param newCellStates
     */
    public void updateCellStates(List<String> newCellStates){
        myCellManagerUI.updateCellStates(newCellStates);
    }
}
