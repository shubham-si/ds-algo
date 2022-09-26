package graphs;

// O(ElogE) : works on undirected connected graph
// Prim’s algorithm gives connected component as well as it works only on undirected connected graph.
// Minimum Spanning Tree with V vertices will have (V - 1) edges.

import trees.PairUtil;

import java.util.*;

// T(ElogE)
public class PrimsUsingPQ {

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
        int minCost = 0;

        int dist[] = new int[V];
        int parent[] = new int[V];
        boolean []mst = new boolean[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        // dist[u]: minimum_of_all_edges_wt_coming_to_u
        dist[src] = 0;
        mst[src] = true;

        // src: {vertex, minimum_of_all_edges_wt_coming_to_src}
        PriorityQueue<Map.Entry<Integer, Integer>> pq= new PriorityQueue<>((a , b) -> Integer.compare(a.getValue(), b.getValue()));

        // reaching src from src takes 0 cost or "minimum reaching cost"
        pq.offer(PairUtil.Of(src, dist[src]));

        while (!pq.isEmpty()) {
            Map.Entry<Integer, Integer> vertex = pq.poll();

            // OR vertex.getValue() === dist[vertex.getKey()]
            minCost += vertex.getValue();
            mst[vertex.getKey()] = true;

            // vertex.getKey() === edge.source
            for(Edge edge : undirectedWeightedAdjList.get(vertex.getKey())) {
                // taking minimum_of_all_edges_wt_coming_to_u
                if (!mst[edge.dest] && dist[edge.dest] > edge.weight) {
                    dist[edge.dest] = edge.weight;
                    parent[edge.dest] = vertex.getKey();

                    pq.offer(PairUtil.Of(edge.dest,  dist[edge.dest]));
                }
            }
        }

        List<Edge> treeEdges = new ArrayList<>();

        // (v - 1) edges among v - vertices
        for (int v = 1; v < V; v++) {
            treeEdges.add(new Edge(parent[v], v, dist[v]));
        }

        System.out.println("MST COST: "+ minCost);

        return treeEdges;
    }
}
