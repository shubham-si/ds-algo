package graphs;

public class CycleGraph {

    // dfs --> saying a cycle: if neighbour 'v' is visited again from 'u' and par(u) != v
    // since neighbour 'v' is already visited and showing there exists a path from v --> u (and v is not parent of u)
    // that means a cycle is present with dfs traversal
    boolean isCyclePresentInUndirectedGraph(int source, Graph graph, int parent, boolean []visited) {
        visited[source] = true;
        for(int neighbour : graph.undirectedAdjList.get(source)) {
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

        for(int neighbour : graph.directedAdjList.get(source)) {
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


/*
Directed graph using color with print path

int n;
vector<vector<int>> adj;
vector<char> color;
vector<int> parent;             // parent[i] = i
int cycle_start, cycle_end;

bool dfs(int src) {
    color[src] = 1;                       // grey
    for (int neighbour : adj[src]) {
        if (color[neighbour] == 0) {            // not visited
            parent[neighbour] = src;
            if (dfs(neighbour) == true)
                return true;
        } else if (color[neighbour] == 1) {         // grey
            cycle_end = src;
            cycle_start = neighbour;                // actual_originator of dfs i.e., 1st call to dfs
            return true;
        }
    }
    color[src] = 2;                           // black
    return false;
}

void print_cycle() {
    color.assign(n, 0);
    parent.assign(n, -1);
    cycle_start = -1;

    for (int v = 0; v < n; v++) {
        if (color[v] == 0 && dfs(v))
            break;
    }

    if (cycle_start == -1) {
        cout << "Acyclic" << endl;
    } else {
        vector<int> cycle;

        // backtrack
        for (int v = cycle_end; v != cycle_start; v = parent[v]) {
            cycle.push_back(v);
        }

        cycle.push_back(cycle_start);

        reverse(cycle.begin(), cycle.end());

        cout << "Cycle found: ";
        for (int v : cycle)
            cout << v << " ";
        cout << endl;
    }
}

 */