package GUI.Cell.CellTypes;

import Backend.Enumerators.FireStates;
import javafx.scene.paint.Color;

/**
 * @author riley
 * This class is the class of the rectangle Fire CellUI classes, responsible for representing a fire rectangle cell on the grid.
 * it depends on the RecCellUI class.  use this class to represent a Fire Rectangle Cell.
 *
 */


public class RecFireCellUI extends RecCellUI {

    private static final Color TREECOLOR = Color.GREEN;
    private static final Color FIRECOLOR = Color.RED;
    private static final Color BURNTCOLOR = Color.YELLOW;

    public RecFireCellUI(double width, double height, double x_position, double y_position, int ui_position_x, int ui_position_y, int state) {
        super(width, height, x_position, y_position, ui_position_x, ui_position_y);
        setState(state);
    }

    /**
     * Updates the state of the cell
     * @param state
     */
    @Override
    public void setState(int state) {
        if(state == FireStates.TREE.getState()){
            super.updateShapeColor(TREECOLOR);
        }
        if(state == FireStates.BURNING.getState()){
            super.updateShapeColor(FIRECOLOR);
        }
        if(state == FireStates.EMPTY.getState()){
            super.updateShapeColor(BURNTCOLOR);
        }
    }

}
