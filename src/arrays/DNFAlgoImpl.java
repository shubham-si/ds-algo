package arrays;

/*
    [0....low-1] --> 0
    [low....k-1] ---> ? <pivot> ==> 1
    [k....n-1] ---> 2
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
        int st = 0, end = array.length-1, index=0;
        while(index < end) {
            switch (array[index]) {
                case 0: swap(array, st, index); st++; index++;          // [0....low-1] --> 0
                        break;
                case 1: index++;
                    break;
                case 2: swap(array, index, end); end--;                // [high....n-1] ---> 2
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
