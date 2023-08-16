package algorithm.dp;

import java.util.HashMap;
import java.util.Map;

/**
 Rod cutting (problem). We want to sell a rod of size n, and to maximize the profit, we can cut it into multiple pieces.
 The price of a piece depends on its length, to know it, we are given an array prices where prices[i] represents
 the price of a piece of length i. You are asked to find the maximum price that we can get by cutting it into pieces.
 Note that you are allowed to sell it as a single piece of length n if it's the most profitable choice.

 Example:
 input:
 n = 8
 prices = [0, 1, 3, 5, 6, 7, 9, 10, 11]
 output: 13
 explanation: The most profitable way of cutting a rod of length 8 with the given prices is by cutting
 it into a piece of length 2 and two pieces of length 3
 prices[2]+prices[3]+prices[3] = 3+5+5 = 13
 */
public class RodCuttingProblem {
    public static void main(String[] args){
        int size = 8;
        int[] prices = {0, 1, 3, 5, 6, 7, 9, 10, 11};

        System.out.println("1. the maximum price for rodCuttingProblem is "+rodCuttingRecursive(prices, size));
        System.out.println("2. the maximum price for rodCuttingMemoization is "+rodCuttingMemoization(prices, size, new HashMap<>()));

        System.out.println("3. the maximum price for rodCuttingProblem is "+rodCuttingTabular(prices, size));

    }

    private static int rodCuttingRecursive(int[] prices,int size){

        int max=0;

        if(size <= 0){
            return 0;
        }else{
            for(int i=1;i<=size;i++){
                max=Math.max(max,prices[i]+rodCuttingRecursive(prices,size-i));
            }
            return max;
        }
    }

    private static int rodCuttingMemoization(int[] prices,int size, Map<Integer,Integer> lookUp){
        if(!lookUp.containsKey(size)){
            if(size <= 0){
                return 0;
            }else{
                int max=0;
                for(int i=1;i<=size;i++){
                    max=Math.max(max,prices[i]+rodCuttingMemoization(prices,size-i,lookUp));
                }
                lookUp.put(size,max);
            }
        }

        return lookUp.get(size);
    }

    private static int rodCuttingTabular(int[] prices,int size){
        int [] dp = new int[prices.length];
        dp[0]=0;

        for(int i=1;i<=size;i++){
            for(int j=0;j<i+1;j++){
                dp[i]= Math.max(dp[i],prices[j]+dp[i-j]);
            }
        }
        return dp[size];
    }
}
