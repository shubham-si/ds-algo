package arrays;

//https://leetcode.com/problems/binary-subarrays-with-sum
//related-prob: https://leetcode.com/problems/count-number-of-nice-subarrays/
/*
Given a binary array nums and an integer goal, return the number of non-empty sub-arrays with a sum goal.

A subarray is a contiguous part of the array.

    Example 1:

    Input: nums = [1,0,1,0,1], goal = 2
    Output: 4
    Explanation: The 4 sub arrays are bolded and underlined below:
    [1,0,1,0,1]
    [1,0,1,0,1]
    [1,0,1,0,1]
    [1,0,1,0,1]
    Example 2:

    Input: nums = [0,0,0,0,0], goal = 0
    Output: 15
 */

// M1: prefix sum way
// M2: all +ve and binary > 2 pointer's way > O(t:2N) + O(s:1)
public class CountSumSubarraysInBinaryArray {
    public int numSubarraysWithSum(int[] arr, int goal) {
        // cnt_sub_with_sum <= goal
        int nsWithAtleastGoal = numSubArraysWithAtleastGoal(arr , goal);
        // cnt_sub_with_sum <= (goal - 1)
        int nsWithAtleastGoalMinusOne = numSubArraysWithAtleastGoal(arr , goal - 1);
        // nsWithAtleastGoal - nsWithAtleastGoalMinusOne => cnt_sub_with_sum = goal
        return nsWithAtleastGoal - nsWithAtleastGoalMinusOne;
    }

    public int numSubArraysWithAtleastGoal(int[] arr, int goal) {
        if (goal < 0) return 0;
        int l = 0, r = 0, s = 0, c = 0;
        while(r < arr.length) {
            s += arr[r];
            while(s > goal) {
                s = s - arr[l];
                l++;
            }
            // count sub array's b/w [l:r]
            c += (r - l + 1);
            r++;
        }
        return c;
    }
}
