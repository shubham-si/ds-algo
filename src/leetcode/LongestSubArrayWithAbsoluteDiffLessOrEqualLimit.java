package leetcode;
import trees.PairUtil;

import java.util.*;

//https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit
/*

Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray
such that the absolute difference between any two elements of this subarray is less than or equal to limit.

    Example 1:

    Input: nums = [8,2,4,7], limit = 4
    Output: 2
    Explanation: All subarrays are:
    [8] with maximum absolute diff |8-8| = 0 <= 4.
    [8,2] with maximum absolute diff |8-2| = 6 > 4.
    [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
    [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
    [2] with maximum absolute diff |2-2| = 0 <= 4.
    [2,4] with maximum absolute diff |2-4| = 2 <= 4.
    [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
    [4] with maximum absolute diff |4-4| = 0 <= 4.
    [4,7] with maximum absolute diff |4-7| = 3 <= 4.
    [7] with maximum absolute diff |7-7| = 0 <= 4.
    Therefore, the size of the longest subarray is 2.
    Example 2:

    Input: nums = [10,1,2,4,7,2], limit = 5
    Output: 4
    Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
    Example 3:

    Input: nums = [4,2,2,2,4,4,2,2], limit = 0
    Output: 3

 */

/*
    fact: b/w [l:r] if abs_diff(minEle, maxElement) <= l  --> all elements in l:r will have differences <= l only
    using min nad max heap to get this values faster and index also for next starting lth position.
 */

// O(N*logN)
public class LongestSubArrayWithAbsoluteDiffLessOrEqualLimit {
    public int longestSubarray(int[] arr, int limit) {
        /*
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((a,b) -> a.getKey().compareTo(b.getKey()));
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((a,b) -> b.getKey().compareTo(a.getKey()));
                                                           OR
         */

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(Map.Entry.comparingByKey());
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>(Map.Entry.comparingByKey(Collections.reverseOrder()));

        int l = 0, r = 0, mxl = 0;
        while (r < arr.length) {

            minHeap.offer(PairUtil.Of(arr[r], r));
            maxHeap.offer(PairUtil.Of(arr[r], r));

            while(Math.abs(minHeap.peek().getKey() - maxHeap.peek().getKey()) > limit) {
                // jump to next index
                l = Math.min(minHeap.peek().getValue() , maxHeap.peek().getValue()) + 1;

                // remove all no use pairs whose idx < l
                while(minHeap.peek().getValue() < l) {
                    minHeap.poll();
                }
                while(maxHeap.peek().getValue() < l) {
                    maxHeap.poll();
                }
            }
            mxl = Math.max(mxl, r - l + 1);
            r++;
        }
        return mxl;
    }
}
