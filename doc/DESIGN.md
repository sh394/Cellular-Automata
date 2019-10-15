# What are the project's design goals? Specifically what kinds of new features did you want to make easy to add?
## Front End
The main goal of the front was to be able to detach itself completely from the backend's cell structure and display *the grid* to the user in a clean manner. Furthermore, the front end had to be flexible enough to support multiple *kinds* of grids and cell types.

Furthermore, the front end must be composed of components that are easily seperable and repeatable from the main pane. This makes it easy to adapt the front end to simulations ran on the back end.

The `simulation pane` is the area in which the simulation is displayed to the user. In order to create a simulation, you need a `cell generator` and `cell placer` object. The `cell generator` object is responsible for creating a specific cell type for a simulation, whereas a `cell placer` is responsible for deciding the position of the cell on the grid. This structure allows us to easily add new simulations and grid structures to the application. For example, if we wanted to add a triangular cell to the front end, we would just have to create two new sub-classes for each of the aforementioned classes, and we would not need to change the code for any other classes at all.

Lastly, we wanted it to be easy to *control* the simulation from the front end. This was easily done by detaching the front and end back end completely. Also UI text was placed in ressource bundles, so altering text and adding langauges would be very easy.


## Backend
### New Simulation
The goal of the backend is to control the simulation taking place. The way in which the cells are updated, the data structures involved, and the methodology by which commands are executed are meant to be kept separate from the front end.

The goal of the backend was to make it very easy to add new simulation cells and graphs. The `Node` abstract class and `Graph` interface create a standardized version of those two types of objects. To make a new graph, simply implement the `Graph` interface and all the required four methods: `addNode()`, `updateGraph`, `getCellsAsList`, and `updateStat`. Then, add the graph type to the CellManger mapping. Using a `CellManager` in the backend makes it very scalable because the front end never has to worry about instantiating the correct graph or cell type. Instead it only ever makes one `CellManager` and then that manager takes care of making the graph.

Additionally, the `CellManager` never has to worry about what types of cells are present because the `Graph` does that. To create a new simulation cell, add a cell to the `CellTypes` directory that extends the `Node` class. This cell can then be used in its corresponding `Graph` class.

So overall, the front end tells the backend to make a `Graph` without worrying about what type of graph is made. The `Graph` makes a set of cells based on the passed in initial conditions without the `CellManager` worrying about what types of cells are being used.

### Dynamic User Input
Currently, the backend supports a user dynamically changing the single `threshold` parameter of a graph in the event that that graph uses only one paramater to determine next state. However, it is also possible to add in other features.

To add a feature such as clicking on a cell changes its state would require adding an `updateCellState()` method in the `CellManager` and then adding a corresponding method in each of the `Graph` classes. Instead of adding this new method to the `Graph` interface, to add methods that change features within the rules or cells themselves new interfaces would be created and the `Graph` class would extend these interfaces.

## Configuration
The main goal of the configuration was to read in the selected xml file containing variables according to the specific parameters, and pass the information to the `Controller` class where it will be processed and passed to the `CellManager` class.

After reading in the data from xml files in the `configReader` class, the `configuration` class make an object that stores the data. Therefore, we can easiliy add or get data from the xml file by improving relatedness inside classes. Data and the relevant behaviors are inside a single class(`configuration`).

Furthemore, we have user-defined exception class that allows us to handle any expections according to user need, so it was easy to implement error checking for incorrect file data and any exceptions that need to be handled for the requirements.


As a reulst, It would be easy to implement error checking for incorrect file data and allow users to save the current state of the simulation as an XML configuration file because all data and the relevant behaviors are inside a single class. All I needed to do was just adding some implementations in the same class.


# Describe the high-level design of your project, focusing on the purpose and interaction of the core classes.
## Front End
### Main Pane

The main window is the root pane that is attached to the scene. It it is responisble for containing and maintaing three main objects:

1.  The simulation display
2.  The controller box
3.  The graphing display

### Simulation

The simulation is responsible for holding cells. They are generated using a `cell generator` object and placed on the simulation using a `cell placer ` object. The simulation updates itself by getting a list of new cell states. It then calls `cell.update(newState)` on each of the cells in the simulation.


### Cell UI

The `cell ui` object is the most abstract class of the cell ui, which are responsible for showing the user the cell state. These objects are attached to the simulation pane.

### Controller Box

This class is the box of buttons that appear on the side of the screen. They allow the user to interact with the simulation and change parameters.

### Graphing display

The graphing diplay is in the corner of the main pane. It displays statistics related to the states of each cell.


## Backend
### Cell Manager
The `CellManager` class is the class instantiated by the controller and the class which takes in front end data and passes back the current simulation state. The `CellManager` takes in all the initial states of the simulation when initialized. Currently, this means that it takes a `String` simulation type, a `int[][]` of simulation initial values, and a set of variable arguments for any special simulations.

The `CellManager` instantiates and controls the graph of cells in the backend. Instead of making the backend a grid like the front end, the backend simply holds a graph with nodes, which allows for a more scalable architecture. The `CellManager` is called by the front end for updating, changing the simulation parameter, and returning the current states of the nodes as a list.

### Graphs
The `Graph` interface defines the characteristics of the backend graph. The simulation `Graph` is instantiated by the `CellManager`. The `Graph` is in charge of holding all the different nodes in the backend and updating each node. The `addNode()` method is called by `CellManager` when adding all the initial states. The `updateGraph()`, `getCellsAsList()`, and `updateStat()` methods are called by `CellManager` when the front makes an analogous call (this keeps the front end from having any direct control over the backend graph).

### Cells
The `Cell` classes hold the varying types of cells for each simulation and are responsible for saving each individual cell's state. All `Cell` classes extend the `Node` interface. The `Node` interface just says that every cell should have a row, column, and current state. Outside of that, each cell can have different neighbor types, different rules, more than one state, etc.

A `Cell` object is only instantiated by a `Graph`. When a graph adds a cell, it creates it with the given row, column, and initial state passed in by the `CellManger`. The graph will then update the cell's state by passing the cell's state along with all its neighbor states to a `Rule` object. This `Rule` object will then return the cell's new state and a new `Cell` will be created to add to the new graph.

### Enumerators
Enumerators are used to store the varying states of each cell type for each simulation. For example, `FireStates` says that a cell can be `EMPTY`, `TREE`, or `BURNING` in the _Spreading Fire_ simulation.

Enumerators are used throughout the application. The backend uses enumerators to compare and set new states. Rules use enumerators to check neighbor conditions.

## Configuration
### configReader
The simulation type will be read in from the xml file in the `configReader` class, then this information will be processed in the controller class and passed through to the `CellManger` class so the class can call methods accordingly. All of the methods for reading and handling XML data will only be declared in this class so that any future updates we may need to make on our configuration can be made and extended easiliy in one location.

### configuration
After reading in the data from xml files in the `configReader` class, the `configuration` class make an object that stores the data. Therefore, we can easiliy add or get data from the xml file by improving relatedness inside classes. Data and the relevant behaviors are inside a single class(`configuration`).

### configException
This class is custom exception class that are used to cuotmize the exception according to user need. By having this customized exception class, we can easiliy catch or check any unexpected exceptions that might occur in the future or need to be handled beforehand.


# What assumptions or decisions were made to simplify your project's design, especially those that affected adding required features?
## Front End
The following assumptions were made on the front end:

### Single Parameter Simulations
We assumed that all new simulations would have a single paremeter, beyond neighbors, that dictate the next state of a cell. For example, in the spreading fire simulation, there is a parameter probability catch, which is the probability that a neighboring cell catches on fire given a cell is on fire. In that regard, we created only one slider to allow the user to edit that parameter as the simulation is running. Because of this assumption, we could not easily add the editing of additional parameters during the second sprint.


## Backend
### Decisions
In the backend, it was decided that the front end would talk to a `CellManager` instead of the graph itself to simplify interaction between the two. This would make it easier to add new simulation types as well as keeping the front end from having direct access to the graph.

Additionally, it was decided that in order to simplify initialization of a graph, the backend would take in a 2D integer array with the initial states of all the cells. This made instantiating the `CellManager` and `Graph` classes far simpler but was paid for in functionality as it only allow for sending back states corresponding to rectangular cells and grids.

### Assumptions
The biggest assumption made in the backend was that each type of cell would rely on a single state parameter that could be changed by passing in the current cell's state along with its neighbor states to a `Rules` class. This was a poor assumption and would be done differently if done again. This assumption became especially apparent when we tried to implement the _WaTor_ simulation, as that graph requires knowing energy, reproduction time, and what types of cells the neighbors are in order to determine the next state.


## Configuration
### Assumptions
The biggest assumption made in the configuration was that inital states of the cell in the xml file could be in any format we wanted. However, it turned out that it was really important to take the foramt of the xml file into consideration because we could simply add additional features if we formatted our xml files according to our program desgin.



# Describe, in detail, how to add new features to your project, especially ones you were not able to complete by the deadline.
## Different Arrangement of Neighbors
### Front End


Since the front end does not calculate the new states of the cells, the neighbors of the cells don't matter for the front end. The back end would have take the new arrangement of neighbors and pass the states to the front end.

### Backend
Currently, most graphs obtain the states of a cell's neighbors like this

```java=
private ArrayList<Integer> neighborStates(GameOfLifeCell cell) {
    ArrayList<Integer> neighborStates = new ArrayList<>();
    if (cell.getRow() - 1 >= 0) {
        neighborStates.add(cells.get(cell.getRow() - 1).get(cell.getCol()).getState());
    }
    if (cell.getRow() + 1 < cells.size()) {
        neighborStates.add(cells.get(cell.getRow() + 1).get(cell.getCol()).getState());
    }
    if (cell.getCol() - 1 >= 0) {
        neighborStates.add(cells.get(cell.getRow()).get(cell.getCol() - 1).getState());
    }
    if (cell.getCol() + 1 < cells.get(cell.getRow()).size()) {
        neighborStates.add(cells.get(cell.getRow()).get(cell.getCol() + 1).getState());
    }

    return neighborStates;
}
```
But instead if the neighbors of each cell were stored directly in the cell like this:

```java=
private int row;
private int col;
private int state;
private List<int[][]> neighborsLocations

public Node(int r, int c, int initialState, List<int[][]> neighbors) {
    row = r;
    col = c;
    state = initialState;

    neighborsLocations = new ArrayList<>();
    neighborsLocations.addAll(neighbors);
}
```
Then each node itself could store the location of its neighbors. This would then make the `neighborStates()` method look more like
```java=
private ArrayList<Integer> neighborStates(GameOfLifeCell cell) {
    ArrayList<Integer> neighborStates = new ArrayList<>();
    for (int[][] pos : cell.getneighborsLocations()) {
        neighborStates.add(cells.get(pos[0]).get(pos[0]).getState());
    }

    return neighborStates;
}
```
Given the above implementation, the XML file would just need to read in what neighbors correspond to what nodes and pass that to the `CellManager`, which would in turn pass it to the `Graph`.

### Configuation
Since the configuration is not responsible for calculating the states of the cells. The back end should be able to calculate based on the data from the xml file, so ,in terms of the configuration class, it would be easy to implement this feature by just adding  what neighbors corresponds to what nodes in the xml file and pass that to the `cellManager`, which would in turn  pass it to the `Graph`


## Different Grid Edges
### Front End
As explained above, in order to create a grid with different cell edges, you would need to create a new `cell generator` for that shape and a  new `cell placer` for the shape. These classes would like the following:

```java=
package GUI.Cell.CellTypes;

import javafx.scene.shape.Rectangle;

/**
 * @author riley
 * This class is the base of the rectangle shape CellUI classes, responsible for representing a rectangle cell on the grid.
 * it depends on the cellUI class.  Use this class as an extension in a specific shape and type of cell combination.
 * Cell UI class.
 */


public abstract class RecCellUI extends CellUI {


    public RecCellUI(double width, double height, double x_position, double y_position,int ui_position_x, int ui_position_y){
        super(ui_position_x, ui_position_y);
        initiateMyShape(createRectangleShape(width, height,x_position,y_position));
    }

    private Rectangle createRectangleShape(double width, double height, double x_position, double y_position) {
        newShape rect = new newShape();
        rect.getStyleClass().add("my-rect");
        rect.setWidth(width);
        rect.setHeight(height);
        rect.setX(x_position);
        rect.setY(y_position);
        return rect;
    }
}

```

And the new cell placer would like the following. Obviously the logic would change depending on the shape. However, the basic implementation is shown below:

```java=
package GUI.Cell.Placers;


import GUI.Cell.CellTypes.CellUI;
import GUI.Cell.Generators.CellGenerator;

import java.util.List;

public class NewShapeCellPlacer extends CellPlacer {

    private double SHAPESIZE;

    public RecCellPlacer(int numColumns, int numRows, double simulationWidth, double simulationHeight) {
        super(numColumns, numRows, simulationHeight, simulationWidth);
        CELLWIDTH = decideShapeSize()


    }

    @Override
    public List<CellUI> createCellList(List<String> cellList, CellGenerator myCellGenerator){
        for(int i =0; i < getRows(); i++){
            for(int j = 0; j < getColumns(); j++){
                createCellAndAddToList(j,i, myCellGenerator, cellList.get(i*getColumns()+j));
            }
        }
        return getMyCells();
    }

    private void createCellAndAddToList(int row, int column, CellGenerator myCellGenerator, String s) {
        int state = Integer.parseInt(s);
        CellUI toAdd = myCellGenerator.generateCell(SHAPESIZE, decideXPosition(row), decideYPosition(column), column, row, state);
        addToMyCells(toAdd);
    }

    private double decideShapeSize() {
        return shapeSize;
    }


}


```

### Backend
This would be implemented in the exact same way as the varying neighbors above. The important difference here is the the XML file would say that the edge cells just have the opposite edge cells as neighbors. So if the grid is a `100x100` grid and cell `(99, 90)` wraps to have cell `(0,90)` as a neighbor, then the XML file would say that these two cells are neighbors.

See the configuration section below for more details.

### Configuation
As stated before, since the configuration class in not responsible for calculation of the grids or cells, it would be implemented easily, in terms of the configuration, by just adding a paratmeter that holds the status of the edge and what neighbors correspond to what nodes at the edge.


## Changing a Cell's State
### Front End

The front end already has a method that tells the back end if a cell was clicked:

```java=
   private void handleMouseInput(double x, double y) {
        if(x<(width-CONTROLLERPADDINGWIDTH)) {
            myCellManager.updateCellState(myConverter.getUIPositionX(x, width - CONTROLLERPADDINGWIDTH, SIMULATIONCOLUMNS), myConverter.getUIPositionY(y, height, SIMULATIONROWS));
        }
    }
```


### Backend
To change a cell's state in the backend, the `CellManager` would need a new method such as below:

```java=
public void updateCellState(int row, int col, int newState) {
    cells.updateCell(row, col, newState);
}
```
Next, each graph would need an `updateCell()` method as is seen below:

```java=
public void updateCell(int row, int col, int newState) {
    cells.get(row).set(col, new Node(row, col, newState));
}
```
Then the next time the list of cell states is asked for by the front end, the new state will be returned.