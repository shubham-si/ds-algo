package linkedlist;

import java.util.LinkedList;
import java.util.Queue;

public class FlattenMultiLevelSinglyLL {
    /*
        You need to flatten the list in way that all nodes at the first level should come first, then nodes of second level,
        1 - 2 - 3 -  4 - 5
        |            |
        6 - 7 - 8    9 - 10
            |   |    |
            11  12 - 13 - 14
                |    |
                15   16 - 17

        O/P ==> [1,2,3,4,5, 6,7,8,9,10, 11,12,13,14, 15,16,17]
     */

    // T(n)
    // S(n)
    // Using queue: since FIFO, used to store down nodes appearing in the current level
    public Node flattenUsingQueue(Node head) {
        Queue<Node> queue = new LinkedList<>();
        Node curr = head;

        while(curr != null) {

            // end of current level
            if (curr.next == null) {
                // node in next down level in queue
                curr.next = queue.poll();
            }

            // if down node exits add to queue so that this level ends node from queue is picked
            if (curr.down != null) {
                queue.offer(curr.down);
            }

            curr = curr.next;
        }
        return head;
    }

    // https://www.codingninjas.com/codestudio/problems/flatten-the-multi-level-linked-list_839810?leftPanelTab=1
    // T(n)
    // S(1)

    // Using tail-ptr pointing to end of current list (while curr.nexr != null)
    // and so that node having down child > link the down node to end of current list (tail pointer)
    public Node flattenSpaceOptimized(Node head) {
        if (head == null) {
            return head;
        }

        Node tail = head;
        while (tail.next != null) {
            // points to last non null node if moving --->(sequentially)
            tail = tail.next;
        }

        Node curr = head;
        while(curr != null) {

            if (curr.down != null) {

                // link the down node to end of current list (tail pointer)
                tail.next = curr.down;

                // move tail to end of current list
                tail = curr.down;

                while(tail.next != null) {
                    tail = tail.next;
                }
            }

            curr = curr.next;

        }

        return head;
    }
}
