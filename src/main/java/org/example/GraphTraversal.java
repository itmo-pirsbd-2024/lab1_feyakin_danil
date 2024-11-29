package org.example;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;

import java.util.*;

public class GraphTraversal {

    public static void bfs(Graph graph, int startVertex) {
        boolean[] visited = new boolean[graph.getNumVertices()];
        Queue<Integer> queue = new LinkedList<>();

        visited[startVertex] = true;
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            for (int neighbor : graph.getNeighbors(vertex)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    public static void bfsOptimized(Graph graph, int startVertex) {
        IntOpenHashSet visited = new IntOpenHashSet();
        IntArrayList queue = new IntArrayList();

        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int vertex = queue.removeInt(0);

            for (int neighbor : graph.getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public static void dfsRecursive(Graph graph, int startVertex) {
        boolean[] visited = new boolean[graph.getNumVertices()];
        dfsHelper(graph, startVertex, visited);
    }

    private static void dfsHelper(Graph graph, int vertex, boolean[] visited) {
        visited[vertex] = true;
        for (int neighbor : graph.getNeighbors(vertex)) {
            if (!visited[neighbor]) {
                dfsHelper(graph, neighbor, visited);
            }
        }
    }

    public static void dfsRecursiveOptimized(Graph graph, int startVertex) {
        boolean[] visited = new boolean[graph.getNumVertices()];
        dfsHelperOptimized(graph, startVertex, visited);
    }

    private static void dfsHelperOptimized(Graph graph, int vertex, boolean[] visited) {
        visited[vertex] = true;

        IntList neighbors = graph.getNeighbors(vertex);
        for (int neighbor : neighbors) {
            if (!visited[neighbor]) {
                dfsHelper(graph, neighbor, visited);
            }
        }
    }


    public static void dfsIterative(Graph graph, int startVertex) {
        boolean[] visited = new boolean[graph.getNumVertices()];
        Stack<Integer> stack = new Stack<>();

        stack.push(startVertex);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();

            if (!visited[vertex]) {
                visited[vertex] = true;

                List<Integer> neighbors = graph.getNeighbors(vertex);
                Collections.reverse(neighbors);
                for (int neighbor : neighbors) {
                    if (!visited[neighbor]) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    public static void dfsIterativeOptimized(Graph graph, int startVertex) {
        boolean[] visited = new boolean[graph.getNumVertices()];
        IntArrayList stack = new IntArrayList();

        stack.push(startVertex);

        while (!stack.isEmpty()) {
            int vertex = stack.popInt();

            if (!visited[vertex]) {
                visited[vertex] = true;

                IntList neighbors = graph.getNeighbors(vertex);
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    int neighbor = neighbors.getInt(i);
                    if (!visited[neighbor]) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }
}


