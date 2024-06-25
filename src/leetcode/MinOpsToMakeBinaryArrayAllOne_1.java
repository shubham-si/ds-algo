package leetcode;

// https://leetcode.com/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-ii/description/

/*
You are given a
binary array
 nums.

You can do the following operation on the array any number of times (possibly zero):

Choose any index i from the array and flip all the elements from index i to the end of the array.
Flipping an element means changing its value from 0 to 1, and from 1 to 0.

Return the minimum number of operations required to make all elements in nums equal to 1.

    Example 1:

    Input: nums = [0,1,1,0,1]

    Output: 4

    Explanation:
    We can do the following operations:

    Choose the index i = 1. The resulting array will be nums = [0,0,0,1,0].
    Choose the index i = 0. The resulting array will be nums = [1,1,1,0,1].
    Choose the index i = 4. The resulting array will be nums = [1,1,1,0,0].
    Choose the index i = 3. The resulting array will be nums = [1,1,1,1,1].
    Example 2:

    Input: nums = [1,0,0,0]

    Output: 1

    Explanation:
    We can do the following operation:

    Choose the index i = 1. The resulting array will be nums = [1,1,1,1].
 */

// O(n): expected
public class MinOpsToMakeBinaryArrayAllOne_1 {
    public int minOperations(int[] nums) {
        // times: say from ith , kth element is flipped x:times
        // say num[k] -> 1 (if x is odd num[k:1] -> num[k:0] and if x is even num[k:1] -> num[k:1]
        // say num[k] -> 0 (if x is odd num[k:0] -> num[k:1] and if x is even num[k:0] -> num[k:0]
        int times = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if ((nums[i] == 0 && times % 2 == 0) || (nums[i] == 1 && times % 2 == 1)) {
                // flip num[i]th and increment times for next k+1 ... n
                times++;
            }
        }
        return times;
    }
}
