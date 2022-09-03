package graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// T: O(E log V)
// Kruskal’s algorithm can generate forest(disconnected components) at any instant as well as it can work on disconnected components.

// Minimum Spanning Tree with V vertices will have (V - 1) edges (undirected graph).
public class Kruskal {

    // the minimum spanning tree formed will be having (V – 1) edges.
    int getMinSpanningTreeCost(Graph graph, int V) {
        Collections.sort(graph.Edges, (a,b) -> a.weight - b.weight);
        int cost = 0;

        List<Edge> tree = new ArrayList<>();

        DisjointSetUnionFind disjointSetUnionFind = new DisjointSetUnionFind(V);

        // relax all edges
        for(Edge edge: graph.Edges) {

            int pu = disjointSetUnionFind.find(edge.source);
            int pv = disjointSetUnionFind.find(edge.dest);

            if (pu != pv) {
                cost += edge.weight;
                disjointSetUnionFind.union(edge.source, edge.dest);

                tree.add(new Edge(edge.source, edge.dest, edge.weight));
            }
        }

        return cost;
    }
}
