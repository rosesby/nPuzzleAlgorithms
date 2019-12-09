package com.mcc;

import org.apache.commons.lang3.SerializationUtils;

import java.util.ArrayList;

public class NPuzzleBoard {
    protected int[][] matrix;

    public NPuzzleBoard (int[] board) throws Exception {
       matrix = Array1DTo2D(board);
    }

    public NPuzzleBoard(int[][] blocks){
        matrix = blocks;
    }

    public static int[][] Array1DTo2D(int[] board) throws Exception {
        int n = (int) Math.sqrt(board.length);
        if (n*n != board.length) throw new Exception("Data array does not match n*n dimensions");
        int[][] matrix = new int[n][n];
        int position = 0;
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n ; j++) {
                matrix[i][j] = board[position];
                position++;
            }
        }
        return matrix;
    }

    public int[][] moveZeroUp(int[][] matrix, int[] zeroIndex) {
        int valueSave = matrix[ zeroIndex[0] ][ zeroIndex[1] - 1 ];
        matrix[ zeroIndex[0] ][ zeroIndex[1] ] = valueSave;
        matrix[ zeroIndex[0] ][ zeroIndex[1] - 1 ] = 0;
        return matrix;
    }

    public int[][] moveZeroDown(int[][] matrix, int[] zeroIndex) {
        int valueSave = matrix[ zeroIndex[0] ][ zeroIndex[1] + 1 ];
        matrix[ zeroIndex[0] ][ zeroIndex[1] ] = valueSave;
        matrix[ zeroIndex[0] ][ zeroIndex[1] + 1 ] = 0;
        return matrix;
    }

    public int[][] moveZeroRight(int[][] matrix, int[] zeroIndex) {
        int valueSave = matrix[ zeroIndex[0] + 1 ][ zeroIndex[1] ];
        matrix[ zeroIndex[0] ][ zeroIndex[1] ] = valueSave;
        matrix[ zeroIndex[0] + 1 ][ zeroIndex[1] ] = 0;
        return matrix;
    }

    public int[][] moveZeroLeft(int[][] matrix, int[] zeroIndex) {
        int valueSave = matrix[ zeroIndex[0] - 1 ][ zeroIndex[1] ];
        matrix[ zeroIndex[0] ][ zeroIndex[1] ] = valueSave;
        matrix[ zeroIndex[0] - 1 ][ zeroIndex[1] ] = 0;
        return matrix;
    }

    protected int[] getPositionOfElementIn2DMatrix(int value, int[][] matrix) {
        for (int y = 0; y < matrix[0].length; y++) {
            for (int x = 0; x < matrix.length; x++) {
                if (matrix[y][x] == value) return new int[]{y, x};
            }
        }
        return new int[]{-1, -1};
    }

    public int[] findZero() {
        for (int y = 0; y < matrix[0].length; y++) {
            for (int x = 0; x < matrix.length; x++) {
                if (matrix[y][x] == 0) return new int[]{y, x};
            }
        }
        return null;
    }

    public void print(String str){
        System.out.println(str + "\n" + this.toString());
    }

    public void print(){
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();

        for (int y = 0; y < matrix[0].length; y++) {
            stringBuilder.append("|");
            for (int x = 0; x < matrix.length; x++) {
                stringBuilder.append(" " + matrix[y][x] + " |");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static String matrixToString(int[][] matrix){
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();

        for (int y = 0; y < matrix[0].length; y++) {
            stringBuilder.append("|");
            for (int x = 0; x < matrix.length; x++) {
                stringBuilder.append(" " + matrix[y][x] + " |");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public int[][] getMatrix() {
        return matrix;
    }
}
