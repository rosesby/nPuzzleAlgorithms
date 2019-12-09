package com.mcc;

import java.io.Serializable;

public interface AStarHeuristic<T> extends Comparable<T> {
    int getHeuristicCost();
}
