package GUI.Cell.CellTypes;

import Backend.Enumerators.Segregation.SegregationCellStates;
import javafx.scene.paint.Color;

/**
 * @author riley
 * This class is the class of the rectangle Segregation CellUI classes, responsible for representing a fire rectangle cell on the grid.
 * it depends on the RecCellUI class.  use this class to represent a Fire Rectangle Cell.
 *
 */

public class RecSegregationCellUI extends RecCellUI {


    private static final Color COLOR1 = Color.RED;
    private static final Color COLOR2 = Color.BLUE;
    private static final Color OFFCOLOR = Color.WHITE;


    public RecSegregationCellUI(double width, double height, double x_position, double y_position, int ui_position_x, int ui_position_y, int state) {
        super(width, height, x_position, y_position, ui_position_x, ui_position_y);
        setState(state);
    }

    /**
     * sets the state of the cell
     * @param state
     */
    @Override
    public void setState(int state) {
        if(state == SegregationCellStates.EMPTY.getState()){
            super.updateShapeColor(OFFCOLOR);
        }
        if(state == SegregationCellStates.AGENT_1.getState()){
            super.updateShapeColor(COLOR1);
        }
        if(state == SegregationCellStates.AGENT_2.getState()){
            super.updateShapeColor(COLOR2);
        }
    }
}
