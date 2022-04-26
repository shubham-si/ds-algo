package arrays;

import java.util.Arrays;

// expected complexity log(k)

public class KthUnion {
	
	// considering minimum(A.length) always --> lenA < lenB
	static int KthRecursive(int A[], int lenA, int B[], int lenB, int k) {
		
		if (((A.length + B.length) < k) || k < 1) {
			return -1;
		}
		
		// considering first array in the argument has minimum(<=) length
		if (lenA > lenB) {
			return KthRecursive(B,lenB,A,lenA,k);
		}
		
		// len(A) is 0 then (k-1)th from B is the kth smallest
		if (lenA == 0) {
			return B[k-1];
		}
		
		// if only one element to process then min(0th from both arrays) is the kth smallest
		if (k == 1) {
			return Math.min(A[0], B[0]);
		}
		
		// if lenA + lenB is the kth then max(nth from both arrays) is the kth smallest as it comes at the last
		if ((lenA + lenB) == k) {
			return Math.max(A[lenA-1], B[lenB-1]);
		}
		
		// taking invariant as (i+j <= k)
		int i = Math.min(lenA, k/2);
		int j = Math.min(lenB, k/2);	// or (j = Math.min(lenB, k-i))
		
		// number of elements skipped from A or B based on condition
		
		if (A[i-1] < B[j-1]) {
			// then 0...i-1 elements can be skipped since kth will not lie in this range
			int temp[] = Arrays.copyOfRange(A, i, lenA);
			return KthRecursive(temp,lenA-i,B,lenB,k-i);
		}
		// B[j-1] <= A[i-1]
		int temp[] = Arrays.copyOfRange(B, j, lenB);
		return KthRecursive(A,lenA,temp,lenB-j,k-j);
	}

	static int KthIterative(int arr1[], int arr2[], int k) {
		int n = arr1.length, m = arr2.length;
		int i = 0, j = 0, k_index = 0, ele = -1;
		// merge two sorted arrays approach
		while( i < n && j < m) {
			if(arr1[i] <= arr2[j]) {
				ele = arr1[i];
				i++;
			} else if(arr2[j] < arr1[i]) {
				ele = arr2[j];
				j++;
			}
			k_index++;
			if(k_index == k) {
				break;
			}
		}
		// case k: when either of one array is finished
		if (k_index != k) {
			if (i == n) {
				// arr1 finished
				ele = arr2[k - k_index];
			} else {
				// arr2 finished
				ele = arr1[k - k_index];
			}
		}
		return ele;
	}
	
	public static void main(String...strings) {
		int A[] = {1,12,13,84};
		int B[] = {0,2,88};
		A = new int[]{5 ,33 ,55 ,65 ,76 ,80 ,90};
		B = new int[]{10 ,13 ,14 ,15 ,15 ,22 ,27 ,32 ,34 ,36 ,36 ,37 ,39 ,40 ,42 ,45 ,49 ,50 ,50 ,53 ,56 ,56 ,57 ,61 ,65 ,66 ,70 ,70 ,71 ,74 ,78 ,84 ,87 ,90 ,91 ,94 ,94, 96, 99};

		System.out.println(KthRecursive(A,A.length,B,B.length,39));
		System.out.println(KthIterative(A,B,39));
	}
}