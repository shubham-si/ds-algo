package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Two_Three_Four_Sum {

    List<List<Integer>> twoSum(int[] arr, int target, int n) {
        Arrays.sort(arr);
        List<List<Integer>> list = new ArrayList<>();

        int s = 0, e = n - 1;

        while (s < e) {
            if ((arr[s] + arr[e]) == target) {
                list.add(Arrays.asList(arr[s], arr[e]));
                s++;
                e--;

                // avoid duplicates
                while (s < e && arr[s] == arr[s - 1]) s++;
                while (s < e && arr[e] == arr[e + 1]) e--;

            } else if ((arr[s] + arr[e]) < target) {
                s++;
            } else {
                e--;
            }
        }
        return list;
    }


    /*
        a + b + c = t
        (a + b) = (t - c)
        for each i >  (t - c:arr[i]) find two sum(a,b)
        O(n2 + nlogn: sort)
        Avoid duplicates
     */
    List<List<Integer>> threeSum(int[] arr, int target, int n) {
        Arrays.sort(arr);
        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {

            int s = (i + 1),                // a
                e = arr.length - 1,         // b
                t = (target - arr[i]);      // since 'i' is c

            // TWO SUM
            while (s < e) {
                if ((arr[s] + arr[e]) == t) {
                    list.add(Arrays.asList(arr[i], arr[s], arr[e]));
                    s++;
                    e--;

                    // avoid duplicates
                    while (s < e && arr[s] == arr[s - 1]) s++;
                    while (s < e && arr[e] == arr[e + 1]) e--;

                } else if ((arr[s] + arr[e]) < t) {
                    s++;
                } else {
                    e--;
                }
            }

            // avoid duplicates
            while (i + 1 < n && arr[i + 1] == arr[i]) i++;

        }
        return list;
    }

    /*
    a + b + c + d = t
    (a + b) = (t - c -d)
    for each i(t-c), j (t-c-d) > find two sum(a,b)
    O(n3 + nlogn: sort)
    Avoid duplicates
 */
    List<List<Integer>> fourSum(int[] arr, int target, int n) {
        Arrays.sort(arr);
        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int target1 = target - arr[i];                      // (t - c)

            for(int j = i + 1; j < n; j++) {
                int     s = j + 1,                              // a
                        e = n - 1,                              // b
                        t = target1 - arr[j];                  // ((t-c) - d)

                // TWO SUM
                while (s < e) {
                    if ((arr[s] + arr[e]) == t) {
                        list.add(Arrays.asList(arr[i], arr[j], arr[s], arr[e]));
                        s++; e--;

                        // avoid duplicates
                        while(s < e && arr[s] == arr[s - 1]) s++;
                        while(s < e && arr[e] == arr[e + 1]) e--;

                    } else if((arr[s] + arr[e]) < t) {
                        s++;
                    } else {
                        e--;
                    }
                }
                // avoid duplicates
                while (j + 1 < n && arr[j + 1] == arr[j]) j++;
            }
            // avoid duplicates
            while (i + 1 < n && arr[i + 1] == arr[i]) i++;
        }

        return list;
    }

}
