package com.mcc;

import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        FileReader fileReaderCatalog;
        BufferedReader bufferedReader;

        fileReaderCatalog = new FileReader("src/data4.txt");
        bufferedReader = new BufferedReader(fileReaderCatalog);

        String numberOfGames;
        String gameSeparator;
        int valueOfN;
        String targets;
        String tables;

        numberOfGames = bufferedReader.readLine();
        gameSeparator = bufferedReader.readLine();
        valueOfN = Integer.parseInt(bufferedReader.readLine());
        targets = bufferedReader.readLine();
        tables = bufferedReader.readLine();

        int[] ints = {1, 2, 3, 4, 5, 0, 7, 8, 9};

/*        String[] strArray = tables.split(" ");
        int[] intArray = new int[strArray.length];
        for(int i = 0; i < strArray.length; i++) {
            intArray[i] = Integer.parseInt(strArray[i]);
        }*/

        int[] intArrayTarget = Arrays.stream(targets.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        NPuzzleNode targetPuzzle = new NPuzzleNode(intArrayTarget, null);
        targetPuzzle.print("Target");

        int[] intArray = Arrays.stream(tables.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        NPuzzleNode initialPuzzle = new NPuzzleNode(intArray, null);
        initialPuzzle.print("Initial");

        var mi = new NPuzzleNodeManhattan(initialPuzzle, 0, targetPuzzle.getMatrix());
        NPuzzleSolver.AStar(mi, targetPuzzle.getMatrix());
        mi = new NPuzzleNodeManhattan(initialPuzzle, 0, targetPuzzle.getMatrix());
        NPuzzleSolver.AStarChildrenFirst(mi, targetPuzzle.getMatrix());
        mi = new NPuzzleNodeManhattan(initialPuzzle, 0, targetPuzzle.getMatrix());
        NPuzzleSolver.AStarChildrenFirstStreams(mi, targetPuzzle.getMatrix());

/*        NPuzzleSolver.BFS(initialPuzzle, targetPuzzle.getMatrix());*/

/*        initialPuzzle = new NPuzzleNode(intArray, null);
        NPuzzleSolver.BFSChildrenFirstStreams(initialPuzzle, targetPuzzle.getMatrix());

        initialPuzzle = new NPuzzleNode(intArray, null);
        NPuzzleSolver.BFSChildrenFirst(initialPuzzle, targetPuzzle.getMatrix());

        initialPuzzle = new NPuzzleNode(intArray, null);
        NPuzzleSolver.BFSChildrenFirst(initialPuzzle, targetPuzzle.getMatrix());

        initialPuzzle = new NPuzzleNode(intArray, null);
        NPuzzleSolver.BFSChildrenFirstStreams(initialPuzzle, targetPuzzle.getMatrix());*/



/*        int[][] target = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}};

        int[][] m1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 8}};
        int[][] m2 = {
                {1, 2, 3},
                {4, 5, 7},
                {6, 0, 8}};
        int[][] m3 = {
                {2, 1, 3},
                {4, 5, 7},
                {6, 0, 8}};
        int[][] m4 = {
                {3, 5, 1},
                {4, 2, 7},
                {6, 0, 8}};


        var b1 = new NPuzzleNodeManhattan(m1, null, 1, target);
        var b2 = new NPuzzleNodeManhattan(m2, null, 1, target);
        var b3 = new NPuzzleNodeManhattan(m3, null, 1, target);
        var b4 = new NPuzzleNodeManhattan(m4, null, 1, target);

        PriorityQueue<NPuzzleNodeManhattan> pq = new PriorityQueue<>();
        pq.add(b3);
        pq.add(b2);
        pq.add(b1);
        pq.add(b4);*/

/*        System.out.println("Iterator Test");
        for (Object o : pq) {
            System.out.println(o);
        }*/

/*        System.out.println("Poll Test");
        while (pq.size() > 0) {
            System.out.println(pq.poll());
        }*/
    }
}
