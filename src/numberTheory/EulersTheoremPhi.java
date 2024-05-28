package numberTheory;

import java.util.*;

public class EulersTheoremPhi {
    public static void main(String[] args) {
        EulersTheoremPhi.phiFunctionWithoutSieve(7);
        EulersTheoremPhi.phiFunctionWithoutSieve(22);
        EulersTheoremPhi.phiFunctionWithoutSieve(24);

        // sieve: [1...n]
        EulersTheoremPhi.phiFunctionPrecomputeViaSieve();
    }

    // O(logn)
    public static void phiFunctionWithoutSieve(int n) {
        int res = n;
        List factors = new ArrayList();
        for (int i = 2 ; i * i <= n; i++) {
            if (n % i == 0) {
                res = res - res / i;
                factors.add(i);
                while (n % i == 0) {
                    n = n / i;
                }
            }
        }
        if (n > 1) {
            // only prime exists that divides res
            res = res - res / n;
            factors.add(n);
        }
        System.out.print(res);
        System.out.println(" " + factors);
    }

    // O(log(log n))
    public static void phiFunctionPrecomputeViaSieve() {
        final int N = 24;
        int phiFunction[] = new int[N + 1];

        // phi function
        for (int i = 0 ; i <= N; i++) {
            phiFunction[i] = i;
        }

        for (int i = 2; i <= N ; i++) {
            if (phiFunction[i] == i) {
                // i: prime number
                // phi(prime) = prime - 1
                phiFunction[i] = i - 1;
                // i*i (additional optimised)
                // say 13 > before [2,5,7,11] -> these already marked its multiples non-prime >  last 11 * 13 (already done by 11)
                for (int j = 2 * i; j <= N ; j += i) {
                    phiFunction[j] -= phiFunction[j] / i;
                }
            }
        }

        for (int i = 2 ; i <= N; i++) {
            System.out.println("phi("+i+") = "+ phiFunction[i]);
        }
    }
}
