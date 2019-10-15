package Util;

import Backend.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author riley
 * this class essentially just houses code that is useful during translating between the back end and front end.
 * For example, when converting a string to a list of strings from the XML to the Front end, this class is used. It does
 * not depend or assume anything
 */
public class Converter {

    /**
     * converts a string to an array lsit
     * @param to
     * @return
     */
    public List<String> StringToArrayList(String to){
        return new ArrayList<String>(Arrays.asList(to.split("")));
    }

    /**
     * converts a list of strings to an array
     * @param initialCellStates
     * @param simulationcolumns
     * @param simulationrows
     * @return
     */
    public int[][] convertListOfStringsToArray(List<String> initialCellStates, int simulationcolumns, int simulationrows) {
        int[][] ret = new int[simulationrows][simulationcolumns];
        for(int row = 0; row < simulationrows; row++){
            for(int col = 0; col < simulationcolumns; col++){
                ret[row][col] = Integer.parseInt(initialCellStates.get(row*simulationcolumns+col));
            }
        }
        return ret;
    }

    /**
     * Converts a list to a string
     * @param statesAsList
     * @return
     */
    public String convertListToString(List<String> statesAsList) {
        StringBuilder str = new StringBuilder();
        for(String s: statesAsList){
            str.append(s);
        }
        return str.toString();
    }

    /**
     * gets an X ui position based on data from the grid
     * @param x
     * @param i
     * @param simulationcolumns
     * @return
     */
    public int getUIPositionX(double x, int i, int simulationcolumns) {
        return getPosition(x,i,simulationcolumns);
    }

    /**
     * gets an Y ui position based on data from the grid
     * @param y
     * @param height
     * @param simulationrows
     * @return
     */
    public int getUIPositionY(double y, int height, int simulationrows) {
        return getPosition(y,height ,simulationrows);
    }

    private int getPosition(double i, int length, int baskets ){
        return(int) Math.floor(i/(length/baskets));
    }

}
