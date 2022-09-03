package leetcode;
import graphs.DisjointSetUnionFind;
import java.util.*;

// https://leetcode.com/problems/min-cost-to-connect-all-points/submissions/

/*
    You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].

    The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|,
    where |val| denotes the absolute value of val.

    Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.
 */

// Kruskal's or Prim's algo
public class MinCostConnectAllPoints {

    public int usingKruskals(int[][] points) {
        // (xi,yi) ---> (xj, yj)
        // wt = |xi - xj| + |yi - yj|
        int V = points.length;
        int cost = 0;

        // all possible edges
        List<Edge> edges = new ArrayList<>();
        for(int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                edges.add(new Edge(points,i, j));
            }
        }

        Collections.sort(edges, (a,b)-> a.wt - b.wt);
        DisjointSetUnionFind set = new DisjointSetUnionFind(V);

        for(Edge edge: edges) {
            System.out.println(edge.u+"->"+edge.v+"{" +set.find(edge.u) + ","+ set.find(edge.v)+"}");
            if (set.find(edge.u) != set.find(edge.v)) {
                cost += edge.wt;
                set.union(edge.u, edge.v);
            }
        }
        return cost;
    }

    public static void main(String[] args) {
        int [][]input = {{0,0},{1,1},{1,0},{-1,1}};
        System.out.println(new MinCostConnectAllPoints().usingKruskals(input));
    }
}

class Edge{
    int u,v,wt;
    int[][] points;
    Edge(int[][] points, int u, int v) {
        this.u = u;
        this.points = points;
        this.v = v;
        this.wt = Math.abs(points[u][0] - points[v][0]) + Math.abs(points[u][1] - points[v][1]);
    }

    public String toString() {
        return u+"->"+v+":"+wt;
    }
}
