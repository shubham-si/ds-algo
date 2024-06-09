package stacks;

import java.util.Stack;

// https://leetcode.com/problems/largest-rectangle-in-histogram/submissions/689018269/
public class LargestAreaHistogram {
    public static int largestRectangleArea(int[] arr) {
        // stack of higher heights
        Stack<Integer> stk = new Stack<>();
        int maxArea = 0;
        // smaller element to the left
        stk.push(-1);
        // assuming (index: -1) represents an empty stack
        for(int i = 0; i < arr.length; i++ ) {
            while(!(stk.peek() == -1) && arr[i] < arr[stk.peek()]) {              // incoming element is < stack.top()
                int height = arr[stk.pop()];

                // i => (i - (-1: empty stack) - 1) i.e., single element present on stack
                // next smaller heights to left and right (not included)
                maxArea = Math.max(maxArea, (i - 1 - stk.peek()) * height);
            }
            stk.push(i);
        }
        while(!(stk.peek() == -1)) {
            int height = arr[stk.pop()];
            // i => (i - (-1: empty stack) - 1) i.e., single element present on stack
            // next smaller heights to left and right (not included)
            maxArea = Math.max(maxArea, (arr.length - 1 - stk.peek()) * height);
        }
        return maxArea;
    }

    public static void main(String ...args) {
        System.out.println(largestRectangleArea(new int[]{4,2}));
    }
}
