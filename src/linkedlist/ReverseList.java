
package linkedlist;

public class ReverseList {

    // T(N), S(1)
    // reverse of n/k elements leftover didn't reverse
    public Node reverseKGroupIterative(Node head, int k) {
        if (head == null || k <= 1 ) return head;

        int c = 0;
        Node temp = head;
        while(temp != null) {
            c++;
            temp = temp.next;
        }

        boolean isFirst = true;
        Node prev = null, beforeStart = null, curr = head, start = head;

        while(c >= k) {

            int i = 0;

            // reverse current group
            while(i < k && curr != null) {
                Node next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
                i++;
            }

            if (isFirst) {
                head = prev;
                isFirst = false;
            }

            // start becomes last node in current reverse chain
            if (start != null) {
                // link the start.next(last node) to next group head(curr)
                start.next = curr;
            }

            if (beforeStart != null) {
                // before start is pointing to last node of last group
                // prev is pointing to new head of current group
                beforeStart.next = prev;
            }

            // last node in current reverse chain (start)
            prev = start;
            beforeStart = start;

            start = curr;

            c = c - k;

        }
        return head;
    }

    // T(N), S(N/K): stack calls
    public Node reverseKGroupRecursive(Node head, int k){
        if (head == null || k <= 1) return head;

        Node prev = null, curr = head;
        int c = 0;

        while(c < k && curr!=null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            c++;
        }

        if (curr != null) {
            // head beacomes last node in current reverse chain, whose next point to new head
            head.next = reverseKGroupRecursive(curr, k);
        }

        // prev becomes new head
        return prev;
    }
}
