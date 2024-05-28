package numberTheory;

import java.util.ArrayList;
import java.util.List;

public class Divisors {
    public static void main(String[] args) {
        List preComputed = Divisors.getDivisors();
        System.out.println(preComputed.get(24));
        System.out.println(preComputed.get(26));
        System.out.println(preComputed.get(36));
    }

    public static List<List<Integer>> getDivisors() {
        int n = 1024;
        List<List<Integer>> divisors = new ArrayList<>(n+1);
        for (int i = 0; i <= n ; i++) {
            divisors.add(new ArrayList<>());
        }
        for (int i = 2; i * i <= n ; i++) {
            for(int j = i ; j <= n; j+= i) {
                divisors.get(j).add(i);
            }
        }
        return divisors;
    }
}
