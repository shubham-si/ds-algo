package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// https://leetcode.com/problems/my-calendar-i
/*

Input
[[], [10, 20], [15, 25], [20, 30]]

Output
[null, true, false, true]
 */
public class Calendar1 {
    // O(n2*logn)
    TreeMap<Integer,Integer> map = new TreeMap<>();
    boolean bookUsingCount(int start, int end) {
        // +1 start
        map.put(start, map.getOrDefault(start, 0) + 1);
        // -1 to end
        map.put(end, map.getOrDefault(end, 0) - 1);

        // s += (+1:s | -1:e)
        int s=0;
        for(Map.Entry<Integer,Integer> entry: map.entrySet()) {
            s = s + entry.getValue();
            // overlapping
            if (s > 1) {
                map.put(start, map.get(start) -1);
                map.put(end, map.get(end) + 1);
                return false;
            }
        }
        return true;
    }



    // O(nlogN)
    TreeMap<Integer,Integer> treeMap = new TreeMap<>();
    boolean book(int start, int end) {
        Integer leftStart = treeMap.floorKey(start);
        Integer rightStart = treeMap.ceilingKey(start);

        // s < e1
        if(leftStart!=null && start < treeMap.get(leftStart)) {
            return false;
        }
        // OR

        // e > s1
        if(rightStart!=null && end > rightStart) {
            return false;
        }
        treeMap.put(start,end);
        return true;
    }
}
