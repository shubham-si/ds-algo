package linkedlist;

public class MergeKSortedLLDivideConqurer {

    // T(N * log k)
    // S(1)
    public Node mergeKLists(Node[] lists) {
        // use merge sort
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeSort(lists, 0 ,lists.length - 1);
    }

    Node mergeSort(Node[] lists, int s, int e) {
        if (s == e) {
            // return head: single list
            return lists[s];
        }
        int mid = s + (e - s) / 2;
        // head node left or right of combined LL's
        Node left = mergeSort(lists, s, mid);
        Node right = mergeSort(lists, mid + 1, e);

        return mergeTwoLists(left , right);
    }

    Node mergeTwoLists(Node l1, Node l2) {

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
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        if (l1 != null) {
            curr.next = l1;
        }
        if (l2 != null) {
            curr.next = l2;
        }

        return dummy.next;
    }
}
