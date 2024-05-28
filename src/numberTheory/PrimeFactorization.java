package numberTheory;

import java.util.*;
import java.util.function.Function;

public class PrimeFactorization {

    public static void main(String[] args) {
        PrimeFactorization.factorizationWithoutSieve(24);
        // [1...n]
        PrimeFactorization.factorizationPrecomputeViaSieve();
    }

    // O(logn)
    public static void factorizationWithoutSieve(int n) {
        Map<Integer, Integer> pf = new HashMap();
        for (int i = 2; i*i <= n ; i++) {
            while (n % i == 0) {
                pf.put(i, 1 + pf.getOrDefault(i, 0));
                n = n / i;
            }
        }
        if (n > 1) {
            // only prime exists that divides n
            pf.put(n, 1 + pf.getOrDefault(n, 0));
        }
        System.out.println(pf);
    }

    // precompute: (prime(sieve) + factorization)
    // O(n log log n)
    public static void factorizationPrecomputeViaSieve() {
        final int N = 24;
        int smallestPrimeFactor[] = new int[N + 1];
        int highestPrimeFactor[] = new int[N + 1];

        for (int i = 0 ; i <= N; i++) {
            smallestPrimeFactor[i] = highestPrimeFactor[i] = i;
        }

        smallestPrimeFactor[0] = highestPrimeFactor[0] = 0;
        smallestPrimeFactor[1] = highestPrimeFactor[1] = 0;

        for (int i = 2; i*i <= N ; i++) {
            if (smallestPrimeFactor[i] == i) {
                // i: prime number
                smallestPrimeFactor[i] = highestPrimeFactor[i] = i;
                // i*i (additional optimised)
                // say 13 > before [2,5,7,11] -> these already marked its multiples non-prime >  last 11 * 13 (already done by 11)
                for (int j = 2 * i; j <= N ; j += i) {
                    highestPrimeFactor[j] = i;
                    if (smallestPrimeFactor[j] == j) {
                        smallestPrimeFactor[j] = i;
                    }
                }
            }
        }

        Function<Integer, List> pf = (num) -> {
            List factors = new ArrayList();
            // O(logn)
            while (num > 1) {
                int spf = smallestPrimeFactor[num];
                while (num % spf == 0) {
                    factors.add(spf);
                    num = num / spf;
                }
            }
            return factors;
        };

        for (int i = 2 ; i <= N; i++) {
            System.out.println("factors("+i+") = "+ pf.apply(i));
        }
    }

}
