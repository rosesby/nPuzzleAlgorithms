package com.mcc;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.ArrayList;

public class NPuzzleNodeManhattan extends NPuzzleNode implements AStarHeuristic<NPuzzleNodeManhattan> {
    private int manhattanDistance;
    private int movementNumber;

    public NPuzzleNodeManhattan(int[] board, NPuzzleNode parent, int movementNumber, int[] target) throws Exception {
        super(board, parent);
        initialize(movementNumber, Array1DTo2D(target));
    }

    public NPuzzleNodeManhattan(int[][] initialState, NPuzzleNode parent, int movementNumber, int[][] target) {
        super(initialState, parent);
        initialize(movementNumber, target);
    }

    public NPuzzleNodeManhattan(NPuzzleNode data, int movementNumber, int[][] target) throws Exception {
        super(data.matrix, data.parent);
        initialize(movementNumber, target);
    }

    private void initialize(int movementNumber, int[][] target) {
        this.movementNumber = movementNumber;
        manhattanDistance(target);
    }

    @Override
    public int compareTo(NPuzzleNodeManhattan npnmd) {
        int pa = this.getHeuristicCost();
        int pb = npnmd.getHeuristicCost();
        if (pa > pb) return 1;
        if (pa < pb) return -1;
        else return 0;
    }

    public ArrayList<NPuzzleNodeManhattan> GenerateChildren(int[][] target) {
        int[] zeroIndex = findZero();
        int zx = zeroIndex[0];
        int zy = zeroIndex[1];
        int childrenLevelNumber = movementNumber + 1;

        ArrayList<NPuzzleNodeManhattan> childs = new ArrayList<>();

        if (zy > 0){
            NPuzzleNodeManhattan up = new NPuzzleNodeManhattan(
                    moveZeroUp(SerializationUtils.clone(matrix), zeroIndex),
                    this, childrenLevelNumber,
                    target);
            childs.add(up);
        }
        if (zy < arrayMaxBound){
            NPuzzleNodeManhattan down = new NPuzzleNodeManhattan(
                    moveZeroDown(SerializationUtils.clone(matrix), zeroIndex),
                    this, childrenLevelNumber,
                    target);
            childs.add(down);
        }
        if (zx < arrayMaxBound){
            NPuzzleNodeManhattan right = new NPuzzleNodeManhattan(
                    moveZeroRight(SerializationUtils.clone(matrix), zeroIndex),
                    this, childrenLevelNumber,
                    target);
            childs.add(right);
        }
        if (zx > 0) {
            NPuzzleNodeManhattan left = new NPuzzleNodeManhattan(
                    moveZeroLeft(SerializationUtils.clone(matrix), zeroIndex),
                    this, childrenLevelNumber,
                    target);
            childs.add(left);
        }
        return childs;
    }

    public void manhattanDistance(int[][] target) {
        manhattanDistance = 0;
        for (int y1 = 0; y1 < matrix[0].length; y1++) {
            for (int x1 = 0; x1 < matrix.length; x1++) {
                int actualValue = matrix[y1][x1];
                int[] valuePositionInTarget = getPositionOfElementIn2DMatrix(actualValue, target);
                int y0 = valuePositionInTarget[0];
                int x0 = valuePositionInTarget[1];
                manhattanDistance += Math.abs(y1 - y0) + Math.abs(x1 - x0);
            }
        }
    }

    public int getHeuristicCost() {
        return manhattanDistance + movementNumber;
    }

    public int getMovementNumber() {
        return movementNumber;
    }

    @Override
    public String toString() {
        return "Cost(" + getHeuristicCost() + ") : L" + movementNumber + " + D" + manhattanDistance + "\n" + super.toString();
    }
}
