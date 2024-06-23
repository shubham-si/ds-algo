package leetcode;

// https://leetcode.com/problems/contiguous-array/

/*
Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.

Example 1:

Input: nums = [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
Example 2:

Input: nums = [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 */

import arrays.LongestSubarrayWithTargetSum;

public class MaximumLengthContiguosArrayWithEqual01s {
    // concept: make all 0's -> -1 and apply longestSubArrayWithZeroSum
    public int findMaxLength(int[] arr) {
        // arr -> if arr[i] == 0 > arr[i] = -1
        return LongestSubarrayWithTargetSum.longestSubArrayWithTargetSum(arr, 0);
    }

}
