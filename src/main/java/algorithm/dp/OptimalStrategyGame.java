package algorithm.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given an integer array nums. Two players are playing a game with this array: player 1
 * and player 2.
 *
 * Player 1 and player 2 take turns, with player 1 starting first. Both players start the game
 * with a score of 0. At each turn, the player takes one of the numbers from either end of
 * the array (i.e., nums[0] or nums[nums.length - 1]) which reduces the size of the array by 1.
 * The player adds the chosen number to their score. The game ends when there are no more
 * elements in the array.
 *
 * Return true if Player 1 can win the game. If the scores of both players are equal,
 * then player 1 is still the winner, and you should also return true.
 * You may assume that both players are playing optimally.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,5,2]
 * Output: false
 * Explanation: Initially, player 1 can choose between 1 and 2.
 * If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5,
 * then player 1 will be left with 1 (or 2).
 * So, final score of player 1 is 1 + 2 = 3, and player 2 is 5.
 * Hence, player 1 will never be the winner and you need to return false.
 */
public class OptimalStrategyGame {

    public static void main(String[] args){
        List<Integer> arr = new ArrayList<>();
        arr.add(8);
        arr.add(15);
        arr.add(3);
        arr.add(7);
        System.out.println("1.optimalStrategy by first user is "+optimalStrategyOfGameMemoization(arr,0,arr.size()-1,new HashMap<>()));

        arr.clear();
        arr.add(2);
        arr.add(2);
        arr.add(2);
        arr.add(2);
        System.out.println("2.optimalStrategy by first user is "+optimalStrategyOfGameMemoization(arr,0,arr.size()-1,new HashMap<>()));

        arr.clear();
        arr.add(20);
        arr.add(30);
        arr.add(2);
        arr.add(2);
        arr.add(2);
        arr.add(10);
        System.out.println("3.optimalStrategy by first user is "+
                                                                optimalStrategyOfGameMemoization(arr,0,arr.size()-1,new HashMap<>()));

        System.out.println("4.optimalStrategy by first user is "+
                                                                optimalStrategyOfGameTabulation(arr));
    }

    static int optimalStrategyOfGameMemoization(List<Integer> arr , int i, int j, Map<String,Integer> lookUP){
        String key = i+"_key_"+j;

        if ((i > j) || (i >= arr.size()) || (j < 0))
            return 0;


        if(!lookUP.containsKey(key)){

            int option1 = arr.get(i) + Math.min(optimalStrategyOfGameMemoization(arr,i + 2, j,lookUP),
                                                optimalStrategyOfGameMemoization(arr,i + 1, j - 1,lookUP));

            int option2 = arr.get(j) + Math.min(optimalStrategyOfGameMemoization(arr,i + 1, j - 1,lookUP),
                                                optimalStrategyOfGameMemoization(arr,i, j - 2,lookUP));
            lookUP.put(key, Math.max(option1, option2));
        }

        return lookUP.get(key);
    }

    static int optimalStrategyOfGameTabulation(List<Integer> arr){

        return 0;
    }
}
