package graphs;

public class CycleGraph {

    // dfs --> saying a cycle: if neighbour 'v' is visited again from 'u' and par(u) != v
    // since neighbour 'v' is already visited and showing there exists a path from v --> u (and v is not parent of u)
    // that means a cycle is present with dfs traversal
    boolean isCyclePresentInUndirectedGraph(int source, Graph graph, int parent, boolean []visited) {
        visited[source] = true;
        for(int neighbour : graph.adjList.get(source)) {
            if (!visited[neighbour]) {
                // if cycle found somewhere keep the answer to true
                if (isCyclePresentInUndirectedGraph(neighbour, graph, source, visited) == true) {
                    return true;
                }
            // neighbour already visited and not parent of source
            } else if (neighbour != parent) {
                // cycle found
                return true;
            }
        }
        return false;
    }


    boolean isCyclePresentInDirectedGraph(int source, Graph graph, boolean []visited, boolean []currentPathStack) {
        visited[source] = true;
        currentPathStack[source] = true;
        for(int neighbour : graph.adjList.get(source)) {
            // in current stack path if 'v' already visited
            if (currentPathStack[neighbour] == true) {
                return true;
            }
            if (!visited[neighbour]) {
                // if cycle found somewhere keep the answer to true
                if (isCyclePresentInDirectedGraph(neighbour, graph, visited, currentPathStack) == true) {
                    return true;
                }
            }
        }
        // current source dfs path completed
        currentPathStack[source] = false;
        return false;
    }
}
