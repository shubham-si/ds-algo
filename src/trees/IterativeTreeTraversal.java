package trees;

// Stack<Pair<Node, State{0,1,2}>>

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class IterativeTreeTraversal {
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
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        root.left.left.left = new Node(8);
        root.left.left.right = new Node(9);
        root.left.right.left = new Node(10);
        root.left.right.right = new Node(11);
        root.right.left.left = new Node(12);
        root.right.left.right = new Node(13);
        root.right.right.left = new Node(14);
        root.right.right.right = new Node(15);

        root.left.left.left.left = new Node(16);
        root.left.left.left.right = new Node(17);
        root.left.left.right.left = new Node(18);
        root.left.left.right.right = new Node(19);
        root.left.right.left.left = new Node(20);
        root.left.right.left.right = new Node(21);
        root.left.right.right.left = new Node(22);
        root.left.right.right.right = new Node(23);
        root.right.left.left.left = new Node(24);
        root.right.left.left.right = new Node(25);
        root.right.left.right.left = new Node(26);
        root.right.left.right.right = new Node(27);
        root.right.right.left.left = new Node(28);
        root.right.right.left.right = new Node(29);
        root.right.right.right.left = new Node(30);
        root.right.right.right.right = new Node(31);
        /*
                        1
                     2.....3
                    4.......7
                   8.........15
                  16...........31
         */
        return root;
    }

    static void traversal(Node root) {
        List<Integer> pre = new ArrayList<>();
        List<Integer> in = new ArrayList<>();
        List<Integer> post = new ArrayList<>();

        Stack<Map.Entry<Node, Integer>> stk = new Stack<>();
        stk.push(PairUtil.Of(root, 0));

        while (!stk.empty()) {
            Map.Entry<Node, Integer> entry = stk.peek();
            stk.pop();

            // 0: pre-order print(root) on first call
            if(entry.getValue() == 0) {
                pre.add(entry.getKey().data);
                // for in order it will print after left is done
                stk.push(PairUtil.Of(entry.getKey(), 1));

                if (entry.getKey().left != null) {
                    stk.push(PairUtil.Of(entry.getKey().left, 0));
                }
            }

            // 1: in-order print(root) on second call
            else if(entry.getValue() == 1) {
                in.add(entry.getKey().data);
                // for post order it will print after right is also done
                stk.push(PairUtil.Of(entry.getKey(), 2));

                if (entry.getKey().right != null) {
                    stk.push(PairUtil.Of(entry.getKey().right, 0));
                }
            }

            // 2: post-order print(root) on third call
            else {
                post.add(entry.getKey().data);
            }
        }

        System.out.println("preOrder "+ pre);
        System.out.println("inOrder "+ in);
        System.out.println("postOrder "+ post);
    }

    public static void main(String ...args) {
        Node root = getBinaryTree();
        traversal(root);
    }
}
