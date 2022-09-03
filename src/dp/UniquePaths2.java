package dp;

import java.util.*;

// https://leetcode.com/problems/unique-paths-ii/
public class UniquePaths2 {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }

        int dp[][] = new int[m][n];
        Arrays.stream(dp).forEach(a -> Arrays.fill(a, -1));

        return recursiveWay(m - 1, n - 1, dp, obstacleGrid);
    }
    int recursiveWay(int i , int j, int[][]dp, int[][] obstacleGrid) {
        if ( i < 0 || j < 0) {
            return 0;
        }

        if (obstacleGrid[i][j] == 1) {
            return 0;
        }


        if (i == 0 && j == 0) {
            return 1;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int up = recursiveWay(i - 1, j, dp, obstacleGrid);
        int left = recursiveWay(i, j - 1, dp, obstacleGrid);

        return dp[i][j] = up + left;
    }

    public int dpUniquePathsWithObstacles2d(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }

        int dp[][] = new int[m][n];

        for (int i = 0 ; i < m ; i++) {
            for (int j = 0 ; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                    continue;
                }

                int up = 0, left = 0;
                if (i > 0) {
                    up = dp[i-1][j];
                }
                if (j > 0) {
                    left = dp[i][j-1];
                }

                dp[i][j] = up + left;
            }
        }

        return dp[m-1][n-1];
    }
}
