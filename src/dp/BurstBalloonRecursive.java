package dp;

import java.util.Arrays;

public class BurstBalloonRecursive {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int []appendedNums = new int[n + 2];

        appendedNums[0] = 1;
        for(int i = 1; i <= n ; i++){
            appendedNums[i] = nums[i-1];
        }
        appendedNums[n + 1] = 1;

        // [1...arr...1]
        int [][]dp = new int[n + 2][n + 2];
        Arrays.stream(dp).forEach(a -> Arrays.fill(a, -1));

        return recursive(1, n, appendedNums, dp);
    }

    // length based
    int recursive(int i, int j, int []arr, int[][] dp) {

        // if window collapse
        if (i > j) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        int maxCost = Integer.MIN_VALUE;

        // for all values in range [i...j] length
        for(int k = i ; k <=j ; k++) {
            int cost = recursive(i, k-1, arr, dp) + recursive(k+1, j, arr, dp) + arr[i-1] * arr[k] * arr[j+1];
            maxCost = Math.max(maxCost, cost);
        }

        return dp[i][j] = maxCost;
    }
}
