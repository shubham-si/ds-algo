package trees;

public class IterativeTreeTraversal {
    static class Node {
        int data;
        LevelOrderPrint.Node left;
        LevelOrderPrint.Node right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    public static Node getBinaryTree() {
        Node root = new Node(1);
        // root.left = new Node(2);
        root.right = new LevelOrderPrint.Node(3);
        root.right.left = new LevelOrderPrint.Node(4);
        root.right.right = new LevelOrderPrint.Node(5);
        root.right.right.left = new LevelOrderPrint.Node(6);
        return root;
    }

    public static void main(String ...args) {
        Node root = getBinaryTree();
    }
}
