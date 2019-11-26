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

        {
            while (!searchQueue.isEmpty()) { //While the queue has nodes
                var result = searchQueue
                        .peek()
                        .GenerateChilds()
                        .stream()
                        .filter(node -> {
                            boolean alreadyAdded = !previousStates.contains(node.getMatrix());
                            if (alreadyAdded) {
                                previousStates.add(node.getMatrix());
                                searchQueue.add(node);
                            }
                            return alreadyAdded;
                        })
                        .filter(node -> Arrays.deepEquals(node.getMatrix(), solution))
                        .findFirst();

                if (result.isPresent()) {
                    printSolutionPath(result.get());
                    return;
                }
                
/*            for (NPuzzleNode node : searchQueue.peek().GenerateChilds()) {
                if (!previousStates.contains(node.getMatrix())) {
                    previousStates.add(node.getMatrix());
                    searchQueue.add(node);
                    if (Arrays.deepEquals(node.getMatrix(), solution)) {
                        printSolutionPath(node);
                        return;
                    }
                }
            }*/
                searchQueue.poll();
            }
        }
        System.out.println("Search finished, without results");
    }

    private static void printSolutionPath(NPuzzleNode lastNode) {
        NPuzzleNode actualNode = lastNode;
        StringBuilder stringBuilder = new StringBuilder();
        boolean condition;
        int stageCounter = actualNode.getParentHierarchyNumber();
        do {
            stringBuilder.insert(0, "Paso " + stageCounter + "\n" + actualNode.toString() + "\n");
            condition = actualNode.parent != null;
            actualNode = actualNode.parent;
            stageCounter--;
        } while (condition);
        System.out.println("Solution Path");
        System.out.println(stringBuilder);
    }
}
