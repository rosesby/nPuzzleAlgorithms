package com.mcc;

import java.util.HashSet;

public class NPuzzleSolver {
    public static HashSet<int[][]> previousStates = new HashSet<>();
    private static int[][] solution;
    public static HashSet<NPuzzleNode> actualLevelNodes = new HashSet<>();
    public static HashSet<NPuzzleNode> nextLevelNodes = new HashSet<>();
    private static int lastColumnIndex, lastRowIndex;

    private NPuzzleSolver() {}

    public static void setWorkingSolution(NPuzzleNode nPuzzleState) {
        solution = nPuzzleState.getMatrix();
        lastColumnIndex = nPuzzleState.getMatrix().length - 1;
        lastRowIndex = nPuzzleState.getMatrix()[0].length - 1;
    }

    public static void startSearch(NPuzzleNode initialNode, NPuzzleNode targetNode){

    }


}
