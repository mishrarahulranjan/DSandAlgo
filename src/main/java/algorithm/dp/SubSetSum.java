package algorithm.dp;

import java.util.Arrays;

/**
 * Subset sum (problem)
 * Given an array arr of strictly positive integers, and an integer k,
 * create a function that returns the number of subsets of arr that sum up to k.
 */
public class SubSetSum {
    public static void main(String[] args){

        int[] arr = {1,2,3,1};
        int k =4;
        System.out.println("1(A).subset sum is "+ subSetSumRecursive(arr,k,0));
        System.out.println("1(B).subset sum is "+ subSetSumMemoization(arr,k,0,new int [arr.length][k+1]));
        System.out.println("1(C).subset sum is "+ subSetSumTabular(arr,k));


        arr = new int[]{1, 2, 3, 1, 4};
        k = 6;
        System.out.println("2(A).subset sum is "+ subSetSumRecursive(arr,k,0));
        System.out.println("2(B).subset sum is "+ subSetSumMemoization(arr,k,0,new int [arr.length][k+1]));
        System.out.println("2(C).subset sum is "+ subSetSumTabular(arr,k));

        arr = new int[]{2, 4, 2, 6, 8};
        k = 8;
        System.out.println("3(A).subset sum is "+ subSetSumRecursive(arr,k,0));
        System.out.println("3(B).subset sum is "+ subSetSumMemoization(arr,k,0,new int [arr.length][k+1]));
        System.out.println("3(C).subset sum is "+ subSetSumTabular(arr,k));


    }

    private static int subSetSumTabular(int[] arr, int k) {

         if(Arrays.stream(arr).sum() <k || arr.length==0){
             return 0;
         }

        int[][] dp = new int[arr.length][k+1];
        dp[0][0] = 1;

        if(arr[0] <= k){
            dp[0][arr[0]] = 1;
        }

        for(int i=1; i< arr.length;i++){
            for(int j=0;j<k+1;j++){
                dp[i][j] = dp[i-1][j];
                if((j-arr[i]) >= 0){
                    dp[i][j] +=dp[i-1][j-arr[i]];
                }
            }
        }

        return dp[arr.length-1][k];
    }

    private static int subSetSumRecursive(int[] arr, int k, int i) {

        if(k==0) {
            return 1;
        }else if (i == arr.length || k <0 ){
            return 0;
        }else{
            return subSetSumRecursive(arr,k-arr[i],i+1) + subSetSumRecursive(arr,k,i+1);
        }
    }

    private static int subSetSumMemoization(int[] arr, int k, int i,int lookup[][]) {

        if(k==0) {
            return 1;
        }else if (i == arr.length || k <0 ){
            return 0;
        }else{
            if(lookup[i][k] !=0){
                return lookup[i][k];
            }
            lookup[i][k]= subSetSumMemoization(arr,k-arr[i],i+1,lookup) + subSetSumMemoization(arr,k,i+1,lookup);
            return lookup[i][k];
        }
    }
}
