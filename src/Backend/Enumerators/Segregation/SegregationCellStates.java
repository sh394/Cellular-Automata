package Backend.Enumerators.Segregation;

/**
 * Purpose: Enumerator to hold the integers representing the different types of segregation cells. There are three different
 * types - agent 1, agent 2, and empty cells.
 * Dependencies: None
 * Example:
 * if (rule.checkDissatisfied(cell) == SegregationAgentStates.DISATISFIED.getState()) {
 * cell.setState(SegregationCellStates.AGENT_2.getState());
 * }
 *
 * @author - Benjamin Lawrence, bcl19
 */
public enum SegregationCellStates {

    AGENT_1(0),
    AGENT_2(1),
    EMPTY(2);

    private int state;

    SegregationCellStates(int s) {
        state = s;
    }

    /**
     * @return - Returns the integer value representing the enumerator object's state
     */
    public int getState() {
        return state;
    }
}
