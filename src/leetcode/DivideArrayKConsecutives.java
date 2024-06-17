package leetcode;

import java.util.*;

// https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
//  https://leetcode.com/problems/hand-of-straights/
/*
    Given an array of integers nums and a positive integer k, check whether it is possible to divide this array into sets of k consecutive numbers.

    Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
    Output: true
    Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].

    Input: nums = [3,2,1,2,3,1], k = 3
    Output: true
    Explanation: Array can be divided into [1,2,3] , [1,2,3]
 */
public class DivideArrayKConsecutives {
    public static boolean isPossibleDivide(int[] nums, int k) {
        // concept build a map[number]: freq
        // 1:2, 3:2, 2:2  => [1,2,3]:1 , [1,2,3]:1
        // sort the numbers
        // decrement frequency and check for next consecutive in map
        // whose freq > 0 else return false
        Map<Integer,Integer> map = new HashMap<>();

        // frequency
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 1,1,2,2,3,3,9,9,10,10
        Arrays.sort(nums);

        for(int num : nums) {

            // already processed while checking for consecutive seq. >> skip that element
            // eg., 1,1,2,2 and  k = 2 => i:0 {1,2}, i:1{1,2} when i:2 or i:3 => (2) is already processed thus check below
            if (map.containsKey(num)) {
                for(int i = num ; i < (num + k); i++) {
                    // 'k' consecutive numbers should be present
                    if(!map.containsKey(i)) {
                        return false;
                    }

                    map.put(i, map.get(i) - 1);
                    if (map.get(i) == 0) {
                        map.remove(i);
                    }
                }
            }

        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPossibleDivide(new int[]{5,1,0,6,4,5,3,0,8,9}, 2));
    }
}
