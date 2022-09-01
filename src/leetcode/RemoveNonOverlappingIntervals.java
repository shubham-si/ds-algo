package leetcode;

// https://leetcode.com/problems/non-overlapping-intervals/
/*
    Given an array of intervals intervals where intervals[i] = [starti, endi],
    return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

    Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
    Output: 1
            [1,2][2,3] : no overlap (0)
    Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 */

import java.util.Arrays;

public class RemoveNonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

        int prev = 0 , count = 0;
        for (int curr = 1; curr < intervals.length; curr++) {
            // incoming start <= prev ends
            if (intervals[curr][0] < intervals[prev][1]) {
                count++;
                // if curr end time <= prev end time
                // remove prev i.e., end = min(prev_end, curr_end) so chances of overlapping decreases
                if(intervals[curr][1] <= intervals[prev][1]) {
                    prev = curr;
                }
            } else {
                prev = curr;
            }
        }
        return count;
    }
}



/*

    https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/

    https://leetcode.com/problems/number-of-islands/
    Maximum Sized Island
    Shortest Path : Graph Traversals
 */