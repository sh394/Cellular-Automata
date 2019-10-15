package Rules.SimulationRules;

import Backend.Enumerators.PercolationStates;

import Rules.Rules;


import java.util.List;

public class PercolationRules extends Rules {
    private static final int OPEN = PercolationStates.OPEN.getState();
    private static final int PERCOLATED = PercolationStates.PERCOLATED.getState();

    public PercolationRules() {
        super();
    }

    @Override
    public int nextGeneration(int currentState, List<Integer> neighborStates) {
            if(toBePercolated(currentState, neighborStates)) {
                return PERCOLATED;
            }
            return currentState;
    }

    private boolean toBePercolated(int currentState, List<Integer> neighborStates) {
        return ((currentState == OPEN && isAround(neighborStates, PERCOLATED)) || currentState == PERCOLATED);
    }
}
