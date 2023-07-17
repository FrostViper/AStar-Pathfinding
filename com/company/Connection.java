package com.company;

public class Connection {
    int conNum;
    int fromNode;
    int toNode;
    float cost;

    public Connection(int conNum, int fromNode, int toNode, float cost) {
        this.conNum = conNum;
        //Subtracts one to allow it to matchup with index's
        this.fromNode = fromNode-1;
        this.toNode = toNode-1;
        this.cost = cost;
    }
}
