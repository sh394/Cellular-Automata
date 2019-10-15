package GUI.Cell.CellTypes;

import Backend.Enumerators.PercolationStates;
import javafx.scene.paint.Color;

public class RecPercolationCellUI extends RecCellUI {

    /**
     * @author riley
     * This class is the class of the rectangle percolation CellUI classes, responsible for representing a fire rectangle cell on the grid.
     * it depends on the RecCellUI class.  use this class to represent a Fire Rectangle Cell.
     *
     */

    private static final Color PERCOLATECOLOR = Color.BLUE;
    private static final Color OFFCOLOR = Color.WHITE;
    private static final Color WALLCOLOR = Color.BLACK;

    public RecPercolationCellUI(double width, double height, double x_position, double y_position, int ui_position_x, int ui_position_y, int state) {
        super(width, height, x_position, y_position, ui_position_x, ui_position_y);
        setState(state);
    }

    /**
     * sets the state of the cell
     * @param state
     */
    @Override
    public void setState(int state) {
        if(state == PercolationStates.BLOCKED.getState()){
            super.updateShapeColor(WALLCOLOR);
        }
        if(state == PercolationStates.OPEN.getState()){
            super.updateShapeColor(OFFCOLOR);
        }
        if(state == PercolationStates.PERCOLATED.getState()){
            super.updateShapeColor(PERCOLATECOLOR);
        }
    }
}
