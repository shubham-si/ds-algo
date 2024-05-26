package bits;

/*
You are given an array arr of positive integers. You are also given the array queries where queries[i] = [lefti, righti].
For each query i compute the XOR of elements from lefti to righti (that is, arr[lefti] XOR arr[lefti + 1] XOR ... XOR arr[righti] ).

Return an array answer where answer[i] is the answer to the ith query.
Input: arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
Output: [2,7,14,8]
 */

public class XorQueriesSubArray {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int prefix[] = new int[arr.length];
        prefix[0] = arr[0];

        for(int i = 1; i < arr.length; i++) {
            prefix[i] = arr[i] ^ prefix[i - 1];
        }

        int res[] = new int[queries.length];
        for(int i = 0; i < queries.length ; i++) {
            int l = queries[i][0], r = queries[i][1];

            if (l == 0) {
                // prefix[r] -> XOR[0...r]
                res[i] = prefix[r];
                continue;
            }
            res[i] = prefix[l - 1] ^ prefix[r];
        }
        return res;

    }
}
