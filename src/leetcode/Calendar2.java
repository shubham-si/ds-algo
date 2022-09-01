package leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

//triple booking not allowed
// https://leetcode.com/problems/my-calendar-ii/
public class Calendar2 {
    TreeMap<Integer,Integer> map;
    public Calendar2() {
        map = new TreeMap<>();
    }

    // worst: O(logN*(1.1 +1.2+1.3....1.(n-1))) => O(n2*logn)
    public boolean book(int start, int end) {
        map.put(start, map.getOrDefault(start,0) + 1);
        map.put(end, map.getOrDefault(end,0) - 1);

        int c = 0;
        for(Integer freq: map.values()) {
            c += freq;
            if (c > 2) {
                map.put(start, map.get(start) - 1);
                map.put(end, map.get(end) + 1);

                if(map.get(start) == 0 ){
                    map.remove(start);
                }
                if(map.get(end) == 0 ){
                    map.remove(end);
                }
                return false;
            }
        }
        return true;
    }


    // worst: O(logN*(N)) => O(n*logn)

    // possible allowed bookings
    List<List<Integer>> bookings;

    // strictly overlapping intervals  ==> for each (s1,e1) <> (s,e) ==> if (max(s1,s):a < min(e1,e):b) => (a,b)
    TreeMap<Integer,Integer> overlaps;

    public boolean bookLinear(int start, int end) {
        Integer left = overlaps.floorKey(start);
        Integer right = overlaps.ceilingKey(start);

        // leftStart <= start < map.get(leftStart)
        if(left != null &&  start < overlaps.get(left)) {
            return false;
        }

        // start <= rightStart < end
        if(right != null && end > right) {
            return false;
        }

        // find strictly overlaps between the bookings and current event
        for(List<Integer> list: bookings) {
            int startOverlap = Math.max(list.get(0), start);
            int endOverlap = Math.min(list.get(1), end);

            if(startOverlap < endOverlap) {
                overlaps.put(startOverlap, endOverlap);
            }
        }

        bookings.add(Arrays.asList(start,end));
        return true;
    }

}