package arrays;

import java.util.Stack;

public class LargestAreaHistogram {
    public static int largestRectangleArea(int[] arr) {
        Stack<Integer> stk = new Stack<>();
        int maxArea = 0;
        for(int i = 0; i< arr.length; i++ ) {
            while(!stk.empty() && arr[i] <= arr[stk.peek()]) {
                int height = arr[stk.pop()];

                if (stk.empty()) {
                    maxArea = Math.max(maxArea, i * height);
                } else {
                    // next smaller heights to left and right
                    maxArea = Math.max(maxArea, (i - stk.peek() - 1)*height);
                }
            }
            stk.push(i);
        }
        while(!stk.empty()) {
            int height = arr[stk.pop()];

            if (stk.empty()) {
                maxArea = Math.max(maxArea, arr.length * height);
            } else {
                // next smaller heights to left and right
                maxArea = Math.max(maxArea, (arr.length - stk.peek() - 1)*height);
            }
        }
        return maxArea;
    }

    public static void main(String ...args) {
        System.out.println(largestRectangleArea(new int[]{4,2}));
    }
}
