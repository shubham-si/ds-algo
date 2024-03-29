package trees;

import java.util.ArrayList;
import java.util.List;

// before evaluating root let's done with left and right in bottom up fashion
public class PrintAllAny_K_SumPaths {

    List<List<Integer>> res = new ArrayList<>();

    // bottom-up approach
    // time: O(n * h * h) ., path list : (max --> h)
    // space: O(h)

    // Bottom-up fashion computation
    /* Before calculation the root compute/done paths to it's left and right */
    void printKSumPaths(Node root, List<Integer> currPath, int target) {

        if (root == null) {
            return;
        }

        currPath.add(root.data);

        // check if there's any k sum path in the left sub-tree.
        printKSumPaths(root.left, currPath, target);

        // check if there's any k sum path in the right sub-tree.
        printKSumPaths(root.right, currPath, target);

        /* Before calculation the root compute/done paths to it's left and right */

        // check if there's any k sum path that terminates at this node
        // Traverse(backtrack) the entire path as there can be negative elements too
        int currSum = 0;
        for (int j = currPath.size() - 1; j >= 0; j--) {
            currSum += currPath.get(j);

            // If path sum is k, print the path
            if (currSum == target)
                // print from last to j'th
                res.add(currPath.subList(currPath.size() - 1, j));
        }

        // remove the current node as it's done
        currPath.remove(currPath.size() - 1);

    }
}
