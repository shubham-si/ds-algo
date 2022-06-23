package dp;

public class KnapSack01 {

    static int knapSack(int W, int wt[], int val[], int n)
    {
        int dp[][] = new int[n][W + 1];

        // base: (index == 0 and wt[0] <= W) ==> return val[0]
        // w >= wt[0]
        for(int w = wt[0]; w <= W ; w++) {
            dp[0][w] = val[0];
        }


        for (int i = 1; i < n; i++) {

            for(int w = 0; w <= W; w++) {

                int notTake = dp[i-1][w];
                int take = Integer.MIN_VALUE;

                if (w >= wt[i]) {
                    take = val[i] + dp[i-1][w - wt[i]];
                }

                dp[i][w] = Math.max(notTake, take);
            }
        }

        return dp[n-1][W];
    }

    static int knapSackSpaceOptimized(int W, int wt[], int val[], int n)
    {

        int prev[] = new int[W + 1];

        // base
        // index == 0 and wt[0] <= W ==> return val[0]
        for(int w = wt[0]; w <= W ; w++) {           // w >= wt[0] we can only give max val[0] (or say only element)
            prev[w] = val[0];
        }
       /*
        we don’t want values from the right, we can start filling this new row from the right rather than the left.
        Now here is the catch, if we are filling from the right and at any time we need the previous row’s value of the leftward columns only,

        why do we need to have two rows in the first place?
        We can use a single row and overwrite the new computed values on itself in order to store it.

        we iterate weight from right side and just overriding (w - w[i]) left side of w, thus creating prev for next (i + 1)
       */

        for (int i = 1; i < n; i++) {

            for(int w = W; w >= 0; w--) {
                int notTake = prev[w];
                int take = Integer.MIN_VALUE;

                if (w >= wt[i]) {
                    take = val[i] + prev[w - wt[i]];
                }

                prev[w] = Math.max(notTake, take);
            }
        }

        return prev[W];
    }

    static int knapSackRecur(int idx, int W, int wt[], int val[], int [][]dp) {
        if (idx == 0 && wt[0] <= W) {
            return val[0];
        }

        if (idx == 0 || W == 0) {
            return 0;
        }

        if(dp[idx][W] != 0) {
            return dp[idx][W];
        }

        int notTake = knapSackRecur(idx - 1, W, wt, val, dp);
        int take = Integer.MIN_VALUE;

        if (wt[idx] <= W) {
            take = val[idx] + knapSackRecur(idx - 1, W - wt[idx], wt, val, dp);
        }

        dp[idx][W] = Math.max(take, notTake);
        return dp[idx][W];
    }
}
