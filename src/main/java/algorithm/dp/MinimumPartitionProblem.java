package algorithm.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Partition a set into two subsets such that the difference of subset sums is minimum
 * Given a set of integers, the task is to divide it into two sets S1 and S2 such
 * that the absolute difference between their sums is minimum.
 * If there is a set S with n elements, then if we assume Subset1 has m elements,
 * Subset2 must have n-m elements and the value of abs(sum(Subset1) – sum(Subset2)) should be minimum.
 *
 * Example:
 *
 * Input:  arr[] = {1, 6, 11, 5}
 * Output: 1
 * Explanation:
 * Subset1 = {1, 5, 6}, sum of Subset1 = 12
 * Subset2 = {11}, sum of Subset2 = 11
 */

public class MinimumPartitionProblem {

    public static void main(String[] args){
        int arr[] = {1, 6, 11, 5};
        int n = arr.length;
        int sumTotal = Arrays.stream(arr).sum();
        System.out.println("1.minimum diff  b/w two sets is "+ minPartitionRecurisve(arr, n, 0, sumTotal));
        System.out.println("2.minimum diff  b/w two sets is "+ minPartitionMemoization(arr, n, 0, sumTotal, new HashMap<>()));
        System.out.println("3.minimum diff  b/w two sets is "+ minPartitionTabulation(arr));
    }

    private static int  minPartitionTabulation(int[] arr) {
        int n = arr.length;
        int sum = Arrays.stream(arr).sum();

        boolean dp[][] = new boolean[n + 1][sum + 1];

        for (int i = 0; i <= n; i++){
            dp[i][0] = true;
        }


        for (int i = 1; i <= sum; i++){
            dp[0][i] = false;
        }


        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j];

                if (arr[i - 1] <= j)
                    dp[i][j] |= dp[i - 1][j - arr[i - 1]];
            }
        }

        // Initialize difference of two sums.
        int diff = Integer.MAX_VALUE;

        // Find the largest j such that dp[n][j]
        // is true where j loops from sum/2 t0 0
        for (int j = sum / 2; j >= 0; j--) {
            // Find the
            if (dp[n][j] == true) {
                diff = sum - 2 * j;
                break;
            }
        }
        return diff;
    }

    private static int minPartitionRecurisve(int[] arr, int i, int sumCalculated, int sumTotal) {
        if (i == 0){
            return Math.abs((sumTotal - sumCalculated)- sumCalculated);
        }


        return Math.min(
                minPartitionRecurisve(arr, i - 1,sumCalculated + arr[i - 1],sumTotal),
                minPartitionRecurisve(arr, i - 1, sumCalculated, sumTotal)
              );
    }

    private static int minPartitionMemoization(int[] arr, int i, int sumCalculated, int sumTotal, Map<String,Integer> lookUp) {
        String key = i+ "_"+sumCalculated;
        if(!lookUp.containsKey(key)){
            if (i == 0){
                return Math.abs((sumTotal - sumCalculated)- sumCalculated);
            }
            int val = Math.min(
                    minPartitionMemoization(arr, i - 1,sumCalculated + arr[i - 1],sumTotal,lookUp),
                    minPartitionMemoization(arr, i - 1, sumCalculated, sumTotal,lookUp));

            lookUp.put(key,val);

        }

        return lookUp.get(key);
    }
}

