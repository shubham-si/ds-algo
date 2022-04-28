package arrays;

import java.util.Stack;

public class LargestAreaHistogram {
    public static int largestRectangleArea(int[] arr) {
        Stack<Integer> stk = new Stack<>();
        int maxArea = 0;
        // smaller element to the left
        stk.push(-1);
        for (int i = 0; i< arr.length; i++ ) {
            while(!(stk.peek() == -1) && arr[i] <= arr[stk.peek()]) {
                int height = arr[stk.peek()];
                stk.pop();
                // next smaller heights to left and right
                maxArea = Math.max(maxArea, (i - stk.peek() - 1)*height);
            }
            stk.push(i);
        }

        while(!(stk.peek() == -1)) {
            int height = arr[stk.peek()];
            stk.pop();
            // next smaller heights to left and right
            maxArea = Math.max(maxArea, (arr.length - stk.peek() - 1) * height);
        }
        return maxArea;
    }

    public static void main(String ...args) {
        System.out.println(largestRectangleArea(new int[]{2}));
    }
}
