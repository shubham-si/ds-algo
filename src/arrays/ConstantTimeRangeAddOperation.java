package arrays;

/*
Given an array of size N which is initialized with all zeros.
We are given many ranges add queries, which should be applied to this array.
We need to print the final updated array as our result.

Examples:
    N = 6
    Arr = [0, 0, 0, 0, 0, 0]
    rangeUpdate1 [0, 2], add 100

    Arr = [100, 100, 100, 0, 0, 0]
    rangeUpdate1 [1, 5], add 100

    Arr = [100, 200, 200, 100, 100, 100]
    rangeUpdate1 [2, 3], add 100

    Arr = [100, 200, 300, 200, 100, 100]
    Which is the final updated array.
 */

import java.util.Arrays;

public class ConstantTimeRangeAddOperation {
    public static void main(String ...args) {
        int arr[] = new int[6];
        update(arr, 0 , 2, 100);
        // Arrays.stream(arr).forEach(System.out::println);
        update(arr, 1 , 5, 100);
        // Arrays.stream(arr).forEach(System.out::println);
        update(arr, 2 , 3, 100);
        doPrefixSum(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    // concept add [val to l and -val to 'r + 1']
    // reason: when we do prefix sum then after 'r' (+val) will compensate -val = 0
    private static void update(int[] arr, int l, int r, int val) {
        arr[l] +=  val;
        if (r < arr.length - 1) {
            arr[r + 1] -=  val;
        }
    }

    private static void doPrefixSum(int[] arr) {
        for(int i = 1 ; i < arr.length ; i++) {
            arr[i] = arr[i] + arr[i-1];
        }
    }
}
