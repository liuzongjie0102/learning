package d20201225;

import java.util.Arrays;

public class Solution {

    public int findContentChildren(int[] g, int[] s) {

        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        for (int j = 0; count < g.length && j < s.length; j++){
            if (g[count] <= s[j]){
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] g = {1,2};
        int[] s = {1,2,3};
        System.out.println(solution.findContentChildren(g, s));
    }
}
