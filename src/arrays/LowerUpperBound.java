package arrays;

/*

  lower_bound(ele) => returns "lowest" index i: arr[i] >= ele
  upper_bound(ele) => returns "lowest" index i: arr[i] > ele

   eg., {1,3,4,4,5,7}
   lower_bound(1) = arr[0] = 1
   lower_bound(4) = arr[2] = 4
   lower_bound(6) = arr[5] = 7

   lower_bound(8) = arr.length: 6 = null(-1)

   upper_bound(6) = arr[5] = 7
   upper_bound(1) = arr[1] = 3
   upper_bound(7) = arr.length = null(-1)

  Note that here high index is set to n instead of n - 1.
  These functions can return an index which is one beyond the bounds of the array. I.e.,
  it will return the size of the array if the search key is not found and it is greater than all the array elements.
 */
public class LowerUpperBound {
    // https://leetcode.com/problems/search-insert-position/
    // ~ treeSet.ceiling()
    // returns "lowest" index i: arr[i] >= ele
    public static int lower_bound(Integer []arr, int n, int ele) {

        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] >= ele) {
                high = mid - 1;
            } else {
                // ele > arr[mid]
                low = mid + 1;
            }
        }

        // "lowest" index
        return low;
    }

    // ~ treeSet.higher()
    // returns "lowest" index i: arr[i] > ele
    public static int upper_bound(Integer []arr, int n, int ele) {

        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] > ele) {
                high = mid - 1;
            } else {
                // ele >= arr[mid]
                low = mid + 1;
            }
        }

        // "lowest" index
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
