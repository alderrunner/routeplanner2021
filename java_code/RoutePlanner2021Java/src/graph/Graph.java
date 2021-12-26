package java_code.RoutePlanner2021Java.src.graph;

public class Graph {
    private final int[] vertexOffset;
    private final int[][] adjacencyArray;

    public Graph(int numOfNodes, int numOfEdges) {
        this.vertexOffset = new int[numOfNodes];
        this.adjacencyArray = new int[numOfEdges][3];
    }

    public int[] getVertexOffset() {
        return this.vertexOffset;
    }

    public int[][] getAdjacencyArray() {
        return this.adjacencyArray;
    }
}
