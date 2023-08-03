package algorithm.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * Ways to climb (problem)
 * Given a stairs with n steps and a list of possible ways to jump (for example if jumps[i] = 4
 * then it's possible to jump by 4 steps),
 * find the total number of ways to reach the nth step starting from the floor.
 */
public class StairProblem {

    public static void main(String[] args) {
        int [] jumps = {2, 4, 5, 8};
        int sum = 10;
        System.out.println("1. total number of ways to reach the nth step is "+ways(jumps,sum));
        System.out.println("2. total number of ways to reach the nth step is "+waysRecursiveMemoization(jumps,sum, new HashMap<>()));
        System.out.println("3. total number of ways to reach the nth step is "+waysRecursive(jumps,sum));
    }

    private static int ways(int[] jumps, int sum) {
        int [] dp = new int[sum+1];
        dp[0] =1;

        for (int i=1; i < sum+1;i++){
            for(Integer jump: jumps){
                if(i-jump >= 0){
                    dp[i] += dp[i-jump];
                }
            }
        }

        return dp[sum];
    }
    private static int waysRecursive(int[] jumps, int sum) {
        if(sum ==0){
            return 1;
        }else if(sum <0){
            return 0;
        }else{
            int ways = 0;
            for(int jump:jumps){
                ways +=waysRecursive(jumps, sum-jump);
            }
            return ways;
        }
    }

    private static int waysRecursiveMemoization(int[] jumps, int sum, Map<Integer, Integer> lookUP) {
        if(!lookUP.containsKey(sum)){
            if(sum ==0){
                return 1;
            }else if(sum <0){
                return 0;
            }else{
                int ways = 0;
                for(int jump:jumps){
                    ways +=waysRecursive(jumps, sum-jump);
                }
                lookUP.put(sum, ways);
            }
        }

        return lookUP.get(sum);
    }
}
