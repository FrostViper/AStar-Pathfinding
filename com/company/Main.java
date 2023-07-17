package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static int findLowest(Graph graph, List<Integer> openNodes){
        float cost = Float.POSITIVE_INFINITY;
        int lowestNode = -1;
        for (Integer openNode : openNodes) {
            Node temp = graph.nodeList.get(openNode);
            if(cost > temp.cost){
                cost = temp.cost;
                lowestNode = openNode;
            }
        }
        return lowestNode;
    }

    public static List<Connection> getConnections(Graph graph, int currentNode){
        List<Connection>output = new ArrayList<>();
        for (Connection connection : graph.connectionList) {
            if(connection.fromNode == currentNode){
                output.add(connection);
            }
        }
        return output;
    }

    public static float heuristic(Graph graph, int nodeOne, int nodeTwo){
        return (float)(Math.sqrt(Math.pow(graph.nodeList.get(nodeTwo).locX - graph.nodeList.get(nodeOne).locX, 2) +
                Math.pow(graph.nodeList.get(nodeTwo).locY - graph.nodeList.get(nodeOne).locY, 2)));
    }

    public static Graph findPath(Graph graph, int first, int last){
        for (Node node : graph.nodeList) {
            node.status = 1;
            node.cost = Float.POSITIVE_INFINITY;
            node.prevNode = -1;
        }

        Node nodeOne = graph.nodeList.get(first);
        nodeOne.status = 2;
        nodeOne.cost = 0;
        List<Integer> openNodes = new ArrayList<>();
        openNodes.add(first);

        while(!openNodes.isEmpty()){

            int currentNode = findLowest(graph,openNodes);
            if(currentNode == last){ break; }

            List<Connection> currentConnections = getConnections(graph,currentNode);

            for (Connection connection : currentConnections) {

                int toNode = connection.toNode;
                float toCost = graph.nodeList.get(currentNode).cost + connection.cost;

                if(toCost < graph.nodeList.get(toNode).cost){
                    graph.nodeList.get(toNode).status = 2;
                    graph.nodeList.get(toNode).cost = toCost;
                    graph.nodeList.get(toNode).estHeuristic = heuristic(graph, toNode,last);
                    graph.nodeList.get(toNode).prevNode = currentNode;
                    openNodes.add(toNode);
                }

            }

            graph.nodeList.get(currentNode).status = 3;
            openNodes.remove(Integer.valueOf(currentNode));
        }

        return graph;
    }

    public static void getPath(Graph graph, int first, int last) throws IOException {
        List<Integer> path = new ArrayList<>();
        int current = last;
        BufferedWriter writer = new BufferedWriter(new FileWriter("Output Path File.txt",true));

        while(current != first && current != -1){
            path.add(current);
            current = graph.nodeList.get(current).prevNode;
        }

        if(current == first){
            path.add(first);
            writer.write("Path From " + (first+1) + " to " + (last+1) + " Path= " + path.stream().map(i -> Integer.toString(i+1)).collect(Collectors.joining(" ")) + " cost= " + graph.nodeList.get(last).cost + "\n");
        }
        else{
            writer.write("Path From " + (first+1) + " to " + (last+1) + " not found" + "\n");
        }
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        Graph graph = new Graph("CS 330, Pathfinding, Graph AB Nodes v3.txt", "CS 330, Pathfinding, Graph AB Connections v3.txt");
        graph = findPath(graph,0,28);
        getPath(graph,0,28);
        graph = findPath(graph,0,37);
        getPath(graph,0,37);
        graph = findPath(graph,10,0);
        getPath(graph,10,0);
        graph = findPath(graph,32,65);
        getPath(graph,32,65);
        graph = findPath(graph,57,42);
        getPath(graph,57,42);
    }

}
