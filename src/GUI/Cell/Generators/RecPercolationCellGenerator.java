package GUI.Cell.Generators;

import GUI.Cell.CellTypes.CellUI;
import GUI.Cell.CellTypes.RecPercolationCellUI;

/**
 * @author riley
 * This class is the Rectangle percolation cell generator.it depends on the CellGenerator interface.
 * Use this class when creating a fire cell simulation.
 *
 */
public class RecPercolationCellGenerator implements CellGenerator {

    /**
     * see interface notes
     */
    @Override
    public CellUI generateCell(double width, double height, double x_position, double y_position, int ui_position_x, int ui_position_y, int state) {
        return new RecPercolationCellUI(width, height, x_position, y_position, ui_position_x, ui_position_y, state);
    }

}
