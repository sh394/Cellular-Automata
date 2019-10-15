package GUI.Cell.CellTypes;

import javafx.scene.shape.Rectangle;

/**
 * @author riley
 * This class is the base of the rectangle shape CellUI classes, responsible for representing a rectangle cell on the grid.
 * it depends on the cellUI class.  Use this class as an extension in a specific shape and type of cell combination.
 * Cell UI class.
 */


public abstract class RecCellUI extends CellUI {

    /**
     * Initialzies the rectangle Cell
     * @param width
     * @param height
     * @param x_position
     * @param y_position
     * @param ui_position_x
     * @param ui_position_y
     */
    public RecCellUI(double width, double height, double x_position, double y_position,int ui_position_x, int ui_position_y){
        super(ui_position_x, ui_position_y);
        initiateMyShape(createRectangleShape(width, height,x_position,y_position));
    }

    private Rectangle createRectangleShape(double width, double height, double x_position, double y_position) {
        Rectangle rect = new Rectangle();
        rect.getStyleClass().add("my-rect");
        rect.setWidth(width);
        rect.setHeight(height);
        rect.setX(x_position);
        rect.setY(y_position);
        return rect;
    }
}
