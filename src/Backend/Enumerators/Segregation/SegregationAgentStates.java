package Backend.Enumerators.Segregation;

/**
 * Purpose: Enumerator to hold the varying integer states of a segregation agent cell type. In this implementation a cell
 * can be either satisfied or dissatisfied.
 * Dependencies: None
 * Example:
 * if (rule.checkDissatisfied(cell) == SegregationAgentStates.DISATISFIED.getState()) {
 * System.out.println("Cell is dissatisfied");
 * }
 *
 * @author - Benjamin Lawrence, bcl19
 */
public enum SegregationAgentStates {

    DISATISFIED(-1);

    private int state;

    SegregationAgentStates(int s) {
        state = s;
    }

    /**
     * @return - Returns the integer state of the enumerator object
     */
    public int getState() {
        return state;
    }
}