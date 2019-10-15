Names: 
- Riley Cohen
- Kyle Hong
- Ben Lawrence

Net Ids:
- rsc30
- bcl19
- sh394

# Refactoring Lab

## GUI

There were two main issues with the GUI that caused a lot of errors in the design checkup app. They were:

1. The incorrect use of private final vs. private static final variables
    - There were some instances were I declared private final variables that never changed values. In these cases I should have declared public static final variables. I was able to fix all of these issues during the lab time.

2. The use of "magic numbers:"
    - There were some cases where I used numbers instead of named position variables. This specifically occured in the controllerBox class, where I use a grid plane to place buttons. The function to place an item on a grid plane takes 4 integer (row, column, length, width) and I hard-coded these integers. I will either move them to the ResourceBundle or make them private static final before sumbmitting the completed project.

From a design perspective, I was pretty satisfied with the GUI. It seems to be abstract and flexible enough to incorporate all of the new requirements for this project.

## Configuration
1. Use of Specific implementattion class
    - I had a lot of specific implementation class such as "ArrayList". The change made to the each rules in order to use Java collection interfaces such as "List". 

2. Use of complex expression.
    - I had an expression which had four conditional operators in just one expression, so I pulled out one of the conditional operators and made it into a method that returns a boolean value.

3. Use of magic numbers
    - I had a lot of magic numbers in both cofiguration and rules classes, so I changed them to well-named constants and used the constants instead.

## Backend
The first change made to the Backend was to add curly brackets to all the single line if statements. For example, the code

```java=
if (col.getState() == SegregationCellStates.EMPTY.getState())
    emptyCells.add(col);
```

was changed to 

```java=
if (col.getState() == SegregationCellStates.EMPTY.getState()) {
    emptyCells.add(col);
}
```

This change does not affect the functionality or even really the overall design of the code. However, in the event that a future programmer wishes to add more functionality to this `if` statement, then the curly braces are required.

______________________

The next code issue that was changed was the removal of unused methods. In `GameOfLifeGraph.java`, there was a method called `removeDissatisfied()` that was empty and never called. This method was left over from a copy-and-paste from `SegregationGraph.java` and had been forgotten about until we used the code refactoring tool in class.

_____________

Finally, the nested `ArrayList` that is holding the graph structure for `GameOfLive.java` was accidentally left public for testing purposes as seen below:

```java
public ArrayList<ArrayList<GameOfLifeCell>> cells;
```

This was quickly refactored to be private so that other code which has an instance of `GameOfLifeGraph` cannot simply use say `GameOfLifeGraph.cells` to modify the graph data. The new code is seen below:

```java
private ArrayList<ArrayList<GameOfLifeCell>> cells;
```