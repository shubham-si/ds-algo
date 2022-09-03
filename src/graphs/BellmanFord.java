package graphs;

import java.util.Arrays;

public class BellmanFord {

    // relax (Edges) : V - 1 times & vth (1 more time loop) to check negative cycle
    boolean isNegativeCyclePresent(Graph graph, int src) {
        int V = graph.V;

        int []dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[src] = 0;

        // V - 1 times: since for V vertices graph will have (V-1) edges
        for (int v = 0; v < (V - 1); v++) {
            // relax all edges
            for (Edge edge: graph.Edges) {

                if (dist[edge.source] != Integer.MAX_VALUE
                        && (dist[edge.dest] > (dist[edge.source] + edge.weight))) {

                    // update if reach to destination is greater
                    dist[edge.dest] = dist[edge.source] + edge.weight;
                }
            }
        }

        // check for negative cycle i.e., any dist[u] should not decrease
        for (Edge edge: graph.Edges) {
            if (dist[edge.source] != Integer.MAX_VALUE && ((dist[edge.source] + edge.weight) < dist[edge.dest])) {
                // found -ve cycle
                return true;
            }
        }

        return false;
    }
}
