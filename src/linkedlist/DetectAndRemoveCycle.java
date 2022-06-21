package linkedlist;

public class DetectAndRemoveCycle {
    public Node detectCycle(Node head) {
        if (head == null || head.next == null) return null;

        Node slow = head, fast = head;
        while(fast != null && fast.next != null) {
            // move slow 1 step and fast 2 steps
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                // cycle found
                // reset slow to start of LL
                slow = head;

                while(slow != fast) {
                    // move both 1 step
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    public Node removeCycle(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node slow = head, fast = head;
        while(fast != null && fast.next != null) {
            // move slow 1 step and fast 2 steps
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                // cycle found
                // reset slow to start of LL
                slow = head;

                while(fast.next != slow) {
                    // move both 1 step
                    slow = slow.next;
                    fast = fast.next;
                }
                fast.next = null;
            }
        }
        return head;
    }
}
