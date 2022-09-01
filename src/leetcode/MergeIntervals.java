package leetcode;

import java.util.*;

//https://leetcode.com/problems/merge-intervals/
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        List<int[]> list = new ArrayList<>();

        for (int curr = 0; curr < intervals.length; curr++) {
            if (list.isEmpty() || intervals[curr][0] > list.get(list.size() - 1)[1]) {
                // strictly non overlapping >> when curr.start() > last.end()
                list.add(intervals[curr]);
            } else {
                // curr.start() <= prev.end() : overlapping
                int []last = list.get(list.size() - 1);

                //merge the intervals and update with new timeline
                last[1] = Math.max(last[1], intervals[curr][1]);
                list.set(list.size() - 1, last);
            }
        }

        return list.toArray(new int[list.size()][]);
    }
}
