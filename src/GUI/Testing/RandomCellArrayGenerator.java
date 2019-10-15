package GUI.Testing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author riley
 * A simple class that was intially used to test the UI before back end was done. Was repurposed into
 * a random fill feature.
 */
public abstract class RandomCellArrayGenerator {

    private Random rand = new Random();

    protected ArrayList<String> getListOfRandomCellStates(int lengthOfList, int lowerBound, int upperBound) {
        ArrayList<String> cellList = new ArrayList<String>();
        for (int i = 0; i < lengthOfList; i++) {
            cellList.add(getRandomStringBetween(lowerBound, upperBound));
        }
        return cellList;
    }

    private String getRandomStringBetween(int lowerBound, int upperBound) {
        return Integer.toString(rand.nextInt(upperBound - lowerBound + 1) + lowerBound);
    }

    /**
     * dummy method that sub classes overwrite
     * @param lengthOfList
     * @return a list of random cell states
     */
    public List<String> getListOfRandomCellStates(int lengthOfList) {
        return null;
    }
}
