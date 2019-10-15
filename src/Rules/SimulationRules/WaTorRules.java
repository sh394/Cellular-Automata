package Rules.SimulationRules;


import Rules.Rules;

import java.util.List;

public class WaTorRules extends Rules {
    @Override
    public int nextGeneration(int currentState, List<Integer> neighbors) {
        return currentState;
    }
}
