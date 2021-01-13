package d20201221;

public class Solution {
    public int minCostClimbingStairs(int[] cost) {

        int size = cost.length;
        int[] dp = new int[size + 1];
        dp[0] = dp[1] = 0;

        for (int i = 2; i <= size; i++ ){
            dp[i] = Math.min(dp[i-2] + cost[i-2], dp[i-1] + cost[i-1]);
        }

        return dp[size];
    }

    public static void main(String[] args) {
        int[] cost = {0,1,1,2};
        System.out.println(new Solution().minCostClimbingStairs(cost));
    }
}
