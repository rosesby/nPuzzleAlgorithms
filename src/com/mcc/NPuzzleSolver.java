package com.mcc;

import org.apache.commons.lang3.SerializationUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class NPuzzleSolver {

    private NPuzzleSolver() {
    }

    public static void AStarStreams(NPuzzleNodeManhattan initialNode, int[][] target) {
        long initialTime = System.currentTimeMillis();
        ArrayDeque<int[][]> previousStates = new ArrayDeque<>();
        PriorityQueue<NPuzzleNodeManhattan> searchQueue = new PriorityQueue<>();
        searchQueue.add(initialNode);
        while (!searchQueue.isEmpty()) { //While the queue has nodes
            if (Arrays.deepEquals(searchQueue.peek().getMatrix(), target)) {
                long elapsedTime = System.currentTimeMillis() - initialTime;
                printSolutionPath(searchQueue.peek(), elapsedTime, previousStates.size(), "Busqueda A estella streams");
                return;
            }
            searchQueue.poll()
                    .GenerateChildren(target)
                    .stream()
                    .forEach(node -> {
                        if (!previousStates.contains(node.getMatrix())) {
                            previousStates.add(node.getMatrix());
                            searchQueue.add(node);
                        }
                    });
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
                printSolutionPath(actualNode, elapsedTime, generatedStates.size(), "Busqueda A estrella");
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
    public static void BFSStreams(NPuzzleNode initialNode, int[][] target) {
        long initialTime = System.currentTimeMillis();
        Deque<int[][]> previousStates = new ArrayDeque<>();
        Deque<NPuzzleNode> searchQueue = new ArrayDeque<>();
        searchQueue.add(initialNode);
        while (!searchQueue.isEmpty()) { //While the queue has nodes
            if (Arrays.deepEquals(searchQueue.peek().getMatrix(), target)) {
                long elapsedTime = System.currentTimeMillis() - initialTime;
                printSolutionPath(searchQueue.peek(), elapsedTime, previousStates.size(), "Busqueda BFS Streams");
                return;
            }
            searchQueue.poll()
                    .GenerateChildren()
                    .stream()
                    .forEach(node -> {
                        if (!previousStates.contains(node.getMatrix())) {
                            previousStates.add(node.getMatrix());
                            searchQueue.add(node);
                        }
                    });
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
                printSolutionPath(actualNode, elapsedTime, generatedStates.size(), "Busqueda BFS");
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

    private static void printSolutionPath(NPuzzleNode lastNode, long elapsedTime, int size, String titulo) {
        System.out.println(titulo);
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
        double seconds = (double) elapsedTime / 1000d;
        System.out.println("ElapseTime : " + seconds + "s");
        System.out.println("Generated tables : " + size);
    }
}
