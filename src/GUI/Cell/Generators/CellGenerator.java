package GUI.Cell.Generators;

import GUI.Cell.CellTypes.CellUI;

/**
 * @author riley
 * This class is the interface for the cell generator class., It is the most abstract interface in its hierarchy adn does
 * not depend on anything. Use this class when creating a new simulation.
 *
 */

public interface  CellGenerator {

    /**
     * Generates a cell given the following paremeters.
     * @param width
     * @param height
     * @param x_position
     * @param y_position
     * @param ui_position_x
     * @param ui_position_y
     * @param state
     * @return
     */
    public CellUI generateCell(double width, double height, double x_position, double y_position, int ui_position_x, int ui_position_y, int state );

}
