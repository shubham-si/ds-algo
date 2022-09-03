package graphs;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class TopologicalSort {



    static Stack<Integer> sort(Graph graph, int n) {
        Stack<Integer> order = new Stack<>();
        boolean[] visited = new boolean[n];

        Arrays.fill(visited, false);
        dfs(graph, 0, visited, order);

        Iterator<Integer> itr= order.listIterator();
        while (!itr.hasNext()) {
            System.out.println(itr.next());
        }
        return order;
    }

    /*
        for(i<--0 to (n-1)) {
            !visited[i]
                call dfs(graph, i, visited, stack)
        }

     */

    static void dfs(Graph graph, int node, boolean[] visited, Stack stack) {
        visited[node] = true;

        for(Integer neighbour: graph.directedAdjList.get(node)) {
            if (!visited[neighbour]) {
                dfs(graph, neighbour, visited, stack);
            }
        }

        stack.push(node);
    }

    // using post time
    // decreasing order [ (n-1) --> 0 ] will have topological order since last time will be top on DAG ie., last to finish
    void dfs(Graph graph, int node, boolean[] visited, Stack stack, int []postTime, int time) {
        visited[node] = true;

        for(Integer neighbour: graph.directedAdjList.get(node)) {
            if (!visited[neighbour]) {
                dfs(graph, neighbour, visited, stack);
            }
        }
        postTime[time++] = node;
    }
}
