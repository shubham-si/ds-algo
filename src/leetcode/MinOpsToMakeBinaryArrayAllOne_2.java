package leetcode;

// https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/description/
// Minimum Number of K Consecutive Bit Flips

/*
You are given a binary array nums and an integer k.

A k-bit flip is choosing a subarray of length k from nums and simultaneously changing every 0 in the subarray to 1, and every 1 in the subarray to 0.

Return the minimum number of k-bit flips required so that there is no 0 in the array. If it is not possible, return -1.

A subarray is a contiguous part of an array.

    Example 1:

    Input: nums = [0,1,0], k = 1
    Output: 2
    Explanation: Flip nums[0], then flip nums[2].
    Example 2:

    Input: nums = [1,1,0], k = 2
    Output: -1
    Explanation: No matter how we flip subarrays of size 2, we cannot make the array become [1,1,1].
    Example 3:

    Input: nums = [0,0,0,1,0,1,1,0], k = 3
    Output: 3
    Explanation:
    Flip nums[0],nums[1],nums[2]: nums becomes [1,1,1,1,0,1,1,0]
    Flip nums[4],nums[5],nums[6]: nums becomes [1,1,1,1,1,0,0,0]
    Flip nums[5],nums[6],nums[7]: nums becomes [1,1,1,1,1,1,1,1]
 */

public class MinOpsToMakeBinaryArrayAllOne_2 {

    // O(t: n), O(s:n)
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int times = 0, ans = 0;
        int[] isFlipped = new int[n];
        for(int i = 0; i < n; i++) {
            if (i >= k) {
                // is from prev. subarray, is there any flipping done at (i-k)th index that increased times
                // since that increased times is not valid for current subarray
                // if so reset times by -1
                times -= isFlipped[i - k];
            }
            if ((nums[i] == 0 && times % 2 == 0) || (nums[i] == 1 && times % 2 == 1)) {
                // flipping required
                if (i + k > n) {
                    // there's no subarray of k size to meet
                    return -1;
                }
                // do flipping and mark isFlipped[i]
                ans++;
                times++;
                isFlipped[i] = 1;
            }
        }
        return ans;
    }

    // O(t: n), O(s:1: inPlace)
    public int minKBitFlipsSpaceOptimizedInPlace(int[] nums, int k) {
        int n = nums.length;
        int times = 0, ans = 0;
        // logic: instead of flipped[i] do --> add +2 to nums[i] -> nums[i] + 2
        // when resetting (nums[i] > 1) do --> sub -2 to nums[i] -> nums[i] - 2
        // so that original array remains same

        for(int i = 0; i < n; i++) {
            if ( i >= k && nums[i-k] > 1) {
                // flipped carried from prev. subarray done at (i-k)th index that increased times
                // since that increased times is not valid for current subarray
                // reset times by -1
                // and reset [i-k] to original array value
                nums[i-k] -= 2;
                times--;
            }

            if ((nums[i] == 0 && times % 2 == 0) || (nums[i] == 1 && times % 2 == 1)) {
                // flipping required
                if (i + k > n) {
                    // subarray of k size is not meeting
                    return -1;
                }
                // do flipping and mark isFlipped[i]
                ans++;
                times++;
                nums[i] += 2;
            }
        }

        // resetting last subarray first index
        if (nums[n - k] > 1) {
            nums[n - k] -= 2;
        }
        return ans;
    }

}
