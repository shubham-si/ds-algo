package dp;

// likewise MCM (length based)
public class BurstBalloon2d {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];

        arr[0] = 1;
        for (int i = 1; i <= n; i++) {
            arr[i] = nums[i - 1];
        }
        arr[n + 1] = 1;

        // [1...arr...1]
        int[][] dp = new int[n + 2][n + 2];

        // pick window-length loop
        for (int l = 1; l <= n; l++) {

            // number of subarray's of len('l')
            // from 1 because appended '1' both sides of array
            // or starting index of window
            for (int i = 1; i <= (n - l + 1); i++) {
                int j = i + l - 1;                              // end index of current window [i...j]

                int maxCost = Integer.MIN_VALUE;

                // choosing 'k'th index element to burst last
                for (int k = i; k <= j; k++) {
                    int cost = dp[i][k - 1] +                       // left points
                            arr[i - 1] * arr[k] * arr[j + 1] +      // points gained when burst 'kth' element in [i...j]
                            dp[k + 1][j];                           // right points

                    maxCost = Math.max(maxCost, cost);
                }

                dp[i][j] = maxCost;
            }
        }

        return dp[1][n];            // since nums is modified front and back with(1)

    }
}
