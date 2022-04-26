package graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// O(V+E)
class Bfs
{	 
	 // Perform BFS on the graph starting from vertex `v`
	 public static void BFSIterative(Graph graph, int vertex, boolean[] discovered)
	 {
		 Queue<Integer> que = new LinkedList<Integer>();

		 que.offer(vertex);
		 discovered[vertex] = true;
		 while(!que.isEmpty()) {

			 int currVer = que.poll();
			 System.out.println(currVer);
			 
			 for(int adj: graph.adjList.get(currVer)) {
				 if (!discovered[adj]) {
					 que.offer(adj);
					 discovered[adj] = true;
				 }
			 }
		 }
	 }
	 
	 // Perform BFS on the graph starting from vertex `v`
	 public static void BFSRecursive(Graph graph, int vertex, boolean[] discovered)
	 {
		 
	 }

 public static void main(String[] args)
 {

     List<Edge> edges = Arrays.asList(
             new Edge(1, 2), new Edge(1, 3), new Edge(1, 4),
             new Edge(2, 5), new Edge(2, 6), new Edge(5, 9),
             new Edge(5, 10), new Edge(4, 7), new Edge(4, 8),
             new Edge(7, 11), new Edge(7, 12)
             // vertex 0, 13, and 14 are single nodes
     );

     // total number of nodes or vertex in the graph
     final int N = 15;

     // build a graph from the given edges
     Graph graph = new Graph(edges, N);

     // to keep track of whether a vertex is discovered or not
     boolean[] discovered = new boolean[N];

     // Perform BFS traversal from all undiscovered nodes to
     // cover all unconnected components of a graph
     for (int i = 0; i < N; i++) {
         if (discovered[i] == false) {
             // start BFS traversal from vertex `i`
             BFSIterative(graph, i, discovered);
         }
     }
 }
}