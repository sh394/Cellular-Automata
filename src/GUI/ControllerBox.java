package GUI;


import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.FileChooser;

import java.util.ResourceBundle;

/**
 * @author riley
 * The controller box is responsible for holding all of the controls the user interacts with. It depends on the
 * cell society grid pane class, as well as certain resource files. It assumes that the correct ressource file exists
 * use it as a part of the main window.
 */

public class ControllerBox extends CellSocietyGridPane {


    private Button startButton;
    private Button stepButton;
    private Button randomFillButton;
    private Slider frameSlider;
    private FileChooser fileChooser;
    private Button fileOpenerButton;
    private Button toXMLButton;
    private Slider constantSlider;

    private String STARTTEXT;
    private  String PAUSETEXT;
    private  String STEPTEXT;
    private  String FRAMELABELTEXT;
    private  String COMBOBOXLABEL;
    private String FILEOPENERTEXT;
    private String RANDOMFILLTEXT;
    private String TOXMLTEXT;

    private  double INITIALFRAMESPEED;
    private  double SLIDERMAX ;
    private  double SLIDERMIN ;
    private double CONSTANTSLIDERMIN;
    private  double CONSTANTSLIDERMAX;

    private int CONSTANTSLIDERCOL;
    private int CONSTANTSLIDERROW;
    private int CONSTANTSLIDERCOLWID;
    private int CONSTANTSLIDERROWHT;

    private int XMLBUTTONCOL;
    private int XMLBUTTONROW;
    private int XMLBUTTONCOLWID;
    private int XMLBUTTONROWHT;

    private int COMBOBOXLABELCOL;
    private int COMBOBOXLABELROW;
    private int COMBOBOXLABELCOLWID;
    private int COMBOBOXLABELROWHT;

    private int COMBOBOXCOL;
    private int COMBOBOXROW;
    private int COMBOBOXCOLWID;
    private int COMBOBOXROWHT;

    private int FRAMESLIDERLABELCOL;
    private int FRAMESLIDERLABELROW;
    private int FRAMESLIDERLABELCOLWID;
    private int FRAMESLIDERLABELROWHT;

    private int FRAMESLIDERCOL;
    private int FRAMESLIDERROW;
    private int FRAMESLIDERCOLWID;
    private int FRAMESLIDERROWHT;

    private int STARTBUTTONCOL;
    private int STARTBUTTONROW;
    private int STARTBUTTONCOLWID;
    private int STARTBUTTONROWHT;

    private int STEPBUTTONCOL;
    private int STEPBUTTONROW;
    private int STEPBUTTONCOLWID;
    private int STEPBUTTONROWHT;

    private int RANDOMFILLBUTTONCOL;
    private int RANDOMFILLBUTTONROW;
    private int RANDOMFILLBUTTONCOLWID;
    private int RANDOMFILLBUTTONROWHT;

    /**
     * initializer
     * @param width
     * @param height
     * @param xOffSet
     * @param yOffSet
     */
    public ControllerBox(int width, int height, int xOffSet, int yOffSet){
        super(width, height, xOffSet, yOffSet);
        loadResourceBundle();
        loadGridPanePositions();
        initializeStartButton();
        initializeStepButton();
        initializeFrameSlider();
        initializeSimulationChooser();
        initializeRandomFillButton();
        initializeToXMLButton();
    }

    private void loadGridPanePositions() {
        ResourceBundle bundle = ResourceBundle.getBundle("UIControllerBoxPositions");
        CONSTANTSLIDERCOL = Integer.parseInt(bundle.getString("constantSliderCol"));
        CONSTANTSLIDERROW = Integer.parseInt(bundle.getString("constantSliderRow"));
        CONSTANTSLIDERCOLWID = Integer.parseInt(bundle.getString("constantSliderColWid"));
        CONSTANTSLIDERROWHT = Integer.parseInt(bundle.getString("constantSliderRowHt"));
        XMLBUTTONCOL = Integer.parseInt(bundle.getString("XMLButtonCol"));
        XMLBUTTONROW = Integer.parseInt(bundle.getString("XMLButtonRow"));
        XMLBUTTONCOLWID = Integer.parseInt(bundle.getString("XMLButtonColWid"));
        XMLBUTTONROWHT = Integer.parseInt(bundle.getString("XMLButtonRowHt"));
        COMBOBOXLABELCOL = Integer.parseInt(bundle.getString("comboBoxLabelCol"));
        COMBOBOXLABELROW = Integer.parseInt(bundle.getString("comboBoxLabelRow"));
        COMBOBOXLABELCOLWID = Integer.parseInt(bundle.getString("comboBoxLabelColWid"));
        COMBOBOXLABELROWHT = Integer.parseInt(bundle.getString("comboBoxLabelRowHt"));
        COMBOBOXCOL = Integer.parseInt(bundle.getString("comboBoxCol"));
        COMBOBOXROW = Integer.parseInt(bundle.getString("comboBoxRow"));
        COMBOBOXCOLWID = Integer.parseInt(bundle.getString("comboBoxColWid"));
        COMBOBOXROWHT = Integer.parseInt(bundle.getString("comboBoxRowHt"));
        FRAMESLIDERLABELCOL = Integer.parseInt(bundle.getString("frameSliderLabelCol"));
        FRAMESLIDERLABELROW = Integer.parseInt(bundle.getString("frameSliderLabelRow"));
        FRAMESLIDERLABELCOLWID = Integer.parseInt(bundle.getString("frameSliderLabelColWid"));
        FRAMESLIDERLABELROWHT = Integer.parseInt(bundle.getString("frameSliderLabelRowHt"));
        FRAMESLIDERCOL = Integer.parseInt(bundle.getString("frameSliderCol"));
        FRAMESLIDERROW = Integer.parseInt(bundle.getString("frameSliderRow"));
        FRAMESLIDERCOLWID = Integer.parseInt(bundle.getString("frameSliderColWid"));
        FRAMESLIDERROWHT = Integer.parseInt(bundle.getString("frameSliderRowHt"));
        STARTBUTTONCOL = Integer.parseInt(bundle.getString("startButtonCol"));
        STARTBUTTONROW = Integer.parseInt(bundle.getString("startButtonRow"));
        STARTBUTTONCOLWID = Integer.parseInt(bundle.getString("startButtonColWid"));
        STARTBUTTONROWHT = Integer.parseInt(bundle.getString("startButtonRowHt"));
        STEPBUTTONCOL = Integer.parseInt(bundle.getString("stepButtonCol"));
        STEPBUTTONROW = Integer.parseInt(bundle.getString("stepButtonRow"));
        STEPBUTTONCOLWID = Integer.parseInt(bundle.getString("stepButtonColWid"));
        STEPBUTTONROWHT = Integer.parseInt(bundle.getString("stepButtonRowHt"));
        RANDOMFILLBUTTONCOL = Integer.parseInt(bundle.getString("randomFillButtonCol"));
        RANDOMFILLBUTTONROW = Integer.parseInt(bundle.getString("randomFillButtonRow"));
        RANDOMFILLBUTTONCOLWID = Integer.parseInt(bundle.getString("randomFillButtonColWid"));
        RANDOMFILLBUTTONROWHT = Integer.parseInt(bundle.getString("randomFillButtonRowHt"));

    }

    private void loadResourceBundle() {
        ResourceBundle bundle = ResourceBundle.getBundle("UIText");
        STARTTEXT=bundle.getString("startText");
        PAUSETEXT=bundle.getString("pauseText");
        STEPTEXT=bundle.getString("stepText");
        FRAMELABELTEXT=bundle.getString("frameLabelText");
        COMBOBOXLABEL=bundle.getString("comboBoxLabel");
        TOXMLTEXT = bundle.getString("toXMLText");
        FILEOPENERTEXT = bundle.getString("fileOpenerButtonText");
        RANDOMFILLTEXT = bundle.getString("randomFillButtonText");
        bundle = ResourceBundle.getBundle("UINumberConstants");
        INITIALFRAMESPEED = Integer.parseInt(bundle.getString("initialFrameSpeed"));
        SLIDERMAX = Double.parseDouble(bundle.getString("frameSliderMax"));
        SLIDERMIN = Double.parseDouble(bundle.getString("frameSliderMin"));
        CONSTANTSLIDERMAX = Double.parseDouble(bundle.getString("constantSliderMax"));
        CONSTANTSLIDERMIN = Double.parseDouble(bundle.getString("constantSliderMin"));

    }

    private void initializeToXMLButton() {
        toXMLButton = new Button();
        toXMLButton.setText(TOXMLTEXT);
        getMyPane().add( toXMLButton,XMLBUTTONCOL,XMLBUTTONROW,XMLBUTTONCOLWID,XMLBUTTONROWHT);
    }

    /**
     * intializes the constant slider to have a certain value
     * @param initialConstantValue
     */
    public void initializeConstantSlider(double initialConstantValue) {
        constantSlider = new Slider(CONSTANTSLIDERMIN, CONSTANTSLIDERMAX, initialConstantValue);
        constantSlider.setShowTickLabels(true);
        getMyPane().add(constantSlider, CONSTANTSLIDERCOL,CONSTANTSLIDERROW,CONSTANTSLIDERCOLWID,CONSTANTSLIDERROWHT);
    }

    private void initializeSimulationChooser() {
        fileChooser = new FileChooser();
        fileOpenerButton = new Button();
        fileOpenerButton.setText(FILEOPENERTEXT);
        Label comboBoxLabel = createLabelWithText(COMBOBOXLABEL);
        getMyPane().add( comboBoxLabel,COMBOBOXLABELCOL,COMBOBOXLABELROW,COMBOBOXLABELCOLWID,COMBOBOXLABELROWHT);
        getMyPane().add(fileOpenerButton, COMBOBOXCOL,COMBOBOXROW,COMBOBOXCOLWID,COMBOBOXROWHT);
    }

    private void initializeFrameSlider() {
        frameSlider = new Slider(SLIDERMIN, SLIDERMAX, INITIALFRAMESPEED);
        frameSlider.setShowTickLabels(true);
        Label frameLabel = createLabelWithText(FRAMELABELTEXT);
        getMyPane().add(frameSlider, FRAMESLIDERLABELCOL,FRAMESLIDERLABELROW,FRAMESLIDERLABELCOLWID,FRAMESLIDERLABELROWHT);
        getMyPane().add(frameLabel, FRAMESLIDERCOL,FRAMESLIDERROW,FRAMESLIDERCOLWID,FRAMESLIDERROWHT);
    }

    private Label createLabelWithText(String text){
        Label label = new Label();
        label.setText(text);
        return label;
    }

    private void initializeStartButton() {
        startButton = new Button();
        startButton.setText(STARTTEXT);
        getMyPane().add(startButton,STARTBUTTONCOL,STARTBUTTONROW,STARTBUTTONCOLWID,STARTBUTTONROWHT);
        getMyPane().setHalignment(startButton, HPos.CENTER);
        getMyPane().setValignment(startButton, VPos.CENTER);
    }

    private void initializeStepButton() {
        stepButton = new Button();
        stepButton.setText(STEPTEXT);
        getMyPane().add(stepButton,STEPBUTTONCOL,STEPBUTTONROW,STEPBUTTONCOLWID,STEPBUTTONROWHT);
    }

    private void initializeRandomFillButton(){
        randomFillButton = new Button();
        randomFillButton.setText(RANDOMFILLTEXT);
        getMyPane().add(randomFillButton, RANDOMFILLBUTTONCOL,RANDOMFILLBUTTONROW,RANDOMFILLBUTTONCOLWID,RANDOMFILLBUTTONROWHT);
    }

    /**
     * sets start button to start
     */
    public void setStartButtonToStart(){startButton.setText(STARTTEXT);}

    /**
     * sets start button to pause
     */
    public void setStartButtonToPause(){startButton.setText(PAUSETEXT);}

    /**
     * getter for start button
     * @return
     */
    public Button getStartButton(){return startButton;}

    /**
     *
     * @return
     */
    public Button getStepButton(){return  stepButton;}

    /**
     *
     * @return
     */
    public Slider getFrameSlider(){return frameSlider;}

    /**
     *
     * @return
     */
    public Button getFileOpenerButton(){return fileOpenerButton;}

    /**
     *
     * @return
     */
    public FileChooser getFileChooser(){return fileChooser;}

    /**
     *
     * @return
     */
    public Button getRandomFillButton(){return randomFillButton;}

    /**
     *
     * @return
     */
    public Button getToXMLButton(){return toXMLButton;}

    /**
     *
     * @return
     */
    public Slider getConstantSlider(){return constantSlider;}


}
