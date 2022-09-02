package graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Krushkal {
    int getMinSpanningTreeCost(List<Edge> edges, int N) {
        Collections.sort(edges, (a,b) -> a.weight - b.weight);
        int cost = 0;

        List<Edge> tree = new ArrayList<>();

        DisjointSetUnionFind disjointSetUnionFind = new DisjointSetUnionFind(N);

        for(Edge edge : edges) {
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
