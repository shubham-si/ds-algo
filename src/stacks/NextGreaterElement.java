package stacks;

import java.util.*;

public class NextGreaterElement {
    public static void main(String ...args) {
        int arr[] = {2, 5, 11, 4, 7, 2, 4};

        System.out.println(nextGreaterElement(arr)); // {5,11,-1,7,-1,4,-1}
        System.out.println(nextGreaterElementCircular(arr, arr.length)); // {5,11,-1,7,11,4,5}
    }

    private static List<Integer> nextGreaterElement(int[] arr) {
        Integer[] nge = new Integer[arr.length];
        Stack<Integer> stk = new Stack<>();

        for(int i = 0 ; i < arr.length ; i++) {
            while(!stk.empty() && arr[i] > arr[stk.peek()]) {
                nge[stk.peek()] = arr[i];
                stk.pop();
            }
            stk.push(i);
        }

        while(!stk.empty()) {
            nge[stk.peek()] = -1;
            stk.pop();
        }
        return Arrays.asList(nge);
    }

    // O(2n) + while(O(2n)) :> these may times push and pop operations will take place across for above loop

    private static List<Integer> nextGreaterElementCircular(int[] arr, int n) {
        Integer[] nge = new Integer[n];
        Stack<Integer> stk = new Stack<>();

        // for circular same added to last  {2, 5, 11, 4, 7, 2, 4} + {2, 5, 11, 4, 7, 2, 4}
        // {2, 5, 11, 4, 7, 2, 4, 2, 5, 11, 4, 7, 2, 4}

        // o/p => {5,11,-1,7,11,4,5}

        // approach --> (n + k) % n ==> k  stated (k < n)

        for(int i = 0 ; i < (2 * n - 1) ; i++) {
            while(!stk.empty() && arr[i % n] > arr[stk.peek()]) {
                nge[stk.peek()] = arr[i % n];
                stk.pop();
            }
            // push upto < n as if pushed further (virtual elements index) leads to same calculation again (n + k:(0...n-1)) % n ie., (without circular array)s
            if (i < n)
                stk.push(i);
        }

        while(!stk.empty()) {
            nge[stk.peek()] = -1;
            stk.pop();
        }
        return Arrays.asList(nge);
    }
}
