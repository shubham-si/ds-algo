package dp;

import java.util.*;

public class LIS {

    // S(n)
    // T(N2)
    public int lengthAndPrintOfLIS1d(int[] arr) {
        int n = arr.length;
        int dp[] = new int[n];

        Arrays.fill(dp, 1);

        int par[] = new int[n];

        // dp[i] <== length of longest LIS ending at 'i'

        int maxLen = 1;
        int lastIdx = 0;

        for (int i = 0 ; i < n; i++) {

            par[i] = i;

            for (int prev = 0 ; prev < i ; prev++) {

                if (arr[prev] < arr[i] && dp[i] < (1 + dp[prev])) {
                    dp[i] = 1 + dp[prev];
                    par[i] = prev;
                }
            }

            if (maxLen < dp[i]) {
                maxLen = dp[i];
                lastIdx = i;
            }
        }

       List<Integer> list = new ArrayList<>();
       list.add(lastIdx);

       while (par[lastIdx] != lastIdx) {
           list.add(par[lastIdx]);
           lastIdx = par[lastIdx];
       }

       Collections.reverse(list);
       list.stream().forEach(System.out::print);

       return maxLen;

    }

    public int lengthOfLIS(int[] arr) {
        int n = arr.length;

        // since prevIdx can be  -1 mapping it to 0th index thus shifting
        int dp[][] = new int[n][n+1];

        Arrays.stream(dp).forEach(a -> Arrays.fill(a, -1));

        // forward movement 0 ---> n
        return lisRecursive(0 , -1, dp, arr, n);
    }

    // forward movement
    // T(n2), S(n2)
    int lisRecursive(int idx, int prevIdx, int dp[][], int[] arr, int n) {
        // forward movement
        if (idx == n) return 0;

        if (dp[idx][prevIdx + 1] != -1) {
            return dp[idx][prevIdx + 1];
        }

        int ntk = 0 + lisRecursive(idx + 1, prevIdx, dp, arr, n);
        int tk = 0;

        if (prevIdx == -1 || arr[idx] > arr[prevIdx]) {
            tk = 1 + lisRecursive(idx + 1, idx, dp, arr, n);
        }

        return dp[idx][prevIdx + 1] = Math.max(tk, ntk);

    }

    public int tabulated2d(int[] arr, int n) {

        int dp[][] = new int[n + 1][n + 1];

        for (int idx = n - 1; idx >= 0 ; idx--) {
            for(int prevIdx = idx - 1; prevIdx >= -1; prevIdx--) {

                int ntk = dp[idx + 1][prevIdx + 1];
                int tk = 0;

                if (prevIdx == -1 || arr[idx] > arr[prevIdx]) {
                    tk = 1 + dp[idx + 1][idx + 1];                // why ? (lisRecursive(idx + 1, idx ?, ...);)
                }

                dp[idx][prevIdx + 1] = Math.max(tk, ntk);
            }
        }

        return dp[0][0];  // (prevIndex = -1) + 1 => 0
    }
}
