package com.mcc;

import java.util.*;

public class NPuzzleSolver {
    private static int[][] solution;
    private static int lastColumnIndex, lastRowIndex;

    private NPuzzleSolver() {
    }

    public static void setWorkingSolution(NPuzzleNode nPuzzleState) {
        solution = nPuzzleState.getMatrix();
        lastColumnIndex = nPuzzleState.getMatrix().length - 1;
        lastRowIndex = nPuzzleState.getMatrix()[0].length - 1;
    }

    public static void startSearch(NPuzzleNode initialNode, NPuzzleNode targetNode) {
        ArrayDeque<int[][]> previousStates = new ArrayDeque<>();
        Deque<NPuzzleNode> searchQueue = new ArrayDeque<>();
        searchQueue.add(initialNode);

        boolean result = false;

        NPuzzleNode solutionNode = null;

        while ((!searchQueue.isEmpty() && !result)) { //While the queue has nodes

            if (previousStates.contains(solution)) {
                System.out.println("found");
                return;
            }
            searchQueue.peek().print("Original node");
            for(NPuzzleNode node : searchQueue.peek().GenerateChilds()){
                if (!previousStates.contains(node.getMatrix())) {
                    previousStates.add(node.getMatrix());
                    searchQueue.add(node);
                    result  = Arrays.deepEquals(node.getMatrix(),solution);

                    if (result) {
                        System.out.println(result);
                        solutionNode = node;
                        printSolutionPath(solutionNode);
                        break;
                    }
                }
            }
            searchQueue.poll();
        }
        System.out.println("Search finished");
    }

    private static void printSolutionPath(NPuzzleNode lastNode) {
        NPuzzleNode actualNode = lastNode;
        StringBuilder stringBuilder = new StringBuilder();
        boolean condition;
        int stageCounter = actualNode.getParentHierarchyNumber();
        do{
            stringBuilder.insert( 0,"Paso " + stageCounter + "\n" + actualNode.toString() + "\n");
            condition = actualNode.parent != null;
            actualNode = actualNode.parent;
            stageCounter--;
        }while (condition);
        System.out.println("Solution Path");
        System.out.println(stringBuilder);
    }
}
