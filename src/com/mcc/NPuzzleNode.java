package com.mcc;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;

public class NPuzzleNode extends NPuzzleBoard implements Serializable {
    protected NPuzzleNode parent;
    protected int arrayMaxBound;

    public NPuzzleNode(int[] board, NPuzzleNode parent) throws Exception {
        super(board);
        initialize((int) Math.sqrt(board.length) - 1, parent);
    }

    public NPuzzleNode(int[][] blocks, NPuzzleNode parent){
        super(blocks);
        initialize(blocks.length-1, parent);
    }

    private void initialize(int nMinusOne, NPuzzleNode parent){
        this.parent = parent;
        arrayMaxBound = nMinusOne;
    }

    public ArrayList<NPuzzleNode> GenerateChildren() {
        int[] zeroIndex = findZero();
        int zx = zeroIndex[0];
        int zy = zeroIndex[1];

        ArrayList<NPuzzleNode> childs = new ArrayList<>();

        if (zy > 0){
            NPuzzleNode up = new NPuzzleNode(moveZeroUp(SerializationUtils.clone(matrix), zeroIndex), this);
            childs.add(up);
        }
        if (zy < arrayMaxBound){
            NPuzzleNode down = new NPuzzleNode(moveZeroDown(SerializationUtils.clone(matrix), zeroIndex), this);
            childs.add(down);
        }
        if (zx < arrayMaxBound){
            NPuzzleNode right = new NPuzzleNode(moveZeroRight(SerializationUtils.clone(matrix), zeroIndex), this);
            childs.add(right);
        }
        if (zx > 0) {
            NPuzzleNode left = new NPuzzleNode(moveZeroLeft(SerializationUtils.clone(matrix), zeroIndex), this);
            childs.add(left);
        }
        return childs;
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

    public NPuzzleNode getParent() {
        return parent;
    }
}
