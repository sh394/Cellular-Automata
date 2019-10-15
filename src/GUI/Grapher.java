package GUI;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;

/**
 * @author riley
 *The grapher is an element of the main window which is reposible for displaying the stats involved when running a simulation.
 * This class assumes that the correct 'data' is being opassed to it when it is updated, and that it is correctly destroyed
 * and remade when switching siomulations.
 */
public class Grapher extends CellSocietyPane {

    private List<XYChart.Series> ACTIVESERIES = new ArrayList<>();

    /**
     * Initializer
     *
     * @param width
     * @param height
     * @param xOffSet
     * @param yOffSet
     */

    int STEP;
    NumberAxis myXAxis = new NumberAxis();
    NumberAxis myYAxis = new NumberAxis();
    LineChart<Number, Number> myChart;

    public Grapher(int width, int height, int xOffSet, int yOffSet, List<Integer> states, List<String> stateNames) {
        super(width, height, xOffSet, yOffSet);
        STEP = 0;

        initializeGraph(states, stateNames);
        myChart.setPrefSize(width,height);
    }

    private void initializeGraph(List<Integer> states, List<String> stateNames){
        getMyPane().getChildren().clear();
        myChart = new LineChart<>(myXAxis, myYAxis);
        createSeriesFromStates(states, stateNames, myChart);
        STEP += 1;
        getMyPane().getChildren().add(myChart);
    }

    private void createSeriesFromStates(List<Integer> states, List<String> stateNames, LineChart<Number, Number> myChart){
        for(int i = 0; i < stateNames.size(); i++){
            myChart.getData().add(createASeries(states, stateNames.get(i), i));
        }
    }

    private XYChart.Series createASeries(List<Integer> states, String stateName, int stateValue) {
        XYChart.Series series = new XYChart.Series();
        series.setName(stateName);
        int total = calculateTotal(states, stateValue);
        series.getData().add(new XYChart.Data(STEP, total));
        ACTIVESERIES.add(series);
        return series;
    }

    /**
     * updates the graph stats based on a list of new cell states
     * @param newStates
     */
    public void updateGraphSeries(List<Integer> newStates){
        for(int i = 0; i<ACTIVESERIES.size();i++){
            ACTIVESERIES.get(i).getData().add(new XYChart.Data(STEP, calculateTotal(newStates,i)));
        }
        STEP+=1;
    }

    private int calculateTotal(List<Integer> states, int stateValue) {
        int total = 0;
        for (int state : states) {
            if (state == stateValue) {
                total += 1;
            }
        }
        return total;
    }
}
