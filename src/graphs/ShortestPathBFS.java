package graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathBFS {

    void shortestPath(Graph graph, int n, int source, int u) {
        Queue<Integer> q = new LinkedList<Integer>();
        boolean visited[] = new boolean[n];
        int dist[] = new int[n];
        int parent[] = new int[n];

        q.offer(source);
        visited[source] = true;
        parent[source] = -1;

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int adj : graph.undirectedAdjList.get(node)) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    q.offer(adj);

                    dist[adj] = dist[node] + 1;
                    parent[adj] = node;
                }
            }
        }

        // If we have to restore and display the shortest path from the source to some vertex u,
        if (!visited[u]) {
            // disconnected graph
            System.out.println("not reachable");
            return;
        }
        // print path
        ArrayList<Integer> path = new ArrayList<Integer>();
        for (int v = u; v != -1; v = parent[v])
            path.add(v);
        Collections.reverse(path);
        for(int v : path)
            System.out.println(v);

    }
}
