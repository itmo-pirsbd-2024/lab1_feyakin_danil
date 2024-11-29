package org.example;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;

import java.util.LinkedList;
import java.util.List;

public class Graph {
    /*private int numVertices;
    private List<Integer>[] adjList;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjList = new LinkedList[numVertices];

        for (int i = 0; i < numVertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int src, int dest) {
        adjList[src].add(dest);
        adjList[dest].add(src);
    }

    public List<Integer> getNeighbors(int vertex) {
        return adjList[vertex];
    }

    public int getNumVertices() {
        return numVertices;
    }
     */
    private int numVertices;
    private IntList[] adjList;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjList = new IntList[numVertices];

        for (int i = 0; i < numVertices; i++) {
            adjList[i] = new IntArrayList();
        }
    }

    public void addEdge(int src, int dest) {
        adjList[src].add(dest);
        adjList[dest].add(src);
    }

    public IntList getNeighbors(int vertex) {
        return adjList[vertex];
    }

    public int getNumVertices() {
        return numVertices;
    }
}

