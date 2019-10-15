package GUI;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author riley
 * This is the main window class, which is responsible for containing the full window that the user sees. It depends on the
 * Simulation, controller box, and grapher class to function. it essentially manages the controllers interaction
 * with the aforementioned classes. Use this class as a instance var for the controller main class.
 */
public class MainWindow {

    private Pane myMainPane;

    private Simulation mySimulation;
    private ControllerBox myControllerBox;
    private Grapher myGrapher;


    private final int CONTROLLERPADDINGWIDTH;
    private final int GRAPHINGPADDINGWIDTH;
    private int SIMULATIONCOLUMNS;
    private int SIMULATIONROWS;


    /**
     * This is a getter function for the instance var myMainPane;
     * @return Pane
     */
    public Pane getMyMainPane(){return myMainPane;}

    /**
     * This is the initializer for the mainWindow class.
     * @param height
     * @param width
     */
    public MainWindow(int width, int height, int numColumns, int numRows, String typeOfSimulation, List<String> initialCellStates, int controllerPaddingWidth, int graphingPaddingWidth){
        SIMULATIONCOLUMNS = numColumns;
        SIMULATIONROWS = numRows;
        CONTROLLERPADDINGWIDTH = controllerPaddingWidth;
        GRAPHINGPADDINGWIDTH = graphingPaddingWidth;

        setUpMainPane(height, width);
        setUpSimulation(width, height, 0, 0, initialCellStates, typeOfSimulation, SIMULATIONCOLUMNS, SIMULATIONROWS);
        setUpControllerBox(CONTROLLERPADDINGWIDTH,height , width-CONTROLLERPADDINGWIDTH,0);
    }

    /**
     *
     * @return
     */
    public Button getStartButton() {return myControllerBox.getStartButton();}

    /**
     *
     * @return
     */
    public Button getStepButton() {return myControllerBox.getStepButton();}

    /**
     *
     * @return
     */
    public Slider getFrameSlider(){return myControllerBox.getFrameSlider();}

    /**
     *
     * @return
     */
    public Button getFileOpenerButton(){return myControllerBox.getFileOpenerButton();}

    /**
     *
     * @return
     */
    public FileChooser getFileChooser(){return myControllerBox.getFileChooser();}

    /**
     *
     * @return
     */
    public Button getRandomFillButton(){return  myControllerBox.getRandomFillButton();}

    /**
     *
     * @return
     */
    public Button getToXMLButton(){return myControllerBox.getToXMLButton();}

    /**
     *
     * @return
     */
    public Slider getConstantSlider(){return myControllerBox.getConstantSlider();}

    private void setUpMainPane(int width, int height){
        myMainPane = new Pane();
        myMainPane.setPrefSize(width, height);
    }


    /**
     * sets up the simulation
     * @param width
     * @param height
     * @param xOffSet
     * @param yOffSet
     * @param initialCellStates
     * @param typeOfSimulation
     * @param newSimRows
     * @param newSimCols
     */
    public void setUpSimulation(int width, int height, int xOffSet, int yOffSet, List<String> initialCellStates, String typeOfSimulation, int newSimRows, int newSimCols){
        SIMULATIONCOLUMNS = newSimCols;
        SIMULATIONROWS = newSimRows;
        mySimulation = new Simulation(width-CONTROLLERPADDINGWIDTH, height, xOffSet, yOffSet, SIMULATIONCOLUMNS, SIMULATIONROWS);
        mySimulation.initializeCellManager(initialCellStates, SIMULATIONCOLUMNS, SIMULATIONROWS, typeOfSimulation);
        setUpGraphingPane(CONTROLLERPADDINGWIDTH, GRAPHINGPADDINGWIDTH, width-CONTROLLERPADDINGWIDTH, height-GRAPHINGPADDINGWIDTH, initialCellStates, createStateNames(typeOfSimulation) );
        mySimulation.attachPaneTo(myMainPane);
    }

    private List<String> createStateNames(String typeOfSimulation){
        ResourceBundle bundle = ResourceBundle.getBundle(typeOfSimulation);
        ArrayList<String> stateNames = new ArrayList<>();
        for (Iterator<String> it = bundle.getKeys().asIterator(); it.hasNext(); ) {
            String key = it.next();
            stateNames.add(bundle.getString(key));
        }
        return stateNames;
    }

    private void setUpControllerBox(int width, int height, int xOffSet, int yOffSet){
        myControllerBox = new ControllerBox( width,  height, xOffSet, yOffSet);
        myControllerBox.attachPaneTo(myMainPane);
    }

    private void setUpGraphingPane(int width, int height, int xOffSet, int yOffSet, List<String> states, List<String> stateNames) {
        if(myGrapher != null){
            myMainPane.getChildren().remove(myGrapher.getMyPane());
        }
        List<Integer> intStates = createIntList(states);
        myGrapher = new Grapher(width,height, xOffSet, yOffSet, intStates, stateNames);
        myGrapher.attachPaneTo(myMainPane);
    }

    private List<Integer> createIntList(List<String> states) {
        ArrayList<Integer> intList = new ArrayList<>();
        for(String str: states){
            intList.add(Integer.parseInt(str));
        }
        return intList;
    }

    /**
     * handles the UI implications of toggling an animation
     */
    public void setStartButtonToStart(){myControllerBox.setStartButtonToStart();}

    /**
     * toggling animation
     */
    public void setStartButtonToPause(){myControllerBox.setStartButtonToPause();}

    /**
     *
     * @param value
     */
    public void initializeConstantSlider(double value){myControllerBox.initializeConstantSlider(value);}

    /**
     * updates the animation based on new states
     * @param newCellStates
     */
    public void updateAnimation(List<String> newCellStates) {
        mySimulation.updateCellStates(newCellStates);
        myGrapher.updateGraphSeries(createIntList(newCellStates));
    }
}
