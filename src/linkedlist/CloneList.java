package linkedlist;

public class CloneList {

    public Node copyRandomList(Node head) {
        if (head == null)
            return head;

        // 1 . add a node to next of current LL node
        Node curr = head, next = null;

        while(curr != null) {
            next = curr.next;
            curr.next = new Node(curr.val);

            // point the new node to old LL next : i.e., link
            curr.next.next = next;
            curr = next;
        }

        // 2. copy random pointers
        curr = head;

        while(curr != null) {
            curr.next.random = (curr.random != null) ? curr.random.next : null;
            // skipping cloned node
            curr = curr.next.next;
        }

        // 3. break the connections
        Node dummy = new Node(-1);
        dummy.next = head.next;

        curr = head;

        while(curr != null) {
            // old LL ref.
            next = curr.next.next;
            curr.next.next = (next != null) ? (next.next): null;

            curr.next = next;
            curr = next;
        }

        // head of new cloned LL
        return dummy.next;
    }
}
