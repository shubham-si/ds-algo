package graphs;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class DfsUsingState {

	enum VertexState {
        White, 			// not visited
        Gray, 			// visited but not completed <sub-nodes>
        Black;			// completed <sub-nodes>
    }

    public static void DFS(Graph graph, int vertexCount)
    {
          VertexState state[] = new VertexState[vertexCount];
          for (int i = 0; i < vertexCount; i++)
                state[i] = VertexState.White;

          for(int i = 0; i < vertexCount; i++) {
        	  if(state[i] == VertexState.White) {
        		  // runDFSRecursive(graph, i, state);
        		  runDFSIterative(graph, i, state);
        	  }
          }
    }

    public static void runDFSRecursive(Graph graph, int u, VertexState[] state)
    {
          state[u] = VertexState.Gray;
          System.out.print(u + " ");
          for (int v : graph.adjList.get(u)) {
              if (state[v] == VertexState.White)
            	  runDFSRecursive(graph, v, state); 
          }
          state[u] = VertexState.Black;
    }
    

    public static void runDFSIterative(Graph graph, int vertex, VertexState[] state)
    {
		 Stack<Integer> stk = new Stack<Integer>();
		 stk.push(vertex);
		 state[vertex] = VertexState.Gray;

		 while(!stk.isEmpty()) {
			 int currVer = stk.pop(); 
			 
			 // case of undirected cyclic graph, so that it'll not process node twice(or print twice)
			 // it can have multiple instances of same node but that will print only once
			 if (state[vertex] == VertexState.Gray) {
				 System.out.print(currVer + " ");
				 state[vertex] = VertexState.Black;
			 }

			 for(int adj: graph.adjList.get(currVer)) {
				 if (state[vertex] == VertexState.White) {
					 stk.push(adj);
					 state[vertex] = VertexState.Gray;
				 }
			 }
		 }
    }
    
	 public static List<Edge> getUndirectGraphEdgesWithCycle() {
	     List<Edge> edges = Arrays.asList(
	             new Edge(1, 2), new Edge(1, 3), new Edge(1, 4),
	             new Edge(2, 5), new Edge(2, 6), new Edge(5, 9),
	             new Edge(5, 10), new Edge(4, 7), new Edge(4, 8),
	             new Edge(7, 11), new Edge(7, 12)
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
        final int N = 15;

        // build a graph from the given edges
        Graph graph = new Graph(edges, N);
        DFS(graph, N);
    }
}
