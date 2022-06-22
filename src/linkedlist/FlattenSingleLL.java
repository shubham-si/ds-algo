package linkedlist;

// https://practice.geeksforgeeks.org/problems/flattening-a-linked-list/1/
public class FlattenSingleLL {
    /*

       5 -> 10 -> 19 -> 28
       |    |     |     |
       V    V     V     V
       7    20    22    35
       |          |     |
       V          V     V
       8          50    40
       |                |
       V                V
       30               45

    O/p => 5->7->8->10->19->20->22->28->30->35->40->45->50.
     */

    // concept like merging k sorted list
    // but from backwards adding node to down of current node/head i.e., (reverse call stack)
    Node flattenSinglyLL(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        // it will send new head from merger of current and next list
        head.next = flattenSinglyLL(head.next);
        return mergeTwoListsDownwards(head, head.next);
    }

    Node mergeTwoListsDownwards(Node l1, Node l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        Node dummy = new Node(-1);
        Node curr = dummy;

        while(l1 != null && l2!= null) {
            if (l1.val < l2.val) {
                curr.down = l1;
                l1 = l1.down;
            } else {
                curr.down = l2;
                l2 = l2.down;
            }
            curr = curr.down;
        }

        if (l1 != null) {
            curr.down = l1;
        }
        if (l2 != null) {
            curr.down = l2;
        }

        return dummy.down;

    }
}
