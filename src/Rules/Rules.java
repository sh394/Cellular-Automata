package Rules;


import Backend.Enumerators.Segregation.SegregationCellStates;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Rules {

    /**
     * Updates the next state of the cell based on the current state and the states of its neighbors
     * @param currentState
     * @param neighborStates
     * @return
     */
    public abstract int nextGeneration(int currentState, List<Integer> neighborStates);


    /**
     * Counts the number of the states of the neighboring cells that are equal to the state passed in
     * @param neighborStates
     * @param state
     * @return
     */
    public int countStates(List<Integer> neighborStates, int state) {
        int count = 0;
        for(int i = 0; i < neighborStates.size(); i++) {
            if (neighborStates.get(i) == state) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns true if any of neighboring cells are equal to the state passed in
     * @param neighborStates
     * @param state
     * @return
     */

    public boolean isAround(List<Integer> neighborStates, int state) {
        for(int i = 0; i < neighborStates.size(); i++) {
            if(neighborStates.get(i) == state) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns random object that will be used when calculating the threshold
     * @return
     */

    public Random getRandom() {
        return new Random();
    }
}
