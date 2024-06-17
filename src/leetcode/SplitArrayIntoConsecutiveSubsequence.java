package leetcode;
import trees.PairUtil;

import java.util.*;

// https://leetcode.com/problems/split-array-into-consecutive-subsequences

/*
    You are given an integer array nums that is **sorted in non-decreasing order**.

    Determine if it is possible to split nums into one or more subsequences such that both of the following conditions are true:

        Each subsequence is a consecutive increasing sequence (i.e. each integer is exactly one more than the previous integer).
        All subsequences have a length of 3 or more.
        Return true if you can split nums according to the above conditions, or false otherwise.

        Note** >> (without disturbing the relative positions of the remaining elements)

    A subsequence of an array is a new array that is formed from
    the original array by deleting some (can be none) of the elements without disturbing the relative positions of the remaining elements.
    (i.e., [1,3,5] is a subsequence of [1,2,3,4,5] while [1,3,2] is not).

    Input: nums = [1,2,3,3,4,4,5,5]
    Output: true
    Explanation: nums can be split into the following subsequences:
    [[1],[2],[3],3,[4],4,[5],5] --> 1, 2, 3, 4, 5
    [1,2,3,[3],4,[4],5,[5]] --> 3, 4, 5

    Input: nums = [1,2,3,3,4,5]
    Output: true
    Explanation: nums can be split into the following subsequences:
    [[1,2,3],3,4,5] --> 1, 2, 3
    [1,2,3,[3,4,5]] --> 3, 4, 5

    Input: nums = [1,2,3,4,4,5]
    Output: false
    Explanation: It is impossible to split nums into consecutive increasing subsequences of length 3 or more.
 */

public class SplitArrayIntoConsecutiveSubsequence {

    // isPossibleUsingPQueue()
    // isPossibleUsingMaps()

    // Note: each sequence first try to fit into a existing sequence before creating its new sequence

    public boolean isPossibleUsingPQueue(int[] nums) {

        // Pair<st_num, end_num>  ==> length = (end_num - st_num + 1)
        PriorityQueue<Map.Entry<Integer, Integer> > pq = new PriorityQueue<>((a,b) -> {
            if (a.getValue().equals(b.getValue())) {
                // greedy: sort by length if end values are same
                // k = 3 => [1..3],[2..3] => [2,3], [1,3] so that if 4 comes it goes to [2,3]
                int la = a.getValue() - a.getKey() + 1;
                int lb = b.getValue() - b.getKey() + 1;

                return la - lb;
            }
            // else sort pairs by end_value
            return a.getValue() - b.getValue();
        });

        for(int num: nums) {

            while (!pq.isEmpty() && (pq.peek().getValue() + 1) < num) {
                // current pq.peek() bucket is done, since array sorted in non-decreasing order
                Map.Entry<Integer, Integer> peek = pq.poll();
                int lenBucket =  peek.getValue() - peek.getKey() + 1;
                if (lenBucket < 3) {
                    return false;
                }
            }


            if (pq.isEmpty() || pq.peek().getValue() == num) {
                // start a new bucket
                pq.offer(PairUtil.Of(num, num));
            } else {
                // (pq.peek().getValue() + 1) >= num : update end for the peek pair
                // or append element to the top bucket
                Map.Entry<Integer, Integer> peek = pq.poll();
                pq.offer(PairUtil.Of(peek.getKey(), num));
            }

        }

        // leftovers
        while(!pq.isEmpty()) {
            Map.Entry<Integer, Integer> peek = pq.poll();
            int lenBucket =  peek.getValue() - peek.getKey() + 1;
            if (lenBucket < 3) {
                return false;
            }
        }

        return true;
    }

    // **sorted in non-decreasing order**
    public boolean isPossibleUsingMaps(int[] nums) {
        HashMap<Integer, Integer> freq = new HashMap<>();

        // hypothetical map -> which tells current num can be part of some bucket
        HashMap<Integer, Integer> nextPossibleNumberMap = new HashMap<>();

        for(int num: nums) {
            freq.put(num, freq.getOrDefault(num , 0) + 1);
        }

        for(int num: nums) {
            if (freq.get(num) == 0) {
                // this num is already processed and part of some prev bucket
                continue;
            }

            // this num can be part of some previous bucket of len(>=3)
            if (nextPossibleNumberMap.getOrDefault(num, 0) > 0) {

                nextPossibleNumberMap.put(num, nextPossibleNumberMap.get(num) - 1);
                freq.put(num, freq.get(num) - 1);

                // add room for next number possibility
                nextPossibleNumberMap.put(num + 1, nextPossibleNumberMap.getOrDefault(num + 1 , 0) + 1);
            } else {

                // check occurrence for next 3 numbers
                for (int i = num; i < (num + 3); i++) {
                    if (freq.getOrDefault(i, 0) <= 0) {
                        return false;
                    }
                    freq.put(i, freq.get(i) - 1);
                }

                // add room for next number possibility after (num, num + 1, num + 2)
                nextPossibleNumberMap.put(num + 3, nextPossibleNumberMap.getOrDefault(num + 3 , 0) + 1);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // // **sorted in non-decreasing order** only
        System.out.println(new SplitArrayIntoConsecutiveSubsequence().isPossibleUsingMaps(new int[]{1,2,3,3,4,5}));
        System.out.println(new SplitArrayIntoConsecutiveSubsequence().isPossibleUsingMaps(new int[]{1,3,3,2,4,4,5}));
        System.out.println(new SplitArrayIntoConsecutiveSubsequence().isPossibleUsingPQueue(new int[]{1,3,3,2,4,4,5}));
    }
}
