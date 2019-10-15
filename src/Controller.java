/**
 * @author riley cohen
 * @author ben lawrence
 * @author kyle hong
 *This is the main class of the application. it is responsible for controlling the project. It assumes that the data
 * files are somewhat correct, although we did implement considerable error checking. It depends on all other classes.
 * Use this class by running the application
 *
 */


import Backend.BackendConstants;
import Backend.CellManager;
import Configuration.ConfigReader;
import Configuration.Configuration;
import GUI.MainWindow;
import GUI.Testing.GOLRandomCellArrayGenerator;
import GUI.Testing.ThreeStateRandomCellArrayGenerator;
import Util.Converter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
 import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Alert.AlertType;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


public class Controller extends Application {
    protected static final List<String> SIMULATION_TYPE = List.of(
            "Fire",
            "GameOfLife",
            "Percolation",
            "WaTor",
            "Segregation"
    );
    private  double MILLISECOND_DELAY ;
    private  int width;
    private  int height;
    private  int SIMULATIONCOLUMNS;
    private  int SIMULATIONROWS;
    private final int CONTROLLERPADDINGWIDTH = Integer.parseInt(ResourceBundle.getBundle("UINumberConstants").getString("controllerPaddingWidth"));
    private int GRAPHINGPADDINGWIDTH;

    private  String GAMETITLE;
    private  String TYPEOFSIMULATION;
    private  int INIT_GRID;

    private boolean isSimulationOn = false;

    private Timeline simulationAnimation;

    private Stage myStage;
    private MainWindow myMainWindow;

    private CellManager myCellManager;
    private Converter myConverter = new Converter();
    private Configuration myConfiguration;

    public void start (Stage stage){
        myConfiguration = getConfig("src/XMLFiles/WaTor.xml");
        loadResources();
        myStage = stage;
        setUpCellManager();
        setUpStage();
        stage.show();
    }

    private void loadResources() {
        ResourceBundle bundle = ResourceBundle.getBundle("UIText");
        GAMETITLE = bundle.getString("gameTitle");
        bundle = ResourceBundle.getBundle("UINumberConstants");
        MILLISECOND_DELAY = Integer.parseInt(bundle.getString("millisecondDelay"));
        width = Integer.parseInt(bundle.getString("windowWidth"));
        height = Integer.parseInt(bundle.getString("windowHeight"));
        GRAPHINGPADDINGWIDTH = Integer.parseInt(bundle.getString("graphingPaddingWidth"));
    }

    private Configuration getConfig(String fileName){
        ConfigReader configReader = new ConfigReader("rule");
        Configuration toReturn = configReader.getConfiguration(new File(fileName));
        SIMULATIONCOLUMNS = toReturn.getMyCol();
        SIMULATIONROWS = toReturn.getMyRow();
        TYPEOFSIMULATION = toReturn.getMyName();
        INIT_GRID = toReturn.getMyInitial().length();

        if(SIMULATIONCOLUMNS * SIMULATIONROWS != INIT_GRID) {
            showMessage(AlertType.ERROR, "GRID_OUT_OF_BOUNCE");
        }

        if(!SIMULATION_TYPE.contains(TYPEOFSIMULATION)) {
            showMessage(AlertType.ERROR, "WRONG_SIMULATION_TYPE");
        }

        return toReturn;

    }

    private void setUpCellManager() {
        int[][] initialState = myConverter.convertListOfStringsToArray(myConverter.StringToArrayList(myConfiguration.getMyInitial()), SIMULATIONCOLUMNS, SIMULATIONROWS);
        myCellManager = new CellManager(TYPEOFSIMULATION, initialState, SIMULATIONCOLUMNS, SIMULATIONROWS);
    }

    private void setUpStage() {
        myStage.setTitle(GAMETITLE);
        myMainWindow = new MainWindow(width, height, SIMULATIONCOLUMNS, SIMULATIONROWS, TYPEOFSIMULATION, myConverter.StringToArrayList(myConfiguration.getMyInitial()),CONTROLLERPADDINGWIDTH, GRAPHINGPADDINGWIDTH);
        myMainWindow.initializeConstantSlider(myConfiguration.getMyThreshold());
        Scene mainScene = new Scene(myMainWindow.getMyMainPane(),width, height);
        mainScene.getStylesheets().add(this.getClass().getResource("main.css").toExternalForm());
        mainScene.setOnKeyPressed(e->handleKeyInput(e.getCode()));
        mainScene.setOnMouseClicked(e->handleMouseInput(e.getX(),e.getY()));
        myStage.setScene(mainScene);
        handleConstantSlider();
        setUpAnimation();
    }

    private void handleMouseInput(double x, double y) {
        if(x<(width-CONTROLLERPADDINGWIDTH)) {
            myCellManager.updateCellState(myConverter.getUIPositionX(x, width - CONTROLLERPADDINGWIDTH, SIMULATIONCOLUMNS), myConverter.getUIPositionY(y, height, SIMULATIONROWS));
        }
    }

    private void handleKeyInput(KeyCode code) {
        if(code.getName().equals("Enter")){
            toggleAnimation();
        }
    }

    private void setUpAnimation(){
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> simulationStep());
        simulationAnimation = new Timeline();
        simulationAnimation.setCycleCount(Timeline.INDEFINITE);
        simulationAnimation.getKeyFrames().add(frame);
        attachControllerBoxButtonsToAnimation();
    }

    private void attachControllerBoxButtonsToAnimation() {
        myMainWindow.getFrameSlider().valueProperty().addListener((ChangeListener) (arg0, arg1, arg2) -> {
            updateFrameDelay(myMainWindow.getFrameSlider().getValue());
        });
        myMainWindow.getConstantSlider().valueProperty().addListener((ChangeListener) (arg0, arg1, arg2) -> {
            updateStat(myMainWindow.getConstantSlider().getValue());
        });
        myMainWindow.getFileOpenerButton().setOnAction(
                (event) -> loadSimulationFromFile()
        );
        myMainWindow.getStartButton().setOnAction(
                (event) -> toggleAnimation()
        );
        myMainWindow.getStepButton().setOnAction(
                (event) -> stepAnimation()
        );
        myMainWindow.getRandomFillButton().setOnAction(
                (event) -> fillGridRandomly()
        );
        myMainWindow.getToXMLButton().setOnAction(
                (event) -> convertGridToXMLFile()
        );
    }

    private void convertGridToXMLFile() {
        ConfigReader configReader = new ConfigReader("rule");
        configReader.writeXMLFile(myConfiguration.getMyName(), myConfiguration.getMyTitle(), myConfiguration.getMyAuthor(),myConfiguration.getMyThreshold(),myConfiguration.getMyRow(),myConfiguration.getMyCol(),myConverter.convertListToString(myCellManager.getStatesAsList()));
    }

    private void fillGridRandomly(){
        int[][] initialState;
        if(TYPEOFSIMULATION.equals(BackendConstants.GAME_OF_LIFE_SIMULATION_NAME.toString())){
            initialState = myConverter.convertListOfStringsToArray(new GOLRandomCellArrayGenerator().getListOfRandomCellStates(SIMULATIONCOLUMNS*SIMULATIONROWS), SIMULATIONCOLUMNS, SIMULATIONROWS);
        }else{
            initialState = myConverter.convertListOfStringsToArray(new ThreeStateRandomCellArrayGenerator().getListOfRandomCellStates(SIMULATIONCOLUMNS*SIMULATIONROWS), SIMULATIONCOLUMNS, SIMULATIONROWS);
        }
        myCellManager = new CellManager(TYPEOFSIMULATION, initialState, SIMULATIONCOLUMNS, SIMULATIONROWS);
        myMainWindow.updateAnimation(myCellManager.getStatesAsList());
    }

    private void loadSimulationFromFile(){
        String fileName = "";

        try{
            fileName = myMainWindow.getFileChooser().showOpenDialog(myStage).toString();
        } catch(NullPointerException e) {
            showMessage(AlertType.ERROR, "You should select a file");
        }
        createNewSimulation(fileName);
    }


    private void showMessage(AlertType type, String message) {
        new Alert(type, message).showAndWait();
    }

    private void createNewSimulation(String newValue) {
        pauseAnimation();
        myConfiguration = getConfig(newValue);
        setUpCellManager();
        handleConstantSlider();
        myMainWindow.setUpSimulation(width,height,0,0,myConverter.StringToArrayList(myConfiguration.getMyInitial()), myConfiguration.getMyName(), myConfiguration.getMyCol(), myConfiguration.getMyRow());
    }

    private void updateFrameDelay(double newValue) {
        simulationAnimation.setRate(newValue);
    }

    private void updateStat(double newValue){
        myCellManager.updateSimStat(newValue);
    }

    private void stepAnimation() {
        if(!isSimulationOn){
            simulationStep();
        }
    }

    private void simulationStep() {
        myCellManager.updateCurrentGraphState();
        myMainWindow.updateAnimation(myCellManager.getStatesAsList());
    }

    private void toggleAnimation() {
        if(isSimulationOn){
            pauseAnimation();
        }
        else{
            myMainWindow.setStartButtonToPause();
            isSimulationOn = true;
            simulationAnimation.play();
        }
    }

    private void pauseAnimation() {
        myMainWindow.setStartButtonToStart();
        isSimulationOn=false;
        simulationAnimation.pause();
    }

    private void handleConstantSlider(){
        Boolean decision =  TYPEOFSIMULATION.equals(BackendConstants.FIRE_SIMULATION_NAME.toString()) ||
                TYPEOFSIMULATION.equals(BackendConstants.SEGREGATION_SIMULATION_NAME.toString());
        myMainWindow.getConstantSlider().setDisable(! decision);
        myMainWindow.getConstantSlider().setValue(myConfiguration.getMyThreshold());

    }

    public static void main(String[] args){
        launch(args);
    }
}
