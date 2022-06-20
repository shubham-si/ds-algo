package leetcode;

import java.util.TreeMap;

//triple booking not allowed
// https://leetcode.com/problems/my-calendar-ii/
public class Calendar2 {
    TreeMap<Integer,Integer> map;
    public Calendar2() {
        map = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        map.put(start, map.getOrDefault(start,0) + 1);
        map.put(end, map.getOrDefault(end,0) - 1);

        int c = 0;
        for(Integer freq: map.values()) {
            c += freq;
            if (c >= 3) {
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
}