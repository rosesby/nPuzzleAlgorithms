package com.mcc;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class NPuzzleSolver {

    private NPuzzleSolver() {
    }

    public static void AStarChildrenFirstStreams(NPuzzleNodeManhattan initialNode, int[][] target) {
        long initialTime = System.currentTimeMillis();
        ArrayDeque<int[][]> previousStates = new ArrayDeque<>();
        PriorityQueue<NPuzzleNodeManhattan> searchQueue = new PriorityQueue<>();
        searchQueue.add(initialNode);
        while (!searchQueue.isEmpty()) { //While the queue has nodes
            var result = searchQueue
                    .poll()
                    .GenerateChildren(target)
                    .stream()
                    .filter(node -> {
                        boolean alreadyAdded = !previousStates.contains(node.getMatrix());
                        if (alreadyAdded) {
                            previousStates.add(node.getMatrix());
                            searchQueue.add(node);
                        }
                        return alreadyAdded;
                    })
                    .filter(node -> Arrays.deepEquals(node.getMatrix(), target))
                    .findFirst();

            if (result.isPresent()) {
                long elapsedTime = System.currentTimeMillis() - initialTime;
                printSolutionPath(result.get(), elapsedTime, previousStates.size());
                return;
            }
        }
        System.out.println("Search finished, without results");
    }

    public static void AStarChildrenFirst(NPuzzleNodeManhattan initialNode, int[][] target) {
        long initialTime = System.currentTimeMillis();
        Deque<int[][]> previousStates = new ArrayDeque<>();
        Deque<NPuzzleNodeManhattan> searchQueue = new ArrayDeque<>();
        searchQueue.add(initialNode);
        previousStates.add(initialNode.getMatrix());
        while (!searchQueue.isEmpty()) {
            NPuzzleNodeManhattan actualNode = searchQueue.poll();
            for (NPuzzleNodeManhattan node : actualNode.GenerateChildren(target)) {
                if (!previousStates.contains(node.getMatrix())) {
                    previousStates.add(node.getMatrix());
                    searchQueue.add(node);
                    if (Arrays.deepEquals(node.getMatrix(), target)) {
                        long elapsedTime = System.currentTimeMillis() - initialTime;
                        printSolutionPath(node, elapsedTime, previousStates.size());
                        return;
                    }
                }
            }
        }
        System.out.println("Search finished, without results");
    }

    public static void AStar(NPuzzleNodeManhattan initialNode, int[][] target) {
        long initialTime = System.currentTimeMillis();
        Deque<int[][]> generatedStates = new ArrayDeque<>();
        Queue<NPuzzleNodeManhattan> searchQueue = new PriorityQueue<>();
        searchQueue.add(initialNode);
        generatedStates.add(initialNode.getMatrix());
        while (!searchQueue.isEmpty()) {
            NPuzzleNodeManhattan actualNode = searchQueue.poll();
            if (Arrays.deepEquals(actualNode.getMatrix(), target)) {
                long elapsedTime = System.currentTimeMillis() - initialTime;
                printSolutionPath(actualNode, elapsedTime, generatedStates.size());
                return;
            }
            actualNode.GenerateChildren(target).forEach(node -> {
                if (!generatedStates.contains(node.getMatrix())) {
                    generatedStates.add(node.getMatrix());
                    searchQueue.add(node);
                }
            });
        }
        System.out.println("Search finished, without results");
    }

    //BFSChildrenFirst versión funcional, desventaja mas lento que BFSChildrenFirst no funcional
    public static void BFSChildrenFirstStreams(NPuzzleNode initialNode, int[][] target) {
        long initialTime = System.currentTimeMillis();
        Deque<int[][]> previousStates = new ArrayDeque<>();
        Deque<NPuzzleNode> searchQueue = new ArrayDeque<>();
        searchQueue.add(initialNode);
        while (!searchQueue.isEmpty()) { //While the queue has nodes
            var result = searchQueue
                    .poll()
                    .GenerateChildren()
                    .stream()
                    .filter(node -> {
                        boolean alreadyAdded = !previousStates.contains(node.getMatrix());
                        if (alreadyAdded) {
                            previousStates.add(node.getMatrix());
                            searchQueue.add(node);
                        }
                        return alreadyAdded;
                    })
                    .filter(node -> Arrays.deepEquals(node.getMatrix(), target))
                    .findFirst();

            if (result.isPresent()) {
                long elapsedTime = System.currentTimeMillis() - initialTime;
                printSolutionPath(result.get(), elapsedTime, previousStates.size());
                return;
            }
        }
        System.out.println("Search finished, without results");
    }

    //El BFS de fuerza bruta que resultó ser más veloz
    public static void BFSChildrenFirst(NPuzzleNode initialNode, int[][] target) {
        long initialTime = System.currentTimeMillis();
        Deque<int[][]> generatedStates = new ArrayDeque<>();
        Deque<NPuzzleNode> searchQueue = new ArrayDeque<>();
        searchQueue.add(initialNode);
        generatedStates.add(initialNode.getMatrix());
        while (!searchQueue.isEmpty()) {
            NPuzzleNode actualNode = searchQueue.poll();
            for (NPuzzleNode node : actualNode.GenerateChildren()) {
                if (!generatedStates.contains(node.getMatrix())) {
                    generatedStates.add(node.getMatrix());
                    searchQueue.add(node);
                    if (Arrays.deepEquals(node.getMatrix(), target)) {
                        long elapsedTime = System.currentTimeMillis() - initialTime;
                        printSolutionPath(node, elapsedTime, generatedStates.size());
                        return;
                    }
                }
            }
        }
        System.out.println("Search finished, without results");
    }

    //Es más lento que BFSChildrenFirst
    public static void BFS(NPuzzleNode initialNode, int[][] target) {
        long initialTime = System.currentTimeMillis();
        Deque<int[][]> generatedStates = new ArrayDeque<>();
        Deque<NPuzzleNode> searchQueue = new ArrayDeque<>();
        searchQueue.add(initialNode);
        generatedStates.add(initialNode.getMatrix());
        while (!searchQueue.isEmpty()) {
            NPuzzleNode actualNode = searchQueue.poll();
            if (Arrays.deepEquals(actualNode.getMatrix(), target)) {
                long elapsedTime = System.currentTimeMillis() - initialTime;
                printSolutionPath(actualNode, elapsedTime, generatedStates.size());
                return;
            }
            for (NPuzzleNode node : actualNode.GenerateChildren()) {
                if (!generatedStates.contains(node.getMatrix())) {
                    generatedStates.add(node.getMatrix());
                    searchQueue.add(node);
                }
            }
        }
        System.out.println("Search finished, without results");
    }

    private static void printSolutionPath(NPuzzleNode lastNode, long elapsedTime, int size) {
        NPuzzleNode actualNode = lastNode;
        StringBuilder stringBuilder = new StringBuilder();
        boolean condition;
        int stageCounter = actualNode.getParentHierarchyNumber();
        do {
            stringBuilder.insert(0, "Paso " + stageCounter + "\n" + actualNode.toString() + "\n");
            condition = actualNode.getParent() != null;
            actualNode = actualNode.getParent();
            stageCounter--;
        } while (condition);
        System.out.println("Solution Path");
        System.out.print(stringBuilder);
        double seconds = (double)elapsedTime/1000d;
        System.out.println("ElapseTime : " + seconds + "s");
        System.out.println("Generated tables : " + size);
    }
}
