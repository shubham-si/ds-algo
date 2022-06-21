package linkedlist;

public class Intersection {
    public Node getIntersectionNode(Node headA, Node headB) {
        Node p1 = headA, p2 = headB;
        while(p1 != p2) {
            // say, if p1 becomes null first assign to headB and move k steps forward in B so that 
            // when p2 finishes later and assigned to A head meanwhile will be k-steps ahead
            // so that p1 and p2 will be (k) steps from intersection point
            p1 = (p1 == null) ? headB : (p1.next);
            p2 = (p2 == null) ? headA : (p2.next);
        }
        return p1;
    }
}
