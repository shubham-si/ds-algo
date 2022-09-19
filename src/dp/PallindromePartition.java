package dp;

public class PallindromePartition {

    boolean isPallindrome(int i ,int j, String s) {
        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    // recursive
    int minPartition(int i, int n, String s, int[] dp) {
        if (i == n) {
            return 0;
        }

        if (dp[i] != -1) {
            return dp[i];
        }

        int minCut = Integer.MAX_VALUE;

        for (int j = i ; j < n; j++) {
            if (isPallindrome(i,j,s)) {
                int cost = 1 + minPartition(j + 1, n, s, dp);
                minCut = Math.min(minCut, cost);
            }
        }

        return dp[i] = minCut;
    }

    // tabulation
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];

        dp[n] = 0;

        for(int i = n - 1; i >= 0; i--) {
            int minCut = Integer.MAX_VALUE;

            for (int j = i ; j < n; j++) {

                if (isPallindrome(i,j,s)) {
                    int cost = 1 + dp[j + 1];
                    minCut = Math.min(minCut, cost);
                }
            }
            dp[i] = minCut;
        }

        return dp[0] - 1;
    }
}
