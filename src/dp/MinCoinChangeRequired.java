package dp;

import java.util.Arrays;

public class MinCoinChangeRequired {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount+1];
        Arrays.stream(dp).forEach(a -> Arrays.fill(a, -1));

        int ans = coinChangeRecursive(n-1 , coins, amount, dp);

        return  ans >= (int) 1e9 ? -1 : ans;
    }

    int coinChangeRecursive(int idx, int[] coins, int amount, int[][] dp) {
        if (idx == 0) {
            // (int) 1e9 :> to avoid overflow (if return MAX_VALUE)
            return (amount % coins[0] == 0) ? (amount / coins[0]) : (int) 1e9;
        }

        if(dp[idx][amount] != -1) {
            return dp[idx][amount];
        }

        int ntk = coinChangeRecursive(idx - 1, coins, amount, dp);
        int tk = Integer.MAX_VALUE;

        if (coins[idx] <= amount) {
            tk = 1 + coinChangeRecursive(idx, coins, amount - coins[idx], dp);
        }

        return dp[idx][amount] = Math.min(ntk , tk);
    }

    public int coinChange2D(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount+1];

        for (int t = 0; t <= amount; t++) {
            dp[0][t] = (int) 1e9;

            if (t % coins[0] == 0) {
                dp[0][t] = t / coins[0];
            }
        }

        for(int i = 1; i< n; i++) {

            for(int t = 0 ; t<= amount; t++) {

                int ntk = dp[i - 1][t];
                int tk = Integer.MAX_VALUE;

                if (coins[i] <= t) {
                    tk = 1 + dp[i][t - coins[i]];
                }

                dp[i][t] = Math.min(ntk , tk);
            }
        }

        int ans = dp[n-1][amount];
        return  ans >= (int) 1e9 ? -1 : ans;
    }

    public int spaceOptimized(int[] coins, int amount) {
        int n = coins.length;
        int[]prev = new int[amount+1];
        int[]curr = new int[amount+1];

        for(int t = 0 ; t<= amount; t++) {
            prev[t] = (t % coins[0]) == 0  ? (t / coins[0]) : (int) 1e9;
        }

        for(int i = 1; i< n; i++) {

            for(int t = 0 ; t<= amount; t++) {

                int ntk = prev[t];
                int tk = Integer.MAX_VALUE;

                if (coins[i] <= t) {
                    tk = 1 + curr[t - coins[i]];
                }

                curr[t] = Math.min(ntk , tk);
            }
            prev = curr;
        }


        int ans = prev[amount];
        return  ans >= (int) 1e9 ? -1 : ans;
    }
}
