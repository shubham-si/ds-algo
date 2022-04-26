package trees;

import java.util.*;

public class ZigZagTraversal {
    static class Node {
        int data;
        ZigZagTraversal.Node left;
        ZigZagTraversal.Node right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    public static Node getBinaryTree() {
        ZigZagTraversal.Node root = new ZigZagTraversal.Node(1);
        root.left = new ZigZagTraversal.Node(2);
        root.right = new ZigZagTraversal.Node(3);
        root.left.left = new ZigZagTraversal.Node(4);
        root.left.right = new ZigZagTraversal.Node(5);
        root.right.left = new ZigZagTraversal.Node(6);
        root.right.right = new ZigZagTraversal.Node(7);

        root.left.left.left = new ZigZagTraversal.Node(8);
        root.left.left.right = new ZigZagTraversal.Node(9);

        root.left.right.left = new ZigZagTraversal.Node(10);
        root.left.right.right = new ZigZagTraversal.Node(11);

        root.right.left.left = new ZigZagTraversal.Node(12);
        root.right.left.right = new ZigZagTraversal.Node(13);

        root.right.right.left = new ZigZagTraversal.Node(14);
        root.right.right.right = new ZigZagTraversal.Node(15);

        root.left.left.left.left = new ZigZagTraversal.Node(16);
        root.left.left.left.right = new ZigZagTraversal.Node(17);

        root.left.left.right.left = new ZigZagTraversal.Node(18);
        root.left.left.right.right = new ZigZagTraversal.Node(19);

        root.left.right.left.left = new ZigZagTraversal.Node(20);
        root.left.right.left.right = new ZigZagTraversal.Node(21);

        root.left.right.right.left = new ZigZagTraversal.Node(22);
        root.left.right.right.right = new ZigZagTraversal.Node(23);

        root.right.left.left.left = new ZigZagTraversal.Node(24);
        root.right.left.left.right = new ZigZagTraversal.Node(25);

        root.right.left.right.left = new ZigZagTraversal.Node(26);
        root.right.left.right.right = new ZigZagTraversal.Node(27);

        root.right.right.left.left = new ZigZagTraversal.Node(28);
        root.right.right.left.right = new ZigZagTraversal.Node(29);

        root.right.right.right.left = new ZigZagTraversal.Node(30);
        root.right.right.right.right = new ZigZagTraversal.Node(31);
        /*
                        1
                     2.....3
                    4.......7
                   8.........15
                  16...........31
         */
        return root;
    }

    static void printZigZagOrder(Node root) {
        boolean lTor = true;
        Deque<Node> deque = new LinkedList<Node>();
        deque.offer(root);
        while(!deque.isEmpty()) {
            // level wise traversal using size
            int size = deque.size();
            while(size > 0 ) {
                if(lTor) {
                    Node node = deque.pollFirst();
                    System.out.print(node.data);
                    // insert <---
                    if (node.left != null)
                        deque.offerLast(node.left);
                    if (node.right != null)
                        deque.offerLast(node.right);
                } else {
                    // right to left
                    Node node = deque.pollLast();
                    System.out.print(node.data);
                    // insert --->
                    if (node.right != null)
                        deque.offerFirst(node.right);
                    if (node.left != null)
                        deque.offerFirst(node.left);
                }
                size--;
            }
            lTor = !lTor;
            System.out.println();
        }
    }

// Given a complete binary tree, perform the following spiral traversal of the tree
    // print left most nodes on each level from top to bottom
    // print all nodes on bottom most level from left to right
    // print right most nodes on each level from bottom to top
    // every node is to be visited only once, perform this iteration until whole tree is traversed

 /*
                                 1
                    2                           3
          4               5               6           7
      8    9         10 .................................  15
  16   17 .............................................        31
32  33 ...................  ................... ...................63

o/p -->
    12481633....633115731
    5917...30146
    1018...13


Approach create map<level, deque<nodes>> ->
    0 -> 1
    1 -> 2, 3
    2-> 4, 5, 6, 7
    3 -> 8, 9, 10, 11, 12, 13, 14, 15

 */

    static void storeLevelOrderNodes(Node root, Map<Integer, Deque<Node>> levelDeque) {
        Queue<Node> q = new LinkedList<Node>();
        q.offer(root);
        int size = 1, level = 0;
        while(!q.isEmpty()) {
            int currSizeOfLevel = size;
            levelDeque.put(level, new LinkedList<Node>());
            while(currSizeOfLevel > 0 && !q.isEmpty()) {
                Node node = q.poll();
                levelDeque.get(level).offerLast(node);
                if(node.left != null) {
                    q.offer(node.left);
                }
                if(node.right != null) {
                    q.offer(node.right);
                }
                currSizeOfLevel--;
            }
            size = size * 2;
            level++;
        }
    }

    static void printCustomZigZag(Node root) {
        Map<Integer, Deque<Node>> levelDeque= new HashMap<Integer, Deque<Node>>();
        storeLevelOrderNodes(root, levelDeque);
        int currLevel = levelDeque.size() - 1;

        while(currLevel >= 0) {
            printLeftMost(currLevel, levelDeque);
            printBottom(currLevel, levelDeque);
            printRightMost(currLevel, levelDeque);
            System.out.println();

            levelDeque.remove(currLevel);
            currLevel--;
        }

    }

    private static void printLeftMost(Integer currLevel, Map<Integer, Deque<Node>> levelDeque) {
        for(int level = 0 ; level < currLevel; level++) {
            Deque<Node> deq = levelDeque.get(level);
            if (deq.isEmpty()) {
                continue;
            }
            System.out.print(deq.pollFirst().data);
        }
    }

    private static void printBottom(Integer currLevel, Map<Integer, Deque<Node>> levelDeque) {
        Deque<Node> bottomQueue = levelDeque.get(currLevel);
        if (bottomQueue.isEmpty()) {
            return;
        }
        int size = bottomQueue.size();
        while(size-- > 0) {
            System.out.print(bottomQueue.pollFirst().data);
        }
    }

    private static void printRightMost(Integer currLevel, Map<Integer, Deque<Node>> levelDeque) {
        for (int level = currLevel ; level >= 0 ; level-- ) {
            Deque<Node> deq = levelDeque.get(level);
            if (deq.isEmpty()) {
                continue;
            }
            System.out.print(levelDeque.get(level).pollLast().data);
        }
    }

    public static void main(String ...args) {
        Node root = getBinaryTree();
        ZigZagTraversal.printCustomZigZag(root);
    }
}