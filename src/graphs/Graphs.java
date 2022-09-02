package graphs;

import java.util.*;

//A class to store a graph edge
class Edge {
    int source, dest, weight;

    public Edge(int source, int dest) {
        this.source = source;
        this.dest = dest;
    }

    public Edge(int source, int dest, int weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }
}

//A class to represent a graph object
class Graph {
    int V;
    List<Edge> Edges;

    List<List<Integer>> undirectedAdjList = null;
    List<List<Integer>> directedAdjList = null;
    List<List<Edge>> weightedAdj = null;

    Graph(List<Edge> edges, int N) {
        this.V = N;
        this.Edges = edges;

        directedAdjList = new ArrayList<>();
        undirectedAdjList = new ArrayList<>();
        weightedAdj = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            directedAdjList.add(new ArrayList<Integer>());
            undirectedAdjList.add(new ArrayList<Integer>());
            weightedAdj.add(new ArrayList<Edge>());
        }

        // adjList[0..n-1]List<>
        // add edges to the undirected graph : u <---> v
        for (Edge edge : edges) {
            directedAdjList.get(edge.source).add(edge.dest);

            undirectedAdjList.get(edge.source).add(edge.dest);
            undirectedAdjList.get(edge.dest).add(edge.source);

            weightedAdj.get(edge.source).add(edge);
        }
    }
}
