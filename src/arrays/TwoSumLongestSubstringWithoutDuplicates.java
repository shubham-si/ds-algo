package arrays;

import java.util.*;

public class TwoSumLongestSubstringWithoutDuplicates {

    // dababccabc --> abc

    // O(2n)
    // abcdefggh

    // move 'l' 1 at a time until all distinct
    int longestSubstringWithTwoNComplexity(String str) {
        int res = 0;

        int l=0, r=0;
        Set<String> set = new HashSet<>();

        while (r < str.length()) {
            // remove and move 'l', while set contains 'str(r)'
            while (set.contains(str.charAt(r))) {
                set.remove(str.charAt(l));
                l++;
            }

            res = Math.max(res, r - l + 1);
            set.add(String.valueOf(str.charAt(r)));
            r++;

        }
        return res;
    }

    // O(N)
    // abcdefggh

    // move 'l' 1 at a time until all distinct
    int longestSubstringWithNComplexity(String str) {
        int res = 0;

        int l=0, r=0;
        Map<String, Integer> map = new HashMap<>();

        while (r < str.length()) {
            // if map contains 'str(r)', jump l to (stored_idx + 1)
            if (map.containsKey(str.charAt(r))) {
                // so that l will not go to it's left
                l = Math.max(l, map.get(str.charAt(r)) + 1);
            }

            res = Math.max(res, r - l + 1);

            // update the newer index for str(r)
            map.put(String.valueOf(str.charAt(r)), r);
            r++;

        }
        return res;
    }
}
