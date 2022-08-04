package dp;

import java.util.*;
import java.lang.*;

public class MinimumSumMatrixWithKLeastSum
{

    static int init(int arr[][], int target) {
        int n = arr.length;
        int[][][] dp = new int[n][target + 1][target + 1];
        Arrays.stream(dp).forEach(b -> Arrays.stream(b).forEach(a -> Arrays.fill(a, -1)));
        return find(n - 1, arr, target, target, dp);
    }

    static int find(int idx, int arr[][], int ltarget, int rtarget, int [][][]dp) {
        if (idx == 0) {
            if ((ltarget == 0 && rtarget == 0)) {
                return 0;
            }
            return (arr[0][1] >= ltarget && arr[0][2] >= rtarget) ? arr[0][0] : (int) 1e9;
        }

        if (ltarget < 0 || rtarget < 0) {
            return (int) 1e9;
        }

        if(dp[idx][ltarget][rtarget] != -1) {
            return dp[idx][ltarget][rtarget];
        }

        int noSelect = find(idx - 1, arr, ltarget, rtarget, dp);
        int bothSelect = arr[idx][0] + find(idx - 1, arr, ltarget - arr[idx][1], rtarget - arr[idx][2], dp);

        return dp[idx][ltarget][rtarget] = Math.min(bothSelect, noSelect);
    }


    public static void main (String[] args) throws java.lang.Exception
    {
        // int [][]arr = {{16,0,0}, {1,0,0}, {2,0,1}, {1,1,1}, {4,1,0}};
        // int [][]arr = {{4,1,1}, {2,0,1}, {1,1,0}};
         int [][]arr = {{1,3,1}, {2,0,0}, {3,0,0}, {4,1,1}, {5,2,4}};
        int target = 5;
        System.out.println(init(arr , target));
    }
}
