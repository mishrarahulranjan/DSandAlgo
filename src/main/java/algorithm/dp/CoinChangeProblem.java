package algorithm.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * Coin change (problem)
 * Given an integer that represents an amount of money and a list of possible coins,
 * find the minimum number of coins to make that amount.
 * Return -1 if it's possible to make the amount with the given coins.
 * Example:
 * input:
 * amount = 15
 * possible_coins = [2, 3, 7]
 *
 * output: 4
 *
 * explanation: We can make the amount 15 by taking 4 coins only, one coin of value 2,
 * two coins of value 3, and one coin of value 7
 * 2+3+3+7 = 15
 */
public class CoinChangeProblem {
    public static void main(String[] args){

        int[] possible_coins    = {2, 10,4};
        int sum                 = 12;

        int count1               = coinChangeRecursive(possible_coins,sum);
        int count2               = coinChange(possible_coins,sum);
        int count3               = coinChangeMemoization(possible_coins,sum, new HashMap<Integer,Integer>());

        System.out.println("1.coin change count is "+(count1==Integer.MAX_VALUE?-1:count1));
        System.out.println("2.coin change count is "+(count2==Integer.MAX_VALUE?-1:count2));
        System.out.println("3.coin change count is "+(count3==Integer.MAX_VALUE?-1:count3));
    }

    private static int coinChangeRecursive(int[] possible_coins, int amount) {
        if(amount == 0){
            return 0;
        }else if(amount < 0){
            return Integer.MAX_VALUE;
        }else{
            int ways = Integer.MAX_VALUE;
            for(int coin:possible_coins){
               int result = coinChangeRecursive(possible_coins, amount-coin);
                if (result != Integer.MAX_VALUE) {
                    ways = Integer.min(ways, result + 1);
                }
            }
            return ways;
        }
    }

    private static int coinChangeMemoization(int[] possible_coins, int amount, Map<Integer,Integer> lookUp) {

        if(!lookUp.containsKey(amount)) {
            if(amount == 0){
                return 0;
            }else if(amount < 0){
                return Integer.MAX_VALUE;
            }else{
                int ways = Integer.MAX_VALUE;
                for(int coin:possible_coins){
                    int result = coinChangeRecursive(possible_coins, amount-coin);
                    if (result != Integer.MAX_VALUE) {
                        ways = Integer.min(ways, result + 1);
                    }
                }

                lookUp.put(amount,ways);
            }
        }

        return lookUp.get(amount);

    }

    private static int coinChange(int[] possible_coins, int sum){

        int [] coins = new int[sum+1];
        for(int i=0;i<sum+1;i++){
            coins[i] = Integer.MAX_VALUE;
        }
        coins[0] = 0;

        for(int i=1;i<sum+1;i++){
            for(Integer coin : possible_coins){
                if((i-coin) >= 0){
                    int c = coins[i-coin];
                    if(coins[i-coin] != Integer.MAX_VALUE){
                        c = 1+ coins[i-coin];
                    }
                    coins[i] = Math.min(coins[i],c);
                }
             }
        }

        return coins[sum] !=0?coins[sum] :-1;
    }
}
