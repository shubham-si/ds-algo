package graphs;

import java.util.*;

//A class to store a graph edge
class Edge
{
 int source, dest;

 public Edge(int source, int dest) {
     this.source = source;
     this.dest = dest;
 }
}

//A class to represent a graph object
class Graph
{
 // A list of lists to represent an adjacency list
 List<List<Integer>> adjList = null;

 // Constructor (n vertex, e edges)
 Graph(List<Edge> edges, int N)
 {
     adjList = new ArrayList<>();

     for (int i = 0; i < N; i++) {
         adjList.add(new ArrayList<>());
     }

     // add edges to the undirected graph : u <---> v
     for (Edge edge: edges)
     {
         int src = edge.source;
         int dest = edge.dest;

         adjList.get(src).add(dest);
         adjList.get(dest).add(src);
     }
 }
 
}
