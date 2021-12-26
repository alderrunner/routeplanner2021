package java_code.RoutePlanner2021Java.src.graphReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import java_code.RoutePlanner2021Java.src.graph.Graph;

public class GraphReader {
    public static Graph readGraph(String file) {
        // For reader
        LineNumberReader reader;
        String line;
        // For nodes and edges
        int numOfNodes = 0;
        int numOfEdges = 0;
        boolean gotNodes = false;
        boolean gotEdges = false;
        // For graph
        Graph g = new Graph(0, 0);
        int[] vertexOffset = new int[0];
        int[][] adjacencyArray = new int[0][0];
        double[][] latLong = new double[0][0];
        // For vertex offset array and adjacency array
        int currNodeID = 0;
        int neighborCounter = 0;
        int edgeCounter = 0;

        try {
            reader = new LineNumberReader(new FileReader(file));

            while ((line = reader.readLine()) != null) {
                // Skip comments
                if (line.startsWith("#") || line.isEmpty()) {
                    continue;
                }

                // Get number of nodes
                if (!gotNodes) {
                    numOfNodes = Integer.parseInt(line);
                    gotNodes = true;
                    continue;
                }

                // Get number of edges, update line numbers and create graph
                if (!gotEdges) {
                    numOfEdges = Integer.parseInt(line);
                    gotEdges = true;

                    reader.setLineNumber(-1);

                    g = new Graph(numOfNodes, numOfEdges);
                    vertexOffset = g.getVertexOffset();
                    adjacencyArray = g.getAdjacencyArray();
                    latLong = g.getLatLong();

                    continue;
                }

                // Parse nodes and edges
                if (reader.getLineNumber() < numOfNodes) {
                    String[] lineArr = line.split(" ");
                    latLong[reader.getLineNumber()] = new double[]{
                        Double.parseDouble(lineArr[2]),
                        Double.parseDouble(lineArr[3])
                    };
                } else {
                    String[] lineArr = line.split(" ");

                    if (currNodeID != Integer.parseInt(lineArr[0])) {
                        vertexOffset[currNodeID+1] = neighborCounter;

                        if (Integer.parseInt(lineArr[0]) - currNodeID > 1) {
                            for (int i = currNodeID+1; i < Integer.parseInt(lineArr[0]); i++) {
                                vertexOffset[i+1] = neighborCounter;
                            }
                        }

                        currNodeID = Integer.parseInt(lineArr[0]);
                    }
                    neighborCounter++;

                    adjacencyArray[edgeCounter] = new int[]{
                        Integer.parseInt(lineArr[0]),
                        Integer.parseInt(lineArr[1]),
                        Integer.parseInt(lineArr[2])
                    };
                    edgeCounter++;
                }
            }

            reader.close();
            return g;

        } catch (FileNotFoundException e) {
            System.out.println("Error: Specified file was not found.");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Error: Reader had problems reading or closing the file.");
            System.exit(1);
        }

        return new Graph(0, 0);
    }
}
