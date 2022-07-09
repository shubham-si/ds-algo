package arrays;

// 3 5 1 4 , k = 1 => 3
//  5 6 7 8 9 10 1 2 3 => k = 10 => 5

public class SearchRotatedArray {
    // non duplicates
    int search(int arr[], int l, int h, int key)
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
}
