package dp;

import java.util.*;

// variation of LIS (https://www.codingninjas.com/codestudio/problems/divisible-set_3754960)
/*


    1 16 7 8 4  => {1,4,8,16}

    1 2 5       => {1,2} or {1,5}

    5           => {5}

    3 3 3       => {3,3,3}

    if i ... j ...k
    j % i == 0 and k % j == 0  =>so:=> k % i == 0
 */
public class LargestDivisbleSubset {
    public static List<Integer> divisibleSet(int arr[]) {

        // sort the array
        // --> if i ... j ...k
        // j % i ==0 and k % j == 0 => k % i == 0
        Arrays.sort(arr);

        int n = arr.length;
        int dp[] = new int[n];
        int par[] = new int[n];

        Arrays.fill(dp, 1);

        // LIS with %
        int maxL = 1;
        int lastIdx = 0;

        for(int i = 0 ; i < n ; i++) {
            par[i] = i;

            for(int prev = 0; prev < i ; prev++) {

                if (arr[i] % arr[prev] == 0 && dp[i] < (1 + dp[prev])) {
                    dp[i] = 1 + dp[prev];
                    par[i] = prev;
                }
            }

            if (maxL < dp[i]) {
                maxL = dp[i];
                lastIdx = i;
            }
        }

        List<Integer> list = new ArrayList<>();
        list.add(arr[lastIdx]);

        while(par[lastIdx] != lastIdx) {
            lastIdx = par[lastIdx];
            list.add(arr[lastIdx]);
        }

        Collections.reverse(list);

        return list;
    }
}
