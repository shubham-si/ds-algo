package arrays;

import trees.PairUtil;

import java.util.*;

public class MinimumSwapsArraySort {

    // Approach 1: Graph based T:(nlogn), S(n)
    int graphBased(int []arr, int n) {
        List<Map.Entry<Integer, Integer>> pairs = new ArrayList();

        for(int i = 0 ; i<n ; i++) {
            pairs.add(PairUtil.Of(arr[i], i));
        }

        // sort according to array element value
        Collections.sort(pairs, (a,b) -> a.getKey().compareTo(b.getKey()));

        boolean visited[] = new boolean[n];
        int swaps = 0;

        for(int i = 0 ; i<n ; i++) {
            if (visited[i] || i == pairs.get(i).getValue()) {
                // element already present at it's correct idx in sorted array
                continue;
            }

            int u = i, cycleLen = 0;

            // dfs (runs in a reversal order) around cycle
            // trace back cyclic path
            while (!visited[u]) {
                visited[u] = true;
                cycleLen++;

                u = pairs.get(u).getValue();
            }

            swaps += (cycleLen > 0) ? (cycleLen - 1) : 0;
        }

        return swaps;
    }


    // Approach 2: Building original array from sorted pair<arr[idx], idx> &> backtrack(swaps) using stored idx
    // T:O(nlogn), S(n)

        /*
            {4, 2, 5, 3}  -> sort -> [{2,1}, {3,3}, {4,0}, {5,2}]

            i = 0 > (2,1)  ==> 0 != 1 swap(0,1) => [{3,3}, {2,1}, {4,0}, {5,2}]

            i = 0 > (0,3) ==> 0 != 3 swap(0,3) => [{5,2}, {2,1}, {4,0}, {3,3}]

            i = 0 > (0,2) ==> 0 != 2 swap(0,2) => [{4,0}, {2,1}, {5,2}, {3,3}]  --> original array

            swaps = 3
         */

    int backtrackBased(int []arr, int n) {
        List<Map.Entry<Integer, Integer>> pairs = new ArrayList();

        for(int i = 0 ; i<n ; i++) {
            pairs.add(PairUtil.Of(arr[i], i));
        }

        // sort according to array element value
        Collections.sort(pairs, (a,b) -> a.getKey().compareTo(b.getKey()));

        int swaps = 0;

        for(int i = 0 ; i<n ; i++) {

            if (i == pairs.get(i).getValue()) {
                continue;
            }

            swaps++;
            swap(pairs, i, pairs.get(i).getValue());

            // decrement(i), & check again the updated pair swapped(at 'i'),
            // whether comes to org. position as in given array ie., ( i == pairs.get(i).getValue())
            i--;
        }

        return swaps;
    }

    private void swap(List<Map.Entry<Integer, Integer>> pairs, int i, int j) {
        Map.Entry<Integer, Integer> temp = pairs.get(i);
        pairs.set(i, pairs.get(j));
        pairs.set(j, temp);
    }
}
