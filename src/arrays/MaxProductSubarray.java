package arrays;

// https://leetcode.com/problems/maximum-product-subarray/
public class MaxProductSubarray {

    public static void main(String ...args) {
        int []array = {-2,0,-2,4,0,3,-3,-1,-5};                     // [3,-3,-1] => 9
        System.out.println(getMaxProdSubArray(array));
    }

    private static int getMaxProdSubArray(int[] array) {
        int max_end_so_far = 1, min_end_so_far = 1, max = Integer.MIN_VALUE;
        // min_end_so_far will tell me what is the minimum (-ve) product found till, so if suppose -ve number is ahead can give +ve max product

        for(int val: array) {
            int temp = max_end_so_far;
            max_end_so_far = Integer.max(Integer.max(val, max_end_so_far * val), min_end_so_far * val);
            min_end_so_far = Integer.min(Integer.min(val, temp * val), min_end_so_far * val);
            max = Integer.max(max, max_end_so_far);
        }
        return max;
    }
}
