package arrays;

// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array
public class FirstAndLastIndex {

    public int[] firstAndLastIndex(int[] arr, int k) {
        int n = arr.length;

        int s = 0;
        int e = n - 1;
        int firstIndex = -1;


        while (s <= e) {
            int m = s + (e - s) / 2;

            if (arr[m] == k) {
                firstIndex = m;

                // hoping to get 'k' also in left part
                e = m - 1;
            } else if (k < arr[m]) {
                e = m - 1;
            } else if (k > arr[m]) {
                s = m + 1;
            }
        }

        int lastIndex = -1;
        s = 0;
        e = n - 1;

        while (s <= e) {
            int m = s + (e - s) / 2;

            if (arr[m] == k) {
                lastIndex = m;

                // hoping to get 'k' also in right part
                s = m + 1;
            } else if (k < arr[m]) {
                e = m - 1;
            } else if (k > arr[m]) {
                s = m + 1;
            }
        }


        return new int[]{firstIndex, lastIndex};
    }
}
