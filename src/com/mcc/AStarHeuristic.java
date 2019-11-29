package com.mcc;

public interface AStarHeuristic<T> extends Comparable<T> {
    int getHeuristicCost();


}
