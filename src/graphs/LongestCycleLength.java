package graphs;

// https://leetcode.com/problems/longest-cycle-in-a-graph/

public class LongestCycleLength {
    int cycle = -1;
    public int longestCycle(int[] arr) {
        int n = arr.length;
        //0, 1, 2
        int color[] = new int[n];

        // step taken to reach from src to 'ith' node
        int timings[] = new int[n];
        for(int i = 0 ; i < n ; i++) {
            if(color[i] == 0) {
                dfs(i, arr, color, timings);
            }
        }
        return cycle;
    }

    void dfs(int src, int[] arr, int color[], int[] timings) {
        color[src] = 1;
        int neighbour = arr[src];

        if (neighbour != -1) {
            if (color[neighbour] == 0) {
                timings[neighbour] = timings[src] + 1;
                dfs(neighbour, arr, color, timings);
            } else if (color[neighbour] == 1) {
                // cycle_start <-- neighbour
                // cycle_end <-- src
                // cycle_len <-- (time_neighbour - time_src + 1)
                cycle = Math.max(cycle, timings[src] - timings[neighbour] + 1);
            }
        }

        color[src] = 2;
    }
}
