package arrays;

public class CountDuplicates {

    int countDuplicates(int[] array, int n) {
        return LastOccurrence(array, n) - FirstOccurrence(array, n);
    }

    int FirstOccurrence(int[] array, int n) {

        int beg = 0;
        int end = array.length - 1;

        while (beg <= end) {

            int mid = beg + (end - beg) / 2;

            if (array[mid] == n) {
                // check for prev. left neighbour if same
                if (mid - 1 >= 0 && array[mid - 1] == array[mid]) {
                    end = mid - 1;
                    continue;
                }
                return mid;
            } else if (array[mid] < n)
                beg = mid + 1;
            else
                end = mid - 1;
        }
        return -1;
    }

    int LastOccurrence(int[] array, int n) {

        int beg = 0;
        int end = array.length - 1;

        while (beg <= end) {

            int mid = beg + (end - beg) / 2;

            if (array[mid] == n) {
                // check for next right neighbour if same
                if (mid + 1 < array.length && array[mid + 1] == array[mid]) {
                    beg = mid + 1;
                    continue;
                }
                return mid;
            } else if (array[mid] < n)
                beg = mid + 1;
            else
                end = mid - 1;
        }
        return -1;
    }
}
