package arrays;

public class KadaneAlgo {

	public static void maxSumSubarray(int[] arr) {
		int s = 0, sum_so_far = 0, max_sum = Integer.MIN_VALUE;
		int start=0, end=0;

		for(int i=0; i< arr.length; i++) {
			sum_so_far += arr[i];

			// max sub sequence range
			if(sum_so_far > max_sum) {
				max_sum = sum_so_far;
				start = s;
				end = i;
			}

			// resetting s_idx & sum_so_far as it'll not give maximum: [ adding current arr[i] leads sum < 0 ]
			if(sum_so_far < 0) {
				sum_so_far = 0;
				s = i + 1;
			}
		}

		System.out.println("st = "+start+" ed = "+end+ " sum = "+ max_sum);
	}


	public static void main(String []args) {
		int[] arr = new int[]{-2, -3, 4, -1, -2, 1, 5, -3};
		maxSumSubarray(arr);
		// st = 2 ed = 6 sum = 7
	}
}
