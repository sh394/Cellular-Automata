package GUI.Cell.Generators;

import GUI.Cell.CellTypes.CellUI;
import GUI.Cell.CellTypes.RecWatorCellUI;
/**
 * @author riley
 * This class is the WaTor segregation cell generator.it depends on the CellGenerator interface.
 * Use this class when creating a WaTor cell simulation.
 *
 */

public class RecWatorCellGenerator implements CellGenerator {
    /**
     * See interface notes.
     * @param width
     * @param height
     * @param x_position
     * @param y_position
     * @param ui_position_x
     * @param ui_position_y
     * @param state
     * @return
     */
    @Override
    public CellUI generateCell(double width, double height, double x_position, double y_position, int ui_position_x, int ui_position_y, int state) {
        return new RecWatorCellUI(width, height, x_position, y_position, ui_position_x, ui_position_y, state);
    }

}
