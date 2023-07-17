package com.company;

public class Node {
    int nodeNum;
    int status;
    float cost;
    float estHeuristic;
    float estTotal;
    int prevNode;
    float locX;
    float locY;

    public Node(int nodeNum, int status, float cost, float estHeuristic, float estTotal, int prevNode, float locX, float locY) {
        this.nodeNum = nodeNum;
        this.status = status;
        this.cost = cost;
        this.estHeuristic = estHeuristic;
        this.estTotal = estTotal;
        this.prevNode = prevNode;
        this.locX = locX;
        this.locY = locY;
    }
}
