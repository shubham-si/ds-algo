package arrays;

/*

  lower_bound(ele) => returns index i: arr[i] >= ele
  upper_bound(ele) => returns index i: arr[i] > ele

   eg., {1,3,4,4,5,7}
   lower_bound(1) = arr[0] = 1
   lower_bound(4) = arr[2] = 4
   lower_bound(6) = arr[5] = 7

   lower_bound(8) = arr.length: 6 = null


   upper_bound(6) = arr[5] = 7
   upper_bound(1) = arr[1] = 3
   upper_bound(7) = arr.length = null

  Note that here high index is set to n instead of n - 1.
  These functions can return an index which is one beyond the bounds of the array. I.e.,
  it will return the size of the array if the search key is not found and it is greater than all the array elements.
 */
public class LowerUpperBound {

    // ~ set.ceiling()
    // returns index i: arr[i] >= ele
    public static int lower_bound(Integer []arr, int n, int ele) {

        // Note that here high index is set to n instead of n - 1.
        int low = 0, high = n;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (ele <= arr[mid]) {
                high = mid;             // instead of mid - 1
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    // ~ set.higher()
    // returns index i: arr[i] > ele
    public static int upper_bound(Integer []arr, int n, int ele) {

        // Note that here high index is set to n instead of n - 1.

        int low = 0, high = n;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (ele >= arr[mid]) {
                low = mid + 1;             // as strictly greater
            } else {
                high = mid;                // as strictly greater
            }
        }

        return low;
    }

    public static void main(String []args) {
        Integer []arr = {1,3,4,4,5,7};
        System.out.println(lower_bound(arr, arr.length, 1));
        System.out.println(lower_bound(arr, arr.length, 4));
        System.out.println(lower_bound(arr, arr.length, 6));
        System.out.println(lower_bound(arr, arr.length, 8));

        System.out.println(upper_bound(arr, arr.length, 6));
        System.out.println(upper_bound(arr, arr.length, 1));
        System.out.println(upper_bound(arr, arr.length, 7));
    }
}
