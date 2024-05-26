package bits;

public class XorQueries1 {
    public void xorQueries(int[][] queries) {
        for(int i = 0; i < queries.length ; i++) {
            // [1^2^3...(L-1)] ^ [1^2^3..(L-1)^L^.....R] => XOR[L...R]
            System.out.println(XorQueries1._xor(queries[i][0] - 1) ^ XorQueries1._xor(queries[i][0]));
        }
    }

    // _xor(n): XOR[1^2...n]
    public static int _xor(int n) {
        if (n % 4 == 1) return 1;
        if (n % 4 == 2) return (n + 1);
        if (n % 4 == 3) return 0;
        // n % 4 == 0
        return n;
    }
}
