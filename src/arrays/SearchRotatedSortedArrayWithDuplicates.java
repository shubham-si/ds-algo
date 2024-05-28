package arrays;

public class SearchRotatedSortedArrayWithDuplicates {

    public int search(int[] arr, int k) {
        int s = 0 , e = arr.length - 1;

        while (s <= e) {
            int m = s + (e - s) / 2;

            if (arr[m] == k) {
                return m;
            }

            // edge case:
            // arr[start] == arr[mid] could be possible
            // the first half could be out of order (i.e. not in the ascending order)
            // eg., [3, 1, 2, 3, 3, 3, 3]
            // In that case, it is guaranteed that arr[high] also equal to arr[mid] > arr[mid] == arr[low] == arr[high]
            // adjust search space by updating positions by 1
            if (arr[s] == arr[m] && arr[e] == arr[m]) {
                s++;
                e--;
                continue;
            }

            // decide where to go

            if(arr[s] <= arr[m]) {
                // left part is sorted
                if (k >= arr[s] && k < arr[m]) {
                    e = m - 1;
                } else {
                    s = m + 1;
                }
            } else {
                if (k > arr[m] && k <= arr[e]) {
                    s = m + 1;
                } else {
                    e = m - 1;
                }
            }
        }

        return -1;
    }
}
