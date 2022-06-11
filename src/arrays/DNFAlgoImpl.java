package arrays;

/*
    [low....mid-1] --> 0
    [mid....high] ---> ? <pivot> ==> 1
    [high....n-1] ---> 2
 */

// Sort an array of 0,1,2
class DNFAlgoImpl {
    public static void main(String ...args) {
        int array[] = {1,1,1,0,2,0,1,0,2,0,1,2,2,1,0,0,1};
        array = getSortedArray(array);
        for(int val: array) {
            System.out.print(val + " ");
        }

    }
    private static int[] getSortedArray(int []array) {
        int low = 0, mid=0;
        int high = array.length-1;

        while(mid < high) {
            switch (array[mid]) {
                case 0:
                    swap(array, low, mid);
                    low++; mid++;           // [0....mid-1] --> 0
                    break;
                case 1: mid++;
                    break;
                case 2:
                    swap(array, mid, high);
                    high--;                 // [high+1....n-1] ---> 2
                    break;
            }
        }
        return array;
    }

    private static void swap(int []array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

}
