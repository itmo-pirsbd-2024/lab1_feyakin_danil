package org.example;
import java.util.Random;

public class GraphGenerator {
    public static Graph generateRandomGraph(int numVertices, int numEdges) {
        Graph graph = new Graph(numVertices);
        Random rand = new Random();

        for (int i = 0; i < numEdges; i++) {
            int src = rand.nextInt(numVertices);
            int dest = rand.nextInt(numVertices);
            graph.addEdge(src, dest);
        }

        return graph;
    }
}
