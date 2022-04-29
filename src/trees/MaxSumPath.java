package trees;

class Result {
    int res = Integer.MIN_VALUE;
}

class MaxSumPath
{
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    int maxPathSumAnyNode(Node node) {
        /*
            max(
                1) only node
                2) via root (l + root + r)
                3) either l,r => ( l or r) + node
            )
        */
        Result res = new Result();
        maxSumBetweenAnyNodes(node, res);

        Result leaf = new Result();
        maxSumBetweenLeafNodes(node, leaf);

        return res.res;
    }

    // post -order approach (like diameter of a tree)
    // maximum possible sum b/w any nodes.
    int maxSumBetweenAnyNodes(Node node, Result res) {
        if (node == null) {
            return 0;
        }

        int l = maxSumBetweenAnyNodes(node.left, res);
        int r = maxSumBetweenAnyNodes(node.right, res);

        int max_l_or_r = Math.max(node.data,     // only node
                Math.max(l , r) + node.data);    // either path

        int temp = Math.max((l + r + node.data),     // via root
                max_l_or_r);                        // either path

        res.res = Math.max(res.res, temp);

        return max_l_or_r;
    }

    // maximum possible sum from one leaf node to another.
    int maxSumBetweenLeafNodes(Node node, Result res) {
        if (node == null) {
            return 0;
        }

        int l = maxSumBetweenAnyNodes(node.left, res);
        int r = maxSumBetweenAnyNodes(node.right, res);

        int max_l_or_r = Math.max(node.data,     // only node
                Math.max(l , r) + node.data);    // either path

        int temp = Math.max((l + r + node.data),     // via root
                max_l_or_r);                        // either path

        res.res = Math.max(res.res, temp);

        return max_l_or_r;
    }
}