package arrays;

public class MaxProductSubarray {

    public static void main(String ...args) {
        int []array = {-2,0,-2,4,0,3,-3,-1,-5};
        System.out.println(getMaxProdSubArray(array));
    }

    private static int getMaxProdSubArray(int[] array) {
        int max_end_so_far = 1, min_end_so_far = 1, max = Integer.MIN_VALUE;
        int start = 0 , s = 0, e = 0;
        // min_end_so_far will tell me what is the minimum (-ve) product found till, so if suppose -ve number is ahead can give +ve max product
        // 1 defines reset condition, if either behaviour breaks
        for(int val: array) {
            if (val > 0) {
                max_end_so_far = Integer.max(max_end_so_far , max_end_so_far * val);
                // say all numbers are positive upto 'i' then min_end_so_far should be 1 only since didn't occur any -ve value
                // otherwise it will lead to false answer
                min_end_so_far = Integer.min(1 , min_end_so_far * val);
            }else if (val == 0) {
                max_end_so_far = 1;
                min_end_so_far = 1;
            }else {                     // val < 0
                int temp = max_end_so_far;
                max_end_so_far = Integer.max(1 , min_end_so_far * val);
                // here min_end_so_far = max_end_so_far(+ve) * val(-ve)
                min_end_so_far = temp * val;
            }
            if (max < max_end_so_far) {
                max = max_end_so_far;
            }
        }
        max_end_so_far = 1; min_end_so_far = 1; max = Integer.MIN_VALUE;

        // 2nd solution (works with all negative numbers also [-ve,0])
        for(int val: array) {
            int temp = max_end_so_far;
            max_end_so_far = Integer.max(Integer.max(val, max_end_so_far * val), min_end_so_far * val);
            min_end_so_far = Integer.min(Integer.min(val, temp * val), min_end_so_far * val);
            max = Integer.max(max, max_end_so_far);
        }
        return max;
    }
}
