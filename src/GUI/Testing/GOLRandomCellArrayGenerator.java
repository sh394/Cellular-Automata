package GUI.Testing;

import java.util.ArrayList;

/**
 * @author riley
 * A class that depends on and extends the random cell generator. It is used to generate random states for the game of life.
 */

public class GOLRandomCellArrayGenerator extends RandomCellArrayGenerator {

    /**
     * Returns a list of random cell states
     * @param lengthOfList
     * @return list of random cell states
     */
    @Override
    public ArrayList<String> getListOfRandomCellStates(int lengthOfList){
        return super.getListOfRandomCellStates(lengthOfList, 0,1);
    }
}
