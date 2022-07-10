package dp;

import arrays.LowerUpperBound;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class LIS_BinarySearch {

    // T(nlogn)
    public int binarySearchWithLIS(int[] arr, int n) {

        // list will contain sorted index|ele
        List<Integer> list = new ArrayList<>();

        int lenLIS = 1;
        list.add(arr[0]);

        for (int i = 1 ; i < n; i++) {

            if (arr[i] > list.get(lenLIS - 1)) {
                list.add(arr[i]);
                lenLIS++;

            } else {
                // since set is sorted set
                // lower_bound(ele): returns first_index: arr[index] >= ele
                int ceil = LowerUpperBound.lower_bound((Integer[]) list.toArray(), lenLIS, arr[i]);
                list.set(ceil, arr[i]);
            }

        }

        return lenLIS;

    }

    // using inbuilt set.ceiling() ~ lower_bound()
    public int lengthOfLIS(int[] arr) {
        int n = arr.length;

        // list containing sorted index|ele
        TreeSet<Integer> set = new TreeSet<>();


        int len = 1;
        set.add(arr[0]);

        for (int i = 1 ; i < n; i++) {

            if (arr[i] > set.last()) {

                len++;

            } else {
                // since set is sorted set
                Integer ceil = set.ceiling(arr[i]);

                set.remove(ceil);

            }
            set.add(arr[i]);
        }

        return len;

    }

    static void printLIS(int []arr, int n) {
        int MinLastIndexOfLength[] = new int[n];
        int par[] = new int[n];

        for(int i=0; i < n ; i++) {
            par[i] = i;
        }

        // T[i]: stores the index of minvalue of the last index of subsequences of length 'i' of given arr
        // e.g., T[2]: {3,4} | {-1,2} => last min(4,2) : 2 ==> T[2]: index(2)
        MinLastIndexOfLength[0] = 0;

        int lastMaxIndex = 0;

        for (int i = 1 ; i < n ; i++) {
            if (arr[i] > arr[MinLastIndexOfLength[lastMaxIndex]]) {

               par[i] = MinLastIndexOfLength[lastMaxIndex];

               lastMaxIndex++;
               MinLastIndexOfLength[lastMaxIndex] = i;

            } else {
                // since set is sorted set
                // lower_bound(ele): returns first_index: arr[index] >= ele
                int ceil = lower_bound( MinLastIndexOfLength, (lastMaxIndex + 1), arr[i], arr);
                MinLastIndexOfLength[ceil] = i;

                par[i] = (ceil == 0) ? i : MinLastIndexOfLength[(ceil - 1)];
            }
        }

        System.out.println("\nLength " + (lastMaxIndex + 1));

        // prints increasing subsequence in reverse order.
        List<Integer> list = new ArrayList<>();
        list.add(MinLastIndexOfLength[lastMaxIndex]);

        int index = MinLastIndexOfLength[lastMaxIndex];

        while(par[index] != index) {
            index = par[index];
            list.add(index);
        }

        Collections.reverse(list);
        list.stream().forEach(e -> System.out.print(arr[e] + " "));
    }

    // returns first_index: arr[index] >= ele
    static int lower_bound(int[]T, int n, int ele, int []arr) {

        // Note that here high index is set to n instead of n - 1.
        int low = 0, high = n;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (ele <= arr[T[mid]]) {
                high = mid;             // instead of mid - 1, as high can be the answer
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    public static void main(String []args) {
        int arr[] = {3, 4, -1, 5, 8, 2, 3, 12, 7, 9, 10};
        printLIS(arr, arr.length);
    }

}
