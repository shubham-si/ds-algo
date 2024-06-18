package leetcode;

// https://leetcode.com/problems/sort-colors/
public class Sort012 {
    // 3-way partitioning
    // [l,j]=0 similar to move all zeros to end of array problem
    // h to end of array > when l == 2 > keep swapping until it reduces to first problem
    public void sortColors(int[] nums) {
        int l = 0, m = 0, h = nums.length - 1;
        while(m <= h) {
            if (nums[m] == 0) {
                swap(nums, l, m);
                l++;
                m++;
            }else if (nums[m] == 1){
                m++;
            }else if (nums[m] == 2){
                // keep swapping until it reduces to first problem
                // arr[m] is 0 or 1
                swap(nums, h, m);
                h--;
            }
        }
    }

    void swap(int []arr, int i , int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
