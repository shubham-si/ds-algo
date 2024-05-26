package bits;

// https://leetcode.com/problems/single-number-iii/
public class Xor2Distincts {
    public int[] singleNumber(int[] nums) {
        int _xor = 0;
        for (int i: nums) {
            _xor = _xor ^ i;
        }
        // find the last set bit in _xor (that will either belong to x or y)
        int lastBitSet = ((_xor & (_xor - 1)) ^ _xor);
        // divide the numbers into two buckets:
        // 1. one with that bit set
        // 2. one with that bit unset
        // the last set bit in _xor (that will either belong to x or y)
        // rest will cancel out in their respective xor buckets

        int c = 1, pos = 0; // 2^0
        while(c != lastBitSet) {
            c = c << 1;
            pos++;
        }

        int b1 = 0, b2 = 0;
        for (int i: nums) {
            // that lastBit is set or not
            if (((i >> pos) & 1) == 1) {
                b1 = b1 ^ i;
            } else {
                b2 = b2 ^ i;
            }
        }

        return new int[]{b1, b2};
    }
}
