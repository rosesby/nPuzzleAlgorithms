package com.mcc;

import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        FileReader fileReaderCatalog;
        BufferedReader bufferedReader;

        fileReaderCatalog = new FileReader("src/data.txt");
        bufferedReader = new BufferedReader(fileReaderCatalog);

        String numberOfGames;
        String gameSeparator;
        String valueOfN;
        String targets;
        String tables;

        numberOfGames = bufferedReader.readLine();
        gameSeparator = bufferedReader.readLine();
        valueOfN = bufferedReader.readLine();
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
        NPuzzleNode targetPuzzle = new NPuzzleNode(3,3, intArrayTarget);
        targetPuzzle.print("Target");

        NPuzzleSolver.setWorkingSolution(targetPuzzle);

        int[] intArray = Arrays.stream(tables.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        NPuzzleNode initialPuzzle = new NPuzzleNode(3,3, intArray);
        initialPuzzle.print("Initial");
    }
}
