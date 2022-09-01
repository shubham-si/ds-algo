package dp;

import java.util.Arrays;

public class CoinChangeWays {

    int coinChangeWays(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount+1];
        Arrays.stream(dp).forEach(a -> Arrays.fill(a, -1));

        return coinChangeWaysRecursive(n-1 , coins, amount, dp);
    }

    int coinChangeWaysRecursive(int idx, int[] coins, int amount, int[][] dp) {
        if (idx == 0) {
            return (amount % coins[0] == 0) ? 1 : 0;
        }

        if(dp[idx][amount] != -1) {
            return dp[idx][amount];
        }

        int ntk = coinChangeWaysRecursive(idx - 1, coins, amount, dp);
        int tk = 0;

        if (coins[idx] <= amount) {
            tk = coinChangeWaysRecursive(idx, coins, amount - coins[idx], dp);
        }

        return dp[idx][amount] = ntk + tk;
    }

    int coinChangeWaysTwoD(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount+1];

        for(int t = 0 ; t<= amount; t++) {
            // those divisible factors
            dp[0][t] = (t % coins[0]) == 0  ? 1 : 0;
        }

        for(int i = 1; i< n; i++) {
            for(int t = 0 ; t<= amount; t++) {
                int ntk = dp[i - 1][t];
                int tk = 0;

                if (coins[i] <= t) {
                    tk = dp[i][t - coins[i]];
                }

                dp[i][t] = ntk + tk;
            }
        }

        return dp[n-1][amount];

    }

    public int changeSpaceOptimized(int amount, int[] coins) {
        int n = coins.length;
        int[]prev = new int[amount+1];
        int[]curr = new int[amount+1];

        for(int t = 0 ; t <= amount; t++) {
            prev[t] = (t % coins[0]) == 0  ? 1 : 0;
        }

        for(int i = 1; i< n; i++) {
            for(int t = 0 ; t<= amount; t++) {
                int ntk = prev[t];
                int tk = 0;

                if (coins[i] <= t) {
                    tk = curr[t - coins[i]];
                }

                curr[t] = ntk + tk;
            }
            prev = curr;
        }

        return prev[amount];

    }
}