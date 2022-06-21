package leetcode;

import java.util.*;

public class CourseSchedule2 {
    public int[] findOrder(int N, int[][] pre) {
        List<List<Integer>> adj  = new ArrayList<>();

        for(int i=0;i<N;i++) {
            adj.add(new ArrayList());
        }

        for(int i=0;i< pre.length;i++) {
            adj.get(pre[i][1]).add(pre[i][0]);
        }
        int color[] = new int[N];
        Stack<Integer> stk = new Stack<>();

        for(int i = 0 ;i< N; i++) {
            if(color[i] == 0 && isCycle(i, color, adj, stk)) {
                return new int[]{};
            }
        }

        int []ans = new int[N];
        int i = 0;
        while(!stk.empty()) {
            ans[i++] = stk.peek();
            stk.pop();
        }

        return ans;
    }

    boolean isCycle(int src, int[] color, List<List<Integer>> adj, Stack<Integer> stk){
        color[src] = 1;
        for(int u: adj.get(src)) {
            if(color[u] == 0 && isCycle(u, color, adj, stk)) {
                return true;
            } else if(color[u] == 1) {
                return true;
            }
        }
        stk.push(src);
        color[src] = 2;
        return false;
    }
}
