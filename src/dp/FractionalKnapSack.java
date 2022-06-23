package dp;

import java.util.*;

public class FractionalKnapSack{

    class Item implements Comparable<Item> {
        int wt,val;
        Float ratio;

        public Item(int wt, int val) {
            this.wt = wt;
            this.val = val;
            // val[i] / wt[i] => how much portion u can carry
            this.ratio = Float.valueOf(this.val) / Float.valueOf(this.wt);
        }


        @Override
        public int compareTo(Item o) {
            return -1 * this.ratio.compareTo(o.ratio);
        }
    }

    float knapSack(int W, int wt[], int val[], int n)
    {
        List<Item> list = new ArrayList<>();
        for(int i = 0 ;i < wt.length; i++) {
            list.add(new Item(wt[i], val[i]));
        }

        // decreasing order of (val[i] / wt[i])
        Collections.sort(list);
        float res = 0;

        for (Item item: list) {
            if (item.wt <= W) {
                // take full
                res = res + item.val;
                W -= item.wt;
            } else {
                // wt[i] > W
                // take fraction from wt[i] i.e., W from above equation
                // units: (W) * (val[i] / wt[i])
                res += W * item.ratio;
                break;
            }
        }
        return res;
    }

    public static void main(String ...args) {
        int[] wt = { 10, 40, 20, 30 };
        int[] val = { 65, 47, 125, 120 };
        int capacity = 50;
        System.out.println(new FractionalKnapSack().knapSack(capacity, wt, val, wt.length));
    }
}
