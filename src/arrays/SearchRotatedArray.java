package arrays;

// 4 5 1 3 , k = 1 => 3
//  5 6 7 8 9 10 1 2 3 => k = 10 => 5

public class SearchRotatedArray {
    // non duplicates
    int withNoDuplicates(int arr[], int l, int h, int key)
    {
        int st = 0, e = arr.length - 1, mid;

        while(st <= e) {
            mid = st + (e - st) / 2;
            if (arr[mid] == key) {
                return mid;
            }

            if (arr[l] <= arr[mid]) {
                // that means [l..mid]: left part is sorted subarray
                if (key >= arr[l] && key < arr[mid]) {
                    // answer lies here [l ... mid]
                    e = mid - 1;
                } else {
                    // answer not lies within this subarray move to right subarray
                    st = mid + 1;
                }
            } else {
                // that means [mid..r]: right part is sorted subarray
                if (key > arr[mid] && key <= arr[e]) {
                    // answer lies here [l ... mid]
                    st = mid + 1;
                } else {
                    // answer not lies within this subarray move to left subarray
                    e = mid - 1;
                }
            }
        }
        return -1;
    }

    // with duplicates
    public int withDuplicates(int[] arr, int k) {
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
