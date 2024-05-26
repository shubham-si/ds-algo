package trees;

import java.util.*;

// O(n)
// O(n)
// path downwards direction only
public class CountAll_Any_K_SumPaths {

    // Method: 1
    // storing prefix sum of current running path
    // checking on each node if (prefix_sum - target) exits in map add (1) to count
    HashMap<Long,Integer> prefixMap;
    public int pathSumViaPrefixMap(Node root, int target) {
        prefixMap = new HashMap();
        return pathSumMap(root, 0L, target);
    }

    public int pathSumMap(Node node, Long runningSum, int target) {
        if (node == null) {
            return 0;
        }

        int count = 0;
        Long currentNodeSum = runningSum + node.data;
        if (currentNodeSum == target) {
            count++;
        }

        // get prefix sum present in current path
        count += prefixMap.getOrDefault(currentNodeSum - target, 0);

        // add current instance currentNodeSum to map
        prefixMap.put(currentNodeSum, prefixMap.getOrDefault(currentNodeSum, 0) + 1);

        int leftC = pathSumMap(node.left, currentNodeSum, target);
        int rightC = pathSumMap(node.right, currentNodeSum, target);

        count += leftC + rightC;

        // remove current instance currentNodeSum from map i.e., (runningSum + root.data)
        prefixMap.put(currentNodeSum, prefixMap.get(currentNodeSum) - 1);
        return count;
    }

    // Method: 2
    // O(n)
    // O(n)
    int count = 0;
    public void pathSumViaList(Node node, ArrayList<Node> arrayList, int target) {
        if (node == null) {
            return;
        }

        arrayList.add(node);
        pathSumViaList(node.left, arrayList, target);
        pathSumViaList(node.right, arrayList, target);

        long s = 0;
        // read last inserted node
        for(int i = arrayList.size() - 1; i >= 0; i--) {
            s += arrayList.get(i).data;
            if (s == target) {
                count++;
            }
        }
        arrayList.remove(arrayList.size() - 1);
    }
}
