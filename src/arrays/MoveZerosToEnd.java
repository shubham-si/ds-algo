package arrays;

// Move all Zeros To End And Maintain Relative Order For NonZeros before all zeros
// Concept: use partition like quick sort using 2 pointer
public class MoveZerosToEnd {
    // t:O(n),  s:O(1)
    void moveZeros(int []arr) {
        int i = 0;
        int j = 0;      // it will be pointing to next zero
        while (i < arr.length) {
            if (arr[i] != 0) {
                swap(arr, i , j);
                j++;
            }
            i++;
        }
    }

    void swap(int []arr, int i , int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
