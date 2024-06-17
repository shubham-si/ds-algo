package leetcode;

import java.util.*;

// https://leetcode.com/problems/find-all-duplicates-in-an-array/description/
/*
Given an integer array nums of length n where all the integers of nums are in the range [1, n]
and each integer appears once or twice, return an array of all the integers that appears twice.

You must write an algorithm that runs in O(n) time and uses only constant extra space.
 */
public class FindAllDuplicates {

    // Ques: how to mark element visited in their index while preserving the element
    // make the arr[(abs(ele) - 1)] => -1 * arr[(abs(ele) - 1)] > preserved the element also
    // by marking that idx **-ve** so that marking/visiting is done
    // if a number is duplicate it will try to make its (num - 1)th idx -ve to +ve again and that number is repeated

    // t: O(n), s: O(1)
    public static List<Integer> findDuplicates(int[] arr) {
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < arr.length; i++) {
            int correctIdxForArri = Math.abs(arr[i]) - 1;
            if(arr[correctIdxForArri] < 0) {
                // already marking done
                list.add(correctIdxForArri + 1);
            }
            // marking that this index number is visited
            arr[correctIdxForArri] = -arr[correctIdxForArri];
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(findDuplicates(new int[]{10,2,5,10,9,1,1,4,3,7}));
    }
}
