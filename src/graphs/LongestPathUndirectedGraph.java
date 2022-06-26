package graphs;

/*
    The longest path will always occur between two leaf nodes.
    We start DFS from a random node and then see which node is farthest from it.
    Let the node farthest be X. It is clear that X will always be a leaf node and a corner of DFS.
    Now if we start DFS from X and check the farthest node from it, we will get the diameter of the tree.

    eg.,    l1 <------------ x ----------->l2
    say l1 to l2 is path of longest diameter and x is random pointer can be or not be in path of this

    so 1st dfs(x:random) will be any farthest leaf node <(l1 or l2), max_distance>
    and second dfs(l1 or l2) will give u max path b/w <l1 and l2, max_distance>
 */

// T(n)
//S(n) : stack calls
public class LongestPathUndirectedGraph {
    int l1 = -1;
    int maxCount = Integer.MIN_VALUE;

    int[] longestPath(Graph graph, int n) {
        int randomNode = 3;
        // 1st dfs
        // DFS from a random node and then see farthest node l1 from it
        dfs(randomNode, graph, new boolean[n], 0);

        //2nd dfs from l1
        // DFS from l1 and check the farthest node from it
        dfs(l1, graph, new boolean[n], 0);

        // here l1 is actually l2
        return new int[]{l1, maxCount};
    }

    void dfs(int u, Graph graph, boolean []visited, int count) {
        visited[u] = true;
        count++;

        for(int v : graph.undirectedAdjList.get(u)) {
            if (!visited[v]) {
                if (count < maxCount ){
                    maxCount = count;
                    l1 = v;
                }
                dfs(v, graph, visited, count);
            }
        }
    }
}
