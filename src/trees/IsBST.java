package trees;

public class IsBST {

    // Preorder based T(n), S(h)
    // saying going down at node(i)  --> (node.data >= min && node.data <= max)
    // max(top node) >= greatest among left
    // min(top node) <= smallest among right
    public boolean isValidBST_1(Node root) {
        return isBST(root, null, null);
    }

    boolean isBST(Node node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }

        if ((min != null && node.data <= min) || (max !=null && node.data >= max)) {
            return false;
        }
        // node will be max on left and min on right
        return isBST(node.left, min, node.data) && isBST(node.right, node.data, max);
    }


    // Inorder based
    // root.node < prev.node : false
    // left: current node is root
    // right : root is prev
    Node prev = null;                                  // single copy
    public boolean isValidBST_2(Node root) {
        return isBSTInorder(root);
    }

    boolean isBSTInorder(Node root) {
        if (root == null) {
            return true;
        }

        // prev last node in inorder
        boolean left = isBSTInorder(root.left);

        // allow distinct values
        if (prev != null && root.data <= prev.data) {
            return false;
        }

        // go right
        prev = root;
        return left && isBSTInorder(root.right);
    }
}
