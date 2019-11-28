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

        int[] ints = {1,2,3,4,5,0,7,8,9};

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

        NPuzzleSolver.setWorkingSolution(targetPuzzle);

        int[] intArray = Arrays.stream(tables.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        NPuzzleNode initialPuzzle = new NPuzzleNode(intArray, null);
        initialPuzzle.print("Initial");

        NPuzzleSolver.startSearch(initialPuzzle, targetPuzzle);

        int[][] target = {{1,2,3},{4,5,6},{7,8,0}};
        int[][] matrix = {{1,2,3},{4,5,6},{7,0,8}};

        //System.out.println(calculateCostOfActualPuzzle(matrix,target));
    }

    private static int calculateCostOfActualPuzzle(int[][] matrix, int[][] target) {
        int distanceCosts = 0;

        for (int y1 = 0; y1 < matrix[0].length; y1++) {
            for (int x1 = 0; x1 < matrix.length; x1++) {
                int actualValue = matrix[y1][x1];
                int[] valuePositionInTarget = getPositionOfElementIn2DMatrix(actualValue, target);
                int y0 = valuePositionInTarget[0];
                int x0 = valuePositionInTarget[1];
                //System.out.println("|y1 - y0| + |x1 - x0| : |" + y1 + " - " + y0 + "| + |" + x1 + " - " + x0 + "|");
                int distanceCost = Math.abs(y1 - y0) + Math.abs(x1 - x0);
                distanceCosts += distanceCost;
                System.out.println("val : " + actualValue + " |" + y1 + " - " + y0 + "| + |" + x1 + " - " + x0 + "| dist: " + distanceCost);
            }
        }
        return distanceCosts;
    }

    private static int[] getPositionOfElementIn2DMatrix(int value, int[][] matrix) {
        for (int y = 0; y < matrix[0].length; y++) {
            for (int x = 0; x < matrix.length; x++) {
                if (matrix[y][x] == value) return new int[]{y, x};
            }
        }
        return new int[]{-1, -1};
    }
}
