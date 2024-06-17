package leetcode;

// https://leetcode.com/problems/first-missing-positive/
/*
Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.

You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.

    Example 1:

    Input: nums = [1,2,0]
    Output: 3
    Explanation: The numbers in the range [1,2] are all in the array.
    Example 2:

    Input: nums = [3,4,-1,1]
    Output: 2
    Explanation: 1 is in the array but 2 is missing.
    Example 3:

    Input: nums = [7,8,9,11,12]
    Output: 1
    Explanation: The smallest positive integer 1 is missing.
 */

public class FindFirstMissingPositiveNumber {

    // using: cyclic sort
    // O(n), O(1)
    private static int findFirstMissingPositive(int[] arr) {
        int i = 0;
        // 1 <= x[i] <=n and
        // if (x[x[i] - 1] != x[i]): not at proper place > swap(x[i], x[x[i] - 1)) > move x[i] to it's proper place
        // else i++
        while (i < arr.length) {
            int correctIdxForArri = arr[i] - 1;
            if ((1 <= arr[i] && arr[i] <= arr.length) && (arr[i] != arr[correctIdxForArri])) {
                //swap(arr[i], arr[arr[i] - 1]);
                // move arr[i] to its proper place idx: (arr[i] - 1) > array is 0 based idx
                swap(arr, i, correctIdxForArri);
            } else {
                i++;
            }
        }
        // 3, 2, 3 , 2
        i = 0;
        // if any x[i] - 1 != i > return (i + 1) otherwise (n + 1)
        while (i < arr.length) {
            if(arr[i] - 1 != i) {
                return i + 1;
            }
            i++;
        }

        return arr.length + 1;
    }

    private static void swap(int []array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
    public static void main(String[] args) {
        System.out.println(findFirstMissingPositive(new int[]{10,2,5,10,9,1,1,4,3,7}));
    }
}
