package algorithm.dp;

import java.util.HashMap;
import java.util.Map;

/**
0-1 knapsack (problem)
Given the value and the weight of each one of n items, we want to maximize the value of items
we take in our knapsack without exceeding its capacity k. You are asked to find the maximum total value that
we can get without exceeding a total weight of k. Note that each item can be took at most once.
Example:
input:  values = [20, 30, 15, 25, 10]
        weights = [6, 13, 5, 10, 3]
        k = 20
        output: 55
*/
public class KnapsackProblem {
    public static void main(String[] args){

        int [] weights = {6,13,5,10,2};
        int [] values = {20,30,15,25,10};

        int k =20;
        System.out.println("1.max values: "+knapsackRecursive(weights,values,k,0));
        System.out.println("2.max values: "+knapsackRecursiveMemoization(weights,values,k,0, new HashMap<String,Integer>()));
        System.out.println("3.max values: "+knapsackTabular(weights,values,k));

    }

    private static int knapsackRecursive(int[] weights, int[] values, int k, int i) {
        if(i >= values.length){
            return 0;
        }
        if(k < weights[i]){
            return knapsackRecursive(weights,values,k,i+1);
        }else{
            return Math.max(values[i]+knapsackRecursive(weights,values,k-weights[i],i+1),
                    knapsackRecursive(weights,values,k,i+1));
        }

    }

    private static int knapsackRecursiveMemoization(int[] weights, int[] values, int k, int i, Map<String ,Integer> lookUp) {
        String key = k+"_"+i;

        if(!lookUp.containsKey(key)){
            if(i >=values.length){
                return 0;
            }
            if(k <0){
                return 0;
            }else{
                int value= Math.max(values[i]+knapsackRecursive(weights,values,k-weights[i],i+1),
                        knapsackRecursive(weights,values,k,i+1));
                lookUp.put(key,value);
            }
        }

        return lookUp.get(key);
    }

    private static int knapsackTabular(int[] weights, int[] values, int k) {
       int[][] knapsack = new int [values.length][k+1];

        //first row
        for (int i=weights[0];i<k+1;i++){
            knapsack[0][i] = values[0];
        }


        for(int i=1;i<values.length;i++){
            for(int j=1;j<k+1;j++){
                if(weights[i]>j){
                    knapsack[i][j] = knapsack[i-1][j];
                }else{
                    knapsack[i][j]=Math.max(values[i]+knapsack[i-1][j-weights[i]],knapsack[i-1][j]);
                }

            }
        }
        return knapsack[values.length-1][k];
    }
}
