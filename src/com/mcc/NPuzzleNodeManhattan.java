package com.mcc;

public class NPuzzleNodeManhattan extends NPuzzleNode implements AStar, Comparable<NPuzzleNodeManhattan> {
    private int manhattanDistance;
    private int movementNumber;

    public NPuzzleNodeManhattan(int[] board, NPuzzleNode parent, int movementNumber, int[][] target) throws Exception {
        super(board, parent);
        initialize(movementNumber, target);
    }

    public NPuzzleNodeManhattan(int[][] initialState, NPuzzleNode parent, int movementNumber, int[][] target) {
        super(initialState, parent);
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

    public void setMovementNumber(int movementNumber) {
        this.movementNumber = movementNumber;
    }

    public int getHeuristicCost() {
        return manhattanDistance + movementNumber;
    }
}
