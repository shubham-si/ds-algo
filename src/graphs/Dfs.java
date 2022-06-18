package graphs;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

//O(V+2E) : 2E :> undirected graph u <---> v
class Dfs
{

	 // Perform DFS on the graph starting from vertex `v`
	 public static void DFSIterative(Graph graph, int vertex, boolean[] discovered)
	 {
		 Stack<Integer> stk = new Stack<Integer>();
		 stk.push(vertex);
		 while(!stk.isEmpty()) {
			 
			 int currVer = stk.pop(); 
			 // case of undirected cyclic graph, so that it'll not process node twice(or print twice)
			 // it can have multiple instances of same node in the stack but that will print only once
			 // otherwise o/p: 0 1 3 2 7 4 6 5 5 4 2 8 9 10 11 12 13 14 <duplicate instances>
			 if (!discovered[currVer]) {
				 System.out.print(currVer + " ");
				 discovered[currVer] = true;
			 }
 
			 for(int adj: graph.undirectedAdjList.get(currVer)) {
				 if (!discovered[adj]) {
					 stk.push(adj);
				 }
			 }
		 }
	 }
	 
	 // Perform DFS on the graph starting from vertex v
	 public static void DFSRecursive(Graph graph, int vertex, boolean[] discovered)
	 {
		 discovered[vertex] = true;
		 System.out.print(vertex + " ");
		 for(int adj: graph.undirectedAdjList.get(vertex)) {
			 if (!discovered[adj]) {
				 DFSRecursive(graph, adj, discovered);
			 }
		 }
	 }	
	 
	 public static List<Edge> getUndirectGraphEdgesWithCycle() {
	     List<Edge> edges = Arrays.asList(
	             new Edge(1, 2), new Edge(1, 3), new Edge(2, 3),
	             new Edge(2, 4), new Edge(2, 5), new Edge(2, 7),
	             new Edge(5, 6), new Edge(6, 7), new Edge(4, 7)
	             // vertex 0, 13, and 14 are single nodes
	     );
	     return edges;
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
     
     edges = getUndirectGraphEdgesWithCycle();

     // total number of nodes in the graph
     final int N = 8;			// 15

     // build a graph from the given edges
     Graph graph = new Graph(edges, N);

     // to keep track of whether a vertex is discovered or not
     boolean[] discovered = new boolean[N];

     // Perform BFS traversal from all undiscovered nodes to
     // cover all unconnected components of a graph
     for (int i = 0; i < N; i++) {
         if (discovered[i] == false) {
             // start DFS traversal from vertex `i`
             DFSIterative(graph, i, discovered);
        	 // DFSRecursive(graph, i, discovered);
         }
     }
 }
}
