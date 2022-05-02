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

        Node setTree(Node root){
            Node temp = new Node(0);
            //if tree is left most
            if(root.right == null){
                root.right=temp;
            }
            if (root.left == null){    //if tree is right most
                root.left = temp;
            }
            return root;
        }
    }

    static void maxSumPathNode(Node root) {
        /*
            max(
                1) only curr_node
                2) via root (l + root + r)
                3) either l,r => ( l or r) + root
            )
        */
        Result res = new Result();
        maxSumBetweenAnyNodes(root, res);
        System.out.println("between any nodes " + res.res);

        // leaf to leaf
        // adjust if root is either left rooted or right rooted only
        // for gfg acceptance

        if (root.left == null || root.right == null) {
            root = root.setTree(root);
        }

        Result leaf = new Result();
        maxSumBetweenLeafNodes(root, leaf);
        System.out.println("between leaf nodes "+ leaf.res);
    }

    // post -order approach (like diameter of a tree)
    // maximum possible sum b/w any nodes.
    static int maxSumBetweenAnyNodes(Node node, Result res) {
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

    // maximum possible sum from one leaf node to another, (w/o including curr_node.data in max computation as separate)
    static int maxSumBetweenLeafNodes(Node node, Result res) {
        if (node == null) {
            return 0;
        }

        int l = maxSumBetweenLeafNodes(node.left, res);
        int r = maxSumBetweenLeafNodes(node.right, res);

        // will not consider either_path(max_l_or_r) for computing maximum result
        // as this can give me any-node path max resulting in wrong answer
        // either_path as max can give me any one leaf node path thus can't be added to calc. res_max

        // update result only when considering leaf to leaf i.e., via root
        if (node.left != null && node.right != null) {
            res.res = Math.max(res.res, l + r + node.data);     // via - root leaf to leaf
        }

        // either path
        return (node.left == null) ? (r + node.data) : (l + node.data);
    }

    public static void main(String args[]) {
        Node root = new Node(5);
        // root.left = new Node(6);
        root.right = new Node(6);
//        root.left.left = new Node(-8);
//        root.left.right = new Node(1);
//        root.left.left.left = new Node(2);
//        root.left.left.right = new Node(6);
          root.right.left = new Node(-5);
          root.right.right = new Node(5);
//        root.right.right.right = new Node(0);
//        root.right.right.right.left = new Node(4);
//        root.right.right.right.right = new Node(-1);
//        root.right.right.right.right.left = new Node(10);

        maxSumPathNode(root);
    }
}

/*

//--- for test case ---
   Node root = new Node(-9);
        root.left = new Node(6);
        root.right = new Node(-10);
       //         7
      //        /    \
        //    Null   -3
      //     (case - 1)
      //   value of res will be INT_MIN but the answer is 4 , which is returned by the
 */