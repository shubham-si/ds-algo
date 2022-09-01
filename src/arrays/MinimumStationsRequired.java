package arrays;

import trees.PairUtil;

import java.util.*;

public class MinimumStationsRequired {

    // T(nlogn), S(1)
    int minStations(int []arr, int []dep, int n) {
        int globMinPlat = 1;
        int platCount = 1;

        Arrays.sort(arr);
        Arrays.sort(dep);

        int in = 1, out = 0;
        while (in < n && out < n) {
            if (arr[in] <= dep[out]) {
                platCount++;
                in++;
            } else {
                platCount--;
                out++;
            }

            if (platCount < globMinPlat) {
                globMinPlat = platCount;
            }
        }
        return globMinPlat;
    }

    // PriorityQueue
    // // T(nlogn), S(n)
    int minStationsQueue(int []arr, int []dep, int n) {
        int platCount = 1;
        List<Map.Entry<Integer, Integer>> pairs = new ArrayList();

        for(int i = 0 ; i < n ; i++) {
            pairs.add(PairUtil.Of(arr[i], dep[i]));
        }

        Collections.sort(pairs, (a,b) -> Integer.compare(a.getKey(), b.getKey()));

        // pq of departures
        Queue<Integer> pq = new PriorityQueue<>();
        pq.offer(pairs.get(0).getValue());

        for (int i = 1 ; i < n ; i++) {
            // arrival time of next train is lesser or equals to depart of prev. train
            if (arr[i] <= pq.peek()) {
                platCount++;
            } else {
                pq.poll();
            }
            pq.offer(pairs.get(i).getValue());
        }

        return platCount;
    }
}
