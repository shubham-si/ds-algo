package arrays;

import java.util.*;

/*

Input:
N=5
sum=-10
    arr[] = { 10, 2, -2, -20, 10 }
Output:  3
Explanation:
Subarrays with sum -10 are:
    [10, 2, -2, -20], [2, -2, -20, 10]
    and [-20, 10].
 */

// idea is to find sum(subarray's) or "<prefix sums> difference that equals to k" (target sum)
public class CountSubarraySumK {
    public static int subArraySum(int[] arr, int n, int sum)
    {
        int c = 0, s = 0;
        // prefix sum's frequency
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0 ; i<n; i++) {
            s = s + arr[i];
            if (s == sum ) {
                // means the local subarray is 0 sum subarray
                c++;
            }
            // left to "cumulative sum" how many subarray's present with (s-sum)
            // eg .. -->  k = 7 => prefix sum array -->  14(p) ..... 14(q) ...... 21(i)
            // 14(2) means that > (p+1) --- i & also (q+1) --- i => sums(7)
            // also between  p & q --> we can say it is a 0-sum subarray that's why prefix sum repeated
            if (map.containsKey(s - sum)) {
                c += map.get(s - sum);
            }
            if(!map.containsKey(s)) {
                map.put(s, 0);
            }
            map.put(s, map.get(s) + 1);

        }
        return c;
    }
}