package graphs;

import java.util.Arrays;
import java.util.Stack;

public class LongestPathDAG {

    // DP (max path)
    // Arrays.fill(dp, 0)
    // return max(dp[0...n-1])
    void longestPathUnWeighted(Graph graph, int node, int dp[], boolean []visited) {
        visited[node] = true;

        for(int neighbour: graph.adjList.get(node)) {
            if (!visited[neighbour]) {
                longestPathUnWeighted(graph, neighbour, dp, visited);
            }

            // bottom-up
            dp[node] = Math.max(dp[node], 1 + dp[neighbour]);
        }
    }

    void longestPathWeightedDAG(Graph graph, int n, int weight[][], int source) {
        // we do topological sort to get a order of dependencies or path from any node
        Stack<Integer> stack = TopologicalSort.sort(graph, n);
        int dist[] = new int[n];
        Arrays.fill(dist, Integer.MIN_VALUE);

        dist[source] = 0;
        while (!stack.empty()) {
            int node = stack.pop();

            // waiting for source node to come or non inf. node
            if (dist[node] != Integer.MIN_VALUE) {
                for(int near: graph.adjList.get(node)) {
                    // weight should be moved to edge property --> adj.node.prop
                    if (dist[near] < (dist[node] + weight[node][near])) {
                        dist[near] = dist[node] + weight[node][near];
                    }
                }
            }
        }

        // Print the calculated longest distances
        for (int v = 0; v < n; v++)
            if(dist[v] == Integer.MIN_VALUE)
                System.out.print("INF");
            else
                System.out.print(dist[v] + " ");
    }
}

// NOTE: for all path longest from all sources after topological sort --> run dfs with each node in a loop


