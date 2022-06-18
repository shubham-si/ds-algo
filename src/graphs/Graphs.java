package graphs;

import java.util.*;

//A class to store a graph edge
class Edge
{
 int source, dest,weight;

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
class Graph
{
 // A 2d-list of lists to represent an adjacency list
      List<List<Integer>> undirectedAdjList = null;
      List<List<Integer>> directedAdjList = null;
      List<List<Edge>> weightedAdj = null;
 // Constructor (n vertex, e edges)
     Graph(List<Edge> edges, int N)
     {
         directedAdjList = new ArrayList<>();
         undirectedAdjList = new ArrayList<>();
         weightedAdj = new ArrayList<>();

         for (int i = 0; i < N; i++) {
             directedAdjList.add(new ArrayList<>());
             undirectedAdjList.add(new ArrayList<>());
             weightedAdj.add(new ArrayList<>());
         }
         for (int i = 0; i < N; i++) {
             undirectedAdjList.add(new ArrayList<>());
         }

         // adjList[0..n-1]List<>
         // add edges to the undirected graph : u <---> v
         for (Edge edge: edges)
         {
             directedAdjList.get(edge.source).add(edge.dest);

             undirectedAdjList.get(edge.source).add(edge.dest);
             undirectedAdjList.get(edge.dest).add(edge.source);

             weightedAdj.get(edge.source).add(edge);
         }
     }
}
