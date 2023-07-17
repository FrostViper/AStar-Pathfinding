package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    List<Node> nodeList = new ArrayList<>();
    List<Connection> connectionList = new ArrayList<>();

    public Graph(String filename1, String filename2) throws IOException {
        BufferedReader nodeReader = new BufferedReader(new FileReader(filename1));
        BufferedReader connectionReader = new BufferedReader(new FileReader(filename2));
        String line = nodeReader.readLine();
        while (line != null){
            if(!line.startsWith("#")){
                String[] lineArr = line.split(",");
                nodeList.add(new Node(
                        Integer.parseInt(lineArr[1].trim()),
                        Integer.parseInt(lineArr[2].trim()),
                        Float.parseFloat(lineArr[3].trim()),
                        Float.parseFloat(lineArr[4].trim()),
                        Float.parseFloat(lineArr[5].trim()),
                        Integer.parseInt(lineArr[6].trim()),
                        Float.parseFloat(lineArr[7].trim()),
                        Float.parseFloat(lineArr[8].trim())
                ));
            }

            line = nodeReader.readLine();
        }

        line = connectionReader.readLine();
        while (line != null){
            if(!line.startsWith("#")){
                String[] lineArr = line.split(",");
                connectionList.add(new Connection(
                        Integer.parseInt(lineArr[1].trim()),
                        Integer.parseInt(lineArr[2].trim()),
                        Integer.parseInt(lineArr[3].trim()),
                        Float.parseFloat(lineArr[4].trim())
                ));
            }
            line = connectionReader.readLine();
        }
    }
}
