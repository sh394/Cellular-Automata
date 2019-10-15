package Backend;

/**
 * Purpose: To hold all the constants for the Backend.
 * Dependencies: None
 * Example:
 *      if (s.equals(Constants.FIRE_SIMULATION_NAME)) {
 *          System.out.println("Starting fire simulation");
 *      }
 *
 * @author - Benjamin Lawrence, bcl19
 */
public abstract class BackendConstants {

    /**
     * Public variables to store the names of the varying simulation types.
     */
    public static final String FIRE_SIMULATION_NAME = "Fire";
    public static final String GAME_OF_LIFE_SIMULATION_NAME = "GameOfLife";
    public static final String PREDATORY_PREY_SIMULATION_NAME = "WaTor";
    public static final String PERCOLATION_SIMULATION_NAME = "Percolation";
    public static final String SEGREGATION_SIMULATION_NAME = "Segregation";

    private BackendConstants() {
        // intentionally left empty
    }
}
