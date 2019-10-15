package GUI;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * @author riley
 * This is the most abstract class of all UI features. It is a pane that is exnteds features useful to this application.
 * use this class as an extension to any other classes that would require to be show graphically. It depends on the cell
 * society pane class. For example, this class is used as an extension to the cell society grid pane.
 *
 */

public abstract class CellSocietyPane {
    private Pane myPane;
    private double HEIGHT;
    private double WIDTH;
    private double XOFFSET;
    private double YOFFSET;

    /**
     *
     * @return the pane
     */
    public Pane getMyPane(){return myPane;}

    /**
     * Initializer
     * @param width
     * @param height
     */
    public CellSocietyPane(int width, int height, int xOffSet, int yOffSet){
        myPane = new Pane();
        myPane.setPrefSize(width, height);
        myPane.setLayoutX(xOffSet);
        myPane.setLayoutY(yOffSet);
        WIDTH=width;
        HEIGHT=height;
        XOFFSET = xOffSet;
        YOFFSET = yOffSet;
    }

    /**
     * Attaches the pane of this object to another pane
     * @param root
     */
    public void attachPaneTo(Pane root){
        root.getChildren().add(myPane);
    }

    /**
     * Removes all the children from the pane
     */
    public void removeAllPaneChildren(){myPane.getChildren().clear();}

    /**
     * Switches the panes background color
     * @param color
     */
    public void setBackgroundColor(Color color){
        myPane.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    protected double getHeight(){return HEIGHT;}
    protected double getWidth(){return WIDTH;}
    protected  double getXOffSet(){return XOFFSET;}
    protected double getYOffSet(){return YOFFSET;}

}
