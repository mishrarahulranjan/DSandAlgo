package algorithm.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Longest increasing subsequence (problem)
 * Given an array of integers arr, find the length of its longest increasing subsequence,
 * its longest subsequence where each element is strictly greater than the previous one.
 * Example 1:
 * input:arr = [7, 5, 2, 4, 7, 2, 3, 6, 4, 5, 12, 1, 7]
 * output: 5
 * explanation: A possible longest increasing subsequence is [2, 3, 4, 5, 7], its length is 5
 *
 * Example 2:
 * input:
 * arr = [8, 5, 5, 3]
 * output: 1
 * explanation: The longest increasing subsequences that we can make are those that contain one element only,
 * like [8] Constraints: len(arr) >= 1
 */
public class LongestIncreasingSequence {

    public static void main(String[] args){

        int[] arr = {7, 5, 2, 4, 7, 2, 3, 6, 4, 5, 12, 1, 7};
        //int[] arr = {8, 5, 5, 3};
        System.out.println("1.Longest Increasing Sequence.. is "
                +list(arr));
        System.out.println("2.Longest Increasing Sequence.. is "
                            +lisRecursive(arr,0,Integer.MIN_VALUE));
        System.out.println("3.Longest Increasing Sequence.. is "
                +lisMemoization(arr,0,Integer.MIN_VALUE, new HashMap<String, Integer>()));

        System.out.println("4.Longest Increasing Sequence.. is "
                +lisTabular(arr));
    }

    private static int list(int[] arr){
        int max= 0;
        for(int i=0;i<arr.length;i++){
            max= Math.max(max,rec(arr,i));
        }
        return max;
    }

    private static int rec(int[] arr, int i){
        int maxLen= 0;
        for(int j=i+1;j<arr.length;j++){
            if(arr[j]>arr[i]){
                maxLen= Math.max(maxLen,rec(arr,j));
            }
        }
        return 1+maxLen;
    }

    private static int lisRecursive(int [] arr, int i,int prev){
        if( i== arr.length){
            return 0;
        }else if (arr[i] <= prev){
            return lisRecursive(arr,i+1, prev);
        }else{
            return Math.max(lisRecursive(arr,i+1, prev),1+lisRecursive(arr,i+1, arr[i]));
        }
    }

    private static int lisMemoization(int [] arr, int i, int prev, Map<String, Integer> lookUp){
        String key = i+ "_key_"+prev;

        if(!lookUp.containsKey(key)){
            if( i== arr.length){
                return 0;
            }else if (arr[i] <= prev){
                int val= lisMemoization(arr,i+1, prev,lookUp);
                lookUp.put(key,val);
            }else{
                int val= Math.max(lisMemoization(arr,i+1, prev,lookUp),1+lisMemoization(arr,i+1, arr[i],lookUp));
                lookUp.put(key,val);
            }
        }

        return lookUp.get(key);

    }


    private static int lisTabular(int [] arr){
       int[] dp = new int [arr.length];

       dp[0]=1;

       for (int i=1;i<arr.length;i++){
           int maxLen=0;
           for(int j=0;j<i;j++){
               if(arr[j]<arr[i] && dp[j]>maxLen){
                   maxLen= dp[j];
               }
           }

           dp[i] = 1+maxLen;
       }

       return Arrays.stream(dp).max().orElse(0);
    }
}
