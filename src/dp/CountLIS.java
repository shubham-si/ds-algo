package dp;

import java.util.Arrays;

public class CountLIS {
    public int findNumberOfLIS(int[] nums) {
        int n= nums.length;
        int dp[] = new int[n];
        int cnt[] = new int[n];

        Arrays.fill(dp , 1);
        Arrays.fill(cnt , 1);

        int maxi = 1;

        for(int i = 0; i < n; i++) {
            for (int prev = 0 ; prev < i ; prev++) {

                if (nums[prev] < nums[i] && dp[i] < (1 + dp[prev])) {
                    dp[i] = 1 + dp[prev];
                    cnt[i] = cnt[prev];
                }

                else if (nums[prev] < nums[i] && dp[i] == (1 + dp[prev])) {
                    // if already dp[i] == (1 + dp[prev])
                    // then add the prev_counts to current[i]
                    cnt[i] += cnt[prev];
                }
            }

            if (maxi < dp[i]) {
                maxi = dp[i];
            }
        }

        int c = 0;

        for(int i = 0; i < n; i++) {
            if (dp[i] == maxi) {
                c += cnt[i];
            }
        }

        return c;
    }
}