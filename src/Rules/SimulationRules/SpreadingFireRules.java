package Rules.SimulationRules;

import Backend.Enumerators.FireStates;
import Configuration.ConfigReader;
import Configuration.Configuration;
import Rules.Rules;

import java.io.File;
import java.util.List;

public class SpreadingFireRules extends Rules {
    private static final int BURNING = FireStates.BURNING.getState();
    private static final int EMPTY = FireStates.EMPTY.getState();
    private static final int TREE = FireStates.TREE.getState();

    private double probCatch;

    public SpreadingFireRules() {
        File datafile = new File("src/XMLFiles/SpreadingFire.xml");
        Configuration config = new ConfigReader("rule").getConfiguration(datafile);
        this.probCatch = config.getMyThreshold();
    }

    @Override
    public int nextGeneration(int currentState, List<Integer> neighborStates) {
       if(currentState == BURNING) {
           return EMPTY;//Burnt
       }
       else if(currentState == TREE) { //check if tree will be burnt
           if(isAround(neighborStates, BURNING) && (getRandom().nextDouble() < probCatch)) {
               return BURNING; // Burnt
           }
           return TREE; // not Burnt
       }
        return EMPTY; // leave empty
    }

    public void updateProbCatch(double newProb) {
        probCatch = newProb;
    }
}
