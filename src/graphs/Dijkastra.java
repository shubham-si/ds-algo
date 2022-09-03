package graphs;

import trees.PairUtil;

import java.util.Map;
import java.util.PriorityQueue;

// Single source shortest path algo
// O(E logV) using PQ
public class Dijkastra {
    int[] shortestPath(Graph graph, int src) {
        int V = graph.V;

        int []dist = new int[V];
        for (int v = 0; v < V; v++) {
            dist[v] = Integer.MAX_VALUE;
        }
        dist[src] = 0;

        // src: {vertex, reachingCost}
        PriorityQueue<Map.Entry<Integer, Integer>> pq= new PriorityQueue<>((a , b) -> Integer.compare(a.getValue(), b.getValue()));

        // reaching src from src takes 0 cost or "minimum reaching cost"
        pq.offer(PairUtil.Of(src, 0));


        while (!pq.isEmpty()) {
            int minCostVertex = pq.peek().getKey();

            pq.poll();

            // all edges going from vertex (u)
            for (Edge edge: graph.weightedDirectedAdj.get(minCostVertex)) {
                // edge.source === minCostVertex

                if (dist[edge.dest] > (dist[minCostVertex] + edge.weight) ) {
                    dist[edge.dest] = dist[minCostVertex] + edge.weight;

                    pq.offer(PairUtil.Of(edge.dest, dist[edge.dest]));
                }
            }
        }

        return dist;
    }
}
