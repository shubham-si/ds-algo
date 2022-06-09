package trees;

/*
 In the in-order traversal for a given node 'n', we visit n.left, then 'n', then n.right.
 Therefore, if we want to find the in-order successor of node 'n', we do the following:
1. First notice that because of the order in which we visit nodes, if 'n' has a right child,
    then the successor must be on the right side of 'n.
    Specifically, immediately after visit of node 'n', the left-most child in the right sub-tree of node 'n' will be visited.

2. If node 'n' does not have right child then -
      a. If 'n' is a left child of its parent(parent.left == 'n'), then parent is the in-order successor of 'n';
      b. If 'n' is a right child of its parent(parent.right == 'n'), and
        -> "if it does not have the right child"
      then we keep on searching for in-order successor by updating 'n' to parent until we find that 'n' is a left child of its parent.
      At this point we return parent(same as step 'a').
3.  If the node is the rightmost child. If that happens there does not exist any inorder successor for the node.
 */

public class InorderSuccessorBinaryTree {

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

    public static Node getBinaryTree() {
        Node root = new Node(1);

        root.left = new Node(2);
        root.right = new Node(6);

        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.left.right.right = new Node(5);
//        root.right.left = new Node(6);
//        root.right.right = new Node(7);
//
//        root.left.left.left = new Node(8);
//        root.left.left.right = new Node(9);
//        root.left.right.left = new Node(10);
//        root.left.right.right = new Node(11);
//        root.right.left.left = new Node(12);
//        root.right.left.right = new Node(13);
//        root.right.right.left = new Node(14);
//        root.right.right.right = new Node(15);
//
//        root.left.left.left.left = new Node(16);
//        root.left.left.left.right = new Node(17);
//        root.left.left.right.left = new Node(18);
//        root.left.left.right.right = new Node(19);
//        root.left.right.left.left = new Node(20);
//        root.left.right.left.right = new Node(21);
//        root.left.right.right.left = new Node(22);
//        root.left.right.right.right = new Node(23);
//        root.right.left.left.left = new Node(24);
//        root.right.left.left.right = new Node(25);
//        root.right.left.right.left = new Node(26);
//        root.right.left.right.right = new Node(27);
//        root.right.right.left.left = new Node(28);
//        root.right.right.left.right = new Node(29);
//        root.right.right.right.left = new Node(30);
//        root.right.right.right.right = new Node(31);
        /*
                        1
                     2.....3
                    4.......7
                   8.........15
                  16...........31
         */
        return root;
    }

    // get leftmost node for a given node (if parent is given node)
    // first right ka left most node -> in-succ of it's parent
    static Node leftMostNode(Node givenNode) {
        while(givenNode != null && givenNode.left != null) {
            givenNode = givenNode.left;
        }
        return givenNode;
    }

    // get rightmost node for a given node (if parent is given node)
    static Node rightMostNode(Node givenNode) {
        while(givenNode != null && givenNode.right != null) {
            givenNode = givenNode.right;
        }
        return givenNode;
    }



    static void findInorderSuccesor(Node root, Node given) {
        // case: 1
        if (given.right != null) {
            Node leftmost = leftMostNode(given.right);
            Node rs =  leftmost == null ? given.right : leftmost;
            System.out.print("Inorder Successor of "+given.data+" is "+rs.data);
            return;
        }
        // if given is right most node of root
        Node rightmost = rightMostNode(root);
        if (rightmost == given) {
            System.out.print("Inorder Successor of "+given.data+" is null as last node");
        }
        // case: 2
        findAncestorSuccessor(root, given);
    }

    // ie., case 2 (given node has no right subtree) can be either left or right subtree of its parent
    // if left then -> parent will be the answer
    // if right then -> until par's are right of parent go up and return the par is left of it's par (first)
    private static Node findAncestorSuccessor(Node root, Node given) {

        if (root == null) {
            return null;
        }

        if (root == given) {
            // backtrack until get first i.e., node is left of it's parent
            return root;
        }

        Node temp = findAncestorSuccessor(root.left, given);
        // if returned node is non empty and left of it's parent then successor is root
        if (temp != null) {
            System.out.print("Inorder Successor of "+given.data+" is "+root.data);
            return null;
        }
        // return node till right of their parent's continues
        return findAncestorSuccessor(root.right, given);
    }

    public static void main(String []args) {
        Node root = getBinaryTree();
        findInorderSuccesor(root, root.left.right.right);
    }

}
