package GUI.Cell;


import GUI.Cell.CellTypes.CellUI;
import GUI.Cell.Generators.CellGenerator;
import GUI.Cell.Placers.CellPlacer;
import javafx.scene.layout.Pane;

import java.util.List;

/**
 * @author riley
 * This class is used to manage the UI of the cells in the simulations. It depends on many things, specifically
 * a list of cells that is generated. Use this class to manage the UI of cells.
 */

public class CellManagerUI {

    private List<CellUI> myCells;
    private int SIMULATIONROWS;
    private int SIMULATIONCOLUMNS;

    /**
     * Class initializer.
     * @param cellList
     * @param myCellGenerator
     * @param myCellPlacer
     * @param simulationPane
     * @param simulationColumns
     * @param simulationRows
     */
    public CellManagerUI(List<String> cellList, CellGenerator myCellGenerator, CellPlacer myCellPlacer, Pane simulationPane, int simulationColumns, int simulationRows){
        SIMULATIONCOLUMNS=simulationColumns;
        SIMULATIONROWS=simulationRows;
        myCells = myCellPlacer.createCellList(cellList, myCellGenerator);
        for(CellUI cell: myCells){
            cell.attachMyShapeTo(simulationPane);
        }
    }

    private CellUI findCellByUIPosition(int x_position, int y_position){
        for(CellUI cell: myCells){
            if(cell.getUI_POSITION_X() == x_position && cell.getUI_POSITION_Y()==y_position){
                return cell;
            }
        }
        return null;
    }

    /**
     * Updates the states of the managers cells based on a list of strings that represent the new states.
     * @param newCellStates
     */
    public void updateCellStates(List<String> newCellStates) {
        for(int i =0; i < SIMULATIONROWS; i++){
            for(int j = 0; j < SIMULATIONCOLUMNS; j++){
                CellUI cell = findCellByUIPosition(i,j);
                cell.setState(Integer.parseInt(newCellStates.get(i*SIMULATIONROWS+j)));
            }
        }

    }
}
