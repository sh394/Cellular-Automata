package GUI.Cell.CellTypes;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * @author riley
 * This class is the base of the CellUI classes, responsible for representing a cell on the grid.
 * It has no dependencies, since it is the most abstract class in its hierarchy. Use this class as an extension in a shape
 * Cell UI class.
 */

public abstract class CellUI {
    private int UI_POSITION_X;
    private int UI_POSITION_Y;

    private Shape myShape;

    /**
     * Initializer
     * @param ui_position_x
     * @param ui_position_y
     */
    public CellUI(int ui_position_x, int ui_position_y){
        UI_POSITION_X = ui_position_x;
        UI_POSITION_Y = ui_position_y;
    }

    /**
     * updates the color of a cell's shape
     * @param newColor
     */
    public void updateShapeColor(Color newColor){
        myShape.setFill(newColor);
    }

    protected void initiateMyShape(Shape shape){
        myShape=shape;
    }

    /**
     * Attaches the cell shape to a pane
     * @param root
     */
    public void attachMyShapeTo(Pane root){root.getChildren().add(myShape);}

    /**
     * Dummy class for setState, a method to be inheretted by more specific sub classes
     * @param newState
     */
    public void setState(int newState){}

    /**
     * Gets the y UI position of the cell
     * @return returns the int of the UI Position X
     */
    public int getUI_POSITION_X(){return UI_POSITION_X;}

    /**
     * Gets the x UI position of the cell
     * @return returns the int of the UI Position Y
     */
    public int getUI_POSITION_Y(){return UI_POSITION_Y;}

}
