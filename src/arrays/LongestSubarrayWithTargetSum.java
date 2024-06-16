package arrays;

import java.util.*;

public class LongestSubarrayWithTargetSum {

    // Note: target can be 0 also
    public static int longestSubArrayWithTargetSum(int []arr, int target) {
        int max_l = 0;
        // <prefix_sum, last_idx>
        Map<Integer, Integer> prefixMap = new HashMap<>();
        int prefix_sum = 0;
        for (int i = 0; i < arr.length; i++) {
            prefix_sum += arr[i];

            if(prefix_sum == target) {
                max_l = i + 1;
            }

            Integer previousIndexForThisPrefixSum = prefixMap.get(prefix_sum - target);
            if (previousIndexForThisPrefixSum != null) {
                max_l = Math.max(max_l, i - previousIndexForThisPrefixSum);
            } else {
                prefixMap.put(prefix_sum, i);
            }
        }
        return max_l;
    }
    public static void main(String[] args) {
        System.out.println(longestSubArrayWithTargetSum(new int[]{15, -2, 2, -8, 1, 7, 10, 23}, 0));
        System.out.println(longestSubArrayWithTargetSum(new int[]{1, 0, 3}, 0));
        System.out.println(longestSubArrayWithTargetSum(new int[]{10, -4, -3, -2, -1}, 0));
        System.out.println(longestSubArrayWithTargetSum(new int[]{1, 2, 3}, 0));
    }
}
