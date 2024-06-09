package arrays;

import java.util.*;

// (i,j) index should be different

class CountPairsWithSum {
    int getPairsCount(int[] arr, int n, int target) {
        Map<Integer,Integer> map = new HashMap();

        int t_c = 0 ;
        for(int i = 0; i < n; i++) {
            // contains pair entry => add the frequency of that entry : (a+b) = target
            if(map.containsKey(target-arr[i])) {
                t_c += map.get(target-arr[i]);
            }
            // update frequency
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        return t_c;
    }
}

/*
 t = 10
 1 5 5 5 5 7

    for i = 0 => get(10-1) = 0
        i = 1 => get(10-5) = 0 > {1:1, 5:1}
        i = 2 => get(10-5) = 1 > (1,2) > {1:1, 5:2}
        i = 3 => get(10-5) = 2 > (1,3),(2,3) > {1:1, 5:3}
        i = 4 => get(10-5) = 3 > (1,4), (2,4), (3,4) > {1:1, 5:4} ...
o/p = 1 + 2 + 3 = 6
 */
