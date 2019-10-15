package Rules.SimulationRules;

import Backend.Enumerators.GameOfLifeStates;

import Rules.Rules;

import java.util.List;

public class GameOfLifeRules extends Rules {
    private static final int ALIVE = GameOfLifeStates.ALIVE.getState();
    private static final int DEAD = GameOfLifeStates.DEAD.getState();
    private double numNeighbors;

    public GameOfLifeRules() {
        super();
        numNeighbors = 3;
    }

    /**
     * Updates the next state of the cell based on the current state and the states of its neighbors
     */
    @Override
    public int nextGeneration(int currentState, List<Integer> neighborStates) {
        int aliveNeighbors = countStates(neighborStates, ALIVE);

        if(reproduction(currentState, aliveNeighbors) || alive(currentState, aliveNeighbors)) {
            return ALIVE;
        }
        return DEAD;
    }

    public void updateNumNeighbors(double numNeighbors) {
        this.numNeighbors = numNeighbors;
    }

    /**
     * check whether if a cell will be alive to the next generation
     */
    private boolean alive(int currentState, int aliveNeighbors) {
        return (currentState == ALIVE && (aliveNeighbors == numNeighbors-1 || aliveNeighbors == numNeighbors));
    }

    /**
     * Reproduce when a dead cell has exactly three neighbors
     */
   private boolean reproduction(int currentState, int aliveNeighbors) {
       return (currentState == DEAD && aliveNeighbors == numNeighbors);
   }
}