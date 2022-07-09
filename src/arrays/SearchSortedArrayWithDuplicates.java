package arrays;

public class SearchSortedArrayWithDuplicates {

    public int search(int[] arr, int k) {
        int s = 0 , e = arr.length - 1;

        while (s <= e) {
            int m = s + (e - s) / 2;

            if (arr[m] == k) {
                return m;
            }

            // edge case: when ele at 's' and 'e' are same
            // eg., [2,5,6,0,0,1,2]
            // creates confusion as in which direction to follow
            // thus .,
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
