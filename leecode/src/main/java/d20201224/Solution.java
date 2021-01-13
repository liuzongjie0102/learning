package d20201224;

import java.util.Arrays;

public class Solution {
    public int candy(int[] ratings) {
        int[] candys = new int[ratings.length];

        candys[0] = 1;
        for (int i = 0; i < ratings.length - 1; i++){

            if (ratings[i + 1] > ratings[i]){
                candys[i + 1] = candys[i] + 1;
            }else {
                candys[i + 1] = 1;
            }
        }

        for (int i = ratings.length - 1; i > 0; i-- ){
            if (ratings[i] < ratings[i-1] && candys[i-1] <= candys[i]){
                candys[i-1] = candys[i] +1;
            }
        }

        return Arrays.stream(candys).sum();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ratings = {1,3,4,5,2};
        System.out.println(solution.candy(ratings));
    }
}
