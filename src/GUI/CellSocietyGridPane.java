package GUI;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

import java.util.ResourceBundle;

/**
 * @author riley
 * A class that extends the cell society pane abstract class. it is used to as a super class to the controller box.
 *
 */

public class CellSocietyGridPane extends CellSocietyPane {

    private GridPane myGridPane;

    private static final int COLUMNS = Integer.parseInt(ResourceBundle.getBundle("UINumberConstants").getString("gridPaneCol"));
    private static final int ROWS = Integer.parseInt(ResourceBundle.getBundle("UINumberConstants").getString("gridPaneRow"));

    /**
     * Initializer.
     * @param width
     * @param height
     * @param xOffSet
     * @param yOffSet
     */
    public CellSocietyGridPane(int width, int height, int xOffSet, int yOffSet){
        super(width, height, xOffSet, yOffSet);
        initializeGridPane();
        setConstraints();
    }

    private void initializeGridPane() {
        myGridPane = new GridPane();
        myGridPane.setPrefSize(getWidth(), getHeight());
        myGridPane.setLayoutX(getXOffSet());
        myGridPane.setLayoutY(getYOffSet());
    }

    /**
     * Attaches itself to another pane.
     * @param root
     */
    @Override
    public void attachPaneTo(Pane root){root.getChildren().add(myGridPane);}

    /**
     * returns the pane
     * @return
     */
    @Override
    public GridPane getMyPane(){return myGridPane;}

    private void  setConstraints(){
        for(int i = 0; i<COLUMNS; i++){
            myGridPane.getColumnConstraints().add(new ColumnConstraints(getWidth()/COLUMNS));
        }
        for(int i = 0; i<ROWS;i++){
            myGridPane.getRowConstraints().add(new RowConstraints(getHeight()/ROWS));
        }
    };

}
