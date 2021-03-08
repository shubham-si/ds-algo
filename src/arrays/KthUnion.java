package arrays;

import java.util.Arrays;

// expected complexity log(k)

public class KthUnion {
	
	// considering minimum(A.length) always
	static int Kth(int A[], int lenA, int B[], int lenB, int k) {
		
		if (((A.length + B.length) < k) || k < 1) {
			return -1;
		}
		
		// considering first array in the argument has minimum(<=) length
		if (lenA > lenB) {
			return Kth(B,lenB,A,lenA,k);
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
			return Kth(temp,lenA-i,B,lenB,k-i);
		}
		// B[j-1] <= A[i-1]
		int temp[] = Arrays.copyOfRange(B, j, lenB);
		return Kth(A,lenA,temp,lenB-j,k-j);
	}
	
	public static void main(String...strings) {
		int A[] = {1,12,13,84};
		int B[] = {0,2,88};
		System.out.print(Kth(A,A.length,B,B.length,5));
	}
}