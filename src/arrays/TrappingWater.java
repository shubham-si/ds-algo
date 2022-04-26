package arrays;

public class TrappingWater {
    static long trappingWater(int arr[], int n) {
        int lh[] = new int[n];
        int rh[] = new int[n];

        lh[0] = arr[0];
        rh[n - 1] = arr[n-1];
        for(int i=1; i<n; i++) {
            lh[i] = Math.max(arr[i], lh[i-1]);
        }

        for(int i=n-2; i>0; i--) {
            rh[i] = Math.max(arr[i], rh[i+1]);
        }
        long count = 0;

        for(int i = 1 ;i < n - 1;i++) {
            count += Math.min(lh[i], rh[i]) - arr[i];
        }
        return count;
    }
}
