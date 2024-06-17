package arrays;

import java.util.Stack;

public class TrappingWater {

    // O(n), O(n)
    static long trappingWaterPrePostArray(int arr[], int n) {
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

    // O(n), O(n)
    static long trappingWaterUsingStack(int arr[], int n) {
        int res = 0, i = 0;
        // stack of lower heights
        Stack<Integer> stack = new Stack();

        while(i < n) {
            while (!stack.empty() && arr[i] > arr[stack.peek()]) {
                int topTower = arr[stack.pop()];
                if (stack.empty()) {
                    // nothing to compare
                    break;
                }
                // excluding ith index
                int w = (i - 1) - stack.peek();
                int h = Math.min(arr[stack.peek()], arr[i]) - topTower;
                res += w * h;
            }
            stack.push(i);
            i++;
        }
        return res;
    }

    // r:O(n), s:O(1)
    static long trappingWaterTwoPointer(int arr[], int n) {
        int res = 0, l = 0, r = n - 1;
        int lmax = 0 ,rmax = 0; // between these
        while (l < r) {
            if (arr[l] < arr[r]) {
                if (lmax < arr[l]) {
                    lmax = arr[l];
                } else {
                    res = res + (lmax - arr[l]);
                }
                l++;
            } else {
                if (rmax < arr[r]) {
                    rmax = arr[r];
                } else {
                    res = res + (rmax - arr[r]);
                }
                r--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int []arr = {4,2,3,5,6}; // {3,2,2,3,1,4,3,2,4,2,6};
        System.out.println(TrappingWater.trappingWaterTwoPointer(arr, arr.length));
        System.out.println(TrappingWater.trappingWaterUsingStack(arr, arr.length));
        System.out.println(TrappingWater.trappingWaterPrePostArray(arr, arr.length));
    }
}
