package GUI.Testing;

import java.util.ArrayList;

/**
 * @author riley
 * A class that depends on and extends the random cell generator. It is used to generate random states for any simulation that
 * has 3 states.
 */

public class ThreeStateRandomCellArrayGenerator extends RandomCellArrayGenerator {
    @Override
    public ArrayList<String> getListOfRandomCellStates(int lengthOfList){
        return super.getListOfRandomCellStates(lengthOfList, 0,2);
    }
}
