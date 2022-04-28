package trees;

import java.util.HashMap;
import java.util.Map;

public class TopView {
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

    static void printPreOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        printPreOrder(node.left);
        printPreOrder(node.right);
    }

    static void printTopView(Node node, int dist,int level, Map<Integer, Map.Entry<Integer, Node>> topViewMap) {
        // do a pre-order traversal
        if (node == null) {
            return;
        }
        if (!topViewMap.containsKey(dist) || level < topViewMap.get(dist).getKey()) {
            topViewMap.put(dist, PairUtil.Of(level, node));
        }
        printTopView(node.left, dist - 1, level + 1, topViewMap);
        printTopView(node.right, dist + 1, level + 1, topViewMap);
    }


    public static void main(String ...args) {
        Node root = getBinaryTree();
        // map<dist, <level, Node>
        Map<Integer, Map.Entry<Integer, Node>> topViewMap = new HashMap<>();
        // printPreOrder(root);
        printTopView(root, 0, 0, topViewMap);
        topViewMap.entrySet().stream().forEach((entry) -> {System.out.print(entry.getValue().getValue().data + " ");});
    }
}


/*
    complexity --> O(nlogn) : TreeMap if order doesn't matter ie., print l -> r <dist>(...-3,-2,-1,0,1,2,3...)
               --> O(n): HashMap
 */