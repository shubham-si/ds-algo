package test;

public class MergeSubSeqZero {
    Node merge(Node head) {
        if(head == null) {
            return head;
        }

        Node dummy = new Node(-1);
        Node curr = head, prev = dummy;

        while (curr != null && curr.next != null) {
            int sum = 0;
            while(curr.next.data != 0) {
                sum += curr.data;
                curr = curr.next;
            }
            sum += curr.data;
            curr = curr.next;

            prev.next = new Node(sum);
            prev = prev.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        Node root = new Node(0);
        root.next = new Node(3);
        root.next.next = new Node(4);
        root.next.next.next = new Node(0);
        root.next.next.next.next = new Node(4);
        root.next.next.next.next.next = new Node(5);
        root.next.next.next.next.next.next = new Node(0);

        root = new MergeSubSeqZero().merge(root);
        while (root != null) {
            System.out.println(root.data);
            root = root.next;
        }
    }
}


class Node {
    int data;
    Node next;
    Node(int d) {
        this.data = d;

    }
}

