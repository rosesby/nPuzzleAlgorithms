package com.mcc;

import org.apache.commons.lang3.SerializationUtils;

import javax.swing.*;
import java.util.ArrayList;

public class NPuzzleNode {
    private int[][] matrix;
    private ArrayList<NPuzzleNode> childs = new ArrayList<>();
    private NPuzzleNode parent;
    private int distanceCost;

    private int pathCost;

    private static int lastColumnIndex, lastRowIndex;

    public NPuzzleNode(int nx, int ny, int[] initialState) throws Exception {
        if (nx*ny != initialState.length)
            throw new Exception("Data array does not match puzzle dimensions");
        matrix = new int[nx][ny];
        lastColumnIndex = nx - 1;
        lastRowIndex = ny - 1;

        int pos = 0;
        for (int i = 0; i < ny ; i++) {
            for (int j = 0; j < nx ; j++) {
                matrix[i][j] = initialState[pos];
                pos++;
            }
        }
    }

    public NPuzzleNode(int[][] initialState, NPuzzleNode parent){
        matrix = initialState;
        this.parent = parent;
    }

    public ArrayList<NPuzzleNode> GenerateChilds() {
        int[] zeroIndex = findZero();
        int zx = zeroIndex[0];
        int zy = zeroIndex[1];

        if (zy > 0){
            NPuzzleNode up = new NPuzzleNode(moveZeroUp(SerializationUtils.clone(matrix), zeroIndex), this);
            childs.add(up);
        }
        if (zy < lastRowIndex){
            NPuzzleNode down = new NPuzzleNode(moveZeroDown(SerializationUtils.clone(matrix), zeroIndex), this);
            childs.add(down);
        }
        if (zx < lastColumnIndex){
            NPuzzleNode right = new NPuzzleNode(moveZeroRight(SerializationUtils.clone(matrix), zeroIndex), this);
            childs.add(right);
        }
        if (zx > 0) {
            NPuzzleNode left = new NPuzzleNode(moveZeroLeft(SerializationUtils.clone(matrix), zeroIndex), this);
            childs.add(left);
        }
        return childs;
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

    public int[] findZero() {
        for (int y = 0; y < matrix[0].length; y++) {
            for (int x = 0; x < matrix.length; x++) {
                if (matrix[y][x] == 0) return new int[]{y, x};
            }
        }
        return null;
    }

    public int getParentHierarchyNumber(){
        NPuzzleNode actualNode = this;
        int stageCounter = 0;
        do{
            actualNode = actualNode.parent;
            stageCounter++;
        }while (actualNode.parent != null);
        return stageCounter;
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

    public void setDistanceCost(int distanceCost) {
        this.distanceCost = distanceCost;
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getDistanceCost() {
        return distanceCost;
    }

    public int getPathCost() {
        return pathCost;
    }

    public NPuzzleNode getParent() {
        return parent;
    }
}
