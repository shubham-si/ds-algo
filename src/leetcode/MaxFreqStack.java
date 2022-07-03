package leetcode;

import java.util.*;

// https://leetcode.com/problems/maximum-frequency-stack/
public class MaxFreqStack {

    Map<Integer,Integer> freqMap;
    List<Stack<Integer>> list;
    public MaxFreqStack() {
        // store frequency of elements
        freqMap = new HashMap<Integer,Integer>();


        // list of stacks i., list[i] <-- stack()
        // why stack? so that at that freq(list: idx) we have elements in LIFO order (Stacked)
        list = new ArrayList<>();
    }

    public void push(int val) {

        freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);
        int freq = freqMap.get(val);

        // if getting any new higher frequency
        // 0 based index
        if ((freq - 1) == list.size()) {
            list.add(new Stack());
        }
        list.get(freq - 1).push(val);
    }

    public Integer pop() {

        if (list.isEmpty()) {
            return null;
        }

        int ele = list.get(list.size() - 1).peek();
        list.get(list.size() - 1).pop();

        // if stacks becomes empty delete the stack
        if (list.get(list.size() - 1).isEmpty()) {
            list.remove(list.size() - 1);
        }

        freqMap.put(ele, freqMap.get(ele) - 1);

        return ele;
    }
}
