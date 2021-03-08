package arrays;

public class SmallestSumSubArray {

	public static void smallestSumSubArray(int[] arr) {
		int s = 0, sum_so_far = 0, min_sum = Integer.MAX_VALUE;
		int start=0, end=0;

		for(int i=0; i< arr.length; i++) {
			sum_so_far += arr[i];
			
			if(sum_so_far < min_sum) {
				min_sum = sum_so_far;
				start = s;
				end = i;
			}
			
			if(sum_so_far > 0) {
				sum_so_far = 0;
				s = i + 1;
			}
		}
		
		System.out.println("st = "+start+" ed = "+end+ " sum = "+ min_sum);			
	}
	
	
	public static void main(String []args) {
		int[] arr = new int[]{3, -4, 2, -3, -1, 7, -5};
		smallestSumSubArray(arr);
		// {-4, 2, -3, -1} = -6
	}
}
