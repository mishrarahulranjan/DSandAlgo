package algorithm.dp;

/**
 * 0-1 knapsack (problem)
 * Given the value and the weight of each one of n items,
 * we want to maximize the value of items we take in our knapsack without exceeding its capacity k.
 * You are asked to find the maximum total value that we can get without exceeding a total weight of k.
 * Note that each item can be took at most once
 */
public class ZeroOneKnapsack {
    public static void main(String[] args){
        int[] values    = {20, 30, 15, 25, 10};
        int[] weights   = {6, 13, 5, 10, 3};

        int k = 20;

        System.out.println("knapsack is "+knapsack(values,weights,k));
    }

    private static int knapsack(int[] values,int[] weights, int k){
        int sum = sum(weights);
        if(k==0) return 0;
        else if (k >= sum) return sum;

        int n = values.length;
        int [][] dp = new int[n+1][k+1];

        for(int i=weights[0];i<k+1;i++){
            dp[0][i] = values[0];
        }

        for(int i=1;i<n;i++){
            for(int j=1;j<k+1;j++){
                if(weights[i]>j){
                    dp[i][j]= dp[i-1][j];
                }else{
                    dp[i][j] = Math.max(values[i]+dp[i-1][j-weights[i]],dp[i-1][j]);
                }
            }
        }



        return dp[n-1][k];
    }

    private static int sum(int[] weights) {
        int sum =0;
        for(Integer w: weights){
            sum +=w;
        }
        return sum;
    }
}
