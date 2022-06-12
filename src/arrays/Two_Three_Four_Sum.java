package arrays;

import java.util.*;

public class Two_Three_Four_Sum {


    List<List<Integer>> twoSum(int []arr, int target) {
        Arrays.sort(arr);
        return null;
    }


    /*
        a + b + c = t
        (a + b) = (t - c)
        for each i >  (t - c:arr[i]) find two sum(a,b)
        O(n2 + nlogn)
        Avoid duplicates
     */
    List<List<Integer>> threeSum(int []arr, int target) {
        Arrays.sort(arr);
        return null;
    }

    /*
    a + b + c + d = t
    (a + b) = (t - c -d)
    for each i(t-c), j (t-c-d) > find two sum(a,b)
    O(n3 + nlogn)
    Avoid duplicates
 */
    List<List<Integer>> fourSum(int []arr, int target) {
        Arrays.sort(arr);
        return null;
    }

}
