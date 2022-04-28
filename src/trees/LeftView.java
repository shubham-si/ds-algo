package trees;

import java.util.*;

public class LeftView {
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

    // O(h) --> (height only stack calls)
    static int printLeftViewWithH_Complexity(Node node, int level, int last_level) {
        if(node == null) {
            return last_level;
        }
        if (last_level < level) {
            // last_level will update only once, when a new level shows up, the current node will be leftMost node only
            System.out.print(node.data + " ");
            last_level = level;
        }
        // change the order of call
        last_level = printLeftViewWithH_Complexity(node.left, level + 1, last_level);
        last_level = printLeftViewWithH_Complexity(node.right, level + 1, last_level);
        return last_level;

    }

    // preOrder -->  O(n), O(n) space
    static void printLeftViewWithN_Complexity(Node node, int level, Map<Integer, Node> map) {
        if(node == null) {
            return;
        }
        if (!map.containsKey(level)) {
            // first time this level is showing so the current node will be left most node only
            map.put(level, node);
        }
        printLeftViewWithN_Complexity(node.left, level + 1, map);
        printLeftViewWithN_Complexity(node.right, level + 1, map);
    }

    public static void main(String ...args) {
        Node root = getBinaryTree();
        Map<Integer, Node> map = new TreeMap<>();
        printLeftViewWithN_Complexity(root, 0, map);
        map.entrySet().stream().forEach((entry) -> {System.out.print(entry.getValue().data + " ");});
        System.out.println("-----------------");
        printLeftViewWithH_Complexity(root, 0, -1);
    }
}

/*
    1) Using queue doing level order traversal and using size() and printing for the first index element at each level

    2) Using level approach --> add to map map[level] <- node (for the first occurence of that level)
 */