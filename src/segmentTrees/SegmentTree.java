package segmentTrees;

public class SegmentTree {

    class SegmentTreeNode {
        public int start, end;
        // You can add other additional attributes
        public int max, min, sum;
        public SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.left = null;
            this.right = null;
        }
    }

    SegmentTreeNode root;

    public SegmentTree(int []arr) {
        this.root = build(arr);
    }

    private SegmentTreeNode build(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        return builder(arr, 0, arr.length - 1);
    }

    // (arr, 0 , n - 1)
    private SegmentTreeNode builder(int[] arr, int low, int high) {
        if (low > high) {
            return null;
        }

        // building top-down
        SegmentTreeNode root = new SegmentTreeNode(low, high);

        if (low == high) {
            root.min = arr[low];
            root.max = arr[low];
            root.sum = arr[low];
            return root;
        }

        int mid = low + (high - low) / 2;

        root.left = builder(arr, low, mid);
        root.right = builder(arr, mid + 1, high);

        root.min = Math.min(root.left.min, root.right.min);
        root.max = Math.max(root.left.max, root.right.max);
        root.sum = root.left.sum + root.right.sum;

        return root;
    }

    public void update(int arr[] ,int idx, int val) {
         update(arr, this.root, idx, val);
    }

    private void update(int arr[] , SegmentTreeNode node, int idx, int val) {
        if (node == null) {
            return;
        }

        if (node.start == idx && node.end == idx) {
            arr[idx] = val;
            node.sum += val;
            node.min = val;
            node.max = val;
            return;
        }

        int mid = node.start + (node.end - node.start) / 2;
        if (idx <= mid) {
            update(arr, node.left, idx, val);
        } else {
            update(arr, node.right, idx, val);
        }

        node.sum = node.left.sum + node.right.sum;
        node.min = Math.min(node.left.min, node.right.min);
        node.max = Math.max(node.left.max, node.right.max);
    }


    public int querySum(int l, int r) {
        return querySum(this.root, l ,r);
    }

    private int querySum(SegmentTreeNode node, int l ,int r) {

        if( node == null || l > node.end || r < node.start) {
            return 0;
        }
        // completely lies in current [low:l, r:high]
        if (node.start == l && node.end == r) {
            return node.sum;
        }

        int mid = node.start + (node.end - node.start) / 2;
        // {l:r} completely lies on left side
        if (r <= mid) {
            return querySum(node.left, l , r);
        } else if (l >= mid) {
            // {l:r} completely lies on right side
            return querySum(node.right, l , r);
        }
        // search range crosses both left and right children
        int left = querySum(node.left, l, mid);
        int right = querySum(node.right, mid + 1, r);

        return left + right;
    }

    public int queryMin(int l, int r) {
        return queryMin(this.root, l ,r);
    }

    private int queryMin(SegmentTreeNode node, int l ,int r) {

        if( node == null || l > node.end || r < node.start) {
            return 0;
        }
        // {start:{l,r}:end}
        if (node.start == l && node.end == r) {
            return node.sum;
        }

        int mid = node.start + (node.end - node.start) / 2;

        // {l:r} completely lies on left side
        if (r <= mid) {
            return queryMin(node.left, l , r);
        } else if (l >= mid) {
            // {l:r} completely lies on right side
            return queryMin(node.right, l , r);
        }
        // search range crosses both left and right children
        int left = queryMin(node.left, l, mid);
        int right = queryMin(node.right, mid + 1, r);

        return Math.min(left, right);
    }
}