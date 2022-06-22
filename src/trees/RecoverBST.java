package trees;

public class RecoverBST {

    Node prev, first, adjacent, last;
    public void recoverTree(Node root) {
        inorder(root);
        if (last != null) {
            swap(first, last);
        } else {
            swap(first, adjacent);
        }
    }

    void swap(Node a, Node b) {
        int temp = a.data;
        a.data = b.data;
        b.data = temp;
    }

    void inorder(Node root) {
        if (root == null) {
            return;
        }

        inorder(root.left);

        // inorder is increasing(or sorted)
        if (prev != null && root.data < prev.data) {
            if (first == null) {
                // 1st conflict (adjacent)
                first = prev;
                adjacent = root;
            } else {
                // 2nd conflict
                last = root;
            }
        }

        prev = root;
        inorder(root.right);
    }
}
