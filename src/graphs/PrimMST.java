package graphs;

// O(V2) : works on undirected connected graph
// Prim’s algorithm gives connected component as well as it works only on undirected connected graph.
// Minimum Spanning Tree with V vertices will have (V - 1) edges.

import java.util.*;

public class PrimMST {

    // O(n2)
    List<Edge> getMST(List<Edge> edges, int V) {

        // create undirected weighted adjList
        List<List<Edge>> undirectedWeightedAdjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            undirectedWeightedAdjList.add(new ArrayList<>());
        }
        for (Edge edge : edges) {
            undirectedWeightedAdjList.get(edge.source).add(edge);
            undirectedWeightedAdjList.get(edge.dest).add(edge);
        }

        int src = 0;
        int dist[] = new int[V];
        int parent[] = new int[V];
        boolean []mst = new boolean[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        // dist[u]: minimum_of_all_edges_wt_coming_to_u
        dist[src] = 0;
        mst[src] = true;

        int minCost = 0;

        // Number of edges to be taken is equal to V-1 as to form tree with 'V' vertices
        for (int i = 0; i < (V - 1); i++) {
            int u = pickMinCostVertex(dist, mst);

            minCost += dist[u];
            mst[u] = true;

            for(Edge edge : undirectedWeightedAdjList.get(u)) {
                // taking minimum_of_all_edges_wt_coming_to_u & not processed before
                if (!mst[edge.dest] && dist[edge.dest] > edge.weight) {
                    dist[edge.dest] = edge.weight;
                    parent[edge.dest] = u;
                }
            }
        }

        List<Edge> treeEdges = new ArrayList<>();
        for (int v = 1; v < V; v++) {
            treeEdges.add(new Edge(parent[v], v, dist[v]));
        }

        System.out.println("MST COST: "+ minCost);

        return treeEdges;
    }

    int pickMinCostVertex(int []dist, boolean []mst) {
        int u = 0, cost = Integer.MAX_VALUE;
        for (int i = 0; i < dist.length; i++) {
            if (!mst[i] && dist[i] < cost) {
                u = i;
                cost = dist[i];
            }
        }
        return u;
    }

}
