package trees;

import java.util.*;

// O(n)
// O(n)
public class CountAll_Any_K_SumPaths {
    public int pathSum(Node root, int target) {

        // storing prefix sum of current running path
        // checking on each node if (prefix_sum - target) exits in map add (1) to count
        HashMap<Integer, Integer> map = new HashMap();

        // prefix_sum_0: 1
        map.put(0, 1);
        return pathSumMap(root, 0, target, map);
    }

    public int pathSumMap(Node root, int runningSum, int target, HashMap<Integer, Integer> map) {
        if (root == null) {
            return 0;
        }

        runningSum += root.data;

        // get prefix sum present in current path
        int count = map.getOrDefault(runningSum - target, 0);

        // add current instance runningSum to map
        map.put(runningSum, map.getOrDefault(runningSum, 0) + 1);

        int leftC = pathSumMap(root.left, runningSum, target, map);
        int rightC = pathSumMap(root.right, runningSum, target, map);

        count += leftC + rightC;

        // remove current instance runningSum from map
        map.put(runningSum, map.get(runningSum) - 1);
        return count;
    }
}
