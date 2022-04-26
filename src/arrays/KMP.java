package arrays;

// O(m + n)
public class KMP {
    public static int[] preCompute(char []pattren) {
        int precompute[] = new int[pattren.length];
        // how much index is matched (prefix)
        int j = 0;
        precompute[0] = 0;
        for(int i = 1; i < pattren.length; i++) {
            while(j > 0 && pattren[i] != pattren[j]) {
                j = precompute[j - 1];
            }
            if(pattren[i] == pattren[j]) {
                // next prefix length match
                j = j + 1;
            }
            precompute[i] = j;
        }
//        for(int val: precompute) {
//            System.out.print(val);
//        }
        return precompute;
    }

    public static void main(String ...args) {
        char txt[] =  "acbacdabcacbacxacbacdabcxabcdacbacdabc".toCharArray();
        char pat[] = "acbacdabc".toCharArray();
        int [] lps = preCompute(pat);

        // search pattern
        int i = 0, j = 0;
        while(i < txt.length) {
            while(j > 0 && txt[i] != pat[j]) {
                j = lps[j - 1];
            }
            if (txt[i] == pat[j]) {
                j++;
            }
            if (j == pat.length) {
                System.out.println("found at " + (i - j + 1));
                j = 0;
            }
            i++;
        }
    }
}
