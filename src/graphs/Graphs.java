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
 // A 2d-list of lists to represent an adjacency list
 List<List<Integer>> adjList = null;

 // Constructor (n vertex, e edges)
 Graph(List<Edge> edges, int N)
 {
     adjList = new ArrayList<>();

     for (int i = 0; i < N; i++) {
         adjList.add(new ArrayList<>());
     }

     // adjList[0..n-1]List<>
     // add edges to the undirected graph : u <---> v
     for (Edge edge: edges)
     {
         adjList.get(edge.source).add(edge.dest);
         adjList.get(edge.dest).add(edge.source);
     }
 }
 
}
