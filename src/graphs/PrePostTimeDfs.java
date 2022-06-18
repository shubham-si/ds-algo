package graphs;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class PrePostTimeDfs {

	static int pre[],post[];
	static int time = 1;
	 
	 // Perform DFS on the graph starting from vertex `v`
	 public static void computerPrePostTimings(Graph graph, int vertex, boolean[] discovered)
	 {
		 discovered[vertex] = true;
		 pre[vertex] = time++;
		 
		 for(int adj: graph.undirectedAdjList.get(vertex)) {
			 if (!discovered[adj]) {
				 computerPrePostTimings(graph, adj, discovered);
			 }
		 }
		 post[vertex] = time++;
	 }	
	 
	 public static List<Edge> getUndirectGraphEdgesWithCycle() {
	     List<Edge> edges = Arrays.asList(
	             new Edge(1, 2), new Edge(1, 3), new Edge(2, 3),
	             new Edge(2, 4), new Edge(2, 5), new Edge(2, 7),
	             new Edge(5, 6), new Edge(6, 7), new Edge(4, 7)
	             // vertex [1...7]
	     );
	     return edges;
	 }
	
	public static void main(String... args) {
		List<Edge> edges = getUndirectGraphEdgesWithCycle();
		int N = 8;
		
		Graph graph = new Graph(edges, N);
		boolean[] discovered = new boolean[N];
		
		pre = new int[N];
		post = new int[N];
		
		computerPrePostTimings(graph, 1, discovered);

		Consumer<Integer> consumerPre = i -> System.out.print("vertex: "+ i + " " + pre[i]);
		Consumer<Integer> consumerPost = i -> System.out.print("/" + post[i]+ "\n");
		IntStream.range(1, 8).mapToObj(i -> Integer.valueOf(i)).forEach(consumerPre.andThen(consumerPost));
	}
}
