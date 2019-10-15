package GUI.Cell.CellTypes;

import Backend.Enumerators.WaTorStates;
import javafx.scene.paint.Color;

/**
 * @author riley
 * This class is the class of the rectangle Wator CellUI classes, responsible for representing a fire rectangle cell on the grid.
 * it depends on the RecCellUI class.  use this class to represent a Fire Rectangle Cell.
 *
 */

public class RecWatorCellUI extends RecCellUI {

    private static final Color OPEN = Color.WHITE;
    private static final Color FISH = Color.GREEN;
    private static final Color SHARK = Color.BLUE;


    public RecWatorCellUI(double width, double height, double x_position, double y_position, int ui_position_x, int ui_position_y, int state) {
        super(width, height, x_position, y_position, ui_position_x, ui_position_y);
        setState(state);
    }

    /**
     * sets the state of the cell
     * @param state
     */
    @Override
    public void setState(int state) {
        if(state == WaTorStates.EMPTY.getState()){
            super.updateShapeColor(OPEN);
        }
        if(state == WaTorStates.FISH.getState()){
            super.updateShapeColor(FISH);
        }
        if(state == WaTorStates.SHARK.getState()){
            super.updateShapeColor(SHARK);
        }
    }
}
