package algorithm.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Partition (problem)
 * Given an array of strictly positive integer arr, check if we can split it into two subsets that have
 * the same sum of elements.
 * Example 1:
 * input:
 * arr = [4, 5, 3, 2, 5, 1]
 * output: true
 *
 * explanation: We can split arr into [4, 3, 2, 1] and [5, 5], and they have the same sum
 * 4+3+2+1 = 5+5 = 10
 * Example 2:
 * input:
 * arr = [5, 6, 2, 3, 8, 1]
 * output: false
 *
 * explanation: We can't split arr into two subsets that have the same sum
 */
public class PartitionProblem {

    public static void main(String[] args){
        int[] arr = {4, 5, 3, 2, 5, 1};

        System.out.println("1. is partition of array  possible? "+partitionCheck(arr,0,0,0));
        System.out.println("2. is partition of array  possible? "+partitionCheckMemoization(arr,0,0,0, new HashMap<>()));
        System.out.println("2. is partition of array  possible? "+partitionCheckUsingSubsetSum(arr));

        int[] arr2 = {4, 4, 4, 4, 4, 2};
        System.out.println("1. is partition of array  possible? "+partitionCheck(arr2,0,0,0));
        System.out.println("2. is partition of array  possible? "+partitionCheckMemoization(arr2,0,0,0, new HashMap<>()));
        System.out.println("3. is partition of array  possible? "+partitionCheckUsingSubsetSum(arr2));
    }

    private static boolean partitionCheck(int[] arr, int i, int sum1,int sum2){
        if(i == arr.length){
            return sum1 == sum2;
        }else{
            return partitionCheck(arr,i+1,sum1+arr[i], sum2) || partitionCheck(arr, i+1, sum1, sum2+arr[i]);
        }
    }

    private static boolean partitionCheckMemoization(int[] arr, int i, int sum1,int sum2, Map<String,Boolean> lookUp){
        String key = i+ "_key1_"+sum1+ "_key2_"+sum2;
        if(!lookUp.containsKey(key)){
            if(i == arr.length){
                return sum1 == sum2;
            }else{
                boolean value = partitionCheck(arr,i+1,sum1+arr[i], sum2)
                            || partitionCheck(arr, i+1, sum1, sum2+arr[i]);
                lookUp.put(key,value);
            }
        }
       return lookUp.get(key);
    }

    private static boolean partitionCheckUsingSubsetSum(int[] arr){
        int sum = Arrays.stream(arr).sum();
        if(sum %2==1){
            return false;
        }else{
            return SubSetSum.subSetSumTabular(arr,sum/2) >0;
        }

    }

}
