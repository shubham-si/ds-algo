package graphs;

public class BellmanFord {

    // relax (Edges) : V - 1 times & vth (1 more time loop) to check negative cycle
    boolean isNegativeCyclePresent(Graph graph, int src) {
        int nV = graph.V;

        int []dist = new int[nV];

        for (int v = 0; v < nV; v++) {
            dist[v] = Integer.MAX_VALUE;
        }
        dist[src] = 0;

        // V - 1 times
        for (int v = 1; v < nV; v++) {
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
