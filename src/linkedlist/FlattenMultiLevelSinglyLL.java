package linkedlist;

import java.util.LinkedList;
import java.util.Queue;

public class FlattenMultiLevelSinglyLL {
    /*
        You need to flatten the list in way that all nodes at the first level should come first, then nodes of second level,
        1 - 2 - 3 - 4 - 5
        |
        6 - 7 - 8   9 - 10
            |   |   |
            11  12  13 - 14
                |   |
                15  16 - 17

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


    // T(n)
    // S(1)
    //
    public Node flattenSpaceOptimized(Node head) {
        return head;
    }
}
