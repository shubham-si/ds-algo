package arrays;

public class QuickSelect {
    public static int kthSmallest(int[] arr, int l, int r, int k)
    {
        // k resides in the range l to r
        if(k > 0 && k <= (r - l + 1)) {
            int pivotIndex = partition(arr, l ,r);
            int count = pivotIndex - l + 1;
            if(count == k) {
                return arr[pivotIndex];
            }
            if(count > k) {
                // go to left part
                return kthSmallest(arr, l, pivotIndex - 1, k);
            }
            // go to right with skipping left elements
            return kthSmallest(arr, pivotIndex + 1, r, k - count );
        }
        return Integer.MAX_VALUE;
    }

    public static int partition(int[] arr, int l, int r) {
        int pivot = arr[r];
        // index of the smaller element
        int i = l-1;
        for(int j=l; j<r; j++) {
            // If current element is smaller than the pivot
            if(arr[j] < pivot) {
                i++;
                swap(arr,i,j);
            }
        }
        i++;
        swap(arr, i, r);
        return i;
    }

    public static void swap(int []arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String ...args) {
        int arr[] = {5,3,6,7,8,4,2};
        System.out.println(kthSmallest(arr,0, arr.length - 1 , 5));
    }
}

class KthSelectLinear {

}
