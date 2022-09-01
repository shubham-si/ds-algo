package segmentTrees;

public class SegmentTreePrefixSum {
    int tree[];

    public SegmentTreePrefixSum(int n) {
        this.tree = new int[4 * n];
    }

    // (arr, 0 , n - 1, val)
    void build(int []arr, int treeIdx, int low , int high) {
        if (low > high) {
            return;
        }

        if (low == high) {
            this.tree[treeIdx] = arr[low];
        }
        else {
            int mid = low + (high - low) / 2;

            build(arr, 2 * treeIdx + 1, low , mid);
            build(arr, 2 * treeIdx + 2, mid + 1 , high);

            this.tree[treeIdx] = this.tree[2 * treeIdx + 1] + this.tree[2 * treeIdx + 2];
        }
    }

    // update (arr)  (idx)  ->  (val)  (0 , 0 , n - 1)
    void update(int []arr, int idx , int val, int treeIdx , int low , int high) {

        if (low == idx && high == idx) {
            arr[idx] += val;
            this.tree[treeIdx] += val;
        }
        else {
            int mid = low + (high - low) / 2;

            if (idx <= mid) {
                update(arr, idx, val, 2 * treeIdx + 1, low, mid);
            } else {
                update(arr, idx, val, 2 * treeIdx + 2, mid + 1, high);
            }

            this.tree[treeIdx] = this.tree[2 * treeIdx + 1] + this.tree[2 * treeIdx + 2];
        }
    }

    // query (l , r, 0:root -> [0 , n - 1])
    int query(int l, int r, int treeIdx, int low, int high) {
        // {l,r} -> [low, high]
        if (l > high || r < low) {
            return 0;
        }
        // [l:r] completely lies in current [low:l, r:high]
        if ( low == l && high == r) {
            return this.tree[treeIdx];
        }

        int mid = low + (high - low) / 2;
        if (r <= mid) {
            // {l:r} completely lies on left side
            return query(l, r, 2 * treeIdx + 1, low, mid);
        } else if ( l >= mid) {
            // {l:r} completely lies on right side
            return query(l, r, 2 * treeIdx + 2, mid + 1, high);
        }

        // search range crosses both left and right children

        int left = query(l, mid, 2 * treeIdx + 1, low, mid);
        int right = query(mid + 1, r, 2 * treeIdx + 2, mid + 1, high);

        return left + right;
    }

    public static void main(String[] args) {
        int arr[] = {1, 3, 5, 7, 9, 11};
        int n = arr.length;
        SegmentTreePrefixSum tree = new SegmentTreePrefixSum(n);
        tree.build(arr, 0,0, n - 1 );

        System.out.println("Sum of values in given range = " +
                tree.query(0, 3, 0 ,0, n - 1));
    }


}


/*
                   [0...n-1]
        [0... n-1/2]     [n/2 .... n-1]
      ....................................
      [0] [1] [2].......................[n - 1]


      int height = (int) (Math.log(n) / Math.log(2)) + 1;
    int treeNodes = (int) Math.pow(2, height + 1);
    treeNodes = n * 4

    Here is why:
    (Math.log(n) / Math.log(2)) + 1 = log2(n) + 1
    2 ^ (log2(n) + 2) = 2 ^ (log2(n)) * 2 ^ 2 = n * 4

 */
