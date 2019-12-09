package com.mcc;

import org.apache.commons.lang3.SerializationUtils;

import javax.crypto.SealedObject;
import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        FileReader fileReaderCatalog;
        BufferedReader bufferedReader;

        fileReaderCatalog = new FileReader("src/data/dataBarron.txt");
        bufferedReader = new BufferedReader(fileReaderCatalog);

        int numberOfGames = Integer.parseInt(bufferedReader.readLine());
        String gameSeparator = bufferedReader.readLine();
        for (int i = 1; i <= numberOfGames; i++) {
            System.out.println("\n--------------\nBusqueda archivo " + i);
            int valueOfN = Integer.parseInt(bufferedReader.readLine());
            String targets = bufferedReader.readLine();
            String tables = bufferedReader.readLine();

            int[] intArrayTarget = Arrays.stream(targets.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            new NPuzzleNode(intArrayTarget, null).print("Target");;

            int[] intArray = Arrays.stream(tables.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            new NPuzzleNode(intArray, null).print("Initial");

            var nodeManthatan = new NPuzzleNodeManhattan(intArray, null, 0, intArrayTarget);
            startSearch(nodeManthatan, NPuzzleBoard.Array1DTo2D(intArrayTarget));
        }
    }

    private static void startSearch(NPuzzleNodeManhattan nPuzzleNodeManhattan, int[][] target){
        NPuzzleSolver.AStarStreams(nPuzzleNodeManhattan, target);
    }
}
