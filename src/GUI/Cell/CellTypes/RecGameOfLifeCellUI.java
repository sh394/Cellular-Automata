package GUI.Cell.CellTypes;

import Backend.Enumerators.GameOfLifeStates;
import javafx.scene.paint.Color;

/**
 * @author riley
 * This class is the class of the rectangle game of life CellUI classes, responsible for representing a fire rectangle cell on the grid.
 * it depends on the RecCellUI class.  use this class to represent a Fire Rectangle Cell.
 *
 */


public class RecGameOfLifeCellUI extends RecCellUI {

    private static final Color OFFCOLOR = Color.WHITE;
    private static final Color ONCOLOR = Color.BLUE;


    public RecGameOfLifeCellUI(double width, double height, double x_position, double y_position, int ui_position_x, int ui_position_y, int state) {
        super(width, height, x_position, y_position, ui_position_x, ui_position_y);
        setState(state);
    }

    /**
     * Sets the state of the cell
     * @param state
     */
    @Override
    public void setState(int state) {
        if(state == GameOfLifeStates.DEAD.getState()){
            super.updateShapeColor(OFFCOLOR);
        }
        if(state == GameOfLifeStates.ALIVE.getState()){
            super.updateShapeColor(ONCOLOR);
        }
    }

}
