package arrays;
/*

    Input:
    n = 2, arr1[] = [10, 12]
    m = 3, arr2[] = [5 18 20]

    Output:
    arr1[] = [5 10]
    arr2[] = [12 18 20]

    Explanation:
    After merging two sorted arrays
    we get 5 10 12 18 20.
 */
public class MergeTwoSortedArrays {
    // O(m * n)
    // O(1) space
    //Function to merge the arrays.
    public static void merge(long arr1[], long arr2[], int n, int m)
    {
        int i = 0, j = 0;
        while(i < n) {
            if (arr1[i] <= arr2[j]) {
                /* do nothing */
            } else {
                // arr[i] > arr[j]
                swap(arr1, i, arr2, j);         // min at arr[i] <-- arr[j]
                insert(arr2 , j, m);            // move arr2[j] to it's correct place in sorted arr2 moving right
            }
            i++;
        }
    }

    public static void swap(long arr1[], int i, long arr2[], int j) {
        long temp = arr1[i];
        arr1[i] = arr2[j];
        arr2[j] = temp;
    }

    // move element arr[j] at it's right place to the right in A2 by shifting elements
    public static void insert(long arr2[], int j, int m) {
        // move arr[j]
        while(j < (m - 1)  && arr2[j] > arr2[j + 1]) {
            swap(arr2, j , arr2, j+1);
            j++;
        }
    }

    public static void main(String[] args)
    {

        long ar1[] = { 1, 5, 9, 10, 15, 20 };
        long ar2[] = { 2, 3, 8, 13 };
        int m = ar1.length;
        int n =ar2.length;
        merge(ar1, ar2, m, n);

        System.out.print("After Merging \nFirst Array: ");
        for (int i = 0; i < m; i++)
            System.out.print(ar1[i]+ " ");
        System.out.print("\nSecond Array: ");
        for (int i = 0; i < n; i++)
            System.out.print(ar2[i]+ " ");
    }

    // O(log(m + n) * (n + m))
    // O(1) space
    // gap method --> T(n/2) + T(n/4) .... T(1)
    public static void mergeLogN() {

    }
}
