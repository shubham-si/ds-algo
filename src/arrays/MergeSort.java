package arrays;

import java.util.Arrays;

public class MergeSort {

    static void merge(int []arr, int l ,int r, int m) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i];
                i++;
            }
            else {
                arr[k++] = R[j];
                j++;
            }
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k++] = L[i];
            i++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k++] = R[j];
            j++;
        }

    }

    static void mergeSort(int []arr, int l ,int r) {
        if( l < r) {
            int mid = l + (r - l) / 2;
            mergeSort(arr, l , mid);
            mergeSort(arr, mid+1 , r);
            merge(arr, l , r, mid);
        }
    }

    public static void main(String []args) {
        int []arr = {8,4,6,2,11,1,3,2};
        mergeSort(arr, 0 ,7);
        Arrays.stream(arr).forEach(System.out::println);
    }

}
