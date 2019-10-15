package Rules.SimulationRules;

import Backend.Enumerators.Segregation.SegregationAgentStates;
import Backend.Enumerators.Segregation.SegregationCellStates;
import Configuration.*;
import Rules.Rules;
import java.io.File;
import java.util.List;

public class SegregationRules extends Rules {
    private static final int EMPTY = SegregationCellStates.EMPTY.getState();
    private static final int DISATISFIED = SegregationAgentStates.DISATISFIED.getState();

    private double threshold;

    public SegregationRules() {
        File datafile = new File("src/XMLFiles/Segregation.xml");
        Configuration config = new ConfigReader("rule").getConfiguration(datafile);
        this.threshold = config.getMyThreshold();
    }

    @Override
    public int nextGeneration(int currentState, List<Integer> neighborStates) {
        if(currentState == EMPTY) { //leave empty spaces
            return EMPTY;
        } else if(checkSatisfied(neighborStates, currentState) < threshold) { // not satisfied
           return DISATISFIED;
        }
        return currentState;
    }

    public double checkSatisfied(List<Integer> neighborStates, int state) {
        double sameNeighbor = countStates(neighborStates, state) + countStates(neighborStates, SegregationCellStates.EMPTY.getState());
        double wholeNeighbor = neighborStates.size();
        return sameNeighbor / wholeNeighbor;
    }
    public void updateThreshold(double newThresh) {
        threshold = newThresh;
    }

}
