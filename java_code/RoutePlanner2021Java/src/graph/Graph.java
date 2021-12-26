package java_code.RoutePlanner2021Java.src.graph;

public class Graph {
    private final int[] vertexOffset;
    private final int[][] adjacencyArray;
    private final double[][] latLong;

    public Graph(int numOfNodes, int numOfEdges) {
        this.vertexOffset = new int[numOfNodes];
        this.adjacencyArray = new int[numOfEdges][3];
        this.latLong = new double[numOfNodes][2];
    }

    public int[] getVertexOffset() {
        return this.vertexOffset;
    }

    public int[][] getAdjacencyArray() {
        return this.adjacencyArray;
    }

    public double[][] getLatLong() {
        return this.latLong;
    }
}
