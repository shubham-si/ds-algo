package dp;

    /*
        Space Optimization

        If we closely look the relation,

        dp[i][j] = dp[i-1][j] + dp[i][j-1])

        We see that we only need the previous row and column, in order to calculate dp[i][j].
        Therefore we can space optimize it.

        Initially, we can take a dummy row ( say prev) and initialize it as 0.
        Now the current row(say temp) only needs the previous row value and the current row’s value in order to calculate dp[i][j].
    */

public class UniquePaths2SpaceOptimzed {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }

        /*
            dp[i][j] = dp[i-1][j] + dp[i][j-1])
            We see that we only need the previous row and column, in order to calculate dp[i][j].
        */
        int prev[] = new int[n];

        for (int i = 0 ; i < m ; i++) {
            // initialization here: since before column in same row(dp[i][j-1]) : (like edist problem)
            int curr[] = new int[n];

            for (int j = 0 ; j < n; j++) {

                if (obstacleGrid[i][j] == 1) {
                    curr[j] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    curr[j] = 1;
                    continue;
                }

                int up = 0, left = 0;
                if (i > 0) {
                    up = prev[j];
                }
                if (j > 0) {
                    left = curr[j-1];
                }

                curr[j] = up + left;
            }
            prev = curr;
        }

        return prev[n-1];
    }
}
