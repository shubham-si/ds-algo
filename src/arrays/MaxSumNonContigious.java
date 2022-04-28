package arrays;

// handles negative
public class MaxSumNonContigious {

    private static Integer recursiveDp(int[] arr, int i, int length, int dp[]) {
        if (length == 1) {
            return arr[0];
        }
        if (i >= length) {
            return 0;
        }
        if (dp[i] > 0) {
            return dp[i];
        }

        dp[i] = Math.max(arr[i],
                    Math.max(arr[i] + recursiveDp(arr, i+2, length, dp), // include arr[i]
                            recursiveDp(arr, i+1, length, dp))            // exclude arr[i]
                );
        return dp[i];
    }

    private static Integer iterativeDp(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }

        int dp[] = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);

        for(int i = 2; i< arr.length ; i++) {
            dp[i] = Math.max(arr[i],
                    Math.max(arr[i] + dp[i-2], // inclusive
                            dp[i-1]));         // exclusive
        }
        return dp[arr.length - 1];
    }

    private static Integer spaceOptimizedSolution(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }

        int prev_prev = arr[0];                // i - 2
        int prev = Math.max(arr[0], arr[1]);   // i - 1

        for(int i = 2; i < arr.length ; i++) {
            int temp = Math.max(arr[i],
                    Math.max(arr[i] + prev_prev, // inclusive ((i-2) + arr[i])
                            prev));         // exclusive (i-1)
            prev_prev = prev;
            prev = temp;
        }
        return prev;
    }

    public static void main(String ...args) {
        int arr[]={2,5,1,4,7,2}; // {5,7} = 12
        System.out.println("recursive "+ recursiveDp(arr, 0, arr.length, new int[arr.length]));
        System.out.println("iterative "+ iterativeDp(arr));
        System.out.println("space optimized "+ spaceOptimizedSolution(arr));
    }
}
